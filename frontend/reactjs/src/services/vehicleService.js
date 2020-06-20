import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const vehicleService = {
    getAll
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