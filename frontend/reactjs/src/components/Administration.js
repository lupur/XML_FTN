import React, {Component} from 'react';
import {Card, Table} from 'react-bootstrap'
import {Link} from 'react-router-dom'

import {authService} from '../services/authService'

export default class Administration extends Component {
    constructor(props) {
        super(props);

        this.state = {

        }
    }


    render() {
        return(
            <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header><h3>Administration Panel</h3></Card.Header>
            <Card.Body>
                <Table bordered>
                    <tbody>
                        <tr><td>
                            <Link className="tableLink" to={'/Brands/'}>Brands</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/ClassTypes/'}>Class Types</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/CollisionDamage/'}>Collision Damage</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/Discounts/'}>Discounts</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/FuelTypes/'}>FuelType</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/PriceLists/'}>Price Lists</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/TransmissionTypes/'}>TransmissionTypes</Link>
                        </td></tr>
                        <tr><td>
                            <Link className="tableLink" to={'/PendingReviews/'}>Pending Reviews</Link>
                        </td></tr>
                    </tbody>
                </Table>
            </Card.Body>
            </Card>
        )
    }
}