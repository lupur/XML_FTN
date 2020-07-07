import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const orderService = {
    sendRentRequest
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