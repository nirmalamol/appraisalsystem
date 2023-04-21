package com.itorganization.appraisalsystem.controllers;

import com.itorganization.appraisalsystem.exceptionhandlers.EmployeeAppraisalException;
import com.itorganization.appraisalsystem.requestobjects.AppraisalRequest;
import com.itorganization.appraisalsystem.services.EmployeeAppraisalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itorganization.appraisalsystem.dto.EmployeeAppraisalDetails;

@RestController
@RequestMapping("/api")
public class EmployeeAppraisalController {

    @Autowired
    private EmployeeAppraisalService employeeAppraisalService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeAppraisalDetails> getEmployeeAppraisalDetails(@PathVariable("employeeId") int employeeId) throws EmployeeAppraisalException {
        EmployeeAppraisalDetails employeeAppraisalDetails= employeeAppraisalService.getEmployeeAppraisalDetails(employeeId);
        return ResponseEntity.ok(employeeAppraisalDetails);
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeAppraisalDetails> putEmployeeAppraisalDetails(@RequestBody AppraisalRequest appraisalRequest) throws EmployeeAppraisalException {
        EmployeeAppraisalDetails employeeAppraisalDetails = employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest);
        return new ResponseEntity<EmployeeAppraisalDetails>(employeeAppraisalDetails, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeeId}/managerhrdiscussion")
    public ResponseEntity putEmployeeManagementDiscussion(@PathVariable("employeeId") int employeeId) throws EmployeeAppraisalException {
        boolean employeeAppraisalDetails = employeeAppraisalService.putEmployeeManagerDiscussion(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
