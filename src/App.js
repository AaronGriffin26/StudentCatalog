import React, {Component} from 'react';
import './App.css';
import {API} from 'aws-amplify';

class App extends Component {
    state = {students: [], postResponse: "", getResponse: ""}

    async componentDidMount() {
        // API.post('studentapi', '/student').then(response => {
        //     this.setState({postData: response})
        // }).catch(error => {
        //     console.log('error posting, response: ' + error)
        // })
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
                {
                    this.state.students.map((student, i) => (
                        <div>
                            <p>First: {student.firstName}</p>
                            <p>Last: {student.lastName}</p>
                            <p>SSN: {student.ssn}</p>
                        </div>
                    ))
                }
            </div>
        )
    }
}

export default App;