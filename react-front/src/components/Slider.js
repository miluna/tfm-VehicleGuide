import React from 'react';

const Slider = ({className, min, max, value}) => {
    return (
        <div className={className}>
            <input className="slider" type="range" min={min} max={max} value={value}/>
        </div>
    );
};

export default Slider;
