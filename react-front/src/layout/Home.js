import React, {Component} from 'react';
import Button from '../components/Button';
import Icon from '../components/Icon';
import ProductCard from "../components/ProductCard";
import mock from "../mocked_data";

class Home extends Component {
    render() {
        return (
            <div>
                <Button type="light" onClick={() => console.log("LOL")}>
                    <Icon iconName="rocket"/>
                    Home
                </Button>
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

export default Home;
