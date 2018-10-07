import React from 'react';

const Select = ({className, options, style}) => {
    const classes = "select " + className;

    return (
        <div className={classes} style={style}>
            <select>
                {options.map(e => <option key={e.id + "_" + e.text}>{e.text}</option>)}
            </select>
        </div>
    );
};

export default Select;
