package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.dto.VehicleDto;
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
	public boolean addVehicle(VehicleDto newVehicle) {
		if (newVehicle == null)
			return false;
		
		try{
			Model model = modelRepostiory.findById(newVehicle.getModelId()).get();
			PriceList priceList = priceListRepository.findById(newVehicle.getPricelistId()).get();
    		CollisionDamageWaiver colDamageWaiver = colDamageWaiverRepository.findById(newVehicle.getColDamageWaiverId()).get();
    		Discount discount = discountRepository.findById(newVehicle.getDiscountId()).get();
    		FuelType fuelType = fuelTypeRepository.findById(newVehicle.getFuelTypeId()).get();
    		TransmissionType transmissionType = transmissionTypeRepository.findById(newVehicle.getTransmissionTypeId()).get();
    		ClassType classType = classTypeRepository.findById(newVehicle.getClassTypeId()).get();
			Vehicle vehicle = new Vehicle();
			
			vehicle.setModel(model);
			vehicle.setPriceList(priceList);
			vehicle.setColDamageWaiver(colDamageWaiver);
			vehicle.setDiscount(discount);
			// Images?
			vehicle.setUserId(newVehicle.getUserId());
			vehicle.setFuelType(fuelType);
			vehicle.setTransmissionType(transmissionType);
			vehicle.setClassType(classType);
			vehicle.setMileage(newVehicle.getMileage());
			vehicle.setMileageConstraint(newVehicle.getMileageConstraint());
			vehicle.setInsurance(newVehicle.isInsurance());
			vehicle.setNumberOfSeats(newVehicle.getNumberOfSeats());
			vehicle.setRating(newVehicle.getRating()); // Or set to 1?
			vehicle.setLocation(newVehicle.getLocation());
			
			vehicleRepository.save(vehicle);
			return true;
    	} catch (Exception e) {
            return false;
        }
	}

	@Override
	public List<Vehicle> getVehiclesOfModel(Long modelId) {
		return vehicleRepository.findByModel_Id(modelId);
	}

}
