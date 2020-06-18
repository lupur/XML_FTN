package com.ftnxml.orderprocessing.controller;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.orderprocessing.dto.OrderRequestDto;
import com.ftnxml.orderprocessing.dto.OrderRequestMapper;
import com.ftnxml.orderprocessing.messaging.OrderRequestPublish;
import com.ftnxml.orderprocessing.service.OrderRequestService;

@RestController
@RequestMapping("/")
public class OrderProcessingController {
	
	@Autowired
	OrderRequestService orderRequestService;
	
	@Autowired
	OrderRequestPublish orderRequestPublish;
	
	@PostMapping(value = "/publish")
	public ResponseEntity publish() {
		List<Long> publishContent = Arrays.asList(1l, 2l, 3l);
		orderRequestPublish.sendOrderRequest(publishContent);
		return ResponseEntity.ok().build();
	}
	
	// GET ALL
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequests() {
    	List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE.toOrderRequestDTOs(
    			orderRequestService.getAllOrderRequests());
    	if(orderRequests != null) {
    		return ResponseEntity.ok(orderRequests);
    	}
    	return ResponseEntity.badRequest().build();
    }
	
	// GET BY REQUEST
    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByRequestId(@PathParam("requestId") Long requestId) {
    	OrderRequestDto orderRequest = OrderRequestMapper.INSTANCE.toOrderRequestDTO(
    			orderRequestService.getOrderRequest(requestId));
    	if(orderRequest != null) {
    		return ResponseEntity.ok(orderRequest);
    	}
    	return ResponseEntity.badRequest().build();
    }
	
	// GET BY VEHICLE
    @GetMapping(value = "/vehicles/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByVehicleId(@PathParam("vehicleId") Long vehicleId) {
    	List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE.toOrderRequestDTOs(
    			orderRequestService.getOrderRequestByVehicleId(vehicleId));
    	if(orderRequests != null) {
    		return ResponseEntity.ok(orderRequests);
    	}
    	return ResponseEntity.badRequest().build();
    }
	
	// GET BY USER
    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByUserId(Long vehicleId) {
    	List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE.toOrderRequestDTOs(
    			orderRequestService.getOrderRequestByUserId(vehicleId));
    	if(orderRequests != null) {
    		return ResponseEntity.ok(orderRequests);
    	}
    	return ResponseEntity.badRequest().build();
    }
	
	// GET BY OWNER
    @GetMapping(value = "/owners/{ownerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByOwnerId(@PathParam ("ownerId") Long vehicleId) {
    	List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE.toOrderRequestDTOs(
    			orderRequestService.getOrderRequestByOwnerId(vehicleId));
    	if(orderRequests != null) {
    		return ResponseEntity.ok(orderRequests);
    	}
    	return ResponseEntity.badRequest().build();
    }
	
    // CRETE NEW REQUEST
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addOrderRequest(@RequestBody OrderRequestDto orderRequestDto) {
        if (orderRequestDto == null) {
            System.out.println("OrderRequestBody is not defined");
            return ResponseEntity.badRequest().build();
        }
        try {
               if (orderRequestService.addOrderRequest(OrderRequestMapper.INSTANCE.INSTANCE.toOrderRequest(orderRequestDto)))
                   return ResponseEntity.ok().build();
               else
                   return ResponseEntity.badRequest().build();
        } catch(Exception e) {
               e.printStackTrace();
               return ResponseEntity.badRequest().build();
        }
    }

	
	// DELETE REQUEST
    @DeleteMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeOrderRequest(@PathVariable Long requestId) {
        if (orderRequestService.removeOrderRequest(requestId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
    
}


