// VEHICLES CRUD
import mock from "../mocked_data";
import axios from "axios";
import config from "../config";
import {getEnvironment} from "./Utils";

export const getVehicle = (id="") => {
    if (getEnvironment() === "dev") return new Promise(mock.car);
    else return axios.get(`${config.REST_BASE_URL}/vehicles/${id}`)
};

export const getAllVehicles = () => {
    if (getEnvironment() === "dev") return new Promise(mock.cars);
    else return axios.get(`${config.REST_BASE_URL}/vehicles`)
};

export const addVehicle = (vehicleObject) => {
    return axios.post(`${config.REST_BASE_URL}/vehicles`, vehicleObject)
};

export const updateVehicle = (id, vehicleObject) => {
    return axios.put(`${config.REST_BASE_URL}/vehicles/${id}`, vehicleObject)
};

export const deleteVehicle = (id) => {
    return axios.delete(`${config.REST_BASE_URL}/vehicles/${id}`)
};

// COMBINED
export const getVehicleEngines = (vehicleId="") => {
    if (getEnvironment() === "dev") return new Promise(mock.car.engines);
    else return axios.get(`${config.REST_BASE_URL}/vehicles/${vehicleId}/engines`)
};

export const getBrandVehicles = (brandId="") => {
    if (getEnvironment() === "dev") return new Promise(mock.brand.cars);
    else return axios.get(`${config.REST_BASE_URL}/vehicles?brandId=${brandId}`)
};
