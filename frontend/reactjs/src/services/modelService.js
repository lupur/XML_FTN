import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const modelService = {
    add,
    remove
}

function add(newmodel) {
    console.log(newmodel)
    return axios.post("http://localhost:8080/vehicle/models",
    { 
        brandId : newmodel.brandId,
        name: newmodel.name
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
    return axios.delete("http://localhost:8080/vehicle/models/"+id,
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}