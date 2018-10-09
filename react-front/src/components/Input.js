import React from 'react';

const Input = ({className, placeholder, type, min, max, value, onChange, onKeyPress}) => {
    const inputComponent = (min || max) ?
        <input placeholder={placeholder}
               type={type}
               min={min}
               max={max}
               value={value}
               onChange={onChange}
               onKeyPress={onKeyPress}/> :
        <input placeholder={placeholder}
               type={type}
               onChange={onChange}
               onKeyPress={onKeyPress}/>;

    return (
        <div className={"input " + className}>
            {inputComponent}
        </div>
    );
};

export default Input;
