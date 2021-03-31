import React, { Component } from 'react';
import { Button, Table, message, Form, Input, Divider } from 'antd';
import './Customer.css';
import { Link } from 'react-router-dom';
import { params, getParams } from '../utils';

export default class customerDetails extends Component {
    state = { 
        data: [],
        orderHistoryData:[],
        filterByCategoryData: [],
        customerId: null,
        customerInfo: null,
        orderInfo:``,
        lowestAvgRestaurant:``
     };

    async componentDidMount() {
        const { customerId, name, address, phoneNumber, email, postCode } = getParams( this.props.location.search );
        const nl = "\n";
        const customerInfo = `Name: ${name}${nl}` + `Address: ${address}${nl}` + `Phone Number: ${phoneNumber}${nl}`
        + `Email: ${email}${nl}` + `Post Code: ${postCode}${nl}`;
        const response = await fetch( `/getAllRestaurants`, {
            method: 'get'
        } );
        const results = await response.json();

        const data = results.map(( info, i ) => {
            return {
                key: i,
                ...info
            };
        } )

        const orderHistoryResponse = await fetch( `/getHistoryOrder?customerId=${customerId}`, {
            method: 'get'
        } );
        const orderHistoryResults = await orderHistoryResponse.json();
        
        const orderHistoryData = orderHistoryResults.map(( info, i ) => {
            return {
                key: i,
                ...info
            };
        } )
        this.setState( { 
            data,
            orderHistoryData,
            customerId,
            customerInfo
            } );
    }

    deleteOrder = async (record) =>{
        const orderId = record.orderId;
        const orderHistoryResponse = await fetch( `/deleteOrder?orderId=${orderId}`, {
            method: 'get'
        } );
        message.info( 'Order Deleted!' );
        this.componentDidMount();
    }

    handleFilterCategory = async (record) => {
        const category = record.category;
        const filterByCategoryResponse = await fetch( `/filterByCategory?category=${category}`, {
            method: 'get'
        } );
        const filterByCategoryResults = await filterByCategoryResponse.json();

        const filterByCategoryData = filterByCategoryResults.map(( info, i ) => {
            return {
                key: i,
                ...info
            };
        } )
        this.setState( { filterByCategoryData } );
    }

    getRestaurantOrders = async () => {
        const {customerId} = this.state;
        const getRestaurantOrdersResponse = await fetch( `/getRestaurantOrders?customerId=${customerId}`, {
            method: 'get'
        } );
        const getRestaurantOrdersResults = await getRestaurantOrdersResponse.json();
        console.log(getRestaurantOrdersResults);
        let orderInfo = ``;
        if (getRestaurantOrdersResults) {
            orderInfo = getRestaurantOrdersResults.join("  ,  ")
        }
        this.setState({
            orderInfo
        })
    }

    lowestAvgRestaurant = async () => {
        const lowestAvgRestaurantResponse = await fetch( `/getCheapRestaurant`, {
            method: 'get'
        } );
        const getRestaurantOrdersResults = await lowestAvgRestaurantResponse.json();

        this.setState ({
            lowestAvgRestaurant: getRestaurantOrdersResults[0]
        })
    }

    render() {
        const { data, orderHistoryData, filterByCategoryData, customerId, customerInfo, orderInfo, lowestAvgRestaurant } = this.state;
        const orderHistoryColumns = [
            {
                title: 'Order Id',
                dataIndex: 'orderId',
                width: '10%'
            },
            {
                title: 'Restaurant',
                dataIndex: 'restaurantName',
                width: '10%'
            },
            {
                title: 'Deliverer',
                dataIndex: 'delivererName',
                width: '10%'
            },
            {
                title: 'Notes',
                dataIndex: 'notes',
                width: '10%'
            },
            {
                title: 'Status',
                dataIndex: 'status',
                width: '10%'
            },
            {
                title: 'Type',
                dataIndex: 'type',
                width: '10%'
            },
            {
                title: 'Total Price',
                dataIndex: 'totalPrice',
                width: '10%'
            },
            {
                title: 'Action',
                dataIndex: 'action',
                width: '30%',
                render: (text, record) => {
                    const { restaurantId, delivererId, customerId } = record;
                    return (
                        <span>
                            <Button type="primary" onClick={() => this.deleteOrder(record)}> Delete</Button>
                            <Divider type="vertical" />
                            <Link to={{ pathname: '/customerGiveReview', search: params({ customerId, restaurantId, delivererId }) }}>
                                <Button type="primary" >Give a review</Button>
                            </Link>
                        </span>   
                        
                    )
                }
            }
        ];

        const filterByCategoryColumns = [
            {
                title: 'Name',
                dataIndex: 'name',
                width: '10%'
            },
            {
                title: 'Address',
                dataIndex: 'address',
                width: '10%'
            },
            {
                title: 'Category',
                dataIndex: 'category',
                width: '10%'
            }
        ];

        const columns = [{
            title: 'Name',
            dataIndex: 'name',
            width: '10%',
        }, 
        {
            title: 'Address',
            dataIndex: 'address',
            width: '10%'
        },
        {
            title: 'Category',
            dataIndex: 'category',
            width: '10%',
        },
        {
            title: 'Postal Code',
            dataIndex: 'postCode',
            width: '10%',
        },
        {
            title: 'Operating Hours',
            dataIndex: 'operatingHours',
            width: '10%',
        },
        {
            title: 'Action',
            dataIndex: 'action',
            width: '30%',
            render: (text, record) => {
                const { restaurantId, name } = record;
                return (
                    <span>
                        <Link to={{ pathname: '/customerPlaceOrder', search: params({ customerId, restaurantId, name }) }}>
                            <Button type="primary" >Select</Button>
                        </Link>
                    </span>
                )    
            }
        }
        ];

        return (
            <div>
                <h2>Customer Info</h2>
                <pre className="card-body">{customerInfo}</pre>
                <Table
                    bordered
                    dataSource={data}
                    columns={columns}
                    title={() => <b>All Restaurants</b>}
                    pagination={false}
                />
                <br />
                <Button type="primary" onClick={this.lowestAvgRestaurant}>Show Restaurant with lowest avg price</Button>
                <pre className="card-body">{lowestAvgRestaurant}</pre>
                <br />
                <Form
                        labelCol= {{ span: 2 }}
                        wrapperCol={{ span: 8 }}
                        initialValues={{ remember: true }}
                        onFinish={this.handleFilterCategory}
                        >
                        <Form.Item
                            label="Filter by category"
                            name="category"
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item 
                        wrapperCol={{ offset: 4, span: 8 }}
                        >
                            <Button type="primary" htmlType="submit">
                             Place filter
                            </Button>
                        </Form.Item>
                </Form>
                <Table
                    bordered
                    dataSource={filterByCategoryData}
                    columns={filterByCategoryColumns}
                    title={() => <b>Filtered Restaurants</b>}
                    pagination={false}
                />
                <br />
                <br />
                <Button type="primary" onClick={this.getRestaurantOrders}>Restaurants ordered at least twice</Button>
                <pre className="card-body">{orderInfo}</pre>
                <Table
                    bordered
                    dataSource={orderHistoryData}
                    columns={orderHistoryColumns}
                    title={() => <b>Order History</b>}
                    pagination={false}
                />
            </div>
        );
    }
}
