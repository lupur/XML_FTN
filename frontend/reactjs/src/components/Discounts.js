import React, {Component} from 'react';
import {discountService} from '../services/discountService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'
import DatePicker from 'react-date-picker'

export default class Discount extends Component {

    constructor(props) {
        super(props);

        this.state = {
            Discounts: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newDiscountNumberOfDays: '',
            newDiscountPercentage: 0,
            newDiscountStartDate: new Date(),
            newDiscountEndDate: new Date()
        }

        this.newDiscountChange = this.newDiscountChange.bind(this);
        this.removeDiscount = this.removeDiscount.bind(this);
        this.addnewDiscount = this.addnewDiscount.bind(this);

        this.dateFormatter = this.dateFormatter.bind(this);
        this.startPicked = this.startPicked.bind(this);
        this.endPicked = this.endPicked.bind(this);
        this.requestDateFormat = this.requestDateFormat.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await discountService.getAll().then(response =>{
            this.setState({Discounts : response })
        });

        console.log(this.state.users);
    }

    newDiscountChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewDiscount(event) {
        event.preventDefault();
        var newDiscount = {
            numberOfDays: this.state.newDiscountNumberOfDays,
            percentage: this.state.newDiscountPercentage,
            startDate: this.requestDateFormat(this.state.startDate),
            endDate:  this.requestDateFormat(this.state.endDate)
        }

        discountService.add(newDiscount)
        .then( response => {
            this.setState({newDiscountNumberOfDays: ""});
            this.setState({newDiscountPercentage: ""});
            this.setState({newDate: new Date()})
            this.setState({endDate: new Date()})
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newDiscountNumberOfDays: ""});
            this.setState({newDiscountPercentage: ""});
            this.setState({newDate: new Date()})
            this.setState({endDate: new Date()})
            this.componentDidMount();
        });
    }

    removeDiscount(event) {
        discountService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    dateFormatter(date) {
        var options = { year: 'numeric', month: 'numeric', day: 'numeric'};
        return new Intl.DateTimeFormat('en-GB', options).format(new Date(date))
    }

    startPicked(date) {
        this.setState({startDate: date})
    }

    endPicked(date) {
        this.setState({endDate: date})
    }
    
    requestDateFormat(date) {
        if(date == null) return;
        let newDate = new Date(date);
        newDate.setMonth(newDate.getMonth()+1);

        return date.getFullYear() + '-' 
            + ('0' + newDate.getMonth()).slice(-2) + '-'
            + ('0' + newDate.getDate()).slice(-2)
    }
    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Discounts</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewDiscount}>
                    <Table bordered>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>No of Days</th>
                            <th>Percentage</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.Discounts && this.state.Discounts.map((Discount, i) =>
                        <tr key={i}>
                            <td>{Discount.id}</td>
                            <td>{Discount.numberOfDays}</td>
                            <td>{Discount.percentage}%</td>
                            <td>{this.dateFormatter(Discount.startDate)}</td>
                            <td>{this.dateFormatter(Discount.endDate)}</td>
                            <td><Button id={Discount.id} onClick={this.removeDiscount} variant="danger">
                                    Remove
                                </Button></td>
                        </tr>
                    )}
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newDiscountNumberOfDays">
                                <Form.Control required
                                    name="newDiscountNumberOfDays"
                                    placeholder="Number of days"
                                    value={this.state.newDiscountNumberOfDays}
                                    onChange={this.newDiscountChange}/>
                            </Form.Group>
                            </td>

                            <td>
                            <Form.Group controlId="newDiscountPercentage">
                                <Form.Control required
                                    name="newDiscountPercentage"
                                    placeholder="Percentage"
                                    value={this.state.newDiscountPercentage}
                                    onChange={this.newDiscountChange}/>
                            </Form.Group>
                            </td>

                            <td>
                                <DatePicker style={{color: "white"}}
                                    value={this.state.startDate}
                                    onChange={this.startPicked}/>
                            </td>
                            <td>
                                <DatePicker style={{color: "white"}}
                                    value={this.state.endDate}
                                    minDate={this.state.startDate}
                                    onChange={this.endPicked}/>
                            </td>
                            <td>
                                <Button variant="success" type="submit">
                                    Add
                                </Button>
                            </td>
                            
                        </tr>
                    </tbody>
                    </Table>
                </Form>
                </Card.Body>
                </Card>

            );
    }
}