import React, { Component } from "react";
import { Link } from "react-router-dom";
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
import * as ROUTES  from "../Routes";
import axios from "axios";
import { APIBASEURL } from "../Routes/BaseURL";


const userState = {
  companySize: "",
  companyName: "",
  firstName: "",
  lastName: "",
  email: "",
  password: "",
  password2: ""
}

class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cardAnimaton: "cardHidden",
      ...userState
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
    const{ companySize, companyName, firstName, lastName, email, password} = this.state;
    let userData = {companySize, companyName, firstName, lastName, email, password}
    axios.post(`${APIBASEURL}/merchant-management/merchants/signup`, userData)
    .then((res)=>{
      console.log(res);
      if(res.data.code === 201){
        alert("Check your email for activation Link")
        document.getElementById("submitBtn").innerHTML = "Sign up"
        this.props.history.push(ROUTES.SIGN_IN)
      }
      else if(res.data.code === 409){
        alert("Email address already exist");
        document.getElementById("submitBtn").innerHTML = "Sign up"
      } 
    })
    .catch((error) => {
      document.getElementById("submitBtn").innerHTML = "Sign up"
      alert("Sign Up Failed")
    })
  }

  onChange = e => {
      this.setState({ [e.target.name]: e.target.value })
  };

  render() {
    const {
        companyName,
        firstName,
        companySize,
        lastName,
        email,
        password,
        password2
    } = this.state 
    
    const isInvalid = password !== password2 || email === "" || lastName === "" || firstName === "";

    const { classes, ...rest } = this.props;
    return (
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
                      <h4>Register your Company</h4>
                    </CardHeader>
                    <CardBody>
                    <CustomInput
                        labelText="Company Name"
                        name="companyName"
                        value={companyName}
                        onChange={this.onChange}
                        id="companyName"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "text"
                        }}
                      />
                      <CustomInput
                        labelText="Company Size"
                        name="companySize"
                        value={companySize}
                        onChange={this.onChange}
                        id="companySize"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "number"
                        }}
                      />
                      <CustomInput
                        labelText="First Name..."
                        name="firstName"
                        value={firstName}
                        onChange={this.onChange}
                        id="firstName"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "text"
                        }}
                      />
                      <CustomInput
                        labelText="Last Name..."
                        name="lastName"
                        value={lastName}
                        onChange={this.onChange}
                        id="lastName"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "text"
                        }}
                      />
                      <CustomInput
                        labelText="Email..."
                        name="email"
                        value={email}
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
                        id="passOne"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "password"
                        }}
                      />
                      <CustomInput
                        labelText="Password2"
                        name="password2"
                        value={password2}
                        onChange={this.onChange}
                        id="pass2"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "password"
                        }}
                      />
                    </CardBody>
                    <div className={classes.button}>
                      <Button simple disabled={isInvalid} type="submit" id="submitBtn" color="info" size="lg">
                        SignUp
                      </Button>
                    </div>
                    <h4 className={classes.already}>Already have an Account? <Link to={ROUTES.SIGN_IN}>Login</Link></h4>
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


export default withStyles(loginPageStyle)(SignUp);
