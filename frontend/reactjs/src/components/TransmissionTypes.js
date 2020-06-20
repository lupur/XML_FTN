import React, {Component} from 'react';
import {transmissionTypeService} from '../services/transmissionTypeService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class TransmissionTypes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            TransmissionTypes: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newTransmissionTypeName: ''
        }

        this.newTransmissionTypeChange = this.newTransmissionTypeChange.bind(this);
        this.removeTransmissionType = this.removeTransmissionType.bind(this);
        this.addnewTransmissionType = this.addnewTransmissionType.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await transmissionTypeService.getAll().then(response =>{
            this.setState({TransmissionTypes : response })
        });

        console.log(this.state.users);
    }

    newTransmissionTypeChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewTransmissionType(event) {
        event.preventDefault();
        var newTransmissionType = {
            name: this.state.newTransmissionTypeName
        }
        transmissionTypeService.add(newTransmissionType)
        .then( response => {
            this.setState({newTransmissionTypeName: ''});
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newTransmissionTypeName:''});
            this.componentDidMount();
        });
    }

    removeTransmissionType(event) {
        transmissionTypeService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Transmission Types</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewTransmissionType}>
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
                    {this.state.TransmissionTypes && this.state.TransmissionTypes.map((TransmissionType, i) =>
                        <tr key={i}>
                            <td>{TransmissionType.id}</td>
                            <td>{TransmissionType.name}</td>
                        {this.state.isAdmin ?
                            <td><Button id={TransmissionType.id} onClick={this.removeTransmissionType} variant="danger">
                                    Remove
                                </Button></td>
                        : null}
                        </tr>
                    )}
    {this.state.isAdmin ?
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newTransmissionTypeName">
                                <Form.Control required
                                    name="newTransmissionTypeName"
                                    placeholder="Name"
                                    value={this.state.newTransmissionTypeName}
                                    onChange={this.newTransmissionTypeChange}/>
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