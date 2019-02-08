import React, { Component } from "react";
import classNames from "classnames";
import withStyles from "@material-ui/core/styles/withStyles";
import { Link } from "react-router-dom";

import Header from "../../BasicComponents/Header/Header";
import Footer from "../../BasicComponents/Footer/Footer";
import GridContainer from "../../BasicComponents/Grid/GridContainer";
import GridItem from "../../BasicComponents/Grid/GridItem";
import Button from "../../BasicComponents/CustomButtons/Button";
import HeaderLinks from "../../BasicComponents/Header/HeaderLinks";
import Parallax from "../../BasicComponents/Parallax/Parallax";
import landingPageStyle from "../../assets/styles/LandingPage";
import ProductSection from "./sections/ProductSection";
import ServiceSection from "./sections/ServiceSection";
import WorkSection from "./sections/WorkSection";
import * as ROUTES from "../Routes/index"

const dashboardRoutes = [];

class LandingPage extends Component {
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
          <div className={classes.container}>
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
          </div>
        </Parallax>
        <div className={classNames(classes.main, classes.mainRaised)}>
          <div className={classes.container}>
            <ProductSection />
            <ServiceSection />
            <WorkSection />
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default withStyles(landingPageStyle)(LandingPage);
