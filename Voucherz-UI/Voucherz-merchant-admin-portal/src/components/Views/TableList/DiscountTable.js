import React, { Component } from "react";

import withStyles from "@material-ui/core/styles/withStyles";

import GridItem from "../../../BasicComponents/Grid/GridItem";
import GridContainer from "../../../BasicComponents/Grid/GridContainer";
import Table from "../../../BasicComponents/Table/Table";
import Card from "../../../BasicComponents/Card/Card";
import CardHeader from "../../../BasicComponents/Card/CardHeader";
import CardBody from "../../../BasicComponents/Card/CardBody";
import tableStyle from "../../../assets/styles/TableStyle";
import axios from "axios";
import Loader from "../Loader";
import {APIBASEURL} from "../../Routes/BaseURL"


class TableList extends Component {
    constructor(props){
      super(props);
      this.state = {
        isLoading: true,
        data: [],
        error: null
      }
    }
    
    
    componentDidMount(){
      this.fetchTableData();
    }

    fetchTableData=()=>{
        const token = sessionStorage.getItem("data");
          const headers = {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          }
        axios.get(`${APIBASEURL}/discount-voucher-service/discount-voucher`, {"headers": headers})
        .then(res => {
          const detail = res.data._embedded.discountVoucherList;
          console.log(detail)
          this.setState({data: detail})
        })
        .catch(error => {
          this.setState({ error, isLoading: false});
        })
      }

    render(){
      const arrTable = (this.state.data);
      console.log(arrTable);
      const tableHead = ["merchantId", "code", "value", "expiryDate", "isActive", "campaignId", "category"];
      const tableData = [];
      arrTable.map(item => {
        const filtered = Object.keys(item)
        .filter(key => 
          tableHead.includes(key))
        .reduce((obj, key) => {
          obj[key] = item[key];
          return obj;
        }, {}); tableData.push(filtered)
      })  
      console.log(tableData);   

    
    return (
      <GridContainer>
        { (<GridItem xs={12} sm={12} md={12}>
          <Card>
            {/* <CardHeader>
              <h4 style={{color: "blue"}}>Voucherz Table</h4>
              <p className={classes.cardCategoryWhite}>
                This table shows your Voucher List
              </p>
            </CardHeader> */}
            <CardBody>
            <h4 style={{color: "#00acc1", textAlign: "center"}}>Voucherz Table</h4>
              <Table
                tableHeaderColor="info"
                tableHead={tableHead}
                tableData={tableData}
              />
            </CardBody>
          </Card>
        </GridItem>)}
      </GridContainer>
    );
  }
}

export default withStyles(tableStyle)(TableList);
