import React, {Component} from 'react';
import {Amplify, API} from 'aws-amplify';
import {Link} from "react-router-dom";

export default class StudentViewComponent extends Component {
    state = {students: []}

    async componentDidMount() {
        API.get('studentapi', '/name').then(response => {
            console.log(response)
            this.setState({students: response.names})
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
                <p><Link to="/professor">View as professor</Link></p>
                <h4>Names:</h4>
                {
                    this.state.students.map((student, i) => (
                        <p key={i}>{student.firstName} {student.lastName}</p>
                    ))
                }
            </div>
        )
    }
}