package com.ftnxml.orderprocessing.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ftnxml.orderprocessing.model.Report;

@Mapper
public interface ReportMapper {
	
	ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);
	
	public ReportDto toReportDTO(Report report);
	public Report toReport(ReportDto reportDTO);
	

}
