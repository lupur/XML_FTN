import React, {Component} from 'react';
import {brandService} from '../services/brandService'
import {priceListService} from '../services/priceListService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'
import {Link} from 'react-router-dom'
import {collisionDamageService} from '../services/collisionDamageService'
import {fuelTypeService} from '../services/fuelTypeService'
import {transmissionTypeService} from '../services/transmissionTypeService'
import {classTypeService} from '../services/classTypeService'
import {discountService} from '../services/discountService'
import {vehicleService} from '../services/vehicleService'


export default class VehicleCreation extends Component {
    constructor(props) {
        super(props);

        this.state = {
            brands: [],
            models: [],
            priceLists: [],
            colDamages: [],
            fuelTypes: [],
            transmissionTypes: [],
            classTypes: [],
            discounts: [],
            currentUser: null,

            brandId: -1,
            modelId: -1,
            priceListId: -1,
            colDamageId: -1,
            fuelTypeId: -1,
            transmissionTypeId: -1,
            classTypeId: -1,
            discountId: -1,
            mileage: 0,
            mileageConstraint: 0,
            location: '',
            insurance: false,
            numberOfSeats: 5
        }

        this.newVehicleChange = this.newVehicleChange.bind(this);
        this.addNewVehicle = this.addNewVehicle.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
            });
        });

        await brandService.getAll().then(response =>{
            this.setState({brands : response })
        });
        this.setState({brandId: this.state.brands[0].id})

        await brandService.getModelsFromBand(this.state.brands[0].id).then(response =>{
            this.setState({models: response.models})
        });
        this.setState({modelId: this.state.models[0].id})

        await priceListService.getAll().then(response =>{
            this.setState({priceLists : response })
        });
        this.setState({priceListId: this.state.priceLists[0].id})

        await collisionDamageService.getAll().then(response =>{
            this.setState({colDamages : response })
        });
        this.setState({colDamageId: this.state.colDamages[0].id})

        await fuelTypeService.getAll().then(response =>{
            this.setState({fuelTypes : response })
        });
        this.setState({fuelTypeId: this.state.fuelTypes[0].id})

        await transmissionTypeService.getAll().then(response =>{
            this.setState({transmissionTypes : response })
        });
        this.setState({transmissionTypeId: this.state.transmissionTypes[0].id})

        await classTypeService.getAll().then(response =>{
            this.setState({classTypes : response })
        });
        this.setState({classTypeId: this.state.classTypes[0].id})

        await discountService.getAll().then(response =>{
            this.setState({discounts : response })
        });
        this.setState({discountId: this.state.discounts[0].id})

        console.log(this.state.currentUser)
    }

    async newVehicleChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        })
        if(event.target.name == "brandId") {
            console.log("Brand id")
            await brandService.getModelsFromBand(event.target.value).then(response =>{
                this.setState({models: response.models})
            });
        }
    }

    addNewVehicle(event) {
        event.preventDefault();
        var newVehicle = {
            modelId: this.state.modelId,
            pricelistId: this.state.priceListId,
            colDamageWaiverId: this.state.colDamageId,
            fuelTypeId: this.state.fuelTypeId,
            transmissionTypeId: this.state.transmissionTypeId,
            classTypeId: this.state.classTypeId,
            mileage: this.state.mileage,
            discount: this.state.discountId,
            location: this.state.location,
            insurance: 0,
            numberOfSeats: this.state.numberOfSeats,
            mileageConstraint: this.state.mileageConstraint
        }

        vehicleService.add(newVehicle)
        .then( response => {
            alert("Vehicle added")
            this.componentDidMount();
        })
        .catch( error => {
            alert(error);
            this.componentDidMount();
        });

        this.setState({location: ""})
        this.setState({mileage: 0})
        this.setState({mileageConstraint: 0})
        this.setState({numberOfSeats: 5})
    }

    render() {
        return(
        <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header><h3>New Vehicle</h3></Card.Header>
            <Form onSubmit={this.addNewVehicle}>

            <Card.Body>
            <Form onSubmit={this.addnewBrand}>
            <Table bordered>
                <tbody>
                <tr>
                    <td>
                        Brand
                    </td>
                    <td>
                <Form.Group controlId="brandId">
                    <Form.Control required as="select"
                        name="brandId"
                        value={this.state.brandId}
                        onChange={this.newVehicleChange}>
                    {this.state.brands && this.state.brands.map((brand, i) =>
                        <option key={brand.id} value={brand.id}>{brand.name}</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Model
                    </td>
                    <td>
                <Form.Group controlId="modelId">
                    <Form.Control required as="select"
                        name="modelId"
                        value={this.state.modelId}
                        onChange={this.newVehicleChange}>
                    {this.state.models && this.state.models.map((model, i) =>
                        <option key={model.id} value={model.id}>{model.name}</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Price List
                    </td>
                    <td>
                <Form.Group controlId="priceListId">
                    <Form.Control required as="select"
                        name="priceListId"
                        value={this.state.priceListId}
                        onChange={this.newVehicleChange}>
                    {this.state.priceLists && this.state.priceLists.map((priceList, i) =>
                        <option key={priceList.id} value={priceList.id}>{priceList.dailyPrice} Eur/Day</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Collision Damage Waiver
                    </td>
                    <td>
                <Form.Group controlId="colDamageId">
                    <Form.Control required as="select"
                        name="colDamageId"
                        value={this.state.colDamageId}
                        onChange={this.newVehicleChange}>
                    {this.state.colDamages && this.state.colDamages.map((colDamage, i) =>
                        <option key={colDamage.id} value={colDamage.id}>{colDamage.price} Eur</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Fuel Type
                    </td>
                    <td>
                <Form.Group controlId="fuelTypeId">
                    <Form.Control required as="select"
                        name="fuelTypeId"
                        value={this.state.fuelTypeId}
                        onChange={this.newVehicleChange}>
                    {this.state.fuelTypes && this.state.fuelTypes.map((fuelType, i) =>
                        <option key={fuelType.id} value={fuelType.id}>{fuelType.name}</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Transmission Type
                    </td>
                    <td>
                <Form.Group controlId="transmissionTypeId">
                    <Form.Control required as="select"
                        name="transmissionTypeId"
                        value={this.state.transmissionTypeId}
                        onChange={this.newVehicleChange}>
                    {this.state.transmissionTypes && this.state.transmissionTypes.map((transmissionType, i) =>
                        <option key={transmissionType.id} value={transmissionType.id}>{transmissionType.name}</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Class Type
                    </td>
                    <td>
                <Form.Group controlId="classTypeId">
                    <Form.Control required as="select"
                        name="classTypeId"
                        value={this.state.classTypeId}
                        onChange={this.newVehicleChange}>
                    {this.state.classTypes && this.state.classTypes.map((classType, i) =>
                        <option key={classType.id} value={classType.id}>{classType.name}</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>
                        Discount
                    </td>
                    <td>
                <Form.Group controlId="discountId">
                    <Form.Control required as="select"
                        name="discountId"
                        value={this.state.discountId}
                        onChange={this.newVehicleChange}>
                    {this.state.discounts && this.state.discounts.map((discount, i) =>
                        <option key={discount.id} value={discount.id}>{discount.percentage}%</option>
                    )}
                </Form.Control>
                </Form.Group>
                </td></tr>
                <tr>
                    <td>Mileage</td>
                    <td>
                    <Form.Group controlId="mileage">
                        <Form.Control required
                            name="mileage"
                            placeholder="Mileage"
                            value={this.state.mileage}
                            onChange={this.newVehicleChange}/>
                    </Form.Group>
                    </td></tr>
                <tr>
                    <td>Mileage Constraint</td>
                    <td>
                    <Form.Group controlId="mileageConstraint">
                        <Form.Control required
                            name="mileageConstraint"
                            placeholder="Mileage Constraint"
                            value={this.state.mileageConstraint}
                            onChange={this.newVehicleChange}/>
                    </Form.Group>
                    </td></tr>
                <tr>
                    <td>Number of Seats</td>
                    <td>
                    <Form.Group controlId="numberOfSeats">
                        <Form.Control required
                            name="numberOfSeats"
                            placeholder="Seats"
                            value={this.state.numberOfSeats}
                            onChange={this.newVehicleChange}/>
                    </Form.Group>
                    </td></tr>
                <tr>
                    <td>Location</td>
                    <td>
                    <Form.Group controlId="location">
                        <Form.Control required
                            name="location"
                            placeholder="Location"
                            value={this.state.location}
                            onChange={this.newVehicleChange}/>
                    </Form.Group>
                    </td></tr>
                </tbody>
            </Table>

            </Form>
            </Card.Body>
            <Card.Body>
                <Button variant="success" type="submit">
                    Add
                </Button>
            </Card.Body>
            </Form>
        </Card>
        );
    }
}