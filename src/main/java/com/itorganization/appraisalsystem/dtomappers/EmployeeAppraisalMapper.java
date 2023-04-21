package com.itorganization.appraisalsystem.dtomappers;

import com.itorganization.appraisalsystem.dto.EmployeeAppraisalDetails;
import com.itorganization.appraisalsystem.models.AppraisalDetails;
import com.itorganization.appraisalsystem.models.Employee;
import com.itorganization.appraisalsystem.requestobjects.AppraisalRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class EmployeeAppraisalMapper {

    public EmployeeAppraisalDetails employeeAppraisalToDTO(Employee employee, AppraisalDetails appraisalDetails){
        EmployeeAppraisalDetails employeeAppraisalDetails = new EmployeeAppraisalDetails();
        employeeAppraisalDetails.setEmpid(employee.getEmpid());
        employeeAppraisalDetails.setName(employee.getName());
        if(Objects.nonNull(appraisalDetails)) {
            employeeAppraisalDetails.setBonus(appraisalDetails.getBonus());
            employeeAppraisalDetails.setComments(appraisalDetails.getComments());
            employeeAppraisalDetails.setTrainingRequired(appraisalDetails.isTrainingRequired());
            employeeAppraisalDetails.setExceptionalemp(appraisalDetails.isExceptionalemp());
            employeeAppraisalDetails.setOldCTC(appraisalDetails.getOldCTC());
            employeeAppraisalDetails.setUpdatedCTC(appraisalDetails.getUpdatedCTC());
            employeeAppraisalDetails.setPercentHike(appraisalDetails.getPercentHike());
            employeeAppraisalDetails.setPip(appraisalDetails.isPip());
            employeeAppraisalDetails.setStarrating(appraisalDetails.getStarrating());
        }
        return employeeAppraisalDetails;

    }

    public AppraisalDetails convertToDBOObjects(AppraisalRequest appraisalRequest) {
        AppraisalDetails appraisalDetails = new AppraisalDetails();
        appraisalDetails.setEmpid(appraisalRequest.getEmpid());
        appraisalDetails.setBonus(appraisalRequest.getBonus());
        appraisalDetails.setComments(appraisalRequest.getComments());
        appraisalDetails.setStarrating(appraisalRequest.getStarrating());
        appraisalDetails.setCreateddate(LocalDateTime.now());
        appraisalDetails.setExceptionalemp(appraisalRequest.isExceptionalemp());
        appraisalDetails.setPip(appraisalRequest.isPip());
        appraisalDetails.setTrainingRequired(appraisalRequest.isTrainingRequired());
        return appraisalDetails;
    }

}
