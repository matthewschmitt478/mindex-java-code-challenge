package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    private String compUrl;
    private String compUrlId;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compUrl = "http://localhost:" + port + "comp/employee/";
        compUrlId = "http://localhost:" + port + "comp/employee/{id}";
    }

    @Test
    public void testCreate() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Dave");
        testEmployee.setLastName("Grohl");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(50000.00f);
        testCompensation.setEffectiveDate(Instant.now().toString());

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compUrl, testCompensation, Compensation.class).getBody();

        assert createdCompensation != null;
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compUrlId, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), testCompensation.getEmployee().getEmployeeId());
        assert readCompensation != null;
        assertCompensationEquivalence(createdCompensation, testCompensation);
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary(), .05);
    }
}
