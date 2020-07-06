package com.ftnxml.vehiclemanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.dto.CreateRequestDto;
import com.ftnxml.vehiclemanagement.dto.CreateRequestDto.CreateRequestVehicleDto;
import com.ftnxml.vehiclemanagement.dto.CreateRequestDto.CreateRequestVehicleDto.CreateRequestDiscountDto;
import com.ftnxml.vehiclemanagement.dto.NewVehicleDto;
import com.ftnxml.vehiclemanagement.model.ClassType;
import com.ftnxml.vehiclemanagement.model.CollisionDamageWaiver;
import com.ftnxml.vehiclemanagement.model.Discount;
import com.ftnxml.vehiclemanagement.model.FuelType;
import com.ftnxml.vehiclemanagement.model.Model;
import com.ftnxml.vehiclemanagement.model.PriceList;
import com.ftnxml.vehiclemanagement.model.TransmissionType;
import com.ftnxml.vehiclemanagement.model.Vehicle;
import com.ftnxml.vehiclemanagement.repository.ClassTypeRepository;
import com.ftnxml.vehiclemanagement.repository.CollisionDamageWaiverRepository;
import com.ftnxml.vehiclemanagement.repository.DiscountRepository;
import com.ftnxml.vehiclemanagement.repository.FuelTypeRepository;
import com.ftnxml.vehiclemanagement.repository.ModelRepository;
import com.ftnxml.vehiclemanagement.repository.PriceListRepository;
import com.ftnxml.vehiclemanagement.repository.TransmissionTypeRepository;
import com.ftnxml.vehiclemanagement.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ModelRepository modelRepostiory;

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    CollisionDamageWaiverRepository colDamageWaiverRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    @Autowired
    TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    ClassTypeRepository classTypeRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByLocation(String location) {
        return vehicleRepository.findByLocation(location);
    }

    @Override
    public Vehicle getVehicle(Long id) {
        try {
            Vehicle b = vehicleRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeVehicle(Long id) {
        try {
            Vehicle b = vehicleRepository.findById(id).get();
            vehicleRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addVehicle(NewVehicleDto newVehicle) {
        if (newVehicle == null)
            return false;

        try {
            Vehicle vehicle = new Vehicle();
            if (newVehicle.getModelId() != null) {
                Model model = modelRepostiory.findById(newVehicle.getModelId()).get();
                vehicle.setModel(model);
            }
            if (newVehicle.getPricelistId() != null) {
                PriceList priceList = priceListRepository.findById(newVehicle.getPricelistId()).get();
                vehicle.setPriceList(priceList);
            }
            if (newVehicle.getColDamageWaiverId() != null) {
                CollisionDamageWaiver colDamageWaiver = colDamageWaiverRepository
                        .findById(newVehicle.getColDamageWaiverId()).get();
                vehicle.setColDamageWaiver(colDamageWaiver);
            }
            if (newVehicle.getDiscountId() != null) {
                Discount discount = discountRepository.findById(newVehicle.getDiscountId()).get();
                vehicle.setDiscount(discount);
            }
            if (newVehicle.getFuelTypeId() != null) {
                FuelType fuelType = fuelTypeRepository.findById(newVehicle.getFuelTypeId()).get();
                vehicle.setFuelType(fuelType);
            }
            if (newVehicle.getTransmissionTypeId() != null) {
                TransmissionType transmissionType = transmissionTypeRepository
                        .findById(newVehicle.getTransmissionTypeId()).get();
                vehicle.setTransmissionType(transmissionType);
            }
            if (newVehicle.getClassTypeId() != null) {
                ClassType classType = classTypeRepository.findById(newVehicle.getClassTypeId()).get();
                vehicle.setClassType(classType);
            }

            vehicle.setUserId(newVehicle.getUserId());

            // Images?

            vehicle.setMileage(newVehicle.getMileage());
            vehicle.setMileageConstraint(newVehicle.getMileageConstraint());
            vehicle.setInsurance(newVehicle.isInsurance());
            vehicle.setNumberOfSeats(newVehicle.getNumberOfSeats());
            vehicle.setRating(newVehicle.getRating()); // Or set to 1?
            vehicle.setLocation(newVehicle.getLocation());

            vehicleRepository.save(vehicle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Vehicle> getVehiclesOfModel(Long modelId) {
        return vehicleRepository.findByModel_Id(modelId);
    }

    @Override
    public CreateRequestDto proceedOrderRequest(CreateRequestDto request) {
        for (CreateRequestVehicleDto vehicleDto : request.getVehicles()) {
            try {
                Vehicle vehicle = getVehicle(vehicleDto.getVehicleId());
                vehicleDto.setPrice(vehicle.getPriceList().getDailyPrice());
                Discount discount = vehicle.getDiscount();
                System.out.println("Discount: " + discount);
                if (discount != null) {
                    Date currentDate = new Date();
                    System.out.println("Grater: " + currentDate.compareTo(discount.getStartDate()));
                    System.out.println("Less: " + currentDate.compareTo(discount.getEndDate()));
                    if (currentDate.compareTo(discount.getStartDate()) >= 0
                            && currentDate.compareTo(discount.getEndDate()) <= 0) {
                        CreateRequestDiscountDto discountDto = new CreateRequestDiscountDto(discount.getNumberOfDays(),
                                discount.getPercentage());
                        vehicleDto.setDiscount(discountDto);
                    }
                }
            } catch (Exception e) {
                request.setRejected(true);
                request.setRejectionMessage("Vehicle info could not be found");
                return request;
            }
        }
        return request;
    }

    @Override
    public List<Vehicle> getUsersVehicles(Long userId) {
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
        return vehicles;
    }

}