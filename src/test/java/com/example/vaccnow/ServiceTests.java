package com.example.vaccnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.List;

import com.example.vaccnow.entity.Branch;
import com.example.vaccnow.entity.Vaccine;
import com.example.vaccnow.service.BranchServiceImpl;
import com.example.vaccnow.service.VaccineServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServiceTests {

    private @Mock VaccineServiceImpl vaccineService;
    private List<Vaccine> all_vaccine;
    private Vaccine vaccine;

    private @Mock BranchServiceImpl branchService;
    private List<Branch> all_branches;
    private Branch branch;

    @BeforeEach
    public void setup() {
        branch = Branch.builder().id(1).email("E1").location("L1").name("B1").phone("01000258741").build();
        all_branches = List.<Branch>of(branch);

        vaccine = Vaccine.builder().name("V1").country("C1").manufacture("M1").notes("Notes 1").build();
        all_vaccine = List.<Vaccine>of(vaccine);
    }

    @Test
    public void test_all_branch_findall() throws Exception {

        doReturn(all_branches).when(branchService).findAll();
        assertEquals(branchService.findAll(), all_branches);
        verify(branchService).findAll();

    }

    @Test
    public void test_craete_branch() throws Exception {
        doReturn(branch).when(branchService).create(branch);
        assertEquals(branchService.create(branch), branch);
        verify(branchService).create(branch);
    }

    @Test
    public void test_all_vaccine_findall() throws Exception {

        doReturn(all_vaccine).when(vaccineService).findAll();
        assertEquals(vaccineService.findAll(), all_vaccine);
        verify(vaccineService).findAll();

    }

    @Test
    public void test_craete_vaccine() throws Exception {
        doReturn(vaccine).when(vaccineService).create(vaccine);
        assertEquals(vaccineService.create(vaccine), vaccine);
        verify(vaccineService).create(vaccine);
    }
}
