package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    Employee getEmployee() {
        return this.employee;
    }

    void setEmployee(Employee employee) {
        this.employee = employee;
    }

    int getNumberOfReports() {
        return this.numberOfReports;
    }

    void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
