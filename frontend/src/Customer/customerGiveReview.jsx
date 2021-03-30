import React, { Component } from 'react';
import { Button, Input, message, Form } from 'antd';
import './Customer.css';
import { getParams } from '../utils';

export default class customerGiveReview extends Component {
    state = { 
        customerId: null,
        restaurantId: null,
        delivererId: null
     };

    async componentDidMount() {
        const { customerId, restaurantId, delivererId } = getParams( this.props.location.search );
        this.setState({
            customerId,
            restaurantId,
            delivererId
        })
    }

    insertNewReview = async (record) => {
        const { customerId, restaurantId, delivererId } = this.state;
        const postData = {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( { 
                customerId: customerId,
                restaurantId: restaurantId,
                delivererId: delivererId,
                comment: record.comment,
                rating: record.rating
             } ),
        }

        const insertReview = await fetch( `/insertReview`, postData );
        await insertReview.json();
        message.info( 'Review Submitted!' );
    }

    render() {
        const { data } = this.state;
    
        return (
            <div>
                <Form
                    labelCol= {{ span: 2 }}
                    wrapperCol={{ span: 8 }}
                    name="Insert a new review"
                    initialValues={{ remember: true }}
                    onFinish={this.insertNewReview}
                    // onFinishFailed={onFinishFailed}
                    >
                    <Form.Item
                        label="Comment"
                        name="comment"
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="Rating"
                        name="rating"
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item 
                    wrapperCol={{ offset: 4, span: 8 }}
                    >
                        <Button type="primary" htmlType="submit">
                        Submit
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        );
    }
}
