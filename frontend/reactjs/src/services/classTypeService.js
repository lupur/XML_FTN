import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const classTypeService = {
    getAll,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/classes",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newClass) {
    console.log(newClass)
    return axios.post("http://localhost:8080/vehicle/classes",
    { 
        name : newClass.name,
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
    return axios.delete("http://localhost:8080/vehicle/classes/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}