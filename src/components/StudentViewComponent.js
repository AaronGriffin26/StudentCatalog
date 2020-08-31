import React, {Component} from 'react';
import {Amplify, API} from 'aws-amplify';

export default class StudentViewComponent extends Component {
    state = {students: [], postResponse: '', getResponse: ''}

    async componentDidMount() {
        API.post('studentapi', '/student', {
            body: {
                firstName: 'Aaron',
                lastName: 'Griffin'
            },
        }).then(response => {
            console.log(response)
            this.setState({postResponse: response.body})
        }).catch(error => {
            console.log('error posting')
            console.log(error)
            this.setState({postResponse: "ERROR!"})
        })
        API.get('studentapi', '/student').then(response => {
            console.log(response)
            this.setState({students: response.students, getResponse: response.body})
        }).catch(error => {
            console.log('error fetching')
            console.log(error)
            this.setState({getResponse: "ERROR!"})
        })
    }

    render() {
        return (
            <div>
                <h3>Student View</h3>
                <div>Post: {this.state.postResponse}</div>
                <div>Get: {this.state.getResponse}</div>
                {
                    this.state.students.map((student, i) => (
                        <div key={i}>
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