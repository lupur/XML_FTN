import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const collisionDamageService = {
    getAll,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/collisionDW",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newCD) {
    console.log(newCD)
    return axios.post("http://localhost:8080/vehicle/collisionDW",
    { 
        price : newCD.price
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
    return axios.delete("http://localhost:8080/vehicle/collisionDW/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}