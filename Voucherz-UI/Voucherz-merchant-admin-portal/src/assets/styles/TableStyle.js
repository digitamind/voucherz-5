import {
    warningColor,
    primaryColor,
    dangerColor,
    successColor,
    infoColor,
    roseColor,
    grayColor,
    defaultFont
  } from "../ColorKit";
  
  const tableStyle = theme => ({
    warningTableHeader: {
      color: warningColor
    },
    primaryTableHeader: {
      color: primaryColor
    },
    dangerTableHeader: {
      color: dangerColor
    },
    successTableHeader: {
      color: successColor
    },
    infoTableHeader: {
      color: infoColor
    },
    roseTableHeader: {
      color: roseColor
    },
    grayTableHeader: {
      color: grayColor
    },
    table: {
      marginBottom: "0",
      width: "100%",
      maxWidth: "100%",
      backgroundColor: "transparent",
      borderSpacing: "0",
      borderCollapse: "collapse"
    },
    tableHeadCell: {
      color: "inherit",
      ...defaultFont,
      fontSize: "1em"
    },
    tableCell: {
      ...defaultFont,
      lineHeight: "1.42857143",
      padding: "12px 8px",
      verticalAlign: "middle"
    },
    tableResponsive: {
      width: "100%",
      marginTop: theme.spacing.unit * 3,
      overflowX: "auto"
    },
    cardCategoryWhite: {
      "&,& a,& a:hover,& a:focus": {
        color: "rgba(255,255,255,.62)",
        margin: "0",
        fontSize: "14px",
        marginTop: "0",
        marginBottom: "0"
      },
      "& a,& a:hover,& a:focus": {
        color: "#FFFFFF"
      }
    },
    cardTitleWhite: {
      color: "#FFFFFF",
      marginTop: "0px",
      minHeight: "auto",
      fontWeight: "300",
      fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
      marginBottom: "3px",
      textDecoration: "none",
      "& small": {
        color: "#777",
        fontSize: "65%",
        fontWeight: "400",
        lineHeight: "1"
      }
    }
  });
  
  export default tableStyle;
  