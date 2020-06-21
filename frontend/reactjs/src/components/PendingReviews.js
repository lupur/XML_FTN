import React, { Component } from 'react';
import { reviewService } from '../services/reviewService'
import { Card, Table, Button, Form } from 'react-bootstrap'
import { authService } from '../services/authService'
import { Role } from '../helpers/role'

export default class PendingReviews extends Component {

    constructor(props) {
        super(props);

        this.state = {
            PendingReviews: null,
            users: null,
            currentUser: null,
            isAdmin: false
        }

        this.rejectReview = this.rejectReview.bind(this);
        this.approveReview = this.approveReview.bind(this);
    }

    async componentDidMount() {
        await authService.currentUser.subscribe(x => {
            this.setState({
                currentUser: x,
                isAdmin: x && x.role === Role.ADMIN
            });
        });

        await reviewService.getAllPendingReviews().then(response => {
            this.setState({ PendingReviews: response })
        });

        console.log(this.state.users);
    }

    rejectReview(event) {
        reviewService.reject(event.target.id)
            .then(response => {
                this.componentDidMount();
            })
    }

    approveReview(event) {
        reviewService.approve(event.target.id)
            .then(response => {
                this.componentDidMount();
            })
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><h3>Pending Reviews</h3></Card.Header>
                <Card.Body>
                    <Form>
                        <Table bordered>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Author ID</th>
                                    <th>Vehicle ID</th>
                                    <th>Author name</th>
                                    <th>Vehicle order ID</th>
                                    <th>Comment</th>
                                    <th>Rating</th>
                                    <th>Creation date</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.PendingReviews && this.state.PendingReviews.map((Review, i) =>
                                    <tr key={i}>
                                        <td>{Review.id}</td>
                                        <td>{Review.authorId}</td>
                                        <td>{Review.vehicleId}</td>
                                        <td>{Review.authorName}</td>
                                        <td>{Review.vehicleOrderId}</td>
                                        <td>{Review.comment}</td>
                                        <td>{Review.rating}</td>
                                        <td>{Review.creationDate.substring(0, 10)}</td>
                                        <td><Button id={Review.id} onClick={this.approveReview} variant="success">Approve</Button></td>
                                        <td><Button id={Review.id} onClick={this.rejectReview} variant="danger">Reject</Button></td>
                                    </tr>
                                )}
                            </tbody>
                        </Table>
                    </Form>
                </Card.Body>
            </Card>

        );
    }
}