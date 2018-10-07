import React from 'react';
import NavButton from './NavButton';
import config from "../config";

const logo = (
    <div className="logo" style={{ display: "flex", flexDirection: "row" }}>
        <img src="images/logo.png" alt="brandImage"/>
    </div>
);

const content = (
    <nav className="nav">
        <div className="navItem left">
            {logo}
            <h1 style={{color: "grey"}} className="title">VehicleGuide</h1>
        </div>
        <div className="navItem right">
            {config.navBarButtons.map(e => <NavButton key={e.link + "_" + e.text}
                                                      path={e.link}
                                                      iconName={e.icon}
                                                      text={e.text} />)}
        </div>
    </nav>
);


const Header = () => {
    return (
        <div>
            {content}
        </div>
    );
};

export default Header;
