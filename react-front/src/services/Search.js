// GENERAL FUNCTIONALITY
import mock from "../mocked_data";
import axios from "axios";
import config from "../config";
import {getEnvironment} from "./Utils";

export const searchBrandsOrVehicles = (typedMessage, type) => {
    if (getEnvironment() === "dev") return new Promise(mock.cars);
    else return axios.get(`${config.REST_BASE_URL}/search?name=${typedMessage}?type=${type}`);
};