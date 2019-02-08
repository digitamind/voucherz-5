import {
    drawerWidth,
    transition,
    container
  } from "../../ColorKit";
  
  const appStyle = theme => ({
    wrapper: {
      position: "relative",
      top: "0",
      height: "100vh"
    },
    mainPanel: {
      [theme.breakpoints.up("md")]: {
        width: `calc(100% - ${drawerWidth}px)`
      },
      overflow: "auto",
      position: "relative",
      float: "right",
      ...transition,
      maxHeight: "100%",
      width: "100%",
      overflowScrolling: "touch"
    },
    content: {
      marginTop: "70px",
      padding: "30px 15px",
      minHeight: "calc(100vh - 123px)"
    },
    container,
    map: {
      marginTop: "70px"
    },
    cardHeaderAlign:{
      display: "flex",
      justifyContent:"space-between",
      width: "96%",
    },
    iconAlign:{
      display: "flex",
      justifyContent: "flex-end",
      marginRight: "50px"
    },
    sectionStyle:{
      display: "flex",
      justifyContent:"center"
    },
    textCenter:{
      textAlign: "center",
      textDecoration: "none"
    },
    btnCenter:{
      textDecoration: "none",
      margin: "0 auto",
      display: "block"
    }
  });
  
  export default appStyle;