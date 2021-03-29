import React, { Component } from 'react';
import { Route } from 'react-router';
import { Link } from 'react-router-dom';
import { ConfigProvider, Layout, Menu } from 'antd';
import ErrorBoundary from './ErrorBoundary';
import enUS from 'antd/lib/locale-provider/en_US';

import './App.css';
import { Customer, customerShowAllRestaurants } from './Customer';

const { Header, Content, Sider } = Layout;

export default class App extends Component {
    render() {
        return <ConfigProvider locale={enUS}>
            <Layout>
                <Header className="header" style={{ background: '#152935' }}>
                    <div className="logo" />
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        defaultSelectedKeys={['2']}
                        style={{ lineHeight: '64px', background: '#152935' }}
                    >
                    <Menu.Item style={{ fontSize: "large" }}key="1">CS304 Group 25</Menu.Item>
                    </Menu>
                </Header>
                <Layout>
                    <Sider width={200} style={{ background: '#fff' }}>
                        <Menu
                            mode="inline"
                            defaultSelectedKeys={['1']}
                            defaultOpenKeys={['3']}
                            style={{ height: '100%', borderRight: 0 }}
                        >
                            <Menu.Item key="1"><Link to="/customer">Customer</Link></Menu.Item>
                            <Menu.Item key="2">Restaurant</Menu.Item>
                        </Menu>
                    </Sider>
                    <Layout style={{ padding: '0 24px 24px' }}>
                        <ErrorBoundary>
                            <Content style={{ background: '#fff', padding: 24, margin: 0, minHeight: 280 }}>
                                <Route path="/customer" component={Customer} />
                                <Route path="/customerShowAllRestaurants" component={customerShowAllRestaurants} />
                                {/* <Route path="/dashboard" component={Dashboard} />
                                <Route path="/tests/:type" component={TopLevelBuilds} />
                                <Route path="/output/:outputType" component={Output} />
                                <Route path="/deepHistory" component={DeepHistory} />
                                <Route path="/gitNewIssue" component={GitNewIssue} />
                                <Route path="/testCompare" component={TestCompare} />
                                <Route path="/perfCompare" component={PerfCompare} />
                                <Route path="/tabularView" component={TabularView} />
                                <Route path="/buildDetail" component={BuildDetail} />
                                <Route path="/allTestsInfo" component={AllTestsInfo} />
                                <Route path="/testPerPlatform" component={TestPerPlatform} />
                                <Route path="/possibleIssues" component={PossibleIssues} />
                                <Route path="/searchResult" component={SearchResult} />
                                <Route path="/resultSummary" component={ResultSummary} />
                                <Route path="/ThirdPartyAppView" component={ThirdPartyAppView} /> */}
                            </Content>
                        </ErrorBoundary>
                    </Layout>
                </Layout>
            </Layout>
        </ConfigProvider>
    }
}