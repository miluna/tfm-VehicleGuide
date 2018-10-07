import React, {Component} from 'react';
import {Switch, Route, withRouter} from 'react-router-dom';
import Footer from './components/Footer';
import Home from './layout/Home';
import AdminPanel from './layout/AdminPanel';
import Product from './layout/Product';
import Search from './layout/Search';
import Header from "./components/Header";


const routes = (
    <Switch>
        <Route exact path="/" component={Home}/>
        <Route path="/products/:id" component={Product}/>
        <Route exact path="/search" component={Search}/>
        <Route exact path="/compare" component={Search}/>
        <Route exact path="/admin" component={AdminPanel}/>
    </Switch>
);

class App extends Component {
    render() {
        return (
            <React.Fragment>
                <Header/>
                <div className="container is-widescreen is-centered">
                    {routes}
                </div>
                <Footer author="Miguel Angel Luna" year="2018"/>
            </React.Fragment>
        );
    }
}

export default withRouter(App);
