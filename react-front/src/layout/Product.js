import React, {Component} from 'react';
import mock from "../mocked_data";
import ProductCard from "../components/ProductCard";
import {withRouter} from "react-router-dom";

class Product extends Component {
    constructor(props) {
        super(props);
        this.state = {
            car: mock.car,
            selectedEngine: mock.car.engines[0]
        }
    }

    changeEngine = (e) => {
        console.log(e);
        this.setState({selectedEngine: e});
    };

    render() {
        const {car, selectedEngine} = this.state;

        return (
            <div>
                <ProductCard
                    title={car.year + " " + car.brand + " " + car.name}
                    subtitle="Este coche es un pepino"
                    mainImage={car.mainImage}
                    description={car.description}
                    engineList={car.engines}
                    onChangeEngine={this.changeEngine}
                    engine={selectedEngine}
                />
            </div>
        );
    }
}

export default withRouter(Product);
