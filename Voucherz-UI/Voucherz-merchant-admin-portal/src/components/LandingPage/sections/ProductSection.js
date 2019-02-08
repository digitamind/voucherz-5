import React, {Component} from "react";
import withStyles from "@material-ui/core/styles/withStyles";

import Chat from "@material-ui/icons/Chat";
import VerifiedUser from "@material-ui/icons/VerifiedUser";
import Fingerprint from "@material-ui/icons/Fingerprint";
import GridContainer from "../../../BasicComponents/Grid/GridContainer";
import GridItem from "../../../BasicComponents/Grid/GridItem";
import InfoArea from "../../../BasicComponents/InfoArea/InfoArea";

import productStyle from "../../../assets/styles/LandingPageSection/ProductStyle";

class ProductSection extends Component {
    render() {
        const { classes } = this.props;
        return (
        <div className={classes.section}>
            <GridContainer justify="center">
            <GridItem xs={12} sm={12} md={8}>
                <h2 className={classes.title}>Our Products</h2>
                <h5 className={classes.description}>
                    Voucherify empowers marketers to enhance customer acquisition,
                    conversion, and retention campaigns with personalized incentives
                </h5>
            </GridItem>
            </GridContainer>
            <div>
            <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                <InfoArea
                    title="Digital Gift Cards"
                    description="Order on-demand and we will deliver instantly via Email, SMS or Print. No more inventory risk."
                    icon={Chat}
                    iconColor="info"
                    vertical
                />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                <InfoArea
                    title="Personalized Coupons"
                    description="Our platform gives you ability to create coupon based on your need and custom format"
                    icon={VerifiedUser}
                    iconColor="success"
                    vertical
                />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                <InfoArea
                    title="Discount Promotion"
                    description="Auto-apply personalized discounts based on CRM data and shopping cart structure."
                    icon={Fingerprint}
                    iconColor="danger"
                    vertical
                />
                </GridItem>
            </GridContainer>
            </div>
        </div>
        );
    }
}

export default withStyles(productStyle)(ProductSection);
