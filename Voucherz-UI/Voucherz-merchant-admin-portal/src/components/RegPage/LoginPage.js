import React, { Component } from "react";
import { Redirect } from 'react-router-dom';

import withStyles from "@material-ui/core/styles/withStyles";
import Header from "../../BasicComponents/Header/Header";
import HeaderLinks from "../../BasicComponents/Header/HeaderLinks";
import GridContainer from "../../BasicComponents/Grid/GridContainer";
import GridItem from "../../BasicComponents/Grid/GridItem";
import Button from "../../BasicComponents/CustomButtons/Button";
import Card from "../../BasicComponents/Card/Card";
import CardBody from "../../BasicComponents/Card/CardBody";
import CardHeader from "../../BasicComponents/Card/CardHeader";
import CustomInput from "../../BasicComponents/CustomInput/CustomInput";

import loginPageStyle from "../../assets/styles/loginPage";
import image from "../../assets/img/bg7.jpg";
import axios from "axios";
import * as ROUTES from "../Routes/index"
import { APIBASEURL } from "../Routes/BaseURL";

const loginState = {
  username: "",
  password: ""
}

class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cardAnimaton: "cardHidden",
      ...loginState
    };
  }
  componentDidMount() {
    setTimeout(
      function() {
        this.setState({ cardAnimaton: "" });
      }.bind(this),
      700
    );
    
  }

  onSubmit = (e) => {
    e.preventDefault();
    document.getElementById("submitBtn").innerHTML = "Processing..."
    const{ username ,password } = this.state;
    let loginData = {username ,password}

    axios.post(`${APIBASEURL}/merchant-management/merchants/login`, loginData, {data: JSON.stringify(loginData)})
    .then((res)=>{
      console.log(res);
      if(res.data.code === 200){
        alert("Logged In")
      }
      document.getElementById("submitBtn").innerHTML = "Sign in"
      if(res.data && res.data.accessToken){
        sessionStorage.setItem('data',res.data.accessToken);
        this.setState({redirectToReferrer: true});
      }
      else if(res.data.code === 409){
        alert("Email address or password is invalid");
        document.getElementById("submitBtn").innerHTML = "Sign up"
      } 
      })
    .catch((error) => {
      document.getElementById("submitBtn").innerHTML = "Sign in"
      alert("Sign in Failed")
    })
  }

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value })
    };

    render(){

      if(this.state.redirectToReferrer){
        return (<Redirect to={ROUTES.Dashboard}/>)
        }
      if(sessionStorage.getItem('data')){
        return (<Redirect to={ROUTES.Dashboard}/>)
        }
      
      const {username , password } = this.state
      const { classes, ...rest } = this.props;
      return(
        <div>
          <Header
          absolute
          color="transparent"
          brand="Voucherz"
          rightLinks={<HeaderLinks />}
          {...rest}
          />
          <div
          className={classes.pageHeader}
          style={{
            backgroundImage: "url(" + image + ")",
            backgroundSize: "cover",
            backgroundPosition: "top center"
            }}
          >
            <div className={classes.container}>
              <GridContainer justify="center">
                <GridItem xs={12} sm={12} md={4}>
                <Card className={classes[this.state.cardAnimaton]}>
                  <form className={classes.form} onSubmit={this.onSubmit}>
                    <CardHeader color="info" className={classes.cardHeader}>
                      <h3>Welcome Back</h3>
                      <h4>Sign into your account here</h4>
                    </CardHeader>
                    <CardBody>
                      <CustomInput
                        labelText="Email..."
                        name="username"
                        value={username}
                        onChange={this.onChange}
                        id="email"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "email"
                        }}
                      />
                      <CustomInput
                        labelText="Password"
                        name="password"
                        value={password}
                        onChange={this.onChange}
                        id="pass"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "password"
                        }}
                      />
                    </CardBody>
                    <div className={classes.button}>
                      <Button simple type="submit" color="info" id="submitBtn" size="lg">
                        Sign In
                      </Button>
                    </div>
                    <h4 className={classes.already}>Forgot Password? <a href="/signup">Recover</a></h4>
                  </form>
                </Card>
                </GridItem>
              </GridContainer>
            </div>
          </div>
        </div>
      );
    }
}


export default withStyles(loginPageStyle)(LoginPage);