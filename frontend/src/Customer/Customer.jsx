import React, { Component } from 'react';
import { CheckOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Table, Input, message, Form, Divider } from 'antd';
import './Customer.css';
import { Link } from 'react-router-dom';
import { params } from '../utils';

class EditableCell extends Component {
    state = {
        value: this.props.value,
        editable: false,
    }

    handleChange = ( e ) => {
        const value = e.target.value;
        this.setState( { value } );
    }

    check = () => {
        this.setState( { editable: false } );
        if ( this.props.onChange ) {
            this.props.onChange( this.state.value );
        }
    }

    edit = () => {
        this.setState( { editable: true } );
    }

    render() {
        const { value, editable } = this.state;
        return (
            <div className="editable-cell">
                {
                    editable ?
                        <div className="editable-cell-input-wrapper">
                            <Input
                                value={value}
                                onChange={this.handleChange}
                                onPressEnter={this.check}
                            />
                            <CheckOutlined className="editable-cell-icon-check" onClick={this.check} />
                        </div>
                        :
                        <div className="editable-cell-text-wrapper">
                            {value || ' '}
                            <EditOutlined className="editable-cell-icon" onClick={this.edit} />
                        </div>
                }
            </div>
        );
    }
}

export default class Customer extends Component {
    state = { 
        data: [],
        vipCustomer: ``
     };

    async componentDidMount() {
        const response = await fetch( `/getAllCustomers`, {
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

    insertNewCustomer = async (newCustomer) => {
        if (newCustomer.phoneNumber.length > 10) {
            message.error( 'phone number too long!' );
            return;
        }
        const postData = {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( { 
                name: newCustomer.name,
                address: newCustomer.address,
                phoneNumber: newCustomer.phoneNumber,
                email: newCustomer.email,
                postCode: newCustomer.postCode.replaceAll(" ","")
             } ),
        }

        const upsertBuildList = await fetch( `/insertCustomer`, postData );
        await upsertBuildList.json();
        message.info( 'Data Submitted!' );
        this.componentDidMount();
    }

    updateCustomerAddress = async (record) => {
        const newAddress = record.address;
        const postData = {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( { 
                customerId: record.customerId
             } ),
        }

        const updateAddress = await fetch( `/updateCustomerAddress?address=${newAddress}`, postData );
        await updateAddress.json();
        message.info( 'Address updated!' );
        this.componentDidMount();
    }

    vipCustomer = async () => {
        const vipResponse = await fetch( `/getVipCustomer`, {
            method: 'get'
        } );
        const vipResults = await vipResponse.json();
        let vipInfo = ``;
        if (vipResults) {
            vipResults.map (vip => {
                vipInfo += vip.name + `  `
            })
        }
        this.setState ({
            vipCustomer: vipInfo
        })
    }

    render() {

        const { data, vipCustomer } = this.state;
        if ( data ) {
            const columns = [{
                title: 'Name',
                dataIndex: 'name',
                width: '10%',
            }, 
            {
                title: 'Address',
                dataIndex: 'address',
                width: '20%',
                render: ( text, record ) => (
                    <EditableCell
                        value={text}
                        onChange={this.onCellChange( record.key, 'address' )}
                    />
                ),
            },
            {
                title: 'Phone Number',
                dataIndex: 'phoneNumber',
                width: '10%'
            },
            {
                title: 'Email',
                dataIndex: 'email',
                width: '10%',
            },
            {
                title: 'Postal Code',
                dataIndex: 'postCode',
                width: '10%',
            },
            {
                title: 'Action',
                dataIndex: 'action',
                width: '30%',
                render: (text, record) => {
                    return (
                        <span>
                            <Button type="primary" onClick={() => this.updateCustomerAddress(record)}> update address</Button>
                            <Divider type="vertical" />
                            <Link to={{ pathname: '/customerDetails', search: params({ ...record }) }}>
                                <Button type="primary" >Continue as this customer</Button>
                            </Link>
                        </span>
                    )    
                }
            }];

            return (
                <div>
                        <Table
                            bordered
                            dataSource={data}
                            columns={columns}
                            title={() => <b>All Customers</b>}
                            pagination={false}
                        />
                    <Divider type="horizontal" />
                    <Button type="primary" onClick={this.vipCustomer}>Show Customers who have Ordered from All Restaurants</Button>
                    <pre className="card-body">{vipCustomer}</pre>
                    <Divider type="horizontal" />
                    <h3>Insert a new Customer</h3>
                    <Form
                        labelCol= {{ span: 2 }}
                        wrapperCol={{ span: 8 }}
                        name="Insert a new Customer"
                        initialValues={{ remember: true }}
                        onFinish={this.insertNewCustomer}
                        // onFinishFailed={onFinishFailed}
                        >
                        <Form.Item
                            label="name"
                            name="name"
                            rules={[{ required: true, message: 'Please input your name!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="address"
                            name="address"
                            rules={[{ required: true, message: 'Please input your address!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="phone number"
                            name="phoneNumber"
                            rules={[{ required: true, message: 'Please input your phone number!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="email"
                            name="email"
                            rules={[{ required: true, message: 'Please input your email!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="postal code"
                            name="postCode"
                            rules={[{ required: true, message: 'Please input your post code!' }]}
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
