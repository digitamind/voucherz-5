import React from 'react';
import { Redirect, Route } from "react-router-dom";

// checkAuth = (nextstate, replace, callback) => {
  //   const token = window.sessionStorage.getItem("data")
  //   if(!token){
  //     replace("/signin");
  //     callback();
  //     return;
  //   }
  // }

  const AuthRoute = ({ component: Component, ...rest }) => {
    return (
      <Route {...rest} render={props => (
          sessionStorage.getItem("data") ? (
            <Component {...props} />
          ) : (
            <Redirect to={{ pathname: "/signin", state: {from: props.location} }}/>
          )
          )}/>
    );
  }

  export default AuthRoute;