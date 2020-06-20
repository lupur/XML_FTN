import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const discountService = {
    getAll,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/discounts/",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(newDiscount) {
    console.log(newDiscount)
    return axios.post("http://localhost:8080/vehicle/discounts/",
    { 
        numberOfDays: newDiscount.numberOfDays,
        percentage: newDiscount.percentage,
        startDate: newDiscount.startDate,
        endDate: newDiscount.endDate
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
    return axios.delete("http://localhost:8080/vehicle/discounts/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}