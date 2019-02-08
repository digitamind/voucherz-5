import React, { Component } from "react";
import {Redirect} from "react-router-dom";
import * as ROUTES from "../Routes/index"
import Button from "../../BasicComponents/Dashboard/CustomButtons/Button";

class Signout extends Component{
    constructor(props){
        super(props);
        this.state={
           redirectToReferrer:false, 
        };
    }

    logout = ()=> {
        sessionStorage.setItem("data",'');
        sessionStorage.clear();
        this.setState({redirectToReferrer: true});
    }
    render(){
        if (this.state.redirectToReferrer) {
            return (<Redirect to={ROUTES.LANDING}/>)
          }
        return(
            <div>
                <Button style={{marginRight: "30px"}} color="info" size="sm" onClick={this.logout}>Log Out</Button>
            </div>
        )
    };
}

export default Signout;
