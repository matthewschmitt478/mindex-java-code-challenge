package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private ReportService reportService;

    @GetMapping("report/employee/{id}")
    public ReportingStructure create(@PathVariable String id) {
        LOG.debug("Received ReportingStructure create request for employee with id [{}]", id);

        return reportService.create(id);
    }
}
