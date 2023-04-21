package com.itorganization.appraisalsystem.repository;

import com.itorganization.appraisalsystem.models.AppraisalDetails;
import com.itorganization.appraisalsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppraisalDetailsRepository extends JpaRepository<AppraisalDetails,Integer> {
}
