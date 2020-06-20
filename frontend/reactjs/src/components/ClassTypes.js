import React, {Component} from 'react';
import {classTypeService} from '../services/classTypeService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class ClassTypes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            ClassTypes: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newClassTypeName: '',
        }

        this.newClassTypeChange = this.newClassTypeChange.bind(this);
        this.removeClassType = this.removeClassType.bind(this);
        this.addnewClassType = this.addnewClassType.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await classTypeService.getAll().then(response =>{
            this.setState({ClassTypes : response })
        });

        console.log(this.state.users);
    }

    newClassTypeChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewClassType(event) {
        event.preventDefault();
        var newClassType = {
            name: this.state.newClassTypeName,
        }

        classTypeService.add(newClassType)
        .then( response => {
            this.setState({newClassTypeName: ""});
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newClassTypeName: ""});
            this.componentDidMount();
        });
    }

    removeClassType(event) {
        classTypeService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>ClassTypes</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewClassType}>
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
                    {this.state.ClassTypes && this.state.ClassTypes.map((ClassType, i) =>
                        <tr key={i}>
                            <td>{ClassType.id}</td>
                            <td>{ClassType.name}</td>
                        {this.state.isAdmin ?
                            <td><Button id={ClassType.id} onClick={this.removeClassType} variant="danger">
                                    Remove
                                </Button></td>
                        : null}
                        </tr>
                    )}
    {this.state.isAdmin ?
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newClassTypeName">
                                <Form.Control required
                                    name="newClassTypeName"
                                    placeholder="Name"
                                    value={this.state.newClassTypeName}
                                    onChange={this.newClassTypeChange}/>
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