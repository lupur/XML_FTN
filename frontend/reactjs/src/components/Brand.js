import React, {Component} from 'react';
import {brandService} from '../services/brandService'
import {modelService} from '../services/modelService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class Model extends Component {

    constructor(props) {
        super(props);

        this.state = {
            BrandId: this.props.match.params.id,
            Brand: {
                name: null,
                id: null,
                models: []
            },

            currentUser: null,
            isAdmin: false,

            newModelName: ''
        }

        this.removeModel = this.removeModel.bind(this);
        this.newModelChange = this.newModelChange.bind(this);
        this.addModel = this.addModel.bind(this);
    }

    newModelChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    async componentDidMount() {
        
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await brandService.getModelsFromBand(this.state.BrandId).then(response =>{
            this.setState({Brand: response})
        });
    }

    async addModel() {
        var newModel = {
            brandId : this.state.Brand.id,
            name : this.state.newModelName
        }

        await modelService.add(newModel)
        .then( response => {
            this.setState({newModelName: ""});
            this.componentDidMount()
        }).catch( error => {
            alert(error);
            this.setState({newModelName: ""});
            this.componentDidMount()
        });
    }

    removeModel(event) {
        modelService.remove(event.target.id)
            .then( response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header><h3>{this.state.Brand ? this.state.Brand.name : "Brand not found"}</h3></Card.Header>
            <Card.Body>
                <Form onSubmit={this.addModel}>
                    <Table bordered>
                    <thead>
                        <tr>
                            <th>Name</th>
                            {this.state.isAdmin ? <th></th> : null}
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.Brand
                        && this.state.Brand.models
                            .sort((a, b) => a.id - b.id)
                            .map((model, i) =>
                        <tr key={i}>
                            <td>{model.name}</td>
                        {this.state.isAdmin ?
                            <td><Button id={model.id} variant="danger" onClick={this.removeModel}>Remove</Button></td>
                        : null }
                        </tr>
                        )}

                    {this.state.isAdmin ?
                        <tr>
                            <td>
                            <Form.Group controlId="newModelName">
                                <Form.Control required
                                    name="newModelName"
                                    placeholder="Name"
                                    value={this.state.newModelName}
                                    onChange={this.newModelChange}/>
                            </Form.Group>
                            </td>
                            <td>
                                <Button variant="success" type="submit">
                                    Add
                                </Button>
                            </td>
                        </tr>
                    : null }
                    </tbody>
                    </Table>
                </Form>
            </Card.Body>
            </Card>
        )
    }
}