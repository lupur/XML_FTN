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
                      console.log("Image sources: " + this.imageSources)
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
                     <tbody>
                         <tr>
                             <th>ID</th>
                             <td>{this.state.Vehicle.id}</td>
                         </tr>
                         <tr>
                             <th>Brand</th>
                             <td>{this.state.Vehicle.brand.name}</td>
                         </tr>
                         <tr>
                             <th>Model</th>
                             <td>{this.state.Vehicle.model.name}</td>
                         </tr>
                         <tr>
                             <th>Daily price</th>
                             <td>{this.state.Vehicle.pricelist.dailyPrice} Eur</td>
                         </tr>
                         <tr>
                             <th>Collision Damage Price</th>
                             <td>{this.state.Vehicle.colDamageWaiver.price} Eur</td>
                         </tr>
                         <tr>
                             <th>Fuel Type</th>
                             <td>{this.state.Vehicle.fuelType.name}</td>
                         </tr>
                         <tr>
                             <th>Class</th>
                             <td>{this.state.Vehicle.classType.name}</td>
                         </tr>
                         <tr>
                             <th>Mileage</th>
                             <td>{this.state.Vehicle.mileage}</td>
                         </tr>
                         <tr>
                             <th>Insurance</th>
                             <td>{this.state.Vehicle.insurance == "true" ? "Yes" : "No"}</td>
                         </tr>
                         <tr>
                             <th>No of Seats</th>
                             <td>{this.state.Vehicle.numberOfSeats}</td>
                         </tr>
                         <tr>
                             <th>Location</th>
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