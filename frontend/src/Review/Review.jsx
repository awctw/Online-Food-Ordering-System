import React, { Component } from 'react';
import { CheckOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Table, Input, message, Form, Divider } from 'antd';
import './Review.css';
import { Link } from 'react-router-dom';
import { params } from '../utils';


export default class Review extends Component {
    state = { 
        data: [],
        showRestaurantRankingData: [],
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


    handlefilterRating = async (record) => {
        const rating = record.rating;
        if (!rating) {
            this.componentDidMount();
        } else {
        const response = await fetch( `/filterByRating?rating=${rating}`, {
            method: 'get'
        } );
        const results = await response.json();
        let reviewIdArray = [];
        results.map(result => {
            reviewIdArray.push(result.reviewId);
        })
        const {data} = this.state;
        let filterData = [];
        data.map(d => {
            if (reviewIdArray.includes(d.reviewId)) filterData.push(d);
        })
        this.setState({
            data: filterData
        })
        }
    }

    handleShowRanking = async () => {
        const response = await fetch( `/showRestaurantRanking`, {
            method: 'get'
        } );
        const results = await response.json();

        const showRestaurantRankingData = results.map(( info, i ) => {
            return {
                key: i,
                ...info
            };
        } )
        this.setState( {showRestaurantRankingData } );
    }
    

    render() {

        const { data, showRestaurantRankingData} = this.state;
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
            
            const showRestaurantRankingColumns = [
                {
                    title: 'Restaurant Name',
                    dataIndex: 'name',
                    width: '10%'
                }
            ];
    

        return (
            <div>
                    <Table
                        bordered
                        dataSource={data}
                        columns={columns}
                        title={() => <b>All Reviews</b>}
                        pagination={false}
                    />

                <br />
                <br />
                <Form
                        labelCol= {{ span: 4 }}
                        wrapperCol={{ span: 5 }}
                        initialValues={{ remember: true }}
                        onFinish={this.handlefilterRating}
                        >
                        <Form.Item
                            label="Filter by Ranking Greater Than or Equal to"
                            name="rating"
                        >
                            <Input />
                        </Form.Item>
                        <Form.Item 
                        wrapperCol={{ offset: 4, span: 8 }}
                        >

                            <Button type="primary" htmlType="submit">
                            Filter by Ranking 
                            </Button>
                        </Form.Item>
                    </Form>
                <br />
                <br />

                <Form
                        labelCol= {{ span: 2 }}
                        wrapperCol={{ span: 8 }}
                        initialValues={{ remember: true }}
                        onFinish={this.handleShowRanking}
                        >

                        <Form.Item 
                        wrapperCol={{ offset: 0, span: 8 }}
                        >
                            <Button type="primary" htmlType="submit">
                             Rank Restaurants By Ratings
                            </Button>
                        </Form.Item>
                </Form>

                <Table
                    bordered
                    dataSource={showRestaurantRankingData}
                    columns={showRestaurantRankingColumns}
                    title={() => <b>Restaurants Rating from Highest to Lowest</b>}
                    pagination={false}
                />
            </div>
            );
        }
        
    }
}
