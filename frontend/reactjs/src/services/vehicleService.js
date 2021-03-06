import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const vehicleService = {
    getAll,
    getImagesNames,
    getImage,
    add,
    getAvailableVehicles
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function getImagesNames(vehicleId) {
    console.log("BLAAAAAAAAAAAAAAAAAAAAAAA");
    console.log(vehicleId);
    return axios.get("http://localhost:8080/vehicle/" + vehicleId + "/images",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function getImage(vehicleId, imageName) {
    return axios.get("http://localhost:8080/vehicle/" + vehicleId + "/images/" + encodeURIComponent(imageName),
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newVehicle) {
    return axios.post("http://localhost:8080/vehicle/",
    { 
        modelId: newVehicle.modelId,
        pricelistId: newVehicle.pricelistId,
        colDamageWaiverId: newVehicle.colDamageWaiverId,
        fuelTypeId: newVehicle.fuelTypeId,
        transmissionTypeId: newVehicle.transmissionTypeId,
        classTypeId: newVehicle.classTypeId,
        mileage: newVehicle.mileage,
        discount: newVehicle.discountId,
        location: newVehicle.location,
        insurance: newVehicle.insurance,
        userId:2,
        numberOfSeats: newVehicle.numberOfSeats,
        mileageConstraint: newVehicle.mileageConstraint
    },
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function getAvailableVehicles(startDate, endDate, listOfVehicles) {
    let vehiclesIdStr = ""
    for(var i = 0; i < listOfVehicles.length; i++)
    {
        vehiclesIdStr+=listOfVehicles[i]+','
    }
    vehiclesIdStr = vehiclesIdStr.substring(0, vehiclesIdStr.length - 1);
    let url = "http://localhost:8080/order/available-vehicles?startDate="+startDate+"&endDate="+endDate+"&vehicles="+vehiclesIdStr
    console.log(url)
    return axios.get( url,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}