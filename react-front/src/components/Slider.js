import React from 'react';

const Slider = ({className, id, min, max, value, onChange}) => {
    return (
        <div className={className}>
            <input id={id} className="slider" type="range" min={min} max={max} value={value} onChange={onChange} step={1000}/>
        </div>
    );
};

export default Slider;
