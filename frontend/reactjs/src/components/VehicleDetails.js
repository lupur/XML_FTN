import React, {Component} from 'react';
import {vehicleService} from '../services/vehicleService'
import {Card, Table, Button, Form } from 'react-bootstrap'
import {authService} from '../services/authService'
import {Role} from '../helpers/role'

export default class VehicleDetails extends Component {

    constructor(props) {
        super(props);

        this.vehicleId = props.location.state.vehicleDetails.id;
        this.imageSources = [];

        this.state = {
            Vehicle: props.location.state.vehicleDetails
        }

        console.log(this.vehicleId);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        // await vehicleService.getImagesNames().then(response =>{
        //     this.setState({Vehicles : response })
        // });

        await vehicleService.getImagesNames(this.vehicleId).then(response =>{
            let source = [];
            for(let i=0; i<response.length; i++) {
                vehicleService.getImage(this.state.Vehicle.id, response[i]).then(image =>{
                    const base64 = btoa(
                        new Uint8Array(image).reduce(
                          (data, byte) => data + String.fromCharCode(byte),
                          '',
                        ),
                      );
                      this.imageSources.push("data:;base64," + base64);
                      this.setState({ imageSources: this.imageSources});
                });
            }
        });
    }

    render() {
        return (
            <div>
                <Card className ={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Vehicle Details</h3></Card.Header>
                <Card.Body>
                     <Table bordered>
                     <thead>
                         <tr>
                             <th>ID</th>
                             <th>Brand</th>
                             <th>Model</th>
                             <th>Daily price</th>
                             <th>Collision Damage Price</th>
                             <th>Fuel Type</th>
                             <th>Class</th>
                             <th>Mileage</th>
                             <th>Insurance</th>
                             <th>No of Seats</th>
                             <th>Location</th>
                         </tr>
                     </thead>
                     <tbody>
                         <tr>
                             <td>{this.state.Vehicle.id}</td>
                             <td>{this.state.Vehicle.brand.name}</td>
                             <td>{this.state.Vehicle.model.name}</td>
                             <td>{this.state.Vehicle.pricelist.dailyPrice} Eur  </td>
                             <td>{this.state.Vehicle.colDamageWaiver.price}</td>
                             <td>{this.state.Vehicle.fuelType.name}</td>
                             <td>{this.state.Vehicle.classType.name}</td>
                             <td>{this.state.Vehicle.mileage}</td>
                             <td>{this.state.Vehicle.insurance == "true" ? "Yes" : "No"}</td>
                             <td>{this.state.Vehicle.numberOfSeats}</td>
                             <td>{this.state.Vehicle.location}</td>
                         </tr>
                     </tbody>
                     </Table>
                </Card.Body>
                </Card>
                <Card className ={"border border-dark bg-dark text-white"}>
                    <Card.Header><h4>Images</h4></Card.Header>
                    <Card.Body>
                    {this.state.imageSources && this.state.imageSources.map((imageSoruce, i) =>
                        <img src={imageSoruce} />
                    )}
                    </Card.Body>
                </Card>
            </div>
            
            );
    }
}