import React, {Component} from 'react';
import {Doughnut} from 'react-chartjs-2';

const data = {
	labels: [
		'Red',
		'Yellow'
	],
	datasets: [{
		data: [300, 100],
		backgroundColor: [
		'#FF6384',
		'#FFCE56'
		],
		hoverBackgroundColor: [
		'#FF6384',
		'#FFCE56'
		]
	}]
};

class DoughnutChart extends Component{

  render() {
    return (
      <div>
        <Doughnut data={data} />
      </div>
    );
  }
};

export default DoughnutChart;