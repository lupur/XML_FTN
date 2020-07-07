import React, {Component} from 'react';
import {vehicleService} from '../services/vehicleService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {orderService} from '../services/orderService'
import {Role} from '../helpers/role'
import {Link} from 'react-router-dom'
import DatePicker from "react-datepicker";
import Checkbox from '@material-ui/core/Checkbox';

import "react-datepicker/dist/react-datepicker.css";

export default class Vehicles extends Component {

    constructor(props) {
        super(props);

        this.state = {
            Vehicles: null,
            availableVehicles: null,
            users: null,
            currentUser: null,
            isAdmin: false,
            startDate: new Date(),
            endDate: new Date()
        }

        this.sendRequest = this.sendRequest.bind(this);
        this.filterVehicles = this.filterVehicles.bind(this);
        this.requestDateFormat = this.requestDateFormat.bind(this);
        this.checkboxChange = this.checkboxChange.bind(this);
    }

    async sendRequest(event) {
        console.log("Send Rent Request")
        let pickedVehicles = []
        var i = this.state.availableVehicles.length
        for(var i = 0; i < this.state.availableVehicles.length; i++)
        {
            if ( this.state.availableVehicles[i].isSelected) { 
                pickedVehicles.push(this.state.availableVehicles[i]);
            } 
        }

        let ownersList = []
        for( var i = 0; i < pickedVehicles.length; i++) {
            ownersList.push(pickedVehicles[i].userId)
        }
        ownersList = ownersList.filter( item  => i).filter((value,index,self) => self.indexOf(value) === index)
        console.log(ownersList)

        for( var i=0; i<ownersList.length; i++)
        {
            console.log("Owners List: " + ownersList[i])
            let ownerId = ownersList[i];
            let createdOn = this.requestDateFormat(new Date())
            let vehicleOrders = []
            for( var j = 0; j < pickedVehicles.length; j++) {
                if(ownerId == pickedVehicles[j].userId) {
                    let veh = {
                        vehicleId: pickedVehicles[j].id,
                        totalPrice: 0,
                        pickupDate: this.requestDateFormat(this.state.startDate),
                        returnDate: this.requestDateFormat(this.state.endDate)
                    }
                    vehicleOrders.push(veh)
                }
            }
            // console.log("Owner ID: " + ownerId)
            // console.log("Created on: " + createdOn)
            // console.log("Vehicle Orders: ")
            console.log(vehicleOrders[0])
            await orderService.sendRentRequest(ownerId, createdOn, vehicleOrders)
                .then(response => {
                    console.log(response)
                })
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

    startDateChange = date => {
        this.setState({
          startDate: date
        });
      };

    endDateChange = date => {
        this.setState({
            endDate: date
        });
    };

    async filterVehicles() {
        var vehiclesId = []
        for(var i = 0; i < this.state.Vehicles.length; i++)
        {
            vehiclesId.push(this.state.Vehicles[i].id)
        }
        await vehicleService
            .getAvailableVehicles(this.requestDateFormat(this.state.startDate)
                                                , this.requestDateFormat(this.state.endDate)
                                                , vehiclesId)
                .then(response =>{
                    this.state.availableVehicles = []
                    for(var i = 0; i < this.state.Vehicles.length; i++)
                    {
                        for(var j = 0; j < response.length; j++)
                        {
                            if(this.state.Vehicles[i].id == response[j])
                            {
                                let tmp = this.state.Vehicles[i]
                                tmp['isSelected'] = false;
                                this.state.availableVehicles.push(tmp)
                                break;
                            }
                        }
                    }
                    this.componentDidMount();
                }
            )
    }

    requestDateFormat(date) {
        return date.getFullYear() + '-' 
            + ('0' + date.getMonth()).slice(-2) + '-'
            + ('0' + date.getDate()).slice(-2)
    }

    checkboxChange(event) {

        for(var i = 0; i < this.state.availableVehicles.length; i++)
        {
            if(this.state.availableVehicles[i].id == event.target.value)
            {
                this.state.availableVehicles[i].isSelected = !this.state.availableVehicles[i].isSelected
            }
        }
    }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                
                <Card.Header><h3>Vehicles</h3></Card.Header>
                <Card.Body>
                <Table bordered>
                <tbody>
                <tr>
                    <td>
                    <DatePicker
                        selected={this.state.startDate}
                        onChange={this.startDateChange}
                    />
                    </td>
                    <td>
                    <DatePicker
                        selected={this.state.endDate}
                        onChange={this.endDateChange}
                    />
                    </td>
                    <td>
                    <Button variant="success" type="submit" onClick={this.filterVehicles}>
                        Filter
                    </Button>
                    </td>
                </tr>
                </tbody>
                </Table>

                <Table bordered>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Brand</th>
                        <th>Daily price</th>
                        {/* <th>Collision Damage Price</th> */}
                        <th>Fuel Type</th>
                        <th>Class</th>
                        {/* <th>Mileage</th> */}
                        {/* <th>Insurance</th> */}
                        {/* <th>No of Seats</th> */}
                        <th>Location</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                {this.state.availableVehicles && this.state.availableVehicles.map((Vehicle, i) =>
                    <tr key={i}>
                        <td><Link to={{pathname: '/vehicles/'+ Vehicle.id, state: {vehicleDetails: Vehicle}}}>{Vehicle.id}</Link></td>
                        <td>{Vehicle.brand.name} {Vehicle.model.name}</td>
                        <td>{Vehicle.pricelist.dailyPrice} Eur  </td>
                        {/* <td>{Vehicle.colDamageWaiver.price} Eur</td> */}
                        <td>{Vehicle.fuelType.name}</td>
                        <td>{Vehicle.classType.name}</td>
                        {/* <td>{Vehicle.mileage}</td> */}
                        {/* <td>{Vehicle.insurance == "true" ? "Yes" : "No"}</td> */}
                        {/* <td>{Vehicle.numberOfSeats}</td> */}
                        <td>{Vehicle.location}</td>
                        <td><Checkbox color="primary" value={Vehicle.id} onChange={this.checkboxChange} /></td>

                    </tr>
                )}

                {this.state.availableVehicles ?
                <tr>
                    <td colSpan="7">
                        <Button onClick={this.sendRequest} variant="success">
                            Send
                        </Button>
                    </td>
                </tr>
                : null }
                </tbody>
                </Table>
                </Card.Body>
                </Card>
            );
    }
}