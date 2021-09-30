package com.example.vaccnow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.vaccnow.service.ScheduleVaccinationService;
import com.example.vaccnow.util.PaymentMethodEnum;
import com.example.vaccnow.util.VaccNowUtils;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Application.class })
class ScheduledVaccinationTests {

    private @Autowired MockMvc mockMvc;
    private @Autowired ScheduleVaccinationService scheduleVaccinationService;

    @BeforeEach
    private void setup() {

    }

    @Test
    @Order(1)
    void test_scheduleVaccination_By_payment() throws Exception {
        mockMvc.perform(get("/scheduleVaccination/vaccination/CASH/temp1@domain.net")).andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentMethod", Matchers.equalTo("CASH")))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
    }

    @Test
    @Order(2)
    void test_scheduleVaccination() throws Exception {
        Long from = VaccNowUtils.getTimeInMillis(new Date());
        Long to = VaccNowUtils
                .getTimeInMillis(VaccNowUtils.add15MinuteToDate(VaccNowUtils.add15MinuteToDate(new Date())));

        scheduleVaccinationService.scheduleVaccinationByPaymentMethod(PaymentMethodEnum.FAWRY, "temp1@domain.net");
        scheduleVaccinationService.scheduleVaccinationByPaymentMethod(PaymentMethodEnum.CREDIT, "temp2@domain.net");

        mockMvc.perform(get("/scheduleVaccination/confirmedVaccination/" + from + "/" + to)).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @AfterEach
    private void tearDown() throws Exception {
        scheduleVaccinationService.deleteAll();

    }
}