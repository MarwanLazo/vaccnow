package com.example.vaccnow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.vaccnow.mapping.BranchMapping;
import com.example.vaccnow.mapping.VaccineMapping;
import com.example.vaccnow.model.BranchModel;
import com.example.vaccnow.model.VaccineModel;
import com.example.vaccnow.service.BranchService;
import com.example.vaccnow.service.VaccineService;
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
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc()
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Application.class })
class ApplicationTests {

	private @Autowired MockMvc mockMvc;

	private @Autowired BranchService branchService;
	private @Autowired BranchMapping branchMapper;
	private List<BranchModel> all_branchModels;

	private @Autowired VaccineService vaccineService;
	private @Autowired VaccineMapping vaccineMapper;
	private List<VaccineModel> all_VaccineModels;

	@BeforeEach
	public void setup() {
		all_branchModels = List.<BranchModel>of(BranchModel.builder().email("E1").location("L1").name("B1").phone("P1")
				.workStartDate(new Date()).phone("P1").workStartDate(new Date())
				.workEndDate(VaccNowUtils.addMinuteToDate(new Date(), 15)).build());
		all_branchModels.stream().map(branchMapper::mapToEntity).map(branchService::create)
				.collect(Collectors.toList());

		all_VaccineModels = List.<VaccineModel>of(
				VaccineModel.builder().name("V1").country("C1").manufacture("M1").notes("Notes 1").build());
		all_VaccineModels.stream().map(vaccineMapper::mapToEntity).map(vaccineService::create)
				.collect(Collectors.toList());

	}

	@Test
	void get_list_of_all_vaccine_controller() throws Exception {

		mockMvc.perform(get("/vaccine")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name", Matchers.equalTo("V1")));

	}

	@Test
	void get_list_of_all_branches_controller() throws Exception {
		mockMvc.perform(get("/branch")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name", Matchers.equalTo("B1")));
	}

	@Test
	void return_message_hello() throws Exception {
		mockMvc.perform(get("/branch/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello")));
	}

	@Test
	@Order(1)
	void return_branch_availableTime() throws Exception {
		MvcResult result = mockMvc.perform(get("/branch/availableTime/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.notNullValue())).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@AfterEach
	void tearDown() throws Exception {
		branchService.deleteAll();
		vaccineService.deleteAll();
	}

}
