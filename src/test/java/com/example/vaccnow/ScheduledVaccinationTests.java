package com.example.vaccnow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.entity.ScheduleVaccination;
import com.example.vaccnow.service.BranchService;
import com.example.vaccnow.service.ScheduleVaccinationService;
import com.example.vaccnow.util.PaymentMethodEnum;
import com.example.vaccnow.util.VaccNowUtils;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Application.class })
class ScheduledVaccinationTests {

        private @Autowired MockMvc mockMvc;
        private @Autowired ScheduleVaccinationService scheduleVaccinationService;
        private @Autowired BranchService branchService;

        @Test
        @Order(2)
        void test_scheduleVaccination_By_payment() throws Exception {

                mockMvc.perform(get("/scheduleVaccination/CASH/temp1@domain.net")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.paymentMethod", Matchers.equalTo("CASH")))
                                .andExpect(jsonPath("$.email", Matchers.equalTo("temp1@domain.net")));
        }

        @Test
        @Order(1)
        void test_scheduleVaccination() throws Exception {
                Long from = VaccNowUtils.getTimeInMillis(new Date());
                Long to = VaccNowUtils.getTimeInMillis(VaccNowUtils.addHoursToDate(new Date(), 2));

                scheduleVaccinationService.scheduleVaccConfirmationByPaymentEmail(PaymentMethodEnum.FAWRY,
                                "temp11@domain.net");
                scheduleVaccinationService.scheduleVaccConfirmationByPaymentEmail(PaymentMethodEnum.CREDIT,
                                "temp12@domain.net");

                String result = mockMvc.perform(get("/scheduleVaccination/confirmed/" + from + "/" + to))
                                .andExpect(status().isOk())// .andExpect(jsonPath("$", Matchers.hasSize(2)))
                                .andReturn().getResponse().getContentAsString();

                System.out.println(result);
        }

        @Test
        @Order(3)
        void test_applied_vaccination_by_branch() throws Exception {

                Branch branch = branchService.create(Branch.builder().email("E1").location("L1").name("B1")
                                .phone("01000258741").workStartDate(new Date()).phone("01000258741")
                                .workStartDate(new Date()).workEndDate(VaccNowUtils.addHoursToDate(new Date(), 8))
                                .build());

                scheduleVaccinationService.create(ScheduleVaccination.builder().branch(branch).email("ttemp@domain.net")
                                .vacDesc("vacDesc").vacTime(VaccNowUtils.addMinuteToDate(new Date(), 15)).build());

                mockMvc.perform(get("/scheduleVaccination/appliedByBranchId/" + branch.getId()))
                                .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                                .andExpect(jsonPath("$[0].branch.id", Matchers.equalTo(branch.getId())));
        }

        @AfterAll
        private void tearDown() throws Exception {
                scheduleVaccinationService.deleteAll();
                branchService.deleteAll();
        }
}