import React from 'react';
import EngineInfo from '../components/EngineInfo';
import Gallery from "./Gallery";
import EngineSelect from "./EngineSelect";


const ProductCard = ({ title, subtitle, mainImage, description, engineList, onChangeEngine, engine }) => {

    const engineInfo = engine || {};

    const img = (mainImage) ?
        <div className="main-image" style={{
            backgroundImage: "url(" + mainImage + ")",
        }}>
            <img src={mainImage} style={{visibility: "hidden"}} />
        </div> : null;

    return (
        <section className="hero">
            <div className="hero-body container">
                <div className="product-container">
                    {img}
                    <div className="product-information">
                        <h1 className="title">
                            {title}
                        </h1>
                        <h2 className="subtitle">
                            {subtitle}
                        </h2>
                        <p>{description}</p>
                    </div>
                </div>
                <div className="product-engine-selector">
                    <p>Available Engines: </p>
                    <EngineSelect onChange={onChangeEngine}
                            options={engineList}/>
                </div>
                <EngineInfo  className="tags-container" info={engineInfo}/>
                <Gallery/>
            </div>
        </section>
    );
};

export default ProductCard;
