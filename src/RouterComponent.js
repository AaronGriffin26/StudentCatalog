import React, {Component} from 'react';
import {Route, Switch} from 'react-router-dom';
import WelcomeComponent from "./components/WelcomeComponent";
import StudentViewComponent from "./components/StudentViewComponent";

export default class RouterComponent extends Component {
    render() {
        return (
            <div>
                <Switch>
                    <Route exact path="/" component={WelcomeComponent}/>
                    <Route path="/student" component={StudentViewComponent}/>
                    <Route path="/professor" component={StudentViewComponent}/>
                </Switch>
            </div>
        );
    }
}