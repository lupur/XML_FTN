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

export default class OrderManagement extends Component {

    constructor(props) {
        super(props);

        this.state = {
            requests: null,
            currentUser: null,
            isAdmin: false
        }

        this.changeStatus = this.changeStatus.bind(this);
        this.filterByStatus = this.filterByStatus.bind(this);
        this.getByOwner = this.getByOwner.bind(this);
        this.getVehicleDetails = this.getVehicleDetails.bind(this);
        this.parseDate = this.parseDate.bind(this);
    }

    async getVehicleDetails(requests) {
        await vehicleService.getAll().then(response => {
            requests.forEach(request => {
                request.vehicles = [];
                request.vehicleOrders.forEach(vehicleOrder =>{
                    let vehicle = response.find(v => v.id === vehicleOrder.vehicleId)
                    if(vehicle) request.vehicles.push('[' + vehicle.id + ']' + ' ' + vehicle.brand.name + ' ' + vehicle.model.name);
                    else request.vehicles.push('[X] Vehicle does not exist');
                })
                console.log(request.vehicles);
            });
            this.setState({
                requests: requests
            })
        })
    }

    async getByOwner() {
        await orderService.getByOwner()
            .then(response => {
                return this.getVehicleDetails(response);
            })
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await this.getByOwner();
    }

    async changeStatus(status, requestId) {
        console.log(requestId + " " + status);
        await orderService.changeStatus(requestId, status)
        .then(response => {
            return this.getByOwner();
        })
    }

    filterByStatus = status => {
        // this.setState({
        //   startDate: date
        // });
      };

    parseDate(str) {
        return new Date(str).toLocaleString();
      }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                
                <Card.Header><h3>Requests</h3></Card.Header>
                <Card.Body>

                <Table bordered>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Vehicle(s)</th>
                        <th>Pick Up Date</th>
                        <th>Return Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                {this.state.requests && this.state.requests.map((request, i) =>
                    <tr key={i}>
                        <td>{request.id}</td>
                        <td>{request.vehicles && request.vehicles.map((vehicle) =><div>{vehicle}<br/></div>)}</td>
                        <td>{this.parseDate(request.vehicleOrders[0].pickupDate)}</td>
                        <td>{this.parseDate(request.vehicleOrders[0].returnDate)}</td>
                        {request.status === 'PENDING' ? 
                            <td>
                                <Button onClick={() => this.changeStatus('PAID', request.id)} variant="success">Accept</Button>
                                <Button onClick={() => this.changeStatus('CANCELED', request.id)} variant="danger">Cancel</Button>
                            </td>
                            : <td>{request.status}</td>}
                    </tr>
                )}
                </tbody>
                </Table>
                </Card.Body>
                </Card>
            );
    }
}