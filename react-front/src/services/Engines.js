// ENGINES CRUD
import mock from "../mocked_data";
import axios from "axios";
import config from "../config";
import {getEnvironment} from "./Utils";

export const getEngine = (id="") => {
    if (getEnvironment() === "dev") return new Promise(mock.car.engines[0]);
    else return axios.get(`${config.REST_BASE_URL}/engines?id=${id}`)
};

export const getAllEngines = () => {
    if (getEnvironment() === "dev") return new Promise(mock.car.engines);
    else return axios.get(`${config.REST_BASE_URL}/engines`)
};

export const addEngine = (engineObject) => {
    return axios.post(`${config.REST_BASE_URL}/engines`, engineObject)
};

export const updateEngine = (engineObject) => {
    return axios.put(`${config.REST_BASE_URL}/engines`, engineObject)
};

export const deleteEngine = (id) => {
    return axios.delete(`${config.REST_BASE_URL}/engines?id=${id}`)
};