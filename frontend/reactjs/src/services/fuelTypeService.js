import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const fuelTypeService = {
    getAll,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/fuelTypes/",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newFuelType) {
    return axios.post("http://localhost:8080/vehicle/fuelTypes/",
    { 
        name: newFuelType.name
    },
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function remove(id) {
    return axios.delete("http://localhost:8080/vehicle/fuelTypes/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}