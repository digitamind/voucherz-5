import React, {Component} from "react";
import PropTypes from "prop-types";
import AddCircle from "@material-ui/icons/AddCircle";
import withStyles from "@material-ui/core/styles/withStyles";

import GridItem from "../../../BasicComponents/Dashboard/Grid/GridItem";
import GridContainer from "../../../BasicComponents/Dashboard/Grid/GridContainer";
import CustomTabs from "../../../BasicComponents/Dashboard/CustomTabs/CustomTabs";
import Card from "../../../BasicComponents/Dashboard/Card/Card";
import CardHeader from "../../../BasicComponents/Dashboard/Card/CardHeader";
import CardBody from "../../../BasicComponents/Dashboard/Card/CardBody";
import Button from "../../../BasicComponents/Dashboard/CustomButtons/Button";
import dashboardStyle from "../../../assets/styles/Layout/DashboardStyle";

class Voucherz extends Component {

  handleChangeVoucherBulk = e => {
    let targetCard = e.currentTarget.name
    console.log(targetCard)
    this.setState({voucherType: targetCard});
    this.props.history.push("/CreateVoucherBulk")
  }
  handleChangeVoucherStandalone = e => {
    let targetCard = e.currentTarget.name
    console.log(targetCard)
    this.setState({voucherType: targetCard});
    this.props.history.push("/CreateVoucherStandalone")
  }

  render() {
    const { classes } = this.props;
    return (
      // <BrowserRouter>
      <div>
        <GridContainer>
          <GridItem xs={12} sm={12} md={12}>
            <CustomTabs
              title="Voucherz:"
              headerColor="info"
              tabs={[
                {
                  tabName: "Bulk Codes",
                  tabContent: (
                    <GridContainer>
                      <GridItem xs={12} sm={12} md={12}>
                        <Card>
                          <CardHeader color="info">
                            <h3 className={classes.textCenter}>Bulk Codes</h3>
                          </CardHeader>
                            <CardBody>
                              <p className={classes.textCenter}>
                              Up to thousands of random discount codes designed for 
                              single use by a limited group of customers (e.g., “ACME-5P13R” 
                              gives $25 off for the first 3 purchases, new customers from Warsaw only)
                              </p>
                              {/* <Link to="/voucherz/CreateBulkVoucher"> */}
                                <Button
                                  color="info"
                                  size="lg"
                                  href=""
                                  data-view="bulk"
                                  name="bulk" 
                                  target="_blank"
                                  rel="noopener noreferrer"
                                  className={classes.btnCenter}
                                  onClick={this.handleChangeVoucherBulk}
                                  >
                                    <AddCircle/>
                                    Create Vouchers
                                </Button>
                              {/* </Link> */}
                            </CardBody>
                        </Card>
                      </GridItem>
                    </GridContainer>
                  )
                },
                {
                  tabName: "Standalone Code",
                  tabContent: (
                    <GridContainer>
                      <GridItem xs={12} sm={12} md={12}>
                        <Card>
                          <CardHeader color="info">
                            <h3 className={classes.textCenter}>Standalone Codes</h3>
                          </CardHeader>
                            <CardBody>
                              <p className={classes.textCenter}>
                              Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                              Suspendisse malesuada lacus ex, sit amet blandit leo lobortis eget. Lorem 
                              ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, 
                              sit amet blandit leo lobortis eget.
                              </p>
                                <Button
                                color="info"
                                size="lg"
                                href=""
                                target="_blank"
                                rel="noopener noreferrer"
                                id="standalone"
                                name="standalone"
                                data-view="standalone" 
                                className={classes.btnCenter}
                                onClick={this.handleChangeVoucherStandalone}
                                >
                                  <AddCircle/>
                                  Create Vouchers
                                </Button>
                            </CardBody>
                        </Card>
                      </GridItem>
                    </GridContainer>
                  )
                }
              ]}
            />
          </GridItem>
        </GridContainer>
        {/* <Switch>
        <Route exact path="/voucherz/CreateBulkVoucher" render={()=><CreateVouchers/>}/>
        </Switch> */}
      </div>
    );
  }
}

Voucherz.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(Voucherz);