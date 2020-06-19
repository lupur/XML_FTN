package com.ftnxml.vehiclemanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftnxml.vehiclemanagement.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
	
//	@Query("SELECT d FROM Discount d where :checkingDate BETWEEN startDate AND endDate")
//	List<Discount> getActiveDiscounts(@Param("checkingDate") Date checkingDate);

}
