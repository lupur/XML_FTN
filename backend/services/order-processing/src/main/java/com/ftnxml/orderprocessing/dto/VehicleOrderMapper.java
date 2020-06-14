package com.ftnxml.orderprocessing.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ftnxml.orderprocessing.model.VehicleOrder;

@Mapper
public interface VehicleOrderMapper {
	
	VehicleOrderMapper INSTANCE = Mappers.getMapper(VehicleOrderMapper.class);
	
	public VehicleOrderDto toVehicleOrderDTO(VehicleOrder vOrder);
	public List<VehicleOrderDto> toVehicleOrderDTOs(List<VehicleOrder> vOrders);
	public VehicleOrder toVehicleOrder(VehicleOrderDto vehicleOrderDTO);
	public List<VehicleOrder> toVehicleOrders(List<VehicleOrderDto> vehicleOrderDTOs);

}
