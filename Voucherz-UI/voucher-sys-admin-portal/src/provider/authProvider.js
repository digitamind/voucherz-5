import { AUTH_LOGIN, AUTH_LOGOUT, AUTH_ERROR, AUTH_CHECK } from 'react-admin';
import decodeJwt from 'jwt-decode';

export default (type, params) => {
    // called when the user attempts to log in
    if (type === AUTH_LOGIN) {
        const { username, password } = params;
        const request = new Request('http://localhost:8080/v1/merchant-management/merchants/login', {
            method: 'POST',
            body: JSON.stringify({username, password}),
            headers: new Headers({'Content-Type': 'application/json'}),
        })

        return fetch(request)
            .then(response => {
                if (response.status < 200 || response.status >= 300) {
                    throw new Error(response.statusText);
                }
                return response.json();
            })
            .then(({ accessToken }) => {
                const decodedToken = decodeJwt(accessToken);
                
                if (decodedToken.auth[0] === "SYSADMIN"){
                    localStorage.setItem('token', accessToken);
                    return Promise.resolve();
                }

                else{
                    return Promise.reject(); 
                }
                
        }); 

        }

        if (type === AUTH_LOGOUT) {
            localStorage.removeItem('username');
            return Promise.resolve();
        }

        if (type === AUTH_ERROR) {
            const { status } = params;
            if (status === 401 || status === 403) {
                localStorage.removeItem('username');
                return Promise.reject(); 
            }
            return Promise.resolve();
        }

    
};