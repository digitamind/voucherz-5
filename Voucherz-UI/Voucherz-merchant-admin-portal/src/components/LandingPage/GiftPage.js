import React, { Component } from "react";
import withStyles from "@material-ui/core/styles/withStyles";

import Header from "../../BasicComponents/Header/Header";
import Footer from "../../BasicComponents/Footer/Footer";
import GridContainer from "../../BasicComponents/Grid/GridContainer";
import GridItem from "../../BasicComponents/Grid/GridItem";
import HeaderLinks from "../../BasicComponents/Header/HeaderLinks";
import Parallax from "../../BasicComponents/Parallax/Parallax";
import landingPageStyle from "../../assets/styles/LandingPage";
import CardBody from "../../BasicComponents/Dashboard/Card/CardBody";
import Card from "../../BasicComponents/Dashboard/Card/Card";
import CardFooter from "../../BasicComponents/Dashboard/Card/CardFooter";
import Modal from "../../BasicComponents/Modal/Modal";
// import img from "../../assets/img/cardImage.jpg";

const dashboardRoutes = [];

class GiftPage extends Component {

  render() {
    const { classes, ...rest } = this.props;
    return (
      <div>
        <Header
          color="transparent"
          routes={dashboardRoutes}
          brand="Voucherz"
          rightLinks={<HeaderLinks />}
          fixed
          changeColorOnScroll={{
            height: 400,
            color: "white"
          }}
          {...rest}
        />
        <Parallax filter image={require("../../assets/img/landing-bg.jpg")}>
          {/* <div className={classes.container}>
            <GridContainer>
              <GridItem xs={12} sm={12} md={6}>
                <h1 className={classes.title}>Promotions made easy</h1>
                <h4>
                  You can grow as far as they let you! Easily reward, motivate and show appreciation to your Employees, Customers and Business Partners.
                </h4>
                <br />
                <Link to={ROUTES.SIGN_UP} id= "signup" className={classes.removeLink}>
                <Button
                  color="danger"
                  size="lg"
                  href=""
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  Register Now
                </Button>
                </Link>
              </GridItem>
            </GridContainer>
          </div> */}
        </Parallax>
        <div className={classes.container}>
        <GridContainer>
            <GridItem xs={12} sm={12} md={4}>
              <Card>
                <CardBody>
                  <h4 style={{textAlign: "center"}}>Voucherz Gift Card</h4>
                  <p style={{textAlign: "center"}}>
                    Get gift card for your friends
                  </p>
                </CardBody>
                <CardFooter>
                    <Modal/>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={4}>
              <Card>
                <CardBody>
                <h4 className={classes.cardTitle} style={{textAlign: "center"}}>Voucherz Gift Card</h4>
                  <p className={classes.cardCategory} style={{textAlign: "center"}}>
                    Get gift card for your friends
                  </p>
                </CardBody>
                <CardFooter>
                  <Modal/>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={4}>
              <Card>
                <CardBody>
                <h4 className={classes.cardTitle} style={{textAlign: "center"}}>Voucherz Gift Card</h4>
                  <p className={classes.cardCategory} style={{textAlign: "center"}}>
                    Get gift card for your friends
                  </p>
                </CardBody>
                <CardFooter>
                  <Modal/>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={4}>
              <Card>
                <CardBody>
                <h4 className={classes.cardTitle} style={{textAlign: "center"}}>Voucherz Gift Card</h4>
                  <p className={classes.cardCategory} style={{textAlign: "center"}}>
                    Get gift card for your friends
                  </p>
                </CardBody>
                <CardFooter>
                  <Modal/>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={4}>
              <Card>
                <CardBody>
                <h4 className={classes.cardTitle} style={{textAlign: "center"}}>Voucherz Gift Card</h4>
                  <p className={classes.cardCategory} style={{textAlign: "center"}}>
                    Get gift card for your friends
                  </p>
                </CardBody>
                <CardFooter>
                  <Modal/>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={4}>
              <Card>
                <CardBody>
                  {/* <img src={img} alt="hey"/> */}
                <h4 className={classes.cardTitle} style={{textAlign: "center"}}>Voucherz Gift Card</h4>
                  <p className={classes.cardCategory} style={{textAlign: "center"}}>
                    Get gift card for your friends
                  </p>
                </CardBody>
                <CardFooter>
                  <Modal/>
                </CardFooter>
              </Card>
            </GridItem>
          </GridContainer>
        </div>
        <Footer />
      </div>
    );
  }
}

export default withStyles(landingPageStyle)(GiftPage);
