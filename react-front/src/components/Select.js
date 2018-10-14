import React from 'react';

const Select = ({className, id, options, style, onChange}) => {
    const classes = "select " + className;

    return (
        <div className={classes} style={style}>
            <select id={id} onChange={onChange}>
                {options.map(e => e.id !== undefined ?
                                <option key={e.id + "_" + e.text} id={e.id}>{e.text}</option> :
                                <option key={e.text}>{e.text}</option>
                )}
            </select>
        </div>
    );
};

export default Select;
