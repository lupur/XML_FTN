import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const vehicleService = {
    getAll,
    getImagesNames,
    getImage
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