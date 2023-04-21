package com.itorganization.appraisalsystem;

import com.itorganization.appraisalsystem.dto.EmployeeAppraisalDetails;
import com.itorganization.appraisalsystem.dtomappers.EmployeeAppraisalMapper;
import com.itorganization.appraisalsystem.exceptionhandlers.EmployeeAppraisalException;
import com.itorganization.appraisalsystem.models.AppraisalDetails;
import com.itorganization.appraisalsystem.models.Employee;
import com.itorganization.appraisalsystem.repository.AppraisalDetailsRepository;
import com.itorganization.appraisalsystem.repository.EmployeeRepository;
import com.itorganization.appraisalsystem.requestobjects.AppraisalRequest;
import com.itorganization.appraisalsystem.services.EmployeeAppraisalService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeAppraisalServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AppraisalDetailsRepository appraisalDetailsRepository;

    @InjectMocks // auto inject helloRepository
    private EmployeeAppraisalService employeeAppraisalService = new EmployeeAppraisalService();

    @Spy
    private EmployeeAppraisalMapper employeeAppraisalMapper = new EmployeeAppraisalMapper();

    @Test
    void test_ThrowManagerHRDiscussionException() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () -> employeeAppraisalService.getEmployeeAppraisalDetails(1));
        assertEquals("Manager or HR Discussion is not done!!", exception.getMessage());
    }

    @Test
    void test_ThrowEmployeeOnNoticePeriodException() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        employee.setOnNotice(true);
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () -> employeeAppraisalService.getEmployeeAppraisalDetails(1));
        assertEquals(employee.getName()+" is On Notice Period!!", exception.getMessage());
    }

    @Test
    void test_ThrowEmployeeNotPresentException() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(null));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () -> employeeAppraisalService.getEmployeeAppraisalDetails(1));
        assertEquals("Employee does not exist!!", exception.getMessage());
    }


    @Test
    void test_EmployeeWithHRAndManagerDiscussionDone() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);

        AppraisalDetails appraisalDetails = constructAppraisalDetails();

        when(appraisalDetailsRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(appraisalDetails));

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        EmployeeAppraisalDetails employeeAppraisalDetails=employeeAppraisalService.getEmployeeAppraisalDetails(1);
        assertEquals(employee.getEmpid(),employeeAppraisalDetails.getEmpid());
        assertEquals(employee.getName(),employeeAppraisalDetails.getName());

    }


    @Test
    void test_ThrowEmployeeNotPresentExceptionForAppraisalDetails() throws EmployeeAppraisalException {
        AppraisalRequest appraisalRequest = constructAppraisalRequest();
        when(employeeRepository.findById(appraisalRequest.getEmpid())).thenReturn(java.util.Optional.ofNullable(null));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () -> employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest));
        assertEquals("Employee does not exist!!", exception.getMessage());
    }


    @Test
    void test_EmployeeWithHRAndManagerDiscussion_LessThan1Year() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);
        employee.setEmpdoj(LocalDateTime.now());
        AppraisalDetails appraisalDetails = constructAppraisalDetails();
        appraisalDetails.setExceptionalemp(true);

        when(appraisalDetailsRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(appraisalDetails));

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        EmployeeAppraisalDetails employeeAppraisalDetails=employeeAppraisalService.getEmployeeAppraisalDetails(1);
        assertEquals(employee.getEmpid(),employeeAppraisalDetails.getEmpid());
        assertEquals(employee.getName(),employeeAppraisalDetails.getName());

    }

    @Test
    void test_ThrowEmployeeWithHRAndManagerDiscussion_LessThan1Year_NoAppraisalDetails() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);
        employee.setEmpdoj(LocalDateTime.now());

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () -> employeeAppraisalService.getEmployeeAppraisalDetails(1));
        assertEquals("Not Completed 1 year in organization!!", exception.getMessage());

    }

    @Test
    void test_EmployeeAppraisalDetails_Add() throws EmployeeAppraisalException {
        AppraisalRequest appraisalRequest = constructAppraisalRequest();
        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        when(appraisalDetailsRepository.save(any())).thenReturn(constructAppraisalDetails());
        EmployeeAppraisalDetails employeeAppraisalDetails=employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest);
        assertEquals(employee.getEmpid(),employeeAppraisalDetails.getEmpid());
        assertEquals(employee.getName(),employeeAppraisalDetails.getName());
    }

    @Test
    void test_EmployeeAppraisalDetails_WhenEmpOnNotice_Add() throws EmployeeAppraisalException {
        AppraisalRequest appraisalRequest = constructAppraisalRequest();
        appraisalRequest.setStarrating(2);

        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);
        employee.setOnNotice(true);


        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        when(appraisalDetailsRepository.save(any())).thenReturn(constructAppraisalDetails());
        EmployeeAppraisalDetails employeeAppraisalDetails=employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest);
        assertEquals(employee.getEmpid(),employeeAppraisalDetails.getEmpid());
        assertEquals(employee.getName(),employeeAppraisalDetails.getName());
    }



    @Test
    void testIsExceptionalEmployee_LessThan1Year() throws EmployeeAppraisalException {
        AppraisalRequest appraisalRequest = constructAppraisalRequest();
        appraisalRequest.setStarrating(2);
        appraisalRequest.setExceptionalemp(true);
        appraisalRequest.setBonus(10000);

        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);
        employee.setEmpdoj(LocalDateTime.now());

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        when(appraisalDetailsRepository.save(any())).thenReturn(constructAppraisalDetails());
        EmployeeAppraisalDetails employeeAppraisalDetails=employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest);
        assertEquals(employee.getEmpid(),employeeAppraisalDetails.getEmpid());
        assertEquals(employee.getName(),employeeAppraisalDetails.getName());

    }

    @Test
    void testIsExceptionalEmployee_LessThan1Year_ExceptionForBonus() throws EmployeeAppraisalException {
        AppraisalRequest appraisalRequest = constructAppraisalRequest();
        appraisalRequest.setStarrating(2);
        appraisalRequest.setExceptionalemp(true);
        appraisalRequest.setBonus(1000);
        Employee employee = constructEmployee();
        employee.setManagerDiscussionDone(true);
        employee.setHRDiscussionDone(true);
        employee.setEmpdoj(LocalDateTime.now());

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () -> employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest));
        assertEquals("Please Provide Bonus Amount within 10000 and 100000 !!", exception.getMessage());

    }


    @Test
    void test_ThrowEmployeeAppraisalDetails_WhenHRandManagerNoDiss() throws EmployeeAppraisalException {
        AppraisalRequest appraisalRequest = constructAppraisalRequest();
        appraisalRequest.setStarrating(2);
        Employee employee = constructEmployee();

        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));

        when(appraisalDetailsRepository.save(any())).thenReturn(constructAppraisalDetails());

        Throwable exception = assertThrows(EmployeeAppraisalException.class, () ->   employeeAppraisalService.putEmployeeAppraisalDetails(appraisalRequest));
        assertEquals("Manager or HR Discussion is not done!!", exception.getMessage());
    }

    @Test
    void test_ThrowEmployeeManagerDiscussion_WhenUpdating() {
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(null));
        Throwable exception = assertThrows(EmployeeAppraisalException.class, () ->   employeeAppraisalService.putEmployeeManagerDiscussion(1));
        assertEquals("Employee does not exist!!", exception.getMessage());
    }

    @Test
    void test_EmployeeManagerDiscussion_WhenUpdating() throws EmployeeAppraisalException {
        Employee employee = constructEmployee();
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));

        when(employeeRepository.save(any())).thenReturn(employee);

        boolean result = employeeAppraisalService.putEmployeeManagerDiscussion(1);
        assertTrue(result);
    }


    public AppraisalRequest constructAppraisalRequest(){
        AppraisalRequest appraisalRequest = new AppraisalRequest();
        appraisalRequest.setPip(false);
        appraisalRequest.setBonus(0);
        appraisalRequest.setExceptionalemp(false);
        appraisalRequest.setEmpid(1);
        appraisalRequest.setTrainingRequired(false);
        appraisalRequest.setStarrating(5);
        appraisalRequest.setComments("");
        return appraisalRequest;
    }

    public AppraisalDetails constructAppraisalDetails(){
        AppraisalDetails appraisalDetails = new AppraisalDetails();
        appraisalDetails.setPip(false);
        appraisalDetails.setStarrating(5);
        appraisalDetails.setPercentHike(10);
        appraisalDetails.setExceptionalemp(false);
        appraisalDetails.setEmpid(1);
        appraisalDetails.setTrainingRequired(false);
        appraisalDetails.setOldCTC(200000);
        appraisalDetails.setUpdatedCTC(220000);
        appraisalDetails.setCreateddate(LocalDateTime.now());
        appraisalDetails.setEffectivedate(LocalDateTime.now().plusMonths(1));
        return appraisalDetails;
    }

    public Employee constructEmployee(){
        Employee employee = new Employee();
        employee.setEmpid(1);
        employee.setName("Amol");
        employee.setManagerDiscussionDone(false);
        employee.setHRDiscussionDone(false);
        employee.setCurrentCTC(200000);
        employee.setEmpdoj(LocalDateTime.now().minusYears(1));
        employee.setOnNotice(false);
        return employee;
    }

}
