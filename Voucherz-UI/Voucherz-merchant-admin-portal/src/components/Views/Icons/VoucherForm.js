import React, {Component} from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
// import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';
import GridItem from "../../../BasicComponents/Dashboard/Grid/GridItem";
import GridContainer from "../../../BasicComponents/Dashboard/Grid/GridContainer";
import IconStyle from "../../../assets/styles/Dashboard/VoucherFormStyle";
import { Grid } from '@material-ui/core';


const charSet = [
  {
    value: null,
    label: 'None',
    holder: null
  },
  {
    value: 'Alphabet',
    label: 'Alphabet',
    holder: 'abcdefg'
  },
  {
    value: 'Number',
    label: 'Number',
    holder: '1234567890'
  },
  {
    value: 'Alpha-Numeric',
    label: 'Alpha-Numeric',
    holder: "1a2b3c4d5e6f"
  },
];



class Icons extends Component {
  constructor(props){
    super(props);
    this.state = {
      length: "",
      charset: "",
  };
}
  

  handleChange = name => event => {
    this.setState({
      [name]: event.target.value,
    });
    console.log(name)
  }
  

  render() {
    const { classes } = this.props;

    return (
      
      <div>
        <form className={classes.container} Validate autoComplete="off">
          <Grid container spacing={24} justify="center">
            <GridContainer>
              <GridItem xs={12} sm={12} md={4}>
                <TextField
                  id="charset"
                  select
                  label="Charset"
                  className={classes.textField}
                  value={this.state.charset}
                  onChange={this.handleChange("charset")}
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
                    <option key={option.value} value={option.holder}>
                      {option.label}
                    </option>
                  ))}
                </TextField>
              </GridItem>
              <GridItem xs={12} sm={12} md={4}>
                <TextField
                disabled
                id="disabled"
                label="Disabled"
                value={this.state.charset}
                className={classes.textField}
                margin="normal"
                variant="filled"
                />
              </GridItem>
              <GridItem xs={12} sm={12} md={4}>
                <TextField
                id="length"
                label="Length"
                type="number"
                value={this.state.length}
                onChange={this.handleChange("length")}
                inputType="number"
                className={classNames(classes.textField, classes.dense)}
                margin="dense"
                variant="filled"
                />
              </GridItem>
              </GridContainer>
              <GridContainer>
              <GridItem xs={12} sm={12} md={4}>
                <TextField
                id="prefix"
                label="Prefix"
                value={this.state.prefix}
                onChange={this.handleChange("prefix")}
                className={classNames(classes.textField, classes.dense)}
                margin="dense"
                variant="filled"
                />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                  <TextField
                  id="pattern"
                  label="Pattern"
                  value={this.state.pattern}
                  onChange={this.handleChange("pattern")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                  <TextField
                  id="postfix"
                  label="Postfix"
                  value={this.state.postfix}
                  onChange={this.handleChange("postfix")}
                  className={classNames(classes.textField, classes.dense)}
                  margin="dense"
                  variant="filled"
                  />
                </GridItem>              
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                  <TextField
                    id="startdate"
                    label=""
                    type="date"
                    value={this.state.startDate}
                    onChange={this.handleChange("startdate")}
                    className={classNames(classes.textField, classes.dense)}
                    margin="dense"
                    variant="filled"
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <TextField
                    id="enddate"
                    label=""
                    type="date"
                    value={this.state.endDate}
                    onChange={this.handleChange("endDate")}
                    className={classNames(classes.textField, classes.dense)}
                    margin="dense"
                    variant="filled"
                  />
                </GridItem>
              </GridContainer>
          </Grid>
        </form>
      </div>
    );
  }
}

Icons.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(IconStyle)(Icons);

