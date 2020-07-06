import 'devextreme/dist/css/dx.common.css';
import 'devextreme/dist/css/dx.light.css';
import React from 'react';

import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {Router, Switch, Route} from 'react-router-dom'

import NavigationBar from './components/NavigationBar';
import Footer from './components/Footer';
import Administration from './components/Administration';
import Users from './components/Users';
import Login from './components/Login'
import Brands from './components/Brands'
import Brand from './components/Brand'
import ClassTypes from './components/ClassTypes'
import Discounts from './components/Discounts'
import FuelTypes from './components/FuelTypes'
import Vehicles from './components/Vehicles'
import VehicleDetails from './components/VehicleDetails'
import TransmissionTypes from './components/TransmissionTypes'
import PriceLists from './components/PriceLists'
import VehicleCreation from './components/VehicleCreation'
import CollisionDamage from './components/CollisionDamage'
import PendingReviews from './components/PendingReviews'
import {authService} from './services/authService'
import {Role} from './helpers/role'
import {history} from './helpers/history'
import {PrivateRoute} from './components/PrivateRoute'
class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            currentUser: null
        };

        this.marginTop = {
            marginTop:"20px"
            }
    }

    componentDidMount() {
        authService.currentUser.subscribe(x => this.setState({
            currentUser: x
        }))
    }

    render() {
        const { currentUser } = this.state;
        return (
            <Router history={history}>
            <div className="App">
                { currentUser &&
                <NavigationBar/>
                }
                <Container className="mw-100">
                    <Row>
                        <Col lg={12} style={this.marginTop}>
                            <Switch>
                                <PrivateRoute path="/" exact component={Vehicles}/>
                                <PrivateRoute path="/vehicles/:id" component={VehicleDetails} />
                                <PrivateRoute path="/users" roles={[Role.ADMIN]}  component={Users}/>
                                <PrivateRoute exact path="/Administration" roles={[Role.ADMIN]}  component={Administration}/>
                                <PrivateRoute exact path="/Brands" roles={[Role.ADMIN]} component={Brands}/>
                                <PrivateRoute exact path="/ClassTypes" roles={[Role.ADMIN]} component={ClassTypes}/>
                                <PrivateRoute exact path="/CollisionDamage" roles={[Role.ADMIN]} component={CollisionDamage}/>
                                <PrivateRoute exact path="/Discounts" roles={[Role.ADMIN]} component={Discounts}/>
                                <PrivateRoute exact path="/FuelTypes" roles={[Role.ADMIN]} component={FuelTypes}/>
                                <PrivateRoute exact path="/TransmissionTypes" roles={[Role.ADMIN]} component={TransmissionTypes}/>
                                <PrivateRoute exact path="/PriceLists" roles={[Role.ADMIN]} component={PriceLists}/>
                                <PrivateRoute exact path="/PendingReviews" roles={[Role.ADMIN]} component={PendingReviews}/>
                                <PrivateRoute exact path="/VehicleCreation" component={VehicleCreation}/>
                                <PrivateRoute path="/Brands/:id" component={Brand} />
                                <Route path="/login" component={Login}/>
                            </Switch>
                    </Col>
                    </Row>
                </Container>
                <Footer/>
            </div>
            </Router>
        );
    }
}

export default App;
