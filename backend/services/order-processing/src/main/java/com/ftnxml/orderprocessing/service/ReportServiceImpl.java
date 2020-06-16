package com.ftnxml.orderprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.orderprocessing.model.Report;
import com.ftnxml.orderprocessing.repository.ReportRepository;
import com.ftnxml.orderprocessing.repository.VehicleOrderRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	VehicleOrderRepository vehicleOrderRepository;
	
	@Override
	public Report gerReport(Long id) {
		try {
			return reportRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Report getReportByOrder(Long orderId) {
		try {
			return reportRepository.findByVehicleOrder_id(orderId);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Report addReport(Report newReport) {
		try {
			return reportRepository.save(newReport);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
