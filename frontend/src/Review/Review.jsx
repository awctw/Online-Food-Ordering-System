import React, { Component } from 'react';
import { CheckOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Table, Input, message, Form, Divider } from 'antd';
import './Review.css';
import { Link } from 'react-router-dom';
import { params } from '../utils';


export default class Review extends Component {
    state = { 
        data: [],
        loading: false,
     };

    async componentDidMount() {
        const response = await fetch( `/getAllReviews`, {
            method: 'get'
        } );

        const results = await response.json();
        if ( results && results.length > 0 ) {
            const data = results.map(( info, i ) => {
                return {
                    key: i,
                    ...info
                };
            } )
            this.setState( { data } );
        }
    }
    onCellChange = ( key, dataIndex ) => {
        return ( value ) => {
            const { data } = this.state;
            const target = data.find( item => item.key === key );
            if ( target ) {
                target[dataIndex] = value;
                this.setState( { data } );
            }
        };
    }

    insertNewReview = async (insertNewReview) => {
        const postData = {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( { 
                reviewId: insertNewReview.reviewId,
                customerId: insertNewReview.customerId,
                restaurantId: insertNewReview.restaurantId,
                delivererId: insertNewReview.delivererId,
                comment: insertNewReview.comment,
                rating: insertNewReview.rating.replaceAll(" ","")
             } ),
        }

        const upsertBuildList = await fetch( `/insertReview`, postData );
        await upsertBuildList.json();
        message.info( 'Data Submitted!' );
        this.componentDidMount();
    }

    render() {

        const { data } = this.state;
        if ( data ) {
            const columns = [{
                title: 'ReviewID',
                dataIndex: 'reviewId',
                width: '10%',
            }, 
            {
                title: 'CustomerID',
                dataIndex: 'customerId',
                width: '20%',
            },
            {
                title: 'RestaurantID',
                dataIndex: 'restaurantId',
                width: '10%'
            },
            {
                title: 'DelivererID',
                dataIndex: 'delivererId',
                width: '10%',
            },
            {
                title: 'Comment',
                dataIndex: 'comment',
                width: '10%',
            },
            {
                title: 'Rating',
                dataIndex: 'rating',
                width: '30%',

            }];

            return (
                <div>
                        <Table
                            bordered
                            dataSource={data}
                            columns={columns}
                            title={() => <b>All Reviews</b>}
                            pagination={false}
                        />
                    <Divider type="horizontal" />
                    <h3>Insert a new Review</h3>
                    <Form
                        labelCol= {{ span: 2 }}
                        wrapperCol={{ span: 8 }}
                        name="Insert a new Review"
                        initialValues={{ remember: true }}
                        onFinish={this.insertNewReview}
                        // onFinishFailed={onFinishFailed}
                        >
                        <Form.Item
                            label="CustomerID"
                            name="customerId"
                            rules={[{ required: true, message: 'Please input your CustomerID!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="RestaurantID"
                            name="restaurantId"
                            rules={[{ required: true, message: 'Please input the RestaurantID!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="DelivererID"
                            name="delivererId"
                            rules={[{ required: true, message: 'Please input the DelivererID!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Comment"
                            name="comment"
                            rules={[{ required: true, message: 'Please input your comment!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Rating"
                            name="rating"
                            rules={[{ required: true, message: 'Please input your rating!' }]}
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
}
