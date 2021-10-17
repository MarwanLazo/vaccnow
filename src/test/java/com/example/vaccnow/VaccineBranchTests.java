
package com.example.vaccnow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.stream.Collectors;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.entity.VaccineBranch;
import com.example.vaccnow.entity.VaccineBranchPK;
import com.example.vaccnow.mapping.BranchMapping;
import com.example.vaccnow.mapping.VaccineMapping;
import com.example.vaccnow.model.BranchModel;
import com.example.vaccnow.model.VaccineModel;
import com.example.vaccnow.service.BranchService;
import com.example.vaccnow.service.VaccineBranchService;
import com.example.vaccnow.service.VaccineService;

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
class VaccineBranchTests {

        private @Autowired MockMvc mockMvc;

        private @Autowired BranchService branchService;
        private @Autowired BranchMapping branchMapper;
        private List<BranchModel> all_branchModels;

        private @Autowired VaccineService vaccineService;
        private @Autowired VaccineMapping vaccineMapper;
        private List<VaccineModel> all_VaccineModels;

        private @Autowired VaccineBranchService vaccineBranchService;

        private List<Branch> all_branches;
        private List<Vaccine> all_Vaccines;

        private @BeforeEach void setup() {
                all_branchModels = List.<BranchModel>of(
                                BranchModel.builder().email("E1").location("L1").name("B1").phone("01000258741")
                                                .build(),
                                BranchModel.builder().email("E2").location("L2").name("B2").phone("01000258741")
                                                .build());

                all_VaccineModels = List.<VaccineModel>of(
                                VaccineModel.builder().name("V1").country("C1").manufacture("M1").notes("Notes 1")
                                                .build(),
                                VaccineModel.builder().name("V2").country("C2").manufacture("M2").notes("Notes 2")
                                                .build(),
                                VaccineModel.builder().name("V3").country("C3").manufacture("M3").notes("Notes 3")
                                                .build());
                // crete Branch
                all_branches = all_branchModels.stream().map(branchMapper::mapToEntity).map(branchService::create)
                                .collect(Collectors.toList());
                // crete Vaccine
                all_Vaccines = all_VaccineModels.stream().map(vaccineMapper::mapToEntity).map(vaccineService::create)
                                .collect(Collectors.toList());

                // crete Vaccine Branch
                all_Vaccines.stream().map(vac -> new VaccineBranch(new VaccineBranchPK(vac, all_branches.get(0))))
                                .map(vaccineBranchService::create).forEach(System.out::println);

                // all_Vaccines.stream().map(vac -> new VaccineBranch(new VaccineBranchPK(vac,
                // all_branches.get(1))))
                // .map(vaccineBranchService::create).forEach(System.out::println);

        }

        // ??? run Fine only when run it alone not all file
        // @Test
        // @Order(1)
        void get_list_of_all_vaccine_by_branch_id() throws Exception {

                mockMvc.perform(get("/vaccine/1")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                                .andExpect(jsonPath("$[0].name", Matchers.equalTo("V1")))
                                .andExpect(jsonPath("$[1].name", Matchers.equalTo("V2")))
                                .andExpect(jsonPath("$[2].name", Matchers.equalTo("V3")));

        }

        /**
         * load all branches with given Vaccine id and Show if it available for every
         * branch
         * 
         * @throws Exception
         */
        @Test
        @Order(2)
        void get_list_of_all_availability_vaccine_by_branch() throws Exception {

                mockMvc.perform(get("/branch/1/forBranch")).andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                                .andExpect(jsonPath("$[0].name", Matchers.equalTo("B1")))
                                .andExpect(jsonPath("$[1].name", Matchers.equalTo("B2")))
                                .andExpect(jsonPath("$[1].vaccineId", Matchers.equalTo(false)));

        }

        @AfterEach
        private void tearDown() throws Exception {
                vaccineBranchService.deleteAll();
                branchService.deleteAll();
                vaccineService.deleteAll();
        }

}
