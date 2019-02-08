import React, {Component} from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import GridItem from "../../../BasicComponents/Dashboard/Grid/GridItem";
import VoucherFormStyle from "../../../assets/styles/Dashboard/VoucherFormStyle";
import Button from "../../../BasicComponents/Dashboard/CustomButtons/Button"

const charSet = [
  {
    value: null,
    label: 'None',
    holder: null
  },
  {
    value: 0,
    label: 'Alphabet',
    holder: 'abcdefg'
  },
  {
    value: 2,
    label: 'Numeric',
    holder: '1234567890'
  },
  {
    value: 1,
    label: 'Alpha-Numeric',
    holder: "1a2b3c4d5e6f"
  },
  {
    value: 3,
    label: 'Custom Value',
    holder: "Type your charset"
  }
];

const discountTypes = [
    {
      value: null,
      label: 'None',
      holder: null
    },
    {
      value: 1,
      label: 'Percentage',
    },
    {
      value: 2,
      label: 'Unit',
    },
    {
      value: 0,
      label: 'Amount',
    },
  ];


class DiscountForm extends Component {
  

  render() {
    const { classes } = this.props;

    return (
      
      <div>
        <form className={classes.container} autoComplete="off" onSubmit={this.props.handleSubmitVoucherDiscount}>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                  id="charset"
                  select
                  className={classes.textField}
                  helperText="Select code charset"
                  value={this.props.charset}
                  onChange={this.props.handleChange("charset")}
                  SelectProps={{
                    native: true,
                    MenuProps: {
                      className: classes.menu,
                    },
                  }}
                  margin="normal"
                  variant="filled"
                >
                  {charSet.map(option => (
                    <option key={option.value} value={option.value}>
                      {option.label}
                    </option>
                  ))}
                </TextField>
              </GridItem>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                disabled
                id="disabled"
                label="Disabled"
                value={this.props.charset}
                helperText="Sample charset"
                className={classes.textField}
                margin="normal"
                variant="filled"
                />
              </GridItem>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                  id="DiscountType"
                  select
                  className={classes.textField}
                  helperText="Select Discount Voucher Type"
                  value={this.props.discountType}
                  onChange={this.props.handleChange("discountType")}
                  SelectProps={{
                    native: true,
                    MenuProps: {
                      className: classes.menu,
                    },
                  }}
                  margin="normal"
                  variant="filled"
                >
                  {discountTypes.map(option => (
                    <option key={option.value} value={option.value}>
                      {option.label}
                    </option>
                  ))}
                </TextField>
              </GridItem>
              <GridItem xs={12} sm={12} md={3}>
                  <TextField
                  required
                  id="Amount"
                  type="number"
                  label="Voucher Amount"
                  disabled={(this.props.discountType === "1" || this.props.discountType === "2") ? true : false}
                  value={this.props.amount}
                  helperText="Enter Voucher Discount in Amount"
                  onChange={this.props.handleChange("amount")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
            </GridItem>
            <GridItem xs={12} sm={12} md={3}>
                  <TextField
                  required
                  id="percentage"
                  type="number"
                  label="Voucher Percentage"
                  disabled={(this.props.discountType === "0" || this.props.discountType === "2") ? true : false}
                  value={this.props.percentage}
                  helperText="Enter Voucher Discount in Percentage"
                  onChange={this.props.handleChange("percentage")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
            </GridItem>
            <GridItem xs={12} sm={12} md={3}>
                  <TextField
                  required
                  id="Unit"
                  type="number"
                  label="Unit"
                  disabled={(this.props.discountType === "0" || this.props.discountType === "1") ? true : false}
                  value={this.props.unit}
                  helperText="Enter Voucher Discount in Unit"
                  onChange={this.props.handleChange("unit")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
              </GridItem>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                required
                id="length"
                label="Length"
                type="number"
                value={this.props.length}
                onChange={this.props.handleChange("length")}
                helperText="Input the length of code"
                className={classNames(classes.textField, classes.dense)}
                margin="dense"
                variant="filled"
                />
              </GridItem>
              <GridItem xs={12} sm={12} md={3}>
                <TextField
                id="prefix"
                label="Prefix"
                value={this.props.prefix}
                helperText="Enter code prefix"
                onChange={this.props.handleChange("prefix")}
                className={classNames(classes.textField, classes.dense)}
                margin="dense"
                variant="filled"
                />
                </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  <TextField
                  required
                  id="pattern"
                  label="Pattern"
                  value={this.props.pattern}
                  helperText="Enter code pattern ###-###"
                  onChange={this.props.handleChange("pattern")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  <TextField
                  id="postfix"
                  label="Postfix"
                  value={this.props.postfix}
                  helperText="Enter code postfix"
                  onChange={this.props.handleChange("postfix")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
                </GridItem>
                {/* <GridItem xs={12} sm={12} md={3}>
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
                    id="enddate"
                    type="date"
                    value={this.props.endDate}
                    helperText="Select expiry date"
                    onChange={this.props.handleChange("endDate")}
                    className={classNames(classes.textField, classes.dense)}
                    margin="dense"
                    variant="filled"
                  />
                </GridItem> */}
                <GridItem xs={12} sm={12} md={3}>
                  <TextField
                    required
                    id="voucherNo"
                    label="voucherNo"
                    type="number"
                    value={this.props.voucherNo}
                    helperText="Numbers of vouchers"
                    onChange={this.props.handleChange("voucherNo")}
                    className={classNames(classes.textField, classes.dense)}
                    margin="dense"
                    variant="filled"
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  <TextField
                  id="code"
                  label="Code"
                  value={this.props.code}
                  helperText="Generate code postfix"
                  onChange={this.props.handleChange("code")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
                  <GridItem xs={12} sm={12} md={3}>
                  <Button onClick={this.props.handleGenerate} color="info" size="lg" >
                    Generate
                  </Button>
                </GridItem>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <TextField
                    id="standard-full-width"
                    label="Additional Description"
                    style={{margin:8}}
                    value={this.props.addDesc}
                    helperText="Additional description"
                    onChange={this.props.handleChange("addDesc")}
                    className={classNames(classes.textField, classes.dense)}
                    fullWidth
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <Button type="submit" id="submitBtn" color="info" size="lg">
                    Submit
                  </Button>
                </GridItem>
        </form>
      </div>
    );
  }
}

DiscountForm.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(VoucherFormStyle)(DiscountForm);

