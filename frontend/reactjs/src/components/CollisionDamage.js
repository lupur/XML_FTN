import React, {Component} from 'react';
import {collisionDamageService} from '../services/collisionDamageService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class CollisionDamages extends Component {

    constructor(props) {
        super(props);

        this.state = {
            CollisionDamages: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newCollisionDamagePrice: 0
        }

        this.newCollisionDamageChange = this.newCollisionDamageChange.bind(this);
        this.removeCollisionDamage = this.removeCollisionDamage.bind(this);
        this.addnewCollisionDamage = this.addnewCollisionDamage.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await collisionDamageService.getAll().then(response =>{
            this.setState({CollisionDamages : response })
        });

        console.log(this.state.users);
    }

    newCollisionDamageChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewCollisionDamage(event) {
        event.preventDefault();
        var newCollisionDamage = {
            price: this.state.newCollisionDamagePrice
        }
        collisionDamageService.add(newCollisionDamage)
        .then( response => {
            this.setState({newCollisionDamagePrice: 0});
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newCollisionDamagePrice:0});
            this.componentDidMount();
        });
    }

    removeCollisionDamage(event) {
        collisionDamageService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className ={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>CollisionDamages</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewCollisionDamage}>
                    <Table bordered>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Price</th>
                        {this.state.isAdmin ?
                            <th></th>
                            : null }
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.CollisionDamages && this.state.CollisionDamages.map((CollisionDamage, i) =>
                        <tr key={i}>
                            <td>{CollisionDamage.id}</td>
                            <td>{CollisionDamage.price}</td>
                        {this.state.isAdmin ?
                            <td><Button id={CollisionDamage.id} onClick={this.removeCollisionDamage} variant="danger">
                                    Remove
                                </Button></td>
                        : null}
                        </tr>
                    )}
    {this.state.isAdmin ?
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newCollisionDamagePrice">
                                <Form.Control required
                                    name="newCollisionDamagePrice"
                                    placeholder="Price"
                                    type="number"
                                    value={this.state.newCollisionDamagePrice}
                                    onChange={this.newCollisionDamageChange}/>
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