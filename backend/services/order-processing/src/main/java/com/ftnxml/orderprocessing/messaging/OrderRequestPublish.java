package com.ftnxml.orderprocessing.messaging;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftnxml.orderprocessing.dto.CreateRequestDto;
import com.ftnxml.orderprocessing.dto.CreateRequestDto.CreateRequestVehicleDto;
import com.ftnxml.orderprocessing.enums.OrderRequestStatus;
import com.ftnxml.orderprocessing.model.OrderRequest;
import com.ftnxml.orderprocessing.model.VehicleOrder;
import com.ftnxml.orderprocessing.service.OrderRequestService;
import com.ftnxml.orderprocessing.service.VehicleOrderService;

@Service
public class OrderRequestPublish implements IOrderMessaging {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private OrderRequestService orderRequestService;
    
    @Autowired
    private VehicleOrderService vehicleOrderService;

    private static final String NEW_ORDER_REQUEST_TOPIC = "new-order-request";

    public void sendOrderRequest(Map<Long, List<Long>> requestVehicles) {
        try {
            kafkaTemplate.send(NEW_ORDER_REQUEST_TOPIC, objectMapper.writeValueAsString(requestVehicles));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	@Override
	@Transactional
	public void consumeNewRequestVehicleResponse(String content) {
		System.out.println("Reseived response from vehicle service...");
		System.out.println(content);
		try {
			CreateRequestDto responseFromVehicle = objectMapper.readValue(content, CreateRequestDto.class);
			OrderRequest orderRequest = orderRequestService.getOrderRequest(responseFromVehicle.getRequestId());
			List<VehicleOrder> vehicleOrders = vehicleOrderService.getVehicleOrdersByRequest(orderRequest.getId());
			Set<VehicleOrder> newVehicleOrders = new HashSet<>();
			if(orderRequest == null || orderRequest.getStatus() == OrderRequestStatus.CANCELED) {
				System.out.println("Order reqest was canceled before response from vehicle service. ");
				return;
			}
			if(responseFromVehicle.getRejected() != null && responseFromVehicle.getRejected()) {
				orderRequest.setStatus(OrderRequestStatus.CANCELED);
				orderRequestService.updateOrderRequest(orderRequest);
			} else {
				for(CreateRequestVehicleDto vehicleDto : responseFromVehicle.getVehicles()) {
					for(VehicleOrder vOrder : vehicleOrders) {
						if(vehicleDto.getVehicleId().equals(vOrder.getVehicleId())) {
							Double pricePerDay = vehicleDto.getPrice();
							System.out.println("Price per day: " + pricePerDay);
							Instant startInstant = vOrder.getPickupDate().toInstant();
							Instant endInstant = vOrder.getReturnDate().toInstant();
							Long numberOfDays = ChronoUnit.DAYS.between(startInstant, endInstant);
							System.out.println("Number of days: " + numberOfDays);
							Double totalPrice = pricePerDay * numberOfDays;
							System.out.println("Price: " + totalPrice);
							if(vehicleDto.getDiscount() != null && vehicleDto.getDiscount().getDaysNumber() <= numberOfDays) {
								System.out.println("Discount: " + vehicleDto.getDiscount().getPercentage());
								totalPrice = totalPrice*(1.00 - vehicleDto.getDiscount().getPercentage()/100.00);						
							}
							System.out.println("Total price: " + totalPrice);
							vOrder.setTotalPrice(totalPrice);
							newVehicleOrders.add(vOrder);
						}
					}
				}
				orderRequest.setStatus(OrderRequestStatus.PENDING);
				orderRequest.setVehicleOrders(newVehicleOrders);
				orderRequestService.updateOrderRequest(orderRequest);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Response from vehicle could not be read");
		}
	}
}
