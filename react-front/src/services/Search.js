// GENERAL FUNCTIONALITY
import axios from "axios";
import config from "../config";
import {getDefaultHeaders} from "./Auth";

export const searchBrandsOrVehicles = (typedMessage, type) => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/search?name=${typedMessage}?type=${type}`, {headers: headers});
};