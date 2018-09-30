import React from 'react';
import EngineInfo from '../components/EngineInfo';


const ProductCard = ({ title, subtitle, mainImage, description, engine }) => {

    const engineInfo = engine || {};

    const img = (mainImage) ?
        <div className="main-image" style={{
            backgroundImage: "url(" + mainImage + ")"
        }}/> : null;

    return (
        <section className="hero">
            <div className="hero-body">
                <div className="container product-container">
                    {img}
                    <div className="product-information">
                        <h1 className="title">
                            {title}
                        </h1>
                        <h2 className="subtitle">
                            {subtitle}
                        </h2>
                        <EngineInfo info={engineInfo}/>
                        <p>{description}</p>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProductCard;
