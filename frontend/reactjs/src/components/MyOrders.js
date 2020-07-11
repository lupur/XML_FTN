import React, {Component} from 'react';
import {vehicleService} from '../services/vehicleService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {orderService} from '../services/orderService'
import {reviewService} from '../services/reviewService'
import {Role} from '../helpers/role'
import {Link} from 'react-router-dom'
import DatePicker from "react-datepicker";
import Checkbox from '@material-ui/core/Checkbox';

import "react-datepicker/dist/react-datepicker.css";

export default class MyOrders extends Component {

    constructor(props) {
        super(props);

        this.state = {
            requests: null,
            currentUser: null,
            isAdmin: false,
            showComment: false,
            comment: '',
            rating: 1
        }

        this.getByOwner = this.getByOwner.bind(this);
        this.getVehicleDetails = this.getVehicleDetails.bind(this);
        this.parseDate = this.parseDate.bind(this);
        this.showCommentComponent = this.showCommentComponent.bind(this);
        this.newReviewChange = this.newReviewChange.bind(this);
        this.addNewReview = this.addNewReview.bind(this);
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
        await orderService.getMine()
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

    parseDate(str) {
        return new Date(str).toLocaleString();
      }
    
    showCommentComponent(){
        this.setState({
            showComment: true
        })
    }
    
    async newReviewChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addNewReview(requestId, vehicleId) {

        var newReview = {
            authorId: this.state.currentUser.id,
            authorName: this.state.currentUser.username,
            vehicleId: vehicleId,
            vehicleOrderId: requestId,
            comment: this.state.comment,
            rating: this.state.rating
        }

        reviewService.add(newReview)
        .then( response => {
            alert("Review added")
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.componentDidMount();
        });

        this.setState({comment: ''})
        this.setState({rating: 1})
        this.setState({showComment: false})
        this.render();
    }


    render() {
        if( this.state.showComment == false ){
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
                        <td>{request.status}</td>
                        <td>{request.vehicles && request.vehicles.map((vehicle) => <div><button onClick={() => this.showCommentComponent(request.id, vehicle.id)} disabled={new Date(request.vehicleOrders[0].returnDate) < this.state.now ? true:false}>Comment</button><br/></div>)}</td>
                    </tr>
                )}
                </tbody>
                </Table>
                </Card.Body>
                </Card>
                );
        }
        else{
            return (
                    <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>New Review</h3></Card.Header>

                <Card.Body>
                <Form>
                <Table bordered>
                    <tbody>
                    <tr>
                        <td>
                            Comment
                        </td>
                        <td>
                        <Form.Group controlId="comment">
                        <Form.Control required
                            name="comment"
                            placeholder="Comment"
                            value={this.state.comment}
                            onChange={this.newReviewChange}/>
                    </Form.Group>
                    </td></tr>
                    <tr>
                        <td>
                            Rating
                        </td>
                        <td>
                    <Form.Group controlId="rating">
                        <Form.Control required as="select"
                            name="rating"
                            onChange={this.newReviewChange}
                            value={this.state.rating}>
                            <option key={1} value="1">1</option>
                            <option key={2} value="2">2</option>
                            <option key={3} value="3">3</option>
                            <option key={4} value="4">4</option>
                            <option key={5} value="5">5</option>
                    </Form.Control>
                    </Form.Group>
                    </td></tr>
                    </tbody>
                </Table>

                </Form>
                </Card.Body>
                <Card.Body>
                    <Button variant="success" type="submit" onClick={() => this.addNewReview()}>
                        Add
                    </Button>
                </Card.Body>
            </Card>
            );
        }
    }
}
