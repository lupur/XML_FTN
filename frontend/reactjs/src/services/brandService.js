import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const brandService = {
    getAll,
    getModelsFromBand,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/brands",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function getModelsFromBand(id) {
    return axios.get("http://localhost:8080/vehicle/brands/" + id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newBrand) {
    console.log(newBrand)
    return axios.post("http://localhost:8080/vehicle/brands",
    { 
        name : newBrand.name,
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
    return axios.delete("http://localhost:8080/vehicle/brands/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}