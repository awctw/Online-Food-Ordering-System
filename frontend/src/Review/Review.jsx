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


    render() {

        const { data } = this.state;
        if ( data ) {
            const columns = [{
                title: 'ReviewID',
                dataIndex: 'reviewId',
                width: '10%',
            }, 
            {
                title: 'Customer Name',
                dataIndex: 'customerName',
                width: '20%',
            },
            {
                title: 'Restaurant Name',
                dataIndex: 'restaurantName',
                width: '10%'
            },
            {
                title: 'Deliverer Name',
                dataIndex: 'delivererName',
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
            </div>
            );
        }
        
    }
}
