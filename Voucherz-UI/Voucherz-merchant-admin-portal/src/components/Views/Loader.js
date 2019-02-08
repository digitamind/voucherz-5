import React from "react";
import {BounceLoader} from "react-spinners";

const override = {
    display: "block",
    margin: "150px auto",
  };

  class Loader extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
      }
    }
    render() {
      return (
        <div>
          <BounceLoader
            css={override}
            sizeUnit={"px"}
            size={150}
            color={'#00acc1'}
            />
        </div> 
      )
    }
  }

  export default Loader;