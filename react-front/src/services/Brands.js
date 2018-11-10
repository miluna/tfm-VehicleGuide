// BRANDS CRUD
import mock from "../mocked_data";
import axios from "axios";
import config from "../config";
import {getEnvironment} from "./Utils";

export const getBrand = (id="") => {
    if (getEnvironment() === "dev") return new Promise(mock.brand);
    else return axios.get(`${config.REST_BASE_URL}/brands/${id}`)
};

export const getAllBrands = () => {
    if (getEnvironment() === "dev") return new Promise(mock.brands);
    else return axios.get(`${config.REST_BASE_URL}/brands`)
};

export const addBrand = (brandObject) => {
    return axios.post(`${config.REST_BASE_URL}/brands`, brandObject)
};

export const updateBrand = (id, brandObject) => {
    return axios.put(`${config.REST_BASE_URL}/brands/${id}`, brandObject)
};

export const deleteBrand = (id) => {
    return axios.delete(`${config.REST_BASE_URL}/brands/${id}`)
};