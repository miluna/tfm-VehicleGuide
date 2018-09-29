import React, {Component} from 'react';
import Button from '../components/Button';
import Icon from '../components/Icon';
import HeroCard from "../components/HeroCard";

class Home extends Component {
    render() {
        return (
            <div>
                <Button type="light" onClick={() => console.log("LOL")}>
                    <Icon iconName="rocket"/>
                    Home
                </Button>
                <HeroCard title="Coche to guapo" subtitle="Este coche es un pepino"/>
            </div>
        );
    }
}

export default Home;
