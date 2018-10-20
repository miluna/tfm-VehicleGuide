import {convertObjectToBase64, readObjectFromBase64} from './Base64';
import axios from "axios";
import {getEnvironment} from "./Utils";
import config from '../config';

export function login(email, password) {
    if (getEnvironment() === "dev") {
        // fakeCredentials
        const fakeCredentials = {
            id: 1,
            email: email,
            role: "ADMIN"
        };
        sessionStorage.setItem("credentials", convertObjectToBase64(fakeCredentials));
        return {status: "OK"};
    } else {
        // send credentials
        axios.post(`${config.REST_BASE_URL}/login`, {email: email, password: password})
            .then(res => {
                if (res.status !== 200) {
                    return {
                        status: "KO",
                        error: "Email or Password incorrect"
                    };
                }
                // save session
                sessionStorage.setItem("credentials", convertObjectToBase64(res.data));
                return {status: "OK"};
            })
            .catch(err => console.log(err));
    }
}

export function isUserAdmin() {
    // get session credentials
    const base64Credentials = sessionStorage.getItem("credentials");

    if (base64Credentials !== null) {
        const credentials = readObjectFromBase64(base64Credentials);

        if (getEnvironment() === "dev") {
            return credentials.role === "ADMIN";
        } else {
            // check if user is admin
            axios.post(`${config.REST_BASE_URL}/checkadmin`, credentials)
                .then(res => {
                    return res.status === 200;
                })
                .catch(err => console.log(err));
        }
    }
    return false;
}
