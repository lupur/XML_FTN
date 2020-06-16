package com.ftnxml.orderprocessing.service;

import com.ftnxml.orderprocessing.model.Report;

public interface ReportService {
	
	Report gerReport(Long id);
	
	Report getReportByOrder(Long orderId);
	
	Report addReport(Report newReport);

}