import axios from "axios"
import {authHeader} from "../helpers/authHeader"

export const userService = {
    getInfo,
    getAll,
    getById,
    changeRole
}

function getInfo() {
    return axios.get("http://localhost:8080/user/info",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error =>
        {
            return Promise.reject(error);
        });
}

function getAll() {
    return axios.get("http://localhost:8080/users",
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error =>
        {
            return Promise.reject(error);
        });
}

function getById(id) {

}

function changeRole(id, role) {
    return axios.put("http://localhost:8080/users",
    { "id" : id, "role" : role },
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error =>
        {
            return Promise.reject(error);
        });
}