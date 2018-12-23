import {convertObjectToBase64, readObjectFromBase64} from './Base64';
import axios from "axios";
import config from '../config';

export const STORAGE_KEY_NAME = "cred";

export async function login(email, password) {
    // send credentials and get authorization token
    const headers = getDefaultHeaders();
    const res = await axios.post(`${config.REST_BASE_URL}/login`, {email: email, password: password}, {headers: headers})

    if (res.status === 200){
        const authToken = res.data["Authorization"];
        // save token to session storage
        sessionStorage.setItem(STORAGE_KEY_NAME, convertObjectToBase64(authToken));
        return true;
    } else {
        return false;
    }

}

export function isUserAdmin() {
    // get session credentials. If non existent, is not admin
    const base64Credentials = sessionStorage.getItem(STORAGE_KEY_NAME);
    return base64Credentials !== null;
}

export function getSavedCredentials() {
    let credentials = sessionStorage.getItem(STORAGE_KEY_NAME);
    return (credentials !== null) ? readObjectFromBase64(credentials) : credentials;
}


export function getAuthHeader(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        Authorization: getSavedCredentials()
    };
}

export function getDefaultHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
}
