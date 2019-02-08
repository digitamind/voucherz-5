import React from "react";
import { Link } from "react-router-dom";

import withStyles from "@material-ui/core/styles/withStyles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import * as ROUTES from "../../components/Routes/index"


import Button from "../CustomButtons/Button";

import headerLinksStyle from "../../assets/styles/HeaderLinksStyle";

function HeaderLinks({ ...props }) {
  const { classes } = props;
  return (
    <List className={classes.list}>
      <ListItem className={classes.listItem}>
        <Button
          color="transparent"
          target="_blank"
          rel="noopener noreferrer"
          className={classes.navLink}
        >
          Airtime
        </Button>
      </ListItem>
      <ListItem className={classes.listItem}>
        <Button
          color="transparent"
          target="_blank"
          rel="noopener noreferrer"
          className={classes.navLink}
        >
          <Link to={ROUTES.Gift} id= "login" className={classes.registerNavLink}>Gift Vouchers</Link>
        </Button>
      </ListItem>
      <ListItem className={classes.listItem}>
        <Button
          color="transparent"
          target="_self"
          rel="noopener noreferrer"
          className={classes.navLink}
        >
          <Link to={ROUTES.SIGN_IN} id= "login" className={classes.registerNavLink}>Login</Link>
        </Button>
      </ListItem>
    </List>
  );
}

export default withStyles(headerLinksStyle)(HeaderLinks);
