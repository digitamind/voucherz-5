import React, {Component} from "react";
import PropTypes from "prop-types";
import withStyles from "@material-ui/core/styles/withStyles";
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import Cloud from "@material-ui/icons/Cloud";
import GridItem from "../../../BasicComponents/Dashboard/Grid/GridItem";
import GridContainer from "../../../BasicComponents/Dashboard/Grid/GridContainer";
import CustomTabs from "../../../BasicComponents/Dashboard/CustomTabs/CustomTabs";
import Card from "../../../BasicComponents/Dashboard/Card/Card";
import CardBody from "../../../BasicComponents/Dashboard/Card/CardBody";
import CardFooter from "../../../BasicComponents/Dashboard/Card/CardFooter";
import VoucherFormSA from "../VoucherForm/VoucherFormSA";
import DiscountFormSA from "../VoucherForm/DiscountFormSA";
import axios from "axios";
import dashboardStyle from "../../../assets/styles/Layout/DashboardStyle";
import CampaignForm from "../VoucherForm/CampaignForm";
import {APIBASEURL} from "../../Routes/BaseURL"
import GiftFormSA from "../VoucherForm/GiftFormSA";
var gen = require("./codeGen")
class Vouchers extends Component {
    constructor(props){
        super(props);
        this.state = {
          expanded: null,
          voucherNoType: "standalone",
          description: "",
          startDate: "",
          unit: "",
          discountType: "",
          category: null,
          voucherType: "",
          expirationDate: "",
          charset: "",
          customerId: "",
          customCharset: "",
          amount: "",
          percentage: "",
          length: "",
          prefix: "",
          pattern: "",
          postfix: "",
          campaignName: "",
          addDesc: "",
          voucherNo: "",
          toggle: true,
          campaignId: "",
          productId: 0,
          isCorporate: true,
          merchantStoreId: 0
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmitVoucherVal = this.handleSubmitVoucherVal.bind(this);
        this.handleSubmitCampaign = this.handleSubmitCampaign.bind(this);
        this.handleSubmitVouchergift = this.handleSubmitVouchergift.bind(this);
        this.handleSubmitVoucherDiscount= this.handleSubmitVoucherDiscount.bind(this);
    }
    

    //Generate Code from the Client Side
    handleGenerate = () => {
      const{charset, customCharset, prefix, postfix, pattern, length } = this.state;
      const codeConfig = {
        length,
        pattern,
        prefix,
        postfix,
        charset,
        customCharset
      };
      const codeGenerator = gen.generate(codeConfig)
      console.log(codeGenerator)
      this.setState({code: codeGenerator});
    }

    handleChangePanel = panel => (event, expanded) => {
        this.setState({
          expanded: expanded ? panel : false,
        });
      };

      handleChange = name => event => {
        this.setState({
          [name]: event.target.value,
        }, ()=>{console.log(this.state)});
      }
       
      //submit value voucher
      handleSubmitVoucherVal = (e) => {
        e.preventDefault();
        document.getElementById("submitBtn").innerHTML = "Processing..."
        const{ code, amount, startDate, expirationDate, toggle, isCorporate, campaignId, customerId, addDesc, productId } = this.state;
        const voucherData = {
            code, amount, dateCreated: startDate, expiryDate: expirationDate, isActive: toggle, isCorporate, campaignId, customerId, additionalInfo: addDesc, productId
        }
        const voucherDataJson = JSON.stringify(voucherData)
        const token = sessionStorage.getItem("data");
        const headers = {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
        axios.post(`${APIBASEURL}/value-voucher-service/value-voucher`, voucherDataJson, {"headers": headers})
        .then((res)=>{
          console.log(res);
          if(res.data.code === "CREATED"){
            alert("Standalone Value Voucher Created Succesfully")
            document.getElementById("submitBtn").innerHTML = "Submit"
          }
          else if(res.code === 409){
            alert(">>>>>>>>>>");
            document.getElementById("submitBtn").innerHTML = "Submit"
          } 
        })
        .catch((error) => {
          document.getElementById("submitBtn").innerHTML = "Submit"
          alert("Voucher Creation Failed")
        })
      }

      //Submit Gift Voucher
      handleSubmitVouchergift = (e) => {
        e.preventDefault();
        document.getElementById("submitBtn").innerHTML = "Processing..."
        const{ code, amount, startDate, expirationDate, toggle, category, isCorporate, campaignId, customerId, addDesc, productId, merchantStoreId } = this.state;
        const voucherData = {
            code, amount, dateCreated: startDate, expiryDate: expirationDate, merchantStoreId, category, isActive: toggle, isCorporate, campaignId, customerId, additionalInfo: addDesc, productId
        }
        const voucherDataJson = JSON.stringify(voucherData)
        const token = sessionStorage.getItem("data");
        const headers = {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
        axios.post(`${APIBASEURL}/gift-voucher-service/gift-voucher`, voucherDataJson, {"headers": headers})
        .then((res)=>{
          console.log(res);
          if(res.data.code === "CREATED"){
            alert("Standalone Gift Voucher Created Succesfully")
            document.getElementById("submitBtn").innerHTML = "Submit"
          }
          else if(res.code === 409){
            alert(">>>>>>>>>>");
            document.getElementById("submitBtn").innerHTML = "Submit"
          } 
        })
        .catch((error) => {
          document.getElementById("submitBtn").innerHTML = "Submit"
          alert("Voucher Creation Failed")
        })
      }
      
      //Submit Discount Voucher
      handleSubmitVoucherDiscount = (e) => {
        e.preventDefault();
        document.getElementById("submitBtn").innerHTML = "Processing..."
        const{ addDesc, discountType, category, expirationDate, charset, productId, customCharset, amount, length, prefix, isCorporate, toggle, customerId, pattern, postfix, campaignId, voucherNo } = this.state;
        const DiscountVoucherData = {
            discountVoucher:{
                type: discountType,
                value: amount,
                expiryDate: expirationDate,
                additionalInfo: addDesc,
                category,
                customerId,
                isActive: toggle,
                campaignId,
                productId,
                isCorporate
            },
            codeConfig:{
                length,
                pattern,
                customCharset,
                charsetType: charset,
                prefix,
                postfix
            },
            quantity: voucherNo
        }
        const DiscountVoucherDataJson = JSON.stringify(DiscountVoucherData)
        console.log(DiscountVoucherDataJson);
        const token = sessionStorage.getItem("data");
        const headers = {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
        axios.post(`${APIBASEURL}/discount-voucher-service/discount-voucher`, DiscountVoucherDataJson, {"headers": headers})
        .then((res)=>{
          console.log(res);
          if(res.data.status === 201){
            alert("Standalone Discount Voucher Created Succesfully")
            document.getElementById("submitBtn").innerHTML = "Submit"
          }
          else if(res.code === 409){
            alert(">>>>>>>>>>");
            document.getElementById("submitBtn").innerHTML = "Submit"
          } 
        })
        .catch((error) => {
          document.getElementById("submitBtn").innerHTML = "Submit"
          alert("Voucher Creation Failed")
        })
      }

    // Submit Campaign
    handleSubmitCampaign = (e) => {
        e.preventDefault();
        const token = sessionStorage.getItem("data");
        const headers = {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
        document.getElementById("submitBtnC").innerHTML = "Processing..."
        const{ campaignName, description, voucherType, startDate, expirationDate } = this.state;
        let campaignData = {name: campaignName, description, voucherType, startDate, expirationDate}
        axios.post(`${APIBASEURL}/merchant-management/campaigns`, campaignData, {"headers": headers})
        .then((res)=>{
          console.log(res);
          if(res.data.code === 201){
            alert("Campaign Created Succesfully")
            document.getElementById("submitBtnC").innerHTML = "Submit"
            this.setState({campaignId: res.data.codeDescription})
            console.log(this.state.campaignId)
          }
          else if(res.data.code === 409){
            alert("Campaign name already exist");
            document.getElementById("submitBtnC").innerHTML = "Submit"
          } 
        })
        .catch((error) => {
        
        })
      }

      
  render() {
    const { expanded } = this.state;

    return (
      <div>
        <GridContainer>
          <GridItem xs={12} sm={12} md={12}>
            <CustomTabs
              title="Campaign:"
              headerColor="info"
              tabs={[
                {
                  tabName: "Value Voucher",
                  tabContent: (<div>
                    <ExpansionPanel expanded={expanded === 'panel1'} onChange={this.handleChangePanel('panel1')}>
                        <ExpansionPanelSummary>
                            <GridItem xs={12} sm={12} md={12}>
                            <Card>
                                <CardBody>
                                <h3>Create Campaign</h3>
                                </CardBody>
                                <CardFooter>
                                <p>Define or generate a fixed code (e.g. voucherify4free) 
                                    for multiple use. Assign tags with custom categories for further maintenance and.
                                </p>
                                </CardFooter>
                            </Card>
                            </GridItem>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                        <CampaignForm 
                            {...this.state}
                            handleChange={this.handleChange}
                            handleSubmitCampaign={this.handleSubmitCampaign}
                            />
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                    <ExpansionPanel expanded={expanded === 'panel2'} onChange={this.handleChangePanel('panel2')}>
                        <ExpansionPanelSummary>
                            <GridItem xs={12} sm={12} md={12}>
                            <Card>
                                <CardBody>
                                <Cloud/>
                                <h3>Value Voucher</h3>
                                </CardBody>
                                <CardFooter>
                                <p>Define or generate a fixed code (e.g. voucherify4free) 
                                    for multiple use. Assign tags with custom categories for further maintenance and.
                                </p>
                                </CardFooter>
                            </Card>
                            </GridItem>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                        <VoucherFormSA
                            {...this.state}
                            handleChange={this.handleChange}
                            handleSubmitVoucherVal={this.handleSubmitVoucherVal}
                            handleGenerate={this.handleGenerate}
                        />
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                    </div>
                  )
                },
                {
                  tabName: "Gift Voucher",
                  tabContent: (<div>
                    <ExpansionPanel expanded={expanded === 'panel3'} onChange={this.handleChangePanel('panel3')}>
                    <ExpansionPanelSummary>
                        <GridItem xs={12} sm={12} md={12}>
                        <Card>
                            <CardBody>
                            <h3>Create Campaign</h3>
                            </CardBody>
                            <CardFooter>
                            <p>Define or generate a fixed code (e.g. voucherify4free) 
                                for multiple use. Assign tags with custom categories for further maintenance and.
                            </p>
                            </CardFooter>
                        </Card>
                        </GridItem>
                    </ExpansionPanelSummary>
                    <ExpansionPanelDetails>
                    <CampaignForm
                        {...this.state}
                        handleChange={this.handleChange}
                        handleSubmitCampaign={this.handleSubmitCampaign}
                    />
                    </ExpansionPanelDetails>
                </ExpansionPanel>
                    <ExpansionPanel expanded={expanded === 'panel4'} onChange={this.handleChangePanel('panel4')}>
                        <ExpansionPanelSummary>
                            <GridItem xs={12} sm={12} md={12}>
                            <Card>
                                <CardBody>
                                <Cloud/>
                                <h3>Gift Voucher</h3>
                                </CardBody>
                                <CardFooter>
                                <p>Generate a pool of unique and hard-to-guess codes. Customise size, prefix, expiry 
                                    date and redemptions limit.
                                </p>
                                </CardFooter>
                            </Card>
                            </GridItem>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                        <GiftFormSA
                            {...this.state}
                            handleChange={this.handleChange}
                            handleSubmitVouchergift={this.handleSubmitVouchergift}
                            handleGenerate={this.handleGenerate}                        
                            />
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                    </div>
                  )
                },
                {
                  tabName: "Discount Voucher",
                  tabContent: (<div>
                      <ExpansionPanel expanded={expanded === 'panel5'} onChange={this.handleChangePanel('panel5')}>
                    <ExpansionPanelSummary>
                        <GridItem xs={12} sm={12} md={12}>
                        <Card>
                            <CardBody>
                            <h3>Create Campaign</h3>
                            </CardBody>
                            <CardFooter>
                            <p>Define or generate a fixed code (e.g. voucherify4free) 
                                for multiple use. Assign tags with custom categories for further maintenance and.
                            </p>
                            </CardFooter>
                        </Card>
                        </GridItem>
                    </ExpansionPanelSummary>
                    <ExpansionPanelDetails>
                    <CampaignForm
                        {...this.state}
                        handleChange={this.handleChange}
                        handleSubmitCampaign={this.handleSubmitCampaign}
                    />
                    </ExpansionPanelDetails>
                </ExpansionPanel>
                    <ExpansionPanel expanded={expanded === 'panel6'} onChange={this.handleChangePanel('panel6')}>
                        <ExpansionPanelSummary>
                            <GridItem xs={12} sm={12} md={12}>
                            <Card>
                                <CardBody>
                                <Cloud/>
                                <h3>Discount Voucher</h3>
                                </CardBody>
                                <CardFooter>
                                <p>Generate a pool of unique and hard-to-guess codes. Customise size, prefix, expiry 
                                    date and redemptions limit.
                                </p>
                                </CardFooter>
                            </Card>
                            </GridItem>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                        <DiscountFormSA
                            {...this.state}
                            handleChange={this.handleChange}
                            handleSubmitVoucherDiscount={this.handleSubmitVoucherDiscount}                            
                            handleGenerate={this.handleGenerate}
                        />
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                    </div>
                  )
                }
              ]}
            />
          </GridItem>
        </GridContainer>
      </div>
    );
  }
}

Vouchers.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(Vouchers);