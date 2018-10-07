import {Link} from "react-router-dom";
import Button from "./Button";
import Icon from "./Icon";
import React from "react";

const NavButton = ({ path, iconName, text}) => {
    return (
        <div style={{ margin: "2%" }}>
            <Link to={path}>
                <Button type="dark">
                    <Icon iconName={iconName}/>
                    {text}
                </Button>
            </Link>
        </div>
    );
};

export default NavButton
