import React, {Component} from 'react';
import {Card, Table} from 'react-bootstrap'
import {authService} from '../services/authService'
import {orderService} from '../services/orderService'
import {Role} from '../helpers/role'

import "react-datepicker/dist/react-datepicker.css";

export default class Order extends Component {

    constructor(props) {
        super(props);

        this.orderId =  this.props.match.params.id;
        this.state = {
            order: null,
            currentUser: null,
            isAdmin: false
        }

    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        let oId = this.orderId
        await orderService.getOrder(oId).then(response=> {
            console.log(response)
            this.setState({order: response})
        })

    }

    parseDate(str) {
        return new Date(str).toLocaleString();
      }

    render() {
        return (

            <Card className ={"border border-dark bg-dark text-white"}>
                
                <Card.Header><h3>Order Request {this.orderId}</h3></Card.Header>
                <Card.Body>

                <Table bordered>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Brand</th>
                        <th>Model</th>
                    </tr>
                </thead>
                <tbody>
                { this.state.order && this.state.order.vehicleOrders.map((order, i) =>
                    <tr key={i}>
                        <td>{order.id}</td>
                        <td>{order.brand}</td>
                        <td>{order.model}</td>
                    </tr>
                )}
                </tbody>
                </Table>

                { this.state.order && this.state.order.status === "PAID" ?
                
                <div>
                    <h3>Communication</h3> 
                    <div style={{float:'right'}}>Poruka1</div>
                    <div>Poruka2</div>
                </div>
                : <div></div>
                }
                </Card.Body>
                </Card>

            );
    }
}