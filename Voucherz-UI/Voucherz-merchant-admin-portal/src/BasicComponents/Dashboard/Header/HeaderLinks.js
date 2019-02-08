import React, {Component} from "react";
import {withRouter} from "react-router-dom"
import withStyles from "@material-ui/core/styles/withStyles";

import headerLinksStyle from "../../../assets/styles/Dashboard/HeaderLinkStyle";
import Signout from "../../../components/RegPage/SignOut";

class HeaderLinks extends Component {

  state = {
    open: false
  };

  handleToggle = () => {
    this.setState(state => ({ open: !state.open }));
  };

  handleClose = event => {
    if (this.anchorEl.contains(event.target)) {
      return;
    }

    this.setState({ open: false });
  };
  
  handleClick = (props) => {
    this.props.history.push("/dashboard")
  };

  handleClickUser = (props) => {
    this.props.history.push("/user")
  };

  render() {
    return (
      <div>
        <Signout/>
      </div>
    );
  }
}

export default withRouter(withStyles(headerLinksStyle)(HeaderLinks));
