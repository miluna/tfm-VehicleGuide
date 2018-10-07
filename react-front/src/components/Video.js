import React from "react";

const Video = ({className, source, width, height}) => {
    return (
        <video className={className} width={width} height={height} muted autoPlay>
            <source src={source} type="video/mp4"/>
        </video>
    );
};

export default Video;
