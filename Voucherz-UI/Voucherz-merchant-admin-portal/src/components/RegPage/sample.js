import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import withStyles from '@material-ui/core/styles/withStyles';
import {Link, withRouter , Redirect} from 'react-router-dom';
import axios from 'axios';
import { compose } from 'recompose';
import { SignUpLink } from '../SignUp';
import { PasswordForgetLink } from '../PasswordForget';
import * as ROUTES from '../../constants/routes';
import AppBar from '../Navigation/AppBar'

const styles = theme => ({
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  paper: {
    marginTop: theme.spacing.unit * 8,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: `${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px`,
  },
  avatar: {
    margin: theme.spacing.unit,
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },
});

const SignInPage = () => (
  <div>
   <AppBar/>

    <h1 style={{textAlign:'center'}}>SignIn</h1>
    <SignInForm />
    <PasswordForgetLink />
    <SignUpLink />
  </div>
);

// const INITIAL_STATE = {
//   email: '',
//   password: '',
//   error: null,
// };

 class SignInFormBase extends Component {
  constructor(props) {
    super(props);
    this.state = { 
      email:"",
      password:'',


    };

     
  }
  onChange = (e) => {
    // Because we named the inputs to match their corresponding values in state, it's
    // super easy to update the state Voucher
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
}

onSubmit = (e) => {
  e.preventDefault();
              document.getElementById("buttonShipper").innerHTML = "signing you in...";
  // get our form data out of state
  var apiBaseUrl = 'http://172.20.20.21:9999/auth/signin';

  const {  email, password  } = this.state;
  
  let data = {
      email,
      password,  
  }

  console.log(JSON.stringify(data));
  
  
  axios.post(apiBaseUrl, data, {
      data: JSON.stringify(data),
           
  }).then((response) => {

    //access the results here....
    // alert(result);
    // console.log(response);
    if(response.data && response.data.accessToken){
      sessionStorage.setItem('data',response.data.accessToken);
      this.setState({redirectToReferrer: true});
      //  this.props.history.push({pathname: '/dashboard'})

        // console.log(response);
        //alert(response);
        // document.getElementById("buttonShipper").innerHTML = "success";
        
    }
    // else{
    //     //alert(response.data);
    //     document.getElementById("buttonShipper").innerHTML = "failed try again1..."
    //   }
})
.catch(function (error) {
    alert("failed to complete");
    document.getElementById("buttonShipper").innerHTML = "failed try again2...";
    //console.log('error got' + error);
    //this.props.history.push('/signin')

});   
}


render() {

  if (this.state.redirectToReferrer){
      
    return (<Redirect to={'/dashboard'}/>)
    }
    if (sessionStorage.getItem('data')){
    
        return (<Redirect to={'/dashboard'}/>)
    }
  
  const { classes } = this.props;


  return (
    <main className={classes.main}>
      <CssBaseline />

      <Paper className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <form className={classes.form} onSubmit={this.onSubmit}>
          <FormControl margin="normal" required fullWidth>
            <InputLabel htmlFor="email">Email Address</InputLabel>
            <Input
              id="email"
              value={this.state.email}
              onChange={this.onChange}
              type="text"
              placeholder="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
            />
            </FormControl>
            <FormControl 
            margin="normal" 
            required fullWidth>
            <InputLabel 
            htmlFor="password">Password</InputLabel>
            <Input
              name="password"
              value={this.state.password}
              onChange={this.onChange}
              type="password"
              placeholder="Password"
              id="password"
              autoComplete="current-password"/>
              </FormControl>
              <FormControlLabel
                control={<Checkbox color="primary" />}
                label="Remember me" />
                    
              <Button 
              fullWidth
              variant="contained"
              color="primary"
              id="buttonShipper"
              className={classes.submit} 
              type="submit">
                Sign In
              </Button>

              
          </form>
        </Paper>
      </main>
    );
  }
}
// const SignUpLink = () => (
//   <p style={{textAlign:'center'}}>
//     Don't have an account? <Link to={ROUTES.SIGN_UP}>Sign Up</Link>
//   </p>
// );

const SignInForm = compose(
  withRouter,
  withStyles(styles)
)(SignInFormBase);


/////////////////////////////////////////////////////////////////////////////////////////////////////////


import React from 'react';
import {Redirect} from 'react-router-dom';



export default class SignOutButton extends React.Component{
    constructor(props){
        super(props);
        this.state={
           redirectToReferrer:false,
            
        }
        this.logout = this.logout.bind(this);
      
      
      }

  



    logout(){
        sessionStorage.setItem("data",'');
     sessionStorage.clear();
     this.setState({redirectToReferrer: true});
    }

    render(){

        if (this.state.redirectToReferrer) {
            return (<Redirect to={'/'}/>)
          }

      return(
                <div>
                <button style={{backgroundColor:'#972FB0'}} type="button" onClick={this.logout} className="btn btn-primary">Log Out</button>
                </div>
            )
    }
        
}