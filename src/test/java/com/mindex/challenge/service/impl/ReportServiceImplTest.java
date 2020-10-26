package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportServiceImplTest {

    private static final String TEST_EMPLOYEE_ID = "16a596ae-edd3-4847-99fe-c4518e82c86f";

    @Autowired
    private EmployeeRepository employeeRepository;
    private String reportUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportUrl = "http://localhost:" + port + "report/employee/{id}";
    }

    @Test
    public void testCreate() {
        Employee testEmployee = employeeRepository.findByEmployeeId(TEST_EMPLOYEE_ID);

        ReportingStructure testReportingStructure = new ReportingStructure();
        testReportingStructure.setEmployee(testEmployee);
        testReportingStructure.setNumberOfReports(2);

        // Read checks
        ReportingStructure reportingStructure = restTemplate.getForEntity(reportUrl, ReportingStructure.class, testEmployee.getEmployeeId()).getBody();
        assert reportingStructure != null;
        assertEquals(reportingStructure.getEmployee().getEmployeeId(), testEmployee.getEmployeeId());
        assertReportingStructureEquivalence(testReportingStructure, reportingStructure);
    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}
