import React, {Component} from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import GridItem from "../../../BasicComponents/Dashboard/Grid/GridItem";
import VoucherFormStyle from "../../../assets/styles/Dashboard/VoucherFormStyle";
import Button from "../../../BasicComponents/Dashboard/CustomButtons/Button"


const voucherTypes = [
    {
      value: null,
      label: 'None',
      holder: null
    },
    {
      value: 3,
      label: 'Value',
    },
    {
      value: 1,
      label: 'Gift',
    },
    {
      value: 2,
      label: 'Discount',
    },
  ];

class CampaignForm extends Component {

  render() {
    const { classes } = this.props;

    return (
      
      <div>
        <form className={classes.container} autoComplete="off" onSubmit={this.props.handleSubmitCampaign}>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                  id="voucherType"
                  select
                  className={classes.textField}
                  helperText="Select your Voucher Type"
                  value={this.props.voucherType}
                  onChange={this.props.handleChange("voucherType")}
                  SelectProps={{
                    native: true,
                    MenuProps: {
                      className: classes.menu,
                    },
                  }}
                  margin="normal"
                  variant="filled"
                >
                  {voucherTypes.map(option => (
                    <option key={option.value} value={option.value}>
                      {option.label}
                    </option>
                  ))}
                </TextField>
              </GridItem>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                required
                id="name"
                label="Name"
                value={this.props.campaignName}
                onChange={this.props.handleChange("campaignName")}
                helperText="Give name to your Campaign"
                className={classNames(classes.textField, classes.dense)}
                margin="dense"
                variant="filled"
                />
              </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  <TextField
                    id="startdate"
                    type="date"
                    value={this.props.startDate}
                    helperText="Select start date"
                    onChange={this.props.handleChange("startDate")}
                    className={classNames(classes.textField, classes.dense)}
                    margin="dense"
                    variant="filled"
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  <TextField
                    id="expirationDate"
                    type="date"
                    value={this.props.expirationDate}
                    helperText="Select expiry date"
                    onChange={this.props.handleChange("expirationDate")}
                    className={classNames(classes.textField, classes.dense)}
                    margin="dense"
                    variant="filled"
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <TextField
                    id="standard-full-width"
                    label="Description"
                    style={{margin:8}}
                    value={this.props.description}
                    helperText="Description"
                    onChange={this.props.handleChange("description")}
                    className={classNames(classes.textField, classes.dense)}
                    fullWidth
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  {/* <Button color="info" size="lg" component="label"> */}
                    <TextField type="file" accept=".csv" 
                      helperText="Upload your Customer List" label="CSV"
                    />
                  {/* </Button> */}
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <Button type="submit" id="submitBtnC" color="info" size="lg">
                    Submit
                  </Button>
                </GridItem>
        </form>
      </div>
    );
  }
}

CampaignForm.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(VoucherFormStyle)(CampaignForm);

