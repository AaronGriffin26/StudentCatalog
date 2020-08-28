import React, {Component} from 'react';
import {Amplify, API} from 'aws-amplify';

export default class StudentViewComponent extends Component {
    state = {students: [], postResponse: '', getResponse: ''}

    async componentDidMount() {
        API.post('studentapi', '/student', {
            body: {
                firstName: 'Aaron',
                lastName: 'Griffin'
            }
        }).then(response => {
            console.log('success, post response: ' + response)
            this.setState({postResponse: response})
        }).catch(error => {
            console.log('error posting, response: ' + error)
        })
        API.get('studentapi', '/student').then(response => {
            console.log('success, get response: ' + response)
            this.setState({students: response.students, getResponse: response})
        }).catch(error => {
            console.log('error fetching, response: ' + error)
        })
    }

    render() {
        return (
            <div>
                <h3>Student View</h3>
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