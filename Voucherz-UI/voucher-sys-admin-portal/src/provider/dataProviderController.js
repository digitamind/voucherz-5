import jsonServerProvider from './dataProvider';
import { fetchUtils } from 'react-admin';

const httpClient = (url, options = {}) => {

    const token = localStorage.getItem('token');
  
    options.user = {
      authenticated: true,
      token: `Bearer ${token}`
    }
    return fetchUtils.fetchJson(url, options);
  }

const dataProviders = [
  { dataProvider: jsonServerProvider('http://localhost:8080/v1/audit-trail', httpClient), resources: ['audits'] },
  { dataProvider: jsonServerProvider('http://localhost:8080/v1/merchant-management', httpClient), resources: ['merchants'] },
  { dataProvider: jsonServerProvider('http://localhost:8080/v1/merchant-management/merchants', httpClient), resources: ['merchant-user'] },
];

export default (type, resource, params) => {
  const dataProviderMapping = dataProviders.find(dp => dp.resources.includes(resource));
  return dataProviderMapping.dataProvider(type, resource, params);
};