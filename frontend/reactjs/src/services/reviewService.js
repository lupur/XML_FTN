import axios from "axios"
import { authHeader } from "../helpers/authHeader"

export const reviewService = {
    getAll,
    getAllPendingReviews,
    remove,
    approve
}

function getAll() {
    return axios.get("http://localhost:8084/review",
        {
            headers: authHeader()
        }).then(response => {
            return response.data;
        }).catch(error => {
            return Promise.reject(error);
        });
}

function getAllPendingReviews() {
    return axios.get("http://localhost:8084/review/pending",
        {
            headers: authHeader()
        }).then(response => {
            return response.data;
        }).catch(error => {
            return Promise.reject(error);
        });
}

function remove(id) {
    return axios.delete("http://localhost:8084/review/" + id,
        {
            headers: authHeader()
        }).then(response => {
            return response.data;
        }).catch(error => {
            return Promise.reject(error);
        });
}

function approve(id) {
    return axios.put("http://localhost:8084/review/" + id + "/approve",
        {
            headers: authHeader()
        }).then(response => {
            return response.data;
        }).catch(error => {
            return Promise.reject(error);
        });
}