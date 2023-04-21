package com.itorganization.appraisalsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class EmployeeAppraisalDetails implements Serializable {

    int empid;

    String name;

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

}
