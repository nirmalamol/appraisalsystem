package com.itorganization.appraisalsystem.models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id
    private int empid;

    private String name;

    private int managerid;

    private LocalDateTime empdoj;

    private boolean isManagerDiscussionDone;

    private boolean isHRDiscussionDone;

    private double currentCTC;

    private boolean isOnNotice;

    public int getEmpid() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public int getManagerid() {
        return managerid;
    }

    public LocalDateTime getEmpdoj() {
        return empdoj;
    }

    public boolean isManagerDiscussionDone() {
        return isManagerDiscussionDone;
    }

    public boolean isHRDiscussionDone() {
        return isHRDiscussionDone;
    }

    public double getCurrentCTC() {
        return currentCTC;
    }

    public boolean isOnNotice() {
        return isOnNotice;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }

    public void setEmpdoj(LocalDateTime empdoj) {
        this.empdoj = empdoj;
    }

    public void setManagerDiscussionDone(boolean managerDiscussionDone) {
        isManagerDiscussionDone = managerDiscussionDone;
    }

    public void setHRDiscussionDone(boolean HRDiscussionDone) {
        isHRDiscussionDone = HRDiscussionDone;
    }

    public void setCurrentCTC(double currentCTC) {
        this.currentCTC = currentCTC;
    }

    public void setOnNotice(boolean onNotice) {
        isOnNotice = onNotice;
    }
}

