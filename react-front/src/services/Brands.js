// BRANDS CRUD
import axios from "axios";
import config from "../config";
import {getAuthHeader, getDefaultHeaders} from "./Auth";

export const getBrand = (id="") => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/brands/${id}`, {headers: headers})
};

export const getAllBrands = () => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/brands`, {headers: headers})
};

export const addBrand = (brandObject) => {
    const headers = getAuthHeader();
    return axios.post(`${config.REST_BASE_URL}/brands`, brandObject, {headers: headers})
};

export const updateBrand = (id, brandObject) => {
    const headers = getAuthHeader();
    return axios.put(`${config.REST_BASE_URL}/brands/${id}`, brandObject, {headers: headers})
};

export const deleteBrand = (id) => {
    const headers = getAuthHeader();
    return axios.delete(`${config.REST_BASE_URL}/brands/${id}`, {headers: headers})
};