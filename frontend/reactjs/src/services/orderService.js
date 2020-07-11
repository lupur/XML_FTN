import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const orderService = {
    getOrder,
    sendRentRequest,
    getByOwner,
    getMine,
    changeStatus
}

function getOrder(orderId) {
    return axios.get("http://localhost:8080/order/"+orderId,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function getByOwner() {
    return axios.get("http://localhost:8080/order/requests",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function getMine() {
    return axios.get("http://localhost:8080/order/users/",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function changeStatus(requestId, status) {
    return axios.put("http://localhost:8080/order/" + requestId + "/status/" + status,
    {},
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

function sendRentRequest(ownerId, createdOn, vehicleOrders) {

    let url = "http://localhost:8080/order/"
    console.log(url)
    let tst = {
        userId: -1,
        ownerId: ownerId,
        createdOn: createdOn,
        vehicleOrders: vehicleOrders
    }
    console.log(tst)
    return axios.post( url,
    {
        userId: -1,
        ownerId: ownerId,
        createdOn: createdOn,
        vehicleOrders: vehicleOrders
    },
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}

