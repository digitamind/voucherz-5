import React, { Component } from 'react';
import LandingPage from './components/LandingPage/LandingPage';
import { BrowserRouter, Route } from "react-router-dom";
import LoginPage from "./components/RegPage/LoginPage";
import SignUp from './components/RegPage/SignUp';
import Dashboard from "./components/Layout/Dashboard/Dashboard"
import * as ROUTES from "./components/Routes/index"
import ConfirmationPage from './components/RegPage/ConfirmationPage';
import ForgotPassword from './components/RegPage/ForgotPassword';
import GetNewPass from './components/RegPage/GetNewPass';
import GiftPage from './components/LandingPage/GiftPage';
import TokenExpire from './components/RegPage/TokenExpire';
import BadToken from './components/RegPage/BadToken';
import AuthRoute from './components/Routes/AuthRoute';
import Redemption from './components/RegPage/Redemption';
import Error404 from './components/LandingPage/Error404';
import RedemptionGift from './components/RegPage/RedemptionGift';
import RedemptionValue from './components/RegPage/RedemptionValue';

class App extends Component {
  
  render() {
    
    return (
      <div>
        <BrowserRouter>
          <div>
            <Route path={ROUTES.LANDING} exact component={LandingPage}/>
            <Route path={ROUTES.SIGN_IN} component = {LoginPage}/>
            <Route path={ROUTES.SIGN_UP} component={SignUp}/>
            <Route path={ROUTES.Gift} component={GiftPage}/>
            <Route path={ROUTES.forgotPassword} component={ForgotPassword}/>
            <AuthRoute path={ROUTES.changePassword} component={GetNewPass}/>
            <Route path={ROUTES.Confirmation} component={ConfirmationPage}/>
            <Route path={ROUTES.Expire} component={TokenExpire}/>
            <Route path={ROUTES.BadToken} component={BadToken}/>
            <AuthRoute path={ROUTES.Dashboard} component={Dashboard}/>
            <AuthRoute path={ROUTES.Voucherz} component={Dashboard}/>
            <AuthRoute path={ROUTES.Notification} component={Dashboard}/>
            <AuthRoute path={ROUTES.CreateVoucherBulk} component={Dashboard}/>
            <AuthRoute path={ROUTES.CreateVoucherStandalone} component={Dashboard}/>
            <AuthRoute path={ROUTES.DiscountTable} component={Dashboard}/>
            <Route path="/redeem" component={Redemption}/>
            <Route path="/redeemgift" component={RedemptionGift}/>
            <Route path="/redeemvalue" component={RedemptionValue}/>
            <Route path="/error" component={Error404}/>
            <AuthRoute path={ROUTES.ValueTable} component={Dashboard}/>
            <AuthRoute path={ROUTES.GiftTable} component={Dashboard}/>

          </div>
        </BrowserRouter>
      </div>
      
    );
  }
}

export default App;
