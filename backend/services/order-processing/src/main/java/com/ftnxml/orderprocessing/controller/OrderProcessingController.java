package com.ftnxml.orderprocessing.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.orderprocessing.client.UserDetailsClient;
import com.ftnxml.orderprocessing.dto.OrderRequestDto;
import com.ftnxml.orderprocessing.dto.OrderRequestMapper;
import com.ftnxml.orderprocessing.dto.UserDto;
import com.ftnxml.orderprocessing.enums.OrderRequestStatus;
import com.ftnxml.orderprocessing.messaging.OrderRequestPublish;
import com.ftnxml.orderprocessing.service.OrderRequestService;
import com.ftnxml.orderprocessing.service.VehicleOrderService;

@RestController
@RequestMapping("/")
public class OrderProcessingController {

    @Autowired
    OrderRequestService orderRequestService;

    @Autowired
    VehicleOrderService vehicleOrderService;

    @Autowired
    OrderRequestPublish orderRequestPublish;

    @Autowired
    UserDetailsClient userDetailsClient;

    @PostMapping(value = "/publish")
    public ResponseEntity publish() {
        List<Long> publishContent = Arrays.asList(1l, 2l, 3l);
        Map<Long, List<Long>> map = new HashedMap<>();
        map.put(13l, publishContent);
        orderRequestPublish.sendOrderRequest(map);
        return ResponseEntity.ok().build();
    }

    // GET ALL
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequests() {
        List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE
                .toOrderRequestDTOs(orderRequestService.getAllOrderRequests());
        if (orderRequests != null) {
            return ResponseEntity.ok(orderRequests);
        }
        return ResponseEntity.badRequest().build();
    }

    // GET BY REQUEST
    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByRequestId(@PathParam("requestId") Long requestId) {
        OrderRequestDto orderRequest = OrderRequestMapper.INSTANCE
                .toOrderRequestDTO(orderRequestService.getOrderRequest(requestId));
        if (orderRequest != null) {
            return ResponseEntity.ok(orderRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    // GET BY VEHICLE
    @GetMapping(value = "/vehicles/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByVehicleId(@PathParam("vehicleId") Long vehicleId) {
        List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE
                .toOrderRequestDTOs(orderRequestService.getOrderRequestByVehicleId(vehicleId));
        if (orderRequests != null) {
            return ResponseEntity.ok(orderRequests);
        }
        return ResponseEntity.badRequest().build();
    }

    // GET BY USER
    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByUserId(Long vehicleId) {
        List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE
                .toOrderRequestDTOs(orderRequestService.getOrderRequestByUserId(vehicleId));
        if (orderRequests != null) {
            return ResponseEntity.ok(orderRequests);
        }
        return ResponseEntity.badRequest().build();
    }

    // GET BY OWNER
    @GetMapping(value = "/owners/{ownerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequestsByOwnerId(@PathParam("ownerId") Long vehicleId) {
        List<OrderRequestDto> orderRequests = OrderRequestMapper.INSTANCE
                .toOrderRequestDTOs(orderRequestService.getOrderRequestByOwnerId(vehicleId));
        if (orderRequests != null) {
            return ResponseEntity.ok(orderRequests);
        }
        return ResponseEntity.badRequest().build();
    }

    // GET AVAILABLE VEHICLES IN SELECTED RANGE
    @GetMapping(value = "/available-vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAvailableVehicles(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("vehicles") List<Long> vehicles) {
        List<Long> availableVehicles = vehicleOrderService.findVehiclesAvailableInRange(startDate, endDate, vehicles);
        return ResponseEntity.ok(availableVehicles);
    }

    // CRETE NEW REQUEST
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addOrderRequest(@RequestHeader("Authorization") String token,
            @RequestBody OrderRequestDto orderRequestDto) {

        System.out.println("Into create new request");
        if (orderRequestDto == null) {
            System.out.println("OrderRequestBody is not defined");
            return ResponseEntity.badRequest().build();
        }

        UserDto user = userDetailsClient.getUserInfo(token);
        orderRequestDto.setUserId(user.getId());
        try {
            if (!orderRequestDto.getUserId().equals(orderRequestDto.getOwnerId())) {
                return orderRequestService
                        .addOrderRequest(OrderRequestMapper.INSTANCE.INSTANCE.toOrderRequest(orderRequestDto))
                                ? ResponseEntity.ok().build()
                                : ResponseEntity.badRequest().build();
            } else {
                return orderRequestService
                        .createRequestByOwner(OrderRequestMapper.INSTANCE.INSTANCE.toOrderRequest(orderRequestDto))
                                ? ResponseEntity.ok().build()
                                : ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
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

    @PutMapping(value = "/{requestId}/status/{requestStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setOrderRequestReserved(@PathVariable("requestId") Long requestId,
            @PathVariable("requestStatus") OrderRequestStatus requestStatus) {
        if (orderRequestService.changeOrderRequestStatus(requestId, requestStatus))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body("No order request with given id");
    }

}
