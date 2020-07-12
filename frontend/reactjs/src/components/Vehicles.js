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
        
        var myDate = new Date();
        myDate.setTime( myDate.getTime() + 2 * 86400000 );

        this.state = {
            Vehicles: null,
            availableVehicles: null,
            users: null,
            currentUser: null,
            isAdmin: false,
            startDate: myDate,
            endDate: myDate,
            location: "",
            mileage: 0,
            seats: ""
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
    
    locationChange(event) {
        this.setState({
            location: event.target.value
        });
    };
    
    mileageChange(event) {
        this.setState({
            mileage: event.target.value
        });
    };
    
    seatsChange(event) {
        this.setState({
            seats: event.target.value
        });
    };

    async filterVehicles() {
        var vehiclesId = []
        if(this.state.Vehicles == null )
        {
            return;
        }
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
                    if( this.state.location.trim() !== "" ){
                        var locationFilteredVehicles = [];
                        for(var i = 0; i < this.state.availableVehicles.length; i++){
                            if( this.state.availableVehicles[i].location === this.state.location ){
                                locationFilteredVehicles.push(this.state.availableVehicles[i]);
                            }
                        }
                        this.state.availableVehicles = locationFilteredVehicles;
                    }
                    
                    if( this.state.mileage > 0 ){
                        var mileageFilteredVehicles = [];
                        for(var i = 0; i < this.state.availableVehicles.length; i++){
                            if( this.state.availableVehicles[i].mileage < this.state.mileage ){
                                mileageFilteredVehicles.push(this.state.availableVehicles[i]);
                            }
                        }
                        this.state.availableVehicles = mileageFilteredVehicles;
                    }
                    
                    if( this.state.seats !== "" ){
                        var seatsFilteredVehicles = [];
                        for(var i = 0; i < this.state.availableVehicles.length; i++){
                            if( this.state.availableVehicles[i].numberOfSeats >= this.state.seats ){
                                seatsFilteredVehicles.push(this.state.availableVehicles[i]);
                            }
                        }
                        this.state.availableVehicles = seatsFilteredVehicles;
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
                        minDate={this.state.startDate}
                    />
                    </td>
                    <td>
                    <DatePicker
                        selected={this.state.endDate}
                        onChange={this.endDateChange}
                        minDate={this.state.startDate}
                    />
                    </td>
                    <td>
                    <input
                        type="text"
                        placeholder="Location"
                        value={this.state.location}
                        onChange={event => this.locationChange(event)}
                    />
                    </td>
                    <td>
                    <input
                        type="number"
                        min="0"
                        placeholder="Mileage"
                        value={this.state.milage}
                        onChange={event => this.mileageChange(event)}
                    />
                    </td>
                    <td>
                    <input
                        type="number"
                        min="1"
                        placeholder="Number of seats"
                        value={this.state.seats}
                        onChange={event => this.seatsChange(event)}
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