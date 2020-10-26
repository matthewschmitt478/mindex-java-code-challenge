package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController{
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("comp/employee")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received ReportingStructure create request for employee with id [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("comp/employee/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received Compensation create request for id [{}]", id);

        return compensationService.read(id);
    }
}
