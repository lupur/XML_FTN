import React, {Component} from 'react';
import {fuelTypeService} from '../services/fuelTypeService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class FuelTypes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            FuelTypes: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newFuelTypeName: ''
        }

        this.newFuelTypeChange = this.newFuelTypeChange.bind(this);
        this.removeFuelType = this.removeFuelType.bind(this);
        this.addnewFuelType = this.addnewFuelType.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await fuelTypeService.getAll().then(response =>{
            this.setState({FuelTypes : response })
        });

        console.log(this.state.users);
    }

    newFuelTypeChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewFuelType(event) {
        event.preventDefault();
        var newFuelType = {
            name: this.state.newFuelTypeName
        }
        fuelTypeService.add(newFuelType)
        .then( response => {
            this.setState({newFuelTypeName: ''});
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newFuelTypeName:''});
            this.componentDidMount();
        });
    }

    removeFuelType(event) {
        fuelTypeService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Fuel Types</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewFuelType}>
                    <Table bordered>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                        {this.state.isAdmin ?
                            <th></th>
                            : null }
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.FuelTypes && this.state.FuelTypes.map((FuelType, i) =>
                        <tr key={i}>
                            <td>{FuelType.id}</td>
                            <td>{FuelType.name}</td>
                        {this.state.isAdmin ?
                            <td><Button id={FuelType.id} onClick={this.removeFuelType} variant="danger">
                                    Remove
                                </Button></td>
                        : null}
                        </tr>
                    )}
    {this.state.isAdmin ?
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newFuelTypeName">
                                <Form.Control required
                                    name="newFuelTypeName"
                                    placeholder="Name"
                                    value={this.state.newFuelTypeName}
                                    onChange={this.newFuelTypeChange}/>
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