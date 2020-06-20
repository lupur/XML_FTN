import React, {Component} from 'react';
import {priceListService} from '../services/priceListService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class PriceLists extends Component {

    constructor(props) {
        super(props);

        this.state = {
            PriceLists: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newPriceListDailyPrice: '',
            newPriceListMileagePenaltyPrice: ''
        }

        this.newPriceListChange = this.newPriceListChange.bind(this);
        this.removePriceList = this.removePriceList.bind(this);
        this.addnewPriceList = this.addnewPriceList.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await priceListService.getAll().then(response =>{
            this.setState({PriceLists : response })
        });

        console.log(this.state.users);
    }

    newPriceListChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewPriceList(event) {
        event.preventDefault();
        var newPriceList = {
            dailyPrice: this.state.newPriceListDailyPrice,
            mileagePenaltyPrice: this.state.newPriceListMileagePenaltyPrice
        }
        priceListService.add(newPriceList)
        .then( response => {
            this.setState({newPriceListName: ''});
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newPriceListName:''});
            this.componentDidMount();
        });
    }

    removePriceList(event) {
        priceListService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Price Lists</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewPriceList}>
                    <Table bordered>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Daily Price</th>
                            <th>Mileage penalty</th>
                        {this.state.isAdmin ?
                            <th></th>
                            : null }
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.PriceLists && this.state.PriceLists.map((PriceList, i) =>
                        <tr key={i}>
                            <td>{PriceList.id}</td>
                            <td>{PriceList.dailyPrice}</td>
                            <td>{PriceList.mileagePenaltyPrice}</td>
                        {this.state.isAdmin ?
                            <td><Button id={PriceList.id} onClick={this.removePriceList} variant="danger">
                                    Remove
                                </Button></td>
                        : null}
                        </tr>
                    )}
    {this.state.isAdmin ?
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newPriceListDailyPrice">
                                <Form.Control required
                                    name="newPriceListDailyPrice"
                                    placeholder="Daily price"
                                    type="number"
                                    value={this.state.newPriceListDailyPrice}
                                    onChange={this.newPriceListChange}/>
                            </Form.Group>
                            </td>
                            <td>
                            <Form.Group controlId="newPriceListMileagePenaltyPrice">
                                <Form.Control required
                                    name="newPriceListMileagePenaltyPrice"
                                    placeholder="Mileage Penalty"
                                    type="number"
                                    value={this.state.newPriceListMileagePenaltyPrice}
                                    onChange={this.newPriceListChange}/>
                            </Form.Group>
                            </td>
                            <td>
                                <Button variant="success" type="submit">
                                    Add
                                </Button>
                            </td>
                            
                        </tr>
    : null}
                    </tbody>
                    </Table>
                </Form>
                </Card.Body>
                </Card>

            );
    }
}