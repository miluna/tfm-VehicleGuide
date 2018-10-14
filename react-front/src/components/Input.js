import React from 'react';

const Input = ({className, placeholder, type, min, max, value, onChange, onKeyPress}) => {
    const inputComponent = (min || max) ?
        <input className={className ? ("input " + className) : "input"}
               placeholder={placeholder}
               type={type}
               min={min}
               max={max}
               value={value}
               onChange={onChange}
               onKeyPress={onKeyPress}/> :
        <input className={className ? ("input " + className) : "input"}
               placeholder={placeholder}
               type={type}
               onChange={onChange}
               onKeyPress={onKeyPress}/>;

    return (
        <div>
            {inputComponent}
        </div>
    );
};

export default Input;
