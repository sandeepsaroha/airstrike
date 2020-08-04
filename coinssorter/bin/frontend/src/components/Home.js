import React from "react";
import ReactDOM from "react-dom"
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import GroupIcon from "@material-ui/icons/Group";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";


const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(7),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  coins: {
    margin: theme.spacing(3),
    backgroundColor: theme.palette.secondary.main,
    width:100,
    height:100
    
    
  },
  form: {
    width: "100%", 
    marginTop: theme.spacing(3)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
  }
}));
export default function Home() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);

  const [coins, setCoins] = React.useState("");

  const handleCoinsChange = event => setCoins(event.target.value);

  const [message, setMessage] = React.useState("No Input Processed");

  async function showResponse(response) {
    var obj = JSON.parse(response);
    var coinsResult = (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <table>
            <tr>
              <td>
                <Avatar className={classes.coins}>
                 One Dollar
                </Avatar>
                
              </td>
              <td>
                <Avatar className={classes.coins}>
                  Half Dollar
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                   Quarter
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                   Dime
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                  
                   Nickel
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                  Pennies
                </Avatar>
              </td>
              </tr>
            <tr>
              <td>
                <Avatar className={classes.coins}>
                  {obj.dollar}
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                  {obj.halfDollar}
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                   {obj.quarter}
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                   {obj.dime}
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                   {obj.nickel}
                </Avatar>
              </td>
              <td>
                <Avatar className={classes.coins}>
                   {obj.penny}
                </Avatar>
              </td>
              </tr>

             
          </table>
        </div>
      </Container>
    );
    ReactDOM.render(coinsResult, document.getElementById('responseData'));
  }
  async function submitData(toInput) {
    const response = await fetch("/api/coinssorter", {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json"
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(toInput)
    });
    let body = await response.json();
    console.log(body);

    setMessage(body ? JSON.stringify(body) : "Data updation failed");
    showResponse(JSON.stringify(body));
  }
  const handleKeyDown = (event) => {
    if (event.keyCode === 13) {
      handleSubmit();
    }
  }
  const handleSubmit = variables => {
    const toInput = { coins };
    submitData(toInput);
    setCoins("");
  };

  if (firstLoad) {
    setLoad(false);
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <GroupIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Show Exact Coins
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="coin"
                value={coins}
                label="Coins"
                name="coin"
                type="number"
                step="0.01"
                onChange={handleCoinsChange}
              />
            </Grid>

          </Grid>
          <Button
            // type="submit"
            fullWidth
            variant="contained"
            color="primary"
            preventDefault
            className={classes.submit}
            onClick={handleSubmit}
            onKeyDown={(e) => handleKeyDown(e)}
          >
            Show Coins
          </Button>
        </form>

        <Typography style={{ margin: 7 }} variant="body1">
          Result: {message}
        </Typography>
      </div>
      <div id="responseData" />
    </Container>
  );
}