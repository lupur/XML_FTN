import React, {Component} from 'react';
import {brandService} from '../services/brandService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class Brands extends Component {

    constructor(props) {
        super(props);

        this.state = {
            Brands: null,
            users: null,
            currentUser: null,
            isAdmin: false,

            newBrandName: ''
        }

        this.newBrandChange = this.newBrandChange.bind(this);
        this.removeBrand = this.removeBrand.bind(this);
        this.addnewBrand = this.addnewBrand.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await brandService.getAll().then(response =>{
            this.setState({Brands : response })
        });

        console.log(this.state.users);
    }

    newBrandChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    addnewBrand(event) {
        event.preventDefault();
        var newBrand = {
            name: this.state.newBrandName,
        }

        brandService.add(newBrand)
        .then( response => {
            this.setState({newBrandName: ""});
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.setState({newBrandName: ""});
            this.componentDidMount();
        });
    }

    removeBrand(event) {
        brandService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Brands</h3></Card.Header>
                <Card.Body>
                <Form onSubmit={this.addnewBrand}>
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
                    {this.state.Brands && this.state.Brands.map((Brand, i) =>
                        <tr key={i}>
                            <td>{Brand.id}</td>
                            <td><Link className="tableLink" to={'/Brands/'+ Brand.id}>{Brand.name}</Link></td>
                        {this.state.isAdmin ?
                            <td><Button id={Brand.id} onClick={this.removeBrand} variant="danger">
                                    Remove
                                </Button></td>
                        : null}
                        </tr>
                    )}
    {this.state.isAdmin ?
                        <tr>
                            <td></td>
                            <td>
                            <Form.Group controlId="newBrandName">
                                <Form.Control required
                                    name="newBrandName"
                                    placeholder="Name"
                                    value={this.state.newBrandName}
                                    onChange={this.newBrandChange}/>
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