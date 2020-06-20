import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const transmissionTypeService = {
    getAll,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/transmissionTypes/",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newTransmissionType) {
    return axios.post("http://localhost:8080/vehicle/transmissionTypes/",
    { 
        name: newTransmissionType.name
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
    return axios.delete("http://localhost:8080/vehicle/transmissionTypes/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}