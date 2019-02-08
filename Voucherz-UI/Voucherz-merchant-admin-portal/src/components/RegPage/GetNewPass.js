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


class GetNewPass extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cardAnimaton: "cardHidden",
      confirmPassword: "",
      newPassword: ""
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

  postPasswordChange = () => {
      const { id } = this.props.match.params
    //   const tokenId = id
      const { confirmPassword, newPassword } = this.state
      const passwordData = {confirmPassword, newPassword}
    axios.post(`${APIBASEURL}/merchant-management/merchants/password-reset?token=${id}`, passwordData, {data: JSON.stringify(passwordData)})
    .then((res)=>{
      if(res.status === 200){
        alert("Password changed Succesfully")
      }
          })
    .then(data=>{
    })
    .catch(err=>{
        console.log(err)
    })
  }

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value })
    };

  render() {
    const {confirmPassword, newPassword} = this.state
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
                  <CardHeader color="info" className={classes.cardHeaderFPass}>
                    Retrieve your Password
                  </CardHeader>
                  <CardBody>
                      <CustomInput
                        labelText="New Password"
                        name="newPassword"
                        value={newPassword}
                        onChange={this.onChange}
                        id="pass"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "password"
                        }}
                      />
                      <CustomInput
                        labelText="Confirm Password"
                        name="confirmPassword"
                        value={confirmPassword}
                        onChange={this.onChange}
                        id="password"
                        formControlProps={{
                          fullWidth: true
                        }}
                        inputProps={{
                          type: "password"
                        }}
                      />
                  </CardBody>
                  <div className={classes.button}>
                      <Button simple type="submit" color="info" size="lg">
                        Submit
                      </Button>
                    </div>
                </Card>
              </GridItem>
            </GridContainer>
          </div>
        </div>
      </div>
    );
  }
}


export default withStyles(loginPageStyle)(GetNewPass);
