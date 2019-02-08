import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from "axios";
import {APIBASEURL} from "../../Routes/BaseURL";
import moment from 'moment'
import Loader from '../Loader';
import ModalTable from '../../../BasicComponents/Modal/ModalTable';


const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
  },
  table: {
    minWidth: 700,
  },
});

class SimpleTable extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      loading: true,
      datas: [],
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
        this.setState({datas: detail})
      })
      .catch(error => {
        this.setState({ error, loading: false});
      })
    }

    render(){
      const { classes } = this.props;
      const {loading} = this.state;

    const arrTable = (this.state.datas);
    const data = [];
    arrTable.map(arr => { let arr1 = (arr); data.push(arr1) })
    console.log(data);

      return (
        <Paper className={classes.root}>
        {(loading ?
          <Table className={classes.table}>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell align="right">Value</TableCell>
                <TableCell align="right">Code</TableCell>
                <TableCell align="right">Merchant Id</TableCell>
                <TableCell align="right">End Date</TableCell>
                <TableCell align="right">Status</TableCell>
                <TableCell align="right">Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {data.map(row => (
                <TableRow key={row.id}>
                  <TableCell component="th" scope="row">{row.id}</TableCell>
                  <TableCell align="right">{row.value}</TableCell>
                  <TableCell align="right">{row.code}</TableCell>
                  <TableCell align="right">{row.merchantId}</TableCell>
                  <TableCell align="right">{moment(row.expiryDate).format('MMMM Do YYYY, h:mm:ss a') === "Invalid date"? "Not Speficied" : moment(row.expiryDate).format('MMMM Do YYYY, h:mm:ss a') }</TableCell>
                  <TableCell align="right">{row.isActive ? "Active" : "Inactive"}</TableCell>
                  <TableCell align="right">{<ModalTable />}</TableCell>

                </TableRow>
              ))}
            </TableBody>
          </Table> : <Loader/>
          )}
        </Paper>
      );
    }
}

SimpleTable.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SimpleTable);
