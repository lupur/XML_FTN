import React, {Component} from 'react';
import {vehicleService} from '../services/vehicleService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class Vehicles extends Component {

    constructor(props) {
        super(props);

        this.state = {
            Vehicles: null,
            users: null,
            currentUser: null,
            isAdmin: false,
        }
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await vehicleService.getAll().then(response =>{
            this.setState({Vehicles : response })
        });
    }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Vehicles</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewVehicle}>
                    <Table bordered>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Brand</th>
                            <th>Daily price</th>
                            <th>Collision Damage Price</th>
                            <th>Fuel Type</th>
                            <th>Class</th>
                            <th>Mileage</th>
                            <th>Insurance</th>
                            <th>No of Seats</th>
                            <th>Location</th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.Vehicles && this.state.Vehicles.map((Vehicle, i) =>
                        <tr key={i}>
                            <td>{Vehicle.id}</td>
                            <td>{Vehicle.brand.name} {Vehicle.model.name}</td>
                            <td>{Vehicle.pricelist.dailyPrice} Eur  </td>
                            <td>{Vehicle.colDamageWaiver.price} Eur</td>
                            <td>{Vehicle.fuelType.name}</td>
                            <td>{Vehicle.classType.name}</td>
                            <td>{Vehicle.mileage}</td>
                            <td>{Vehicle.insurance == "true" ? "Yes" : "No"}</td>
                            <td>{Vehicle.numberOfSeats}</td>
                            <td>{Vehicle.location}</td>
                        </tr>
                    )}
                    </tbody>
                    </Table>
                </Form>
                </Card.Body>
                </Card>
            );
    }
}