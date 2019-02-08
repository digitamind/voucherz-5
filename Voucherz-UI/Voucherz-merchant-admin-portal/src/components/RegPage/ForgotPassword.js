import React, { Component } from "react";
import withStyles from "@material-ui/core/styles/withStyles";
import Header from "../../BasicComponents/Header/Header";
import HeaderLinks from "../../BasicComponents/Header/HeaderLinks";
import GridContainer from "../../BasicComponents/Grid/GridContainer";
import GridItem from "../../BasicComponents/Grid/GridItem";
import Card from "../../BasicComponents/Card/Card";
import CardBody from "../../BasicComponents/Card/CardBody";
import CardHeader from "../../BasicComponents/Card/CardHeader";
import Button from "../../BasicComponents/CustomButtons/Button";

import loginPageStyle from "../../assets/styles/loginPage";
import image from "../../assets/img/bg7.jpg";
import axios from "axios";
import CustomInput from "../../BasicComponents/CustomInput/CustomInput";
import { APIBASEURL } from "../Routes/BaseURL";


class ForgotPassword extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cardAnimaton: "cardHidden",
      username: ""
    };
  }
  componentDidMount() {
    setTimeout(
      function() {
        this.setState({ cardAnimaton: ""});
      }.bind(this),
      700
    );
  }

  onSubmit = (e) => {
    e.preventDefault();
    const{ username} = this.state;
    let emailData = {username}
    axios.post(`${APIBASEURL}/merchant-management/merchants/forgot-password`, emailData, {data: JSON.stringify(emailData)})
    .then((res)=>{
      console.log(res.data);
      if(res.status === 200){
        alert("Check your email for password reset Link")
      }
    })
    .catch(respose => {
    
    })
  }

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value })
    };

  render() {
    const {username} = this.state
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
                  <CardHeader color="info" className={classes.cardHeaderFPass}>
                    Retrive your Password
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
                  </CardBody>
                  <div className={classes.button}>
                      <Button simple type="submit" color="info" size="lg">
                        Submit
                      </Button>
                    </div>
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


export default withStyles(loginPageStyle)(ForgotPassword);
