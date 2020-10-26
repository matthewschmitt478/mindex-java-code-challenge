package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationRepository compensationRepository;


    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating a Compensation for employee with id [{}]", compensation.getEmployee());

        Compensation compensation1 = new Compensation();
        compensation1.setEmployee(compensation.getEmployee());
        compensation1.setEffectiveDate(Instant.now().toString());
        compensation1.setSalary(compensation.getSalary());

        compensationRepository.insert(compensation1);

        return compensation1;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Creating a Compensation for employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("Invalid employee: " + id);
        }

        return compensation;
    }
}