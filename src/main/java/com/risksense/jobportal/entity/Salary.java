package com.risksense.jobportal.entity;

public class Salary {

    private Long salaryFrom;

    private Long salaryTo;

    public Long getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Long salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Long getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Long salaryTo) {
        this.salaryTo = salaryTo;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryFrom=" + salaryFrom +
                ", salaryTo=" + salaryTo +
                '}';
    }
}
