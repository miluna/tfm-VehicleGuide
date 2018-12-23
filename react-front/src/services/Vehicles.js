// VEHICLES CRUD
import axios from "axios";
import config from "../config";
import {getAuthHeader, getDefaultHeaders} from "./Auth";

export const getVehicle = (id="") => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/vehicles/${id}`, {headers: headers})
};

export const getAllVehicles = () => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/vehicles`, {headers: headers})
};

export const addVehicle = (vehicleObject) => {
    const headers = getAuthHeader();
    return axios.post(`${config.REST_BASE_URL}/vehicles`, vehicleObject, {headers: headers})
};

export const updateVehicle = (id, vehicleObject) => {
    const headers = getAuthHeader();
    return axios.put(`${config.REST_BASE_URL}/vehicles/${id}`, vehicleObject, {headers: headers})
};

export const deleteVehicle = (id) => {
    const headers = getAuthHeader();
    return axios.delete(`${config.REST_BASE_URL}/vehicles/${id}`, {headers: headers})
};

// COMBINED
export const getVehicleEngines = (vehicleId="") => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/vehicles/${vehicleId}/engines`, {headers: headers})
};

export const getBrandVehicles = (brandId="") => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/vehicles?brandId=${brandId}`, {headers: headers})
};
