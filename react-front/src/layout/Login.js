import React, {Component} from 'react';
import {Redirect} from 'react-router-dom';
import Button from '../components/Button';
import Input from '../components/Input';
import {login} from "../services/Auth";
import {withRouter} from "react-router";

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            error: "",
            logged: false
        }
    }

    updateEmail = (e) => {
        this.setState({email: e.target.value})
    };

    updatePassword = (e) => {
        this.setState({password: e.target.value})
    };

    sendCredentialsOnKeyPress = (e) => {
        if (e.key === "Enter") this.sendCredentials();
    };

    sendCredentials = () => {
        if (this.validateParameters()) {
            // send credentials
            let isLoggedin = login(this.state.email, this.state.password);
            if (isLoggedin) this.setState({logged: true});
            else this.setState({error: "Email or password are incorrect"});
        }
    };

    validateParameters = () => {
        const {email, password} = this.state;

        // validate parameters
        if (email === "") {
            this.setState({error: "Email field empty"});
            return false;
        }
        if (!this.validateEmail(email)){
            this.setState({error: "Email format incorrect"});
            return false;
        }
        if (password === "") {
            this.setState({error: "Password field empty"});
            return false;
        }
        if (password.length < 6) {
            this.setState({error: "Password should be more than 6 characters"});
            return false;
        }

        return true;
    };

    validateEmail = (email) => {
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };


    render() {
        // redirect to admin panel
        if (this.state.logged) return <Redirect to="/admin" />;

        return (
            <div className="container" style={{marginTop: '2rem', marginBottom: '2rem'}}>
                <div className="form card centered" style={{backgroundColor: '#f5f5f5'}}>
                    <h3 className="Subtitle" style={{fontWeight: 600}}>Email</h3>
                    <Input
                        type="email"
                        name="email"
                        placeholder="Insert your email"
                        onChange={this.updateEmail}
                    />
                    <br/>
                    <h3 className="Subtitle" style={{fontWeight: 600}}>Password</h3>
                    <Input
                        type="password"
                        name="password"
                        placeholder="Type your password"
                        onChange={this.updatePassword}
                        onKeyPress={this.sendCredentialsOnKeyPress}
                    />
                    <br/>
                    <span style={{display: 'flex', justifyContent: 'center'}}>
                        <Button type="primary" size="medium" text="Sign In" onClick={this.sendCredentials}/>
                    </span>
                    <br/>
                    <p style={{color: 'red'}}>{this.state.error}</p>
                </div>
            </div>
        );
    }
}

export default withRouter(Login);