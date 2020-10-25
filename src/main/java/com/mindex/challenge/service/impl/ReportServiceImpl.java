package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure create(String id) {
        LOG.debug("Creating ReportingStructure for employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);

        int count = 0;
        for (Employee e : employee.getDirectReports()) {
            count++;
        }

        reportingStructure.setNumberOfReports(count);

        return reportingStructure;
    }
}
