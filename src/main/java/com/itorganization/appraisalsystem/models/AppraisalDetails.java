package com.itorganization.appraisalsystem.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class AppraisalDetails {

    @Id
    int empid;
    boolean isExceptionalemp;
    double bonus;
    int starrating;
    double percentHike;
    String comments;
    double oldCTC;
    boolean trainingRequired;
    boolean pip;
    double updatedCTC;
    LocalDateTime effectivedate;
    LocalDateTime createddate;

    public int getEmpid() {
        return empid;
    }

    public boolean isExceptionalemp() {
        return isExceptionalemp;
    }


    public double getBonus() {
        return bonus;
    }

    public int getStarrating() {
        return starrating;
    }

    public double getPercentHike() {
        return percentHike;
    }

    public String getComments() {
        return comments;
    }

    public double getOldCTC() {
        return oldCTC;
    }

    public boolean isTrainingRequired() {
        return trainingRequired;
    }

    public boolean isPip() {
        return pip;
    }

    public double getUpdatedCTC() {
        return updatedCTC;
    }

    public LocalDateTime getEffectivedate() {
        return effectivedate;
    }

    public LocalDateTime getCreateddate() {
        return createddate;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public void setExceptionalemp(boolean exceptionalemp) {
        isExceptionalemp = exceptionalemp;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setStarrating(int starrating) {
        this.starrating = starrating;
    }

    public void setPercentHike(double percentHike) {
        this.percentHike = percentHike;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setOldCTC(double oldCTC) {
        this.oldCTC = oldCTC;
    }

    public void setTrainingRequired(boolean trainingRequired) {
        this.trainingRequired = trainingRequired;
    }

    public void setPip(boolean pip) {
        this.pip = pip;
    }

    public void setUpdatedCTC(double updatedCTC) {
        this.updatedCTC = updatedCTC;
    }

    public void setEffectivedate(LocalDateTime effectivedate) {
        this.effectivedate = effectivedate;
    }

    public void setCreateddate(LocalDateTime createddate) {
        this.createddate = createddate;
    }
}
