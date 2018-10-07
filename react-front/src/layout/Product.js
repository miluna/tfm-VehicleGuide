import React, {Component} from 'react';
import mock from "../mocked_data";
import ProductCard from "../components/ProductCard";
import {withRouter} from "react-router-dom";

class Product extends Component {
    render() {
        return (
            <div>
                <ProductCard
                    title={mock.car.year + " " + mock.car.brand + " " + mock.car.model}
                    subtitle="Este coche es un pepino"
                    mainImage={mock.car.mainImage}
                    description={mock.car.description}
                    engine={mock.car.engines[0]}
                />
            </div>
        );
    }
}

export default withRouter(Product);
