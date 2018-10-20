import React from 'react';
import EngineTag from '../components/EngineTag';
import config from '../config';

const EngineInfo = ({ info, className }) => {

    // engine properties
    const cylinders = info["cylinders"] || 0;
    const displacement = info["displacement"] || "0";
    const turbo = info["hasTurbo"] || false;
    const type = info["type"].replace(
        info["type"], info["type"].charAt(0).toUpperCase() +
        info["type"].slice(1))
        || "Electric";
    const pollution = info["pollution"] || "No pollution";
    const autonomy = info["autonomy"] || "No autonomy registered";
    const maxSpeed = info["maxSpeed"] || "No max speed registered";
    const horsePower = info["horsepower"] || "";
    const weight = info["weight"] || "No weight registered";


    // phrases
    const cylindersInfo = (cylinders > 0) ?
        cylinders + " cylinders & " :
        "";
    const displacementInfo = (type.toLowerCase() === "electric") ?
        displacement + " KW engine" :
        displacement + " cc engine";

    // icons
    const typeIcon = (type.toLowerCase() === "electric") ?
        config.ProductIcons.electricIcon : config.ProductIcons.petrolIcon;

    // css classes
    const tagClass = "vehicle-tag";

    // all parameters
    const parameters = [
        {
            iconName: config.ProductIcons.engineIcon,
            text:
                (turbo) ?
                cylindersInfo + displacementInfo + " with turbo" :
                cylindersInfo + displacementInfo,
        },
        {
            iconName: typeIcon,
            text:
                (horsePower !== "") ?
                type + ", " + horsePower + "hp" :
                type,
        },
        {
            iconName: config.ProductIcons.pollutionIcon,
            text:
                (String(pollution).startsWith("No")) ?
                pollution : pollution + " g/cm3",
        },
        {
            iconName: config.ProductIcons.autonomyIcon,
            text:
                (String(autonomy).startsWith("No")) ?
                autonomy : autonomy + " km of autonomy",
        },
        {
            iconName: config.ProductIcons.speedIcon,
            text:
                (String(maxSpeed).startsWith("No")) ?
                maxSpeed : "Max Speed is " + maxSpeed + " km/h",
        },
        {
            iconName: config.ProductIcons.weightIcon,
            text:
                (String(weight).startsWith("No")) ?
                    weight : weight + " kg",
        }
    ];

    const content = parameters.map(p => <EngineTag key={p.iconName} iconName={p.iconName} text={p.text} tagClass={tagClass}/>);
    // return
    return (
        <div className={className}>
            {content}
        </div>
    );
};

export default EngineInfo;
