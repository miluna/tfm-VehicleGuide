import axios from "axios";
import config from "../config";
import {getDefaultHeaders} from "./Auth";

export const getLatestNews = () => {
    const headers = getDefaultHeaders();
    return axios.get(`${config.REST_BASE_URL}/latest`, {headers: headers});
};
