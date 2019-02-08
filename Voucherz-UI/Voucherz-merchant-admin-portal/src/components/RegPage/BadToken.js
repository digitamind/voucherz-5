import React, { Component } from "react";
import withStyles from "@material-ui/core/styles/withStyles";
import Header from "../../BasicComponents/Header/Header";
import HeaderLinks from "../../BasicComponents/Header/HeaderLinks";
import GridContainer from "../../BasicComponents/Grid/GridContainer";
import GridItem from "../../BasicComponents/Grid/GridItem";
import Card from "../../BasicComponents/Card/Card";
import CardBody from "../../BasicComponents/Card/CardBody";
import CardHeader from "../../BasicComponents/Card/CardHeader";
import loginPageStyle from "../../assets/styles/loginPage";
import image from "../../assets/img/bg7.jpg";



class BadToken extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cardAnimaton: "cardHidden",
      confirmimg: false
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

 
  render() {

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
                  <CardHeader color="info" className={classes.cardHeaderConfirm}>
                    Token Error
                  </CardHeader>
                  <CardBody>
                    <h4 className={classes.cardHeaderConfirm}>Token does not exist</h4>
                  </CardBody>
                </Card>
              </GridItem>
            </GridContainer>
          </div>
        </div>
      </div>
    );
  }
}


export default withStyles(loginPageStyle)(BadToken);
