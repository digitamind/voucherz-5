import React from "react";
import withStyles from "@material-ui/core/styles/withStyles";

import GridContainer from "../../../BasicComponents/Grid/GridContainer";
import GridItem from "../../../BasicComponents/Grid/GridItem";
import CustomInput from "../../../BasicComponents/CustomInput/CustomInput";
import Button from "../../../BasicComponents/CustomButtons/Button";

import workStyle from "../../../assets/styles/LandingPageSection/WorkStyle";

class WorkSection extends React.Component {
    render() {
        const { classes } = this.props;
        return (
        <div className={classes.section}>
            <GridContainer justify="center">
            <GridItem cs={12} sm={12} md={8}>
                <h2 className={classes.title}>Work with us</h2>
                <h4 className={classes.description}>
                    Our top class team will guide you through our process
                </h4>
                <form>
                <GridContainer>
                    <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                        labelText="Your Name"
                        id="name"
                        formControlProps={{
                        fullWidth: true
                        }}
                    />
                    </GridItem>
                    <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                        labelText="Your Email"
                        id="email"
                        formControlProps={{
                        fullWidth: true
                        }}
                    />
                    </GridItem>
                    <CustomInput
                    labelText="Your Message"
                    id="message"
                    formControlProps={{
                        fullWidth: true,
                        className: classes.textArea
                    }}
                    inputProps={{
                        multiline: true,
                        rows: 5
                    }}
                    />
                    <GridContainer justify="center">
                    <GridItem
                        xs={12}
                        sm={12}
                        md={4}
                        className={classes.textCenter}
                    >
                        <Button color="info">Send Message</Button>
                    </GridItem>
                    </GridContainer>
                </GridContainer>
                </form>
            </GridItem>
            </GridContainer>
        </div>
        );
    }
}

export default withStyles(workStyle)(WorkSection);
