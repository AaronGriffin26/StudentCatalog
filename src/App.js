import React, {Component, useEffect, useState} from 'react';
import './App.css';
import {Amplify, API, Storage} from 'aws-amplify';
import {AmplifySignOut, withAuthenticator} from '@aws-amplify/ui-react';
import awsmobile from "./aws-exports";

const initialFormState = {name: '', description: ''}

class App extends Component {
    state = {students: [], postResponse: "", getResponse: ""}

    async componentDidMount() {
        Amplify.configure(awsmobile)
        API.configure()
        API.post('studentapi', '/student').then(response => {
            this.setState({postData: response})
        }).catch(error => {
            console.log('error posting, response: ' + error)
        })
        API.get('studentapi', '/student').then(response => {
            this.setState({students: response.students, getData: response})
        }).catch(error => {
            console.log('error fetching, response: ' + error)
        })
    }

    render() {
        return (
            <div className="App">
                <h3>H3H3</h3>
                <div>Post: {this.state.postResponse}</div>
                <div>Get: {this.state.getResponse}</div>
                {/*{*/}
                {/*    this.state.students.map((student, i) => (*/}
                {/*        <div>*/}
                {/*            <p>First: {student.firstName}</p>*/}
                {/*            <p>Last: {student.lastName}</p>*/}
                {/*            <p>SSN: {student.ssn}</p>*/}
                {/*        </div>*/}
                {/*    ))*/}
                {/*}*/}
            </div>
        )
    }
}

export default withAuthenticator(App);