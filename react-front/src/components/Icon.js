import React from 'react';

const Icon = ({ iconName }) => {
    let iconClass = "fa fa-";
    if (iconName) iconClass+= iconName;
    return (
        <span className="icon" style={{margin: "2%"}}>
            <i className={iconClass}/>
        </span>
    );
};

export default Icon;
