import React from 'react';

const Button = ({ className, type, size, text, onClick, children }) => {
    if (className) className += " button"; else className = "button";
    if (type) className += " is-" + type;
    if (size) className += " is-" + size;

    return (
        <div className={className} onClick={onClick}>
            {children || text}
        </div>
    );
};

export default Button;
