import React, {Component} from 'react';
import Button from '../components/Button';

class AdminPanel extends Component {
    render() {
        return (
            <div className="container">
                <h2 className="title">Admin Panel</h2>
                <div className="card">
                    <h3 className="subtitle">Add a new brand</h3>
                    <input className="input admin-input" placeholder="Brand name"/>
                    <input className="input admin-input" placeholder="Year of birth"/>
                    <Button text="Send"/>
                </div>
                <div className="card">
                    <h3 className="subtitle">Modify a brand</h3>
                </div>
                <div className="card">
                    <h3 className="subtitle">Add a new vehicle</h3>
                </div>
                <div className="card">
                    <h3 className="subtitle">Modify a vehicle</h3>
                </div>
                <div className="card">
                    <h3 className="subtitle">Add a new engine</h3>
                </div>
                <div className="card">
                    <h3 className="subtitle">Modify an engine</h3>
                </div>
            </div>
        );
    }
}

export default AdminPanel;
