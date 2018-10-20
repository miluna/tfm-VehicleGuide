import React from 'react';

const Select = ({className, id, options, style, onChange}) => {
    const classes = "select " + className;
    let content = null;

    if(Array.isArray(options) && options.length > 0) {
        content =  options.map(e => e.id !== undefined ?
            <option key={e.id + "_" + e.text} id={e.id}>{e.text}</option> :
            <option key={e.text}>{e.text}</option>
        )
    }

    return (
        <div className={classes} style={style}>
            <select id={id} onChange={onChange}>
                {content}
            </select>
        </div>
    );
};

export default Select;
