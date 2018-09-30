import React from 'react';
import Icon from './Icon';

const EngineTag = ({ tagClass, iconName, text, id }) => {
    return (
        <div className={tagClass} id={id}>
            <Icon iconName={iconName}/>
            <p>{text}</p>
    </div>
    );
};

export default EngineTag;
