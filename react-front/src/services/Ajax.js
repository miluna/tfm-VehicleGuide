import config from '../config';
import mock from '../mocked_data';
import axios from 'axios';


const getEnvironment = () =>{
    return config.environment;
};

// GENERAL FUNCTIONALITY
export const searchBrandsOrVehicles = (typedMessage, type) => {
    if (getEnvironment() === "dev") return new Promise(mock.cars);
    else return axios.get(`${config.REST_BASE_URL}/search?name=${typedMessage}?type=${type}`);
};

export const getLatestNews = () => {
    if (getEnvironment() === "dev") return new Promise(mock.cars);
    else return axios.get(`${config.REST_BASE_URL}/latest`);
};


// ADMIN PANEL
export const loginAdmin = (email, password) => {
    if (getEnvironment() === "dev") return new Promise(true);
    else return axios.post(`${config.REST_BASE_URL}/login`, { email: email, password: password})
};


// VEHICLES CRUD
export const getVehicle = (id="") => {
    if (getEnvironment() === "dev") return new Promise(mock.car);
    else return axios.get(`${config.REST_BASE_URL}/vehicles?id=${id}`)
};

export const getAllVehicle = () => {
    if (getEnvironment() === "dev") return new Promise(mock.cars);
    else return axios.get(`${config.REST_BASE_URL}/vehicles`)
};

export const addVehicle = (vehicleObject) => {
    return axios.post(`${config.REST_BASE_URL}/vehicles`, vehicleObject)
};

export const updateVehicle = (id, vehicleObject) => {
    return axios.put(`${config.REST_BASE_URL}/vehicles?id=${id}`, vehicleObject)
};

export const deleteVehicle = (id) => {
    return axios.delete(`${config.REST_BASE_URL}/vehicles?id=${id}`)
};


// BRANDS CRUD
export const getBrand = (id="") => {
    if (getEnvironment() === "dev") return new Promise(mock.brand);
    else return axios.get(`${config.REST_BASE_URL}/brands?id=${id}`)
};

export const getAllBrands = () => {
    if (getEnvironment() === "dev") return new Promise(mock.brands);
    else return axios.get(`${config.REST_BASE_URL}/brands`)
};

export const addBrand = (brandObject) => {
    return axios.post(`${config.REST_BASE_URL}/brands`, brandObject)
};

export const updateBrand = (id, brandObject) => {
    return axios.put(`${config.REST_BASE_URL}/brands?id=${id}`, brandObject)
};

export const deleteBrand = (id) => {
    return axios.delete(`${config.REST_BASE_URL}/brands?id=${id}`)
};


// ENGINES CRUD
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


// COMBINED
export const getVehicleEngines = (vehicleId="") => {
    if (getEnvironment() === "dev") return new Promise(mock.car.engines);
    else return axios.get(`${config.REST_BASE_URL}/engines?vehicleId=${vehicleId}`)
};

export const getBrandVehicles = (brandId="") => {
    if (getEnvironment() === "dev") return new Promise(mock.brand.cars);
    else return axios.get(`${config.REST_BASE_URL}/vehicles?brandId=${brandId}`)
};
