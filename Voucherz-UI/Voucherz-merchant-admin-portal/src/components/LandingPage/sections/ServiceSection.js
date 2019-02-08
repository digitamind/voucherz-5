import React, { Component } from "react";
import classNames from "classnames";
import withStyles from "@material-ui/core/styles/withStyles";

import GridContainer from "../../../BasicComponents/Grid/GridContainer";
import GridItem from "../../../BasicComponents/Grid/GridItem";
import Button from "../../../BasicComponents/CustomButtons/Button";
import Card from "../../../BasicComponents/Card/Card";
import CardBody from "../../../BasicComponents/Card/CardBody";
import CardFooter from "../../../BasicComponents/Card/CardFooter";

import teamStyle from "../../../assets/styles/LandingPageSection/TeamStyle";

import team1 from "../../../assets/img/employeeReward.jpg";
import team2 from "../../../assets/img/customerReward.png";
// import team3 from "../../../assets/img/kendall.jpg";

class TeamSection extends Component {
    render() {
        const { classes } = this.props;
        const imageClasses = classNames(
        classes.imgRaised,
        classes.imgRoundedCircle,
        classes.imgFluid
        );
        return (
        <div className={classes.section}>
            <h2 className={classes.title}>Our Corporate Solutions</h2>
            <div>
            <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                <Card plain>
                    <GridItem xs={12} sm={12} md={6} className={classes.itemGrid}>
                    <img src={team1} alt="..." className={imageClasses} />
                    </GridItem>
                    <h4 className={classes.cardTitle}>
                    EMPLOYEE REWARDS
                    </h4>
                    <CardBody>
                    <p className={classes.description}>
                    Our personalized gift cards are designed to help organizations. Increase overall employee performance, Boost employee morale and improve wellness.
                    Drive optimal productivity, Create atmosphere for healthy rivalry
                    </p>
                    </CardBody>
                    <CardFooter className={classes.justifyCenter}>
                    <Button
                        color="info"
                        size="lg"
                        href=""
                        target="_self"
                        rel="noopener noreferrer"
                    >
                    <i className="fas fa-play" />Learn More
                    </Button>
                    </CardFooter>
                </Card>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                <Card plain>
                    <GridItem xs={12} sm={12} md={6} className={classes.itemGrid}>
                    <img src={team2} alt="..." className={imageClasses} />
                    </GridItem>
                    <h4 className={classes.cardTitle}>
                    CUSTOMER REWARDS
                    </h4>
                    <CardBody>
                    <p className={classes.description}>
                    With well thought message to fit every occasion, our unique gift cards help organizations. Enhance consumers loyalty, Reinforce brand retention.
                    Appreciate existing customers and recruit new brand advocates.
                    </p>
                    </CardBody>
                    <CardFooter className={classes.justifyCenter}>
                    <Button
                        color="info"
                        size="lg"
                        href=""
                        target="_self"
                        rel="noopener noreferrer"
                    >
                        <i className="fas fa-play" />Learn More
                    </Button>
                    </CardFooter>
                </Card>
                </GridItem>
                {/* <GridItem xs={12} sm={12} md={4}>
                <Card plain>
                    <GridItem xs={12} sm={12} md={6} className={classes.itemGrid}>
                    <img src={team3} alt="..." className={imageClasses} />
                    </GridItem>
                    <h4 className={classes.cardTitle}>
                    Kendall Jenner
                    <br />
                    <small className={classes.smallTitle}>Model</small>
                    </h4>
                    <CardBody>
                    <p className={classes.description}>
                        You can write here details about one of your team members.
                        You can give more details about what they do. Feel free to
                        add some <a href="#pablo">links</a> for people to be able to
                        follow them outside the site.
                    </p>
                    </CardBody>
                    <CardFooter className={classes.justifyCenter}>
                    <Button
                        justIcon
                        color="transparent"
                        className={classes.margin5}
                    >
                        <i className={classes.socials + " fab fa-twitter"} />
                    </Button>
                    <Button
                        justIcon
                        color="transparent"
                        className={classes.margin5}
                    >
                        <i className={classes.socials + " fab fa-instagram"} />
                    </Button>
                    <Button
                        justIcon
                        color="transparent"
                        className={classes.margin5}
                    >
                        <i className={classes.socials + " fab fa-facebook"} />
                    </Button>
                    </CardFooter>
                </Card>
                </GridItem> */}
            </GridContainer>
            </div>
        </div>
        );
    }
}

export default withStyles(teamStyle)(TeamSection);
