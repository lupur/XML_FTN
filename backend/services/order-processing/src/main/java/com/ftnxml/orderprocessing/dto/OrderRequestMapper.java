package com.ftnxml.orderprocessing.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ftnxml.orderprocessing.model.OrderRequest;

@Mapper
public interface OrderRequestMapper {
	
	OrderRequestMapper INSTANCE = Mappers.getMapper(OrderRequestMapper.class);
	
	public OrderRequestDto toOrderRequestDTO(OrderRequest orderRequest);
	public List<OrderRequestDto> toOrderRequestDTOs(List<OrderRequest> orderRequests);
	public OrderRequest toOrderRequest(OrderRequestDto orderRequestDTO);
	public List<OrderRequest> toOrderRequests(List<OrderRequestDto> orderRequestDTOs);

}
