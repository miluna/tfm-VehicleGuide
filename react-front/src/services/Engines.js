// ENGINES CRUD
import axios from "axios";
import config from "../config";
import {getAuthHeader, getDefaultHeaders} from "./Auth";

export const getEngine = (id="") => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/engines?id=${id}`, {headers: headers})
};

export const getAllEngines = () => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/engines`, {headers: headers})
};

export const addEngine = (engineObject) => {
    const headers = getAuthHeader();
    return axios.post(`${config.REST_BASE_URL}/engines`, engineObject, {headers: headers})
};

export const updateEngine = (engineObject) => {
    const headers = getAuthHeader();
    return axios.put(`${config.REST_BASE_URL}/engines`, engineObject, {headers: headers})
};

export const deleteEngine = (id) => {
    const headers = getAuthHeader();
    return axios.delete(`${config.REST_BASE_URL}/engines?id=${id}`, {headers: headers})
};