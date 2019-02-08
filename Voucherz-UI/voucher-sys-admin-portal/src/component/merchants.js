import React from 'react';
import { List, Datagrid, TextField } from 'react-admin';

export const Merchants = props => (
    <List {...props} title="Voucherz Merchants" perPage={15}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="companyName" />
            <TextField source="companySize" />
            <TextField source="dateCreated" />
        </Datagrid>
    </List>
);