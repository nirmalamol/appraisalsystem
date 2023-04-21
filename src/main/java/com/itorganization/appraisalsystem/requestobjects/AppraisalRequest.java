package com.itorganization.appraisalsystem.requestobjects;

import lombok.Setter;

@Setter
public class AppraisalRequest {

    int empid;
    boolean exceptionalemp;
    double bonus;
    int starrating;
    String comments;
    boolean trainingRequired;
    boolean pip;

    public int getEmpid() {
        return empid;
    }

    public boolean isExceptionalemp() {
        return exceptionalemp;
    }

    public double getBonus() {
        return bonus;
    }

    public int getStarrating() {
        return starrating;
    }

    public String getComments() {
        return comments;
    }

    public boolean isTrainingRequired() {
        return trainingRequired;
    }

    public boolean isPip() {
        return pip;
    }
}
