import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const priceListService = {
    getAll,
    add,
    remove
}

function getAll() {
    return axios.get("http://localhost:8080/vehicle/priceLists/",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function add(priceList) {
    return axios.post("http://localhost:8080/vehicle/priceLists/",
    {
        dailyPrice: priceList.dailyPrice,
        mileagePenaltyPrice: priceList.mileagePenaltyPrice
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
    return axios.delete("http://localhost:8080/vehicle/priceLists/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}