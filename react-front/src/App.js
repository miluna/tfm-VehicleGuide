import React, {Component} from 'react';
import {Switch, Route, withRouter} from 'react-router-dom';
import ProtectedRoute from "./components/ProtectedRoute";
import Footer from './components/Footer';
import Home from './layout/Home';
import Login from './layout/Login';
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
        <Route exact path="/login" component={Login}/>
        <ProtectedRoute isAuthenticated={sessionStorage.getItem("authentication") === "yes"} exact path="/admin" component={AdminPanel}/>
    </Switch>
);

class App extends Component {
    render() {
        return (
            <React.Fragment>
                <Header/>
                {routes}
                <Footer author="Miguel Angel Luna" year="2018"/>
            </React.Fragment>
        );
    }
}

export default withRouter(App);
