import React, { Component } from 'react';
import { Button, Table } from 'antd';
import './Customer.css';
import { Link } from 'react-router-dom';
import { params, getParams } from '../utils';

export default class customerShowAllRestaurants extends Component {
    state = { 
        data: [],
        customerId: null,
        customerInfo: null,
     };

    async componentDidMount() {
        const { customerId, name, address, phoneNumber, email, postCode } = getParams( this.props.location.search );
        console.log(name);
        console.log(address);
        const nl = "\n";
        const customerInfo = `Name: ${name}${nl}` + `Address: ${address}${nl}` + `Phone Number: ${phoneNumber}${nl}`
        + `Email: ${email}${nl}` + `Post Code: ${postCode}${nl}`;
        const response = await fetch( `/getAllRestaurants`, {
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
            this.setState( { 
                data,
                customerId,
                customerInfo
             } );
        }
    }

    render() {

        const { data, customerId, customerInfo } = this.state;
        if ( data ) {
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
                    console.log(record);
                    const { restaurantId } = record;
                    return (
                        <span>
                            <Link to={{ pathname: '/customerShowAllFood', search: params({ customerId, restaurantId }) }}>
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
                </div>
            );
        }
    }
}
