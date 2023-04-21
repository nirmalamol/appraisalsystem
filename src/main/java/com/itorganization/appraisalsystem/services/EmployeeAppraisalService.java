package com.itorganization.appraisalsystem.services;

import com.itorganization.appraisalsystem.dto.EmployeeAppraisalDetails;
import com.itorganization.appraisalsystem.dtomappers.EmployeeAppraisalMapper;
import com.itorganization.appraisalsystem.exceptionhandlers.EmployeeAppraisalException;
import com.itorganization.appraisalsystem.models.AppraisalDetails;
import com.itorganization.appraisalsystem.models.Employee;
import com.itorganization.appraisalsystem.repository.AppraisalDetailsRepository;
import com.itorganization.appraisalsystem.repository.EmployeeRepository;
import com.itorganization.appraisalsystem.requestobjects.AppraisalRequest;
import com.itorganization.appraisalsystem.constants.StarRatingPercentEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeAppraisalService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AppraisalDetailsRepository appraisalDetailsRepository;

    @Autowired
    EmployeeAppraisalMapper employeeAppraisalMapper;

    public EmployeeAppraisalDetails getEmployeeAppraisalDetails(int employeeID) throws EmployeeAppraisalException {
        Employee emp = employeeRepository.findById(employeeID).orElse(null);
        if(Objects.isNull(emp)){
            throw new EmployeeAppraisalException("Employee does not exist!!");
        }
        Period period = Period.between(emp.getEmpdoj().toLocalDate(), LocalDate.now());
        if(emp.isOnNotice()){
            throw new EmployeeAppraisalException(emp.getName()+" is On Notice Period!!");
        }
        if(emp.isHRDiscussionDone() && emp.isManagerDiscussionDone()){
            EmployeeAppraisalDetails employeeAppraisalDetails = null;
            AppraisalDetails appraisalDetails = appraisalDetailsRepository.findById(employeeID).orElse(null);
            if(period.getYears() < 1 && Objects.nonNull(appraisalDetails) && appraisalDetails.isExceptionalemp()) {
                employeeAppraisalDetails = employeeAppraisalMapper.employeeAppraisalToDTO(emp, appraisalDetails);
            }else if(period.getYears() < 1){
                    throw new EmployeeAppraisalException("Not Completed 1 year in organization!!");
            }else{
                employeeAppraisalDetails = employeeAppraisalMapper.employeeAppraisalToDTO(emp, appraisalDetails);
            }
            return employeeAppraisalDetails;
        }else{
            throw new EmployeeAppraisalException("Manager or HR Discussion is not done!!");
        }
    }

    public EmployeeAppraisalDetails putEmployeeAppraisalDetails( AppraisalRequest appraisalRequest) throws EmployeeAppraisalException {
        Employee emp = employeeRepository.findById(appraisalRequest.getEmpid()).orElse(null);
        if(Objects.isNull(emp)){
            throw new EmployeeAppraisalException("Employee does not exist!!");
        }
        Period period = Period.between(emp.getEmpdoj().toLocalDate(), LocalDate.now());
        if(emp.isHRDiscussionDone() && emp.isManagerDiscussionDone()) {
            AppraisalDetails appraisalDetails = employeeAppraisalMapper.convertToDBOObjects(appraisalRequest);
            if(appraisalDetails.isExceptionalemp() && period.getYears() < 1){
                if(appraisalDetails.getBonus() < 10000 || appraisalDetails.getBonus() >100000){
                    throw new EmployeeAppraisalException("Please Provide Bonus Amount within 10000 and 100000 !!");
                }
                appraisalDetails.setTrainingRequired(false);
                appraisalDetails.setPip(false);
                appraisalDetails.setStarrating(0);
            }
            if(appraisalDetails.getStarrating() >= 3 && !appraisalDetails.isExceptionalemp()){
                appraisalDetails.setTrainingRequired(false);
                appraisalDetails.setPip(false);
                appraisalDetails.setBonus(0);
                appraisalDetails.setExceptionalemp(false);
            }
            if(appraisalRequest.getStarrating() < 3 && (period.getYears() < 1 || emp.isOnNotice())){
                appraisalDetails.setTrainingRequired(true);
                appraisalDetails.setPip(false);
                appraisalDetails.setBonus(0);
                appraisalDetails.setExceptionalemp(false);
            }
            if (appraisalRequest.getStarrating() < 3 && period.getYears() >= 1){
                appraisalDetails.setPip(true);
                appraisalDetails.setBonus(0);
                appraisalDetails.setExceptionalemp(false);
            }
            calculateEffectiveNewCTC(appraisalDetails, appraisalRequest, emp);
            appraisalDetailsRepository.save(appraisalDetails);
            EmployeeAppraisalDetails employeeAppraisalDetails = employeeAppraisalMapper.employeeAppraisalToDTO(emp, appraisalDetails);
            return employeeAppraisalDetails;
        }else{
            throw new EmployeeAppraisalException("Manager or HR Discussion is not done!!");
        }
    }

    public boolean putEmployeeManagerDiscussion( int employeeID) throws EmployeeAppraisalException {
        Employee emp = employeeRepository.findById(employeeID).orElse(null);
        if(Objects.nonNull(emp)){
            emp.setHRDiscussionDone(true);
            emp.setManagerDiscussionDone(true);
            employeeRepository.save(emp);
            return true;
        }else{
            throw new EmployeeAppraisalException("Employee does not exist!!");
        }
    }

    public void calculateEffectiveNewCTC(AppraisalDetails appraisalDetails, AppraisalRequest appraisalRequest,Employee emp){
           double currentCTC = emp.getCurrentCTC();
            Map starMapping = generateStarMapping();
           LocalDateTime effectiveDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth());
           int percentHike = StarRatingPercentEnum.valueOf(String.valueOf(starMapping.get(appraisalDetails.getStarrating()))).getPercentHike();
           double newCTC = percentHike == 0 ? currentCTC : currentCTC + (currentCTC * percentHike) / 100;
           appraisalDetails.setOldCTC(currentCTC);
           appraisalDetails.setUpdatedCTC(newCTC);
           appraisalDetails.setEffectivedate(effectiveDate);
    }

    public Map<Integer,String> generateStarMapping(){
        return Map.of(5, "FIVE", 4, "FOUR", 3, "THREE",2,"TWO",1,"ONE",0,"ZERO");
    }

}
