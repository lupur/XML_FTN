import axios from "axios"
import { authHeader } from "../helpers/authHeader"

export const reviewService = {
    getAll,
    getAllPendingReviews,
    reject,
    approve,
    add
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

function reject(id) {
    return axios.put("http://localhost:8084/review/" + id + "/reject",
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

function add(newReview) {
    return axios.post("http://localhost:8080/review",
    { 
        authorId: newReview.authorId,
        authorName: newReview.authorName,
        vehicleId: newReview.vehicleId,
        vehicleOrderId: newReview.vehicleOrderId,
        comment: newReview.comment,
        rating: newReview.rating
    },
    {
        headers: authHeader()
    }).then( response => {
        return response.data;
    }).catch(error => {
        return Promise.reject(error);
    });
}