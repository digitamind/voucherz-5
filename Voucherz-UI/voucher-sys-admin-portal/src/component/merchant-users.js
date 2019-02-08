import React from 'react';
import { List, Datagrid, TextField, ReferenceField } from 'react-admin';

export const MerchantUsers = props => (
    <List {...props} title="Voucherz Users" perPage={15}>
        <Datagrid>
        <ReferenceField source="merchantId" reference="merchants">
                <TextField source="companyName" />
            </ReferenceField>
            <TextField source="id" />
            <TextField source="firstName" />
            <TextField source="lastName" />
            <TextField source="email" />
            <TextField source="role" />
        </Datagrid>
    </List>
);