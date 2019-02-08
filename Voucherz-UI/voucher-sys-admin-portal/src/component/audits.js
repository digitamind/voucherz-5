import React from 'react';
import { List, Datagrid, TextField } from 'react-admin';

export const AuditTrails = props => (
    <List {...props} title="Voucherz Audits" perPage={15}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="eventDate" />
            <TextField source="eventType" />
            <TextField source="username" />
            <TextField source="description" />
        </Datagrid>
    </List>
);