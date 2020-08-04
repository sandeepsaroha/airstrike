import React,{Component} from 'react';
import { Route, BrowserRouter as Router } from "react-router-dom";
import Home from './components/Home';
import logo from './logo.svg';
import './App.css';


class App extends Component {
  
  render() {
    return (
      <Router>
        <Route exact path="/" component={Home} />
      </Router>
    );
  }
}


export default App;
