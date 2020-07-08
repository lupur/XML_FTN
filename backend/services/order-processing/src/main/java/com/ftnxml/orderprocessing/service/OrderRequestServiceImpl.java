package com.ftnxml.orderprocessing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.orderprocessing.enums.OrderRequestStatus;
import com.ftnxml.orderprocessing.messaging.OrderRequestPublish;
import com.ftnxml.orderprocessing.model.OrderRequest;
import com.ftnxml.orderprocessing.model.VehicleOrder;
import com.ftnxml.orderprocessing.repository.OrderRequestRepository;
import com.ftnxml.orderprocessing.repository.VehicleOrderRepository;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    @Autowired
    OrderRequestRepository orderRequestRepository;

    @Autowired
    VehicleOrderRepository vehicleOrderRepository;

    @Autowired
    OrderRequestPublish orderRequestPublish;

    @Override
    public List<OrderRequest> getAllOrderRequests() {
        try {
            return orderRequestRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OrderRequest getOrderRequest(Long id) {
        try {
            return orderRequestRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeOrderRequest(Long id) {
        try {
            orderRequestRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addOrderRequest(OrderRequest newOrderRequest) {
        if (getOrderRequest(newOrderRequest.getId()) != null) {
            return false;
        }
        List<Long> vehicleIDs = new ArrayList<>();
        for (VehicleOrder newOrder : newOrderRequest.getVehicleOrders()) {
            List<VehicleOrder> ordersOfSelectedVehicle = vehicleOrderRepository
                    .findByVehicleId(newOrder.getVehicleId());
            for (VehicleOrder orderOfSelectedVehicle : ordersOfSelectedVehicle) {
                if (!orderOfSelectedVehicle.getOrderRequest().getStatus().equals(OrderRequestStatus.PROCESSING)
                        && !orderOfSelectedVehicle.getOrderRequest().getStatus().equals(OrderRequestStatus.PENDING)
                        && (newOrder.getPickupDate().compareTo(orderOfSelectedVehicle.getPickupDate()) <= 0
                                && newOrder.getReturnDate().compareTo(orderOfSelectedVehicle.getPickupDate()) >= 0
                                || newOrder.getPickupDate().compareTo(orderOfSelectedVehicle.getPickupDate()) >= 0
                                        && newOrder.getPickupDate()
                                                .compareTo(orderOfSelectedVehicle.getReturnDate()) <= 0)) {
                    System.out.println(
                            "Request can not be processed because dates for selected vehicle are overlapping with other request(s)...");
                    return false;
                }
            }
            vehicleIDs.add(newOrder.getVehicleId());
            newOrder.setOrderRequest(newOrderRequest);
        }
        newOrderRequest.setStatus(OrderRequestStatus.PROCESSING);
        OrderRequest orderRequest = orderRequestRepository.save(newOrderRequest);
        Map<Long, List<Long>> vehicleServiceRequest = new HashMap<>();
        vehicleServiceRequest.put(orderRequest.getId(), vehicleIDs);
        orderRequestPublish.sendOrderRequest(vehicleServiceRequest);
        return true;
    }

    @Override
    public boolean createRequestByOwner(OrderRequest newOrderRequest) {
        if (getOrderRequest(newOrderRequest.getId()) != null) {
            return false;
        }
        for (VehicleOrder newOrder : newOrderRequest.getVehicleOrders()) {
            List<VehicleOrder> ordersOfSelectedVehicle = vehicleOrderRepository
                    .findByVehicleId(newOrder.getVehicleId());
            for (VehicleOrder orderOfSelectedVehicle : ordersOfSelectedVehicle) {
                if (!orderOfSelectedVehicle.getOrderRequest().getStatus().equals(OrderRequestStatus.PROCESSING)
                        && !orderOfSelectedVehicle.getOrderRequest().getStatus().equals(OrderRequestStatus.PENDING)
                        && (newOrder.getPickupDate().compareTo(orderOfSelectedVehicle.getPickupDate()) <= 0
                                && newOrder.getReturnDate().compareTo(orderOfSelectedVehicle.getPickupDate()) >= 0
                                || newOrder.getPickupDate().compareTo(orderOfSelectedVehicle.getPickupDate()) >= 0
                                        && newOrder.getPickupDate()
                                                .compareTo(orderOfSelectedVehicle.getReturnDate()) <= 0)) {
                    System.out.println(
                            "Request can not be processed because dates for selected vehicle are overlapping with other request(s)...");
                    return false;
                }
            }
            newOrder.setOrderRequest(newOrderRequest);
        }
        newOrderRequest.setStatus(OrderRequestStatus.RESREVED);
        OrderRequest orderRequest = orderRequestRepository.save(newOrderRequest);

        List<VehicleOrder> ordersOfAcceptedRequest = vehicleOrderRepository.findByOrderRequest_Id(orderRequest.getId());

        for (VehicleOrder acceptedOrder : ordersOfAcceptedRequest) {
            List<VehicleOrder> existingOrders = vehicleOrderRepository.findByVehicleId(acceptedOrder.getVehicleId());
            for (VehicleOrder existingOrder : existingOrders) {
                if (!existingOrder.getOrderRequest().getId().equals(acceptedOrder.getOrderRequest().getId())
                        && (existingOrder.getOrderRequest().getStatus().equals(OrderRequestStatus.PENDING)
                                || existingOrder.getOrderRequest().getStatus().equals(OrderRequestStatus.PROCESSING))
                        && (existingOrder.getPickupDate().compareTo(acceptedOrder.getPickupDate()) <= 0
                                && existingOrder.getReturnDate().compareTo(acceptedOrder.getPickupDate()) >= 0
                                || existingOrder.getPickupDate().compareTo(acceptedOrder.getPickupDate()) >= 0
                                        && existingOrder.getPickupDate()
                                                .compareTo(acceptedOrder.getReturnDate()) <= 0)) {
                    OrderRequest existingOrderRequest = getOrderRequest(existingOrder.getOrderRequest().getId());
                    existingOrderRequest.setStatus(OrderRequestStatus.CANCELED);
                    orderRequestRepository.save(existingOrderRequest);
                }
            }
        }

        return true;

    }

    @Override
    public List<OrderRequest> getOrderRequestByVehicleId(Long vehicleId) {
        try {
            return orderRequestRepository.findByVehicleId(vehicleId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateOrderRequest(OrderRequest orderRequest) {
        if (getOrderRequest(orderRequest.getId()) == null) {
            return false;
        }
        orderRequestRepository.save(orderRequest);
        return true;
    }

    @Override
    public List<OrderRequest> getOrderRequestByUserId(Long userId) {
        try {
            System.out.println("Fetching by user id: " + userId);
            return orderRequestRepository.findByUserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderRequest> getOrderRequestByOwnerId(Long ownerId) {
        try {
            return orderRequestRepository.findByOwnerId(ownerId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean changeOrderRequestStatus(Long orderId, OrderRequestStatus newStatus) {
        OrderRequest orderRequest = getOrderRequest(orderId);

        if (orderRequest == null) {
            return false;
        }

        orderRequest.setStatus(newStatus);
        orderRequestRepository.save(orderRequest);

        List<VehicleOrder> ordersOfAcceptedRequest = vehicleOrderRepository.findByOrderRequest_Id(orderRequest.getId());

        if (newStatus.equals(OrderRequestStatus.PAID)) {
            for (VehicleOrder acceptedOrder : ordersOfAcceptedRequest) {
                List<VehicleOrder> existingOrders = vehicleOrderRepository
                        .findByVehicleId(acceptedOrder.getVehicleId());
                for (VehicleOrder existingOrder : existingOrders) {
                    if (!existingOrder.getOrderRequest().getId().equals(acceptedOrder.getOrderRequest().getId())
                            && (existingOrder.getOrderRequest().getStatus().equals(OrderRequestStatus.PENDING)
                                    || existingOrder.getOrderRequest().getStatus()
                                            .equals(OrderRequestStatus.PROCESSING))
                            && (existingOrder.getPickupDate().compareTo(acceptedOrder.getPickupDate()) <= 0
                                    && existingOrder.getReturnDate().compareTo(acceptedOrder.getPickupDate()) >= 0
                                    || existingOrder.getPickupDate().compareTo(acceptedOrder.getPickupDate()) >= 0
                                            && existingOrder.getPickupDate()
                                                    .compareTo(acceptedOrder.getReturnDate()) <= 0)) {
                        OrderRequest existingOrderRequest = getOrderRequest(existingOrder.getOrderRequest().getId());
                        existingOrderRequest.setStatus(OrderRequestStatus.CANCELED);
                        orderRequestRepository.save(existingOrderRequest);
                    }
                }
            }
        }
        return true;
    }
}
