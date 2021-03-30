import React, { Component } from 'react';
import { Button, Table, Input, InputNumber, Divider, Radio, message, Form} from 'antd';
import './Customer.css';
import {  getParams } from '../utils';

export default class customerShowAllFood extends Component {
    state = { 
        data: [],
        customerId: null,
        restaurantId: null,
        customerInfo: null,
        cart: [],
        totalPrice: 0,
        isPickUp: 1,
        notes: null
     };

    async componentDidMount() {
        const { customerId, restaurantId, name } = getParams( this.props.location.search );
        const response = await fetch( `/getAllFoodFromARestaurant?restaurantId=${restaurantId}`, {
            method: 'get'
        } );

        const results = await response.json();
        if ( results && results.length > 0 ) {
            const data = results.map(( info, i ) => {
                return {
                    key: i,
                    quantity:1,
                    ...info
                };
            } )
            this.setState( { 
                data,
                name,
                customerId,
                restaurantId
             } );
        }
    }

    onQuantityChange = (record, e) =>{
        const {data} = this.state;
        if ( data && data.length > record.key ) {
            data[record.key].quantity = e;
            this.setState( data );
        }
    }

    addToCart = (record) => {
        let {cart} = this.state;
        let flag = true;
        cart.map((cartItem, i) =>{
            if (cartItem.foodId === record.foodId) {
                cart[i].quantity += record.quantity;
                cart[i].price = cart[i].quantity * record.price;
                flag = false;
            }
        })
        if (flag) {
            const newItem = {
                key: cart ? cart.length : 0,
                foodId: record.foodId,
                type: record.type,
                name: record.name,
                quantity: record.quantity,
                price: record.quantity * record.price,
            };
            cart = [...cart, newItem]
        }
        this.setState( {
            cart
        } );
        let totalPrice = 0;
        cart.map(item =>{
            totalPrice += item.price;
        })
        totalPrice = totalPrice.toFixed(2);
        this.setState({
            totalPrice
        })
    }

    handleTotalPrice = () =>{
        const {cart} = this.state;
        let totalPrice = 0;
        cart.map(item =>{
            totalPrice += item.price;
        })
        this.setState({
            totalPrice
        })
    }

    handleNotes = (obj, e) =>{
        this.setState({
            notes: obj.target.value
        })
    }

    handleDeliveryOrPickup = obj => {
        this.setState({
            isPickUp: obj.target.value
        })
    }

    handleFilterMenu = async (record) => {
        const {restaurantId} = this.state;
        const menuType = record.menuType;
        if (!menuType) {
            this.componentDidMount();
        } else {
            const response = await fetch( `/getRestaurantMenuItems?menuType=${menuType}&restaurantId=${restaurantId}`, {
                method: 'get'
            } );
            const results = await response.json();
            let foodIdArray = [];
            results.map(result => {
                foodIdArray.push(result.foodtId);
            })
            const {data} = this.state;
            let filterData = [];
            data.map(d => {
                if (foodIdArray.includes(d.foodId)) filterData.push(d);
            })
            this.setState({
                data: filterData
            })
        }
        
    }

    handleSubmit = async (notes) => {
        const {cart, restaurantId, customerId, isPickUp, totalPrice} = this.state;
        let orderDetailList = [];
        cart.map(item => {
            orderDetailList.push({
                foodId: item.foodId,
                quantity: item.quantity
            })
        })
        const postData = {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( { 
                order:{
                    restaurantId: restaurantId,
                    customerId: customerId,
                    notes: notes.notes,
                    status: "in progress",    
                    totalPrice: totalPrice   
                },
                orderDetailList,
                isPickUp
             } ),
        }

        const submitOrder = await fetch( `/insertOrder`, postData );
        await submitOrder.json();
        message.info( 'Order Submitted!' );
    }

    render() {

        const { data, name, cart, isPickUp, totalPrice} = this.state;
        if ( data ) {
            const columns = [{
                title: 'Menu Type',
                dataIndex: 'type',
                width: '10%',
            }, 
            {
                title: 'Name',
                dataIndex: 'name',
                width: '10%'
            },
            {
                title: 'Description',
                dataIndex: 'description',
                width: '10%',
            },
            {
                title: 'Price',
                dataIndex: 'price',
                width: '10%',
            },
            {
                title: 'Quantity',
                width: '10%',
                render: (text, record) => {
                    return (
                        <InputNumber min={1} max={10} defaultValue={1} onChange={this.onQuantityChange.bind(null, record)} />
                    )
                }
            },
            {
                title: 'Action',
                dataIndex: 'action',
                width: '30%',
                render: (text, record) => {
                    return (
                        <Button type="primary" onClick={() => this.addToCart(record)}> Add to cart</Button>
                    )
                }
            }
            ];

            const cartColumns =[
                {
                    title: 'Type',
                    dataIndex: 'type',
                    width: '10%',
                }, 
                {
                    title: 'Name',
                    dataIndex: 'name',
                    width: '10%'
                },
                {
                    title: 'Quantity',
                    dataIndex: "quantity",
                    width: '10%',
                },
                {
                    title: 'Price',
                    dataIndex: 'price',
                    width: '10%',
                }
            ]

            return (
                <div>
                    <Table
                        bordered
                        dataSource={data}
                        columns={columns}
                        title={() => <b>Restaurant: {name}</b>}
                        pagination={false}
                    />
                    <br />
                    <Form
                        labelCol= {{ span: 2 }}
                        wrapperCol={{ span: 8 }}
                        initialValues={{ remember: true }}
                        onFinish={this.handleFilterMenu}
                        >
                        <Form.Item
                            label="Filter by Menu"
                            name="menuType"
                        >
                            <Input />
                        </Form.Item>
                        <Form.Item 
                        wrapperCol={{ offset: 4, span: 8 }}
                        >
                            <Button type="primary" htmlType="submit">
                            Filter by Menu
                            </Button>
                        </Form.Item>
                    </Form>
                    <Divider type="horizontal" />
                    <h2>Your cart, totalPrice: {totalPrice}</h2>
                    <Table
                        bordered
                        dataSource={cart}
                        columns={cartColumns}
                        title={() => <b>Cart:</b>}
                        pagination={false}
                    />
                    <br />
                    <br />
                    <Radio.Group onChange={this.handleDeliveryOrPickup} value={isPickUp}>
                        <Radio value={0}>Delivery</Radio>
                        <Radio value={1}>PickUp</Radio>
                    </Radio.Group>
                    <h2>Your notes:</h2>
                    <Form
                        labelCol= {{ span: 2 }}
                        wrapperCol={{ span: 8 }}
                        initialValues={{ remember: true }}
                        onFinish={this.handleSubmit}
                        >
                        <Form.Item
                            label="Your notes"
                            name="notes"
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item 
                        wrapperCol={{ offset: 4, span: 8 }}
                        >
                            <Button type="primary" htmlType="submit">
                            Place the order!
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
                
            );
        }
    }
}
