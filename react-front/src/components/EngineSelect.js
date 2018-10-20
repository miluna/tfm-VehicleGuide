import React from 'react';

const EngineSelect = ({className, id, options, style, onChange}) => {
    const classes = "select " + className;
    let content = null;

    if(Array.isArray(options) && options.length > 0) {
        content =  options.map(e =>
            <option key={e.displacement + e.horsepower}
                    id={e.id}
                    onClick={e => console.log(e)}
            >{e.type + ", " + e.horsepower + "hp"}
            </option>
        )
    }

    return (
        <div className={classes} style={style}>
            <select id={id} onChange={e => onChange(getId(e.target.value, options))}>
                {content}
            </select>
        </div>
    );
};

const getId = (text, options) => {
    const stringArray = text.split(",").map(each => each.trim());
    const selected = options.filter(e =>
        e.type === stringArray[0] && e.horsepower === parseInt(stringArray[1])
    );
    return selected[0];
};

export default EngineSelect;
