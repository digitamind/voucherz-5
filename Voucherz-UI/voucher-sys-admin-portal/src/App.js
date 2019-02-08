import React, { Component } from 'react';
import { Admin, Resource, fetchUtils, ListGuesser } from 'react-admin';
import logo from './logo.svg';
import './App.css';
import authProvider from './provider/authProvider';
import dataProvider from './provider/dataProviderController';
import NotFound from './component/NotFound';
import Dashboard from './component/Dashboard';
import { PostList } from './component/posts';
import { AuditTrails } from './component/audits';
import {Merchants} from './component/merchants';
import {MerchantUsers} from './component/merchant-users';



// const dataProvider = jsonServerProvider('http://localhost:8080/v1/audit-trail', httpClient);

const App = () => (
  <Admin catchAll={NotFound} dashboard={Dashboard} authProvider={authProvider} dataProvider={dataProvider}>
    <Resource name="audits" list={AuditTrails} />
    <Resource name="merchants" list={Merchants} />
    <Resource name="merchant-user" list={MerchantUsers} title="Merchant User" />
  </Admin>

);
export default App;