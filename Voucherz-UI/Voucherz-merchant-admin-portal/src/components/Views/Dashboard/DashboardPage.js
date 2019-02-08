import React, {Component} from "react";
import PropTypes from "prop-types";

import withStyles from "@material-ui/core/styles/withStyles";
import AddCircle from "@material-ui/icons/AddCircle";
import AccessTime from "@material-ui/icons/AccessTime";
import Cloud from "@material-ui/icons/Cloud";

import GridItem from "../../../BasicComponents/Dashboard/Grid/GridItem";
import GridContainer from "../../../BasicComponents/Dashboard/Grid/GridContainer";
import Card from "../../../BasicComponents/Dashboard/Card/Card";
import CardHeader from "../../../BasicComponents/Dashboard/Card/CardHeader";
import CardBody from "../../../BasicComponents/Dashboard/Card/CardBody";
import CardFooter from "../../../BasicComponents/Dashboard/Card/CardFooter";
import BarExample from "./Chart/BarChart"

import dashboardStyle from "../../../assets/styles/Layout/DashboardStyle";
import { IconButton } from "@material-ui/core";
import PieExample from "./Chart/PieChart";
import DoughnutChart from "./Chart/DoughnutChart";


class Dashboard extends Component {
  state = {
    value: 0
  };
  handleChange = (event, value) => {
    this.setState({ value });
  };

  handleChangeIndex = index => {
    this.setState({ value: index });
  };

  handleClick = (props) => {
    this.props.history.push("/voucherz")
  };

  render() {
    const { classes } = this.props;
    return (
      <div>
          <GridContainer>
            <GridItem xs={12} sm={12} md={12}>
              <Card plain>
                <CardHeader plain color="info" className={classes.cardHeaderAlign}>
                  <h3>Dashboard</h3>
                  <div className={classes.iconAlign}>
                        <IconButton onClick={this.handleClick}>
                          <AddCircle/>
                        </IconButton>
                    <IconButton>
                      <Cloud/>
                    </IconButton>
                  </div>
                </CardHeader>
              </Card>
            </GridItem>
          </GridContainer>
          <GridContainer>
            <GridItem xs={12} sm={12} md={12}>
              <Card plain>
                <CardHeader plain color="info" className={classes.cardHeaderAlign}>
                  <div>
                    <h3>0</h3>
                    <h4>Voucher Created</h4>
                  </div>
                  <div>
                    <h3>0</h3>
                    <h4>Voucher Published</h4>
                  </div>
                  <div>
                    <h3>0</h3>
                    <h4>Message Sent</h4>
                  </div>
                  <div>
                    <h3>0</h3>
                    <h4>Voucher Redeemed</h4>
                  </div>
                </CardHeader>
              </Card>
            </GridItem>
          </GridContainer>
          <GridContainer>
            <GridItem xs={12} sm={12} md={6}>
              <Card chart>
                  <BarExample/>
                <CardBody style={{width:"80%"}}>
                  <h4 className={classes.cardTitle}>Total Redemption</h4>
                  <p className={classes.cardCategory}>
                    Last Campaign Performance
                  </p>
                </CardBody>
                <CardFooter chart>
                  <div className={classes.stats}>
                    <AccessTime /> updated 4 minutes ago
                  </div>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={6}>
              <Card chart>
                  <PieExample/>
                <CardBody>
                  <h4 className={classes.cardTitle}>Redeemed Vouchers by Type</h4>
                  <p className={classes.cardCategory}>
                    Last Campaign Performance
                  </p>
                </CardBody>
                <CardFooter chart>
                  <div className={classes.stats}>
                    <AccessTime /> campaign sent 2 days ago
                  </div>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={6}>
              <Card chart>
                <CardBody>
                  <h4 className={classes.cardTitle}>Most recent Redemption</h4>
                  <p className={classes.cardCategory}>
                    Last Campaign Performance
                  </p>
                </CardBody>
                <CardFooter chart>
                  <div className={classes.stats}>
                    <AccessTime /> campaign sent 2 days ago
                  </div>
                </CardFooter>
              </Card>
            </GridItem>
            <GridItem xs={12} sm={12} md={6}>
              <Card chart>
                  <DoughnutChart/>
                <CardBody>
                  <h4 className={classes.cardTitle}>Total Rollbacks</h4>
                  <p className={classes.cardCategory}>
                    Last Campaign Performance
                  </p>
                </CardBody>
                <CardFooter chart>
                  <div className={classes.stats}>
                    <AccessTime /> campaign sent 2 days ago
                  </div>
                </CardFooter>
              </Card>
            </GridItem>
          </GridContainer>
      </div>
    );
  }
}

Dashboard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(Dashboard);