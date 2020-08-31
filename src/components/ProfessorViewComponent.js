import React, {Component} from 'react';
import {Amplify, API} from 'aws-amplify';
import {Link} from "react-router-dom";

export default class ProfesorViewComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            students: [],
            postResponse: '',
            postErrorResponse: '',
            firstName: '',
            firstNameError: '',
            lastName: '',
            lastNameError: '',
            ssn: '',
            ssnError: '',
            addingStudent: false
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.onAdd = this.onAdd.bind(this)
        this.onCancel = this.onCancel.bind(this)
        this.handleFirstName = this.handleFirstName.bind(this)
        this.handleLastName = this.handleLastName.bind(this)
        this.handleSSN = this.handleSSN.bind(this)
    }

    async componentDidMount() {
        API.get('studentapi', '/student').then(response => {
            console.log(response)
            this.setState({students: response.students})
        }).catch(error => {
            console.log('error fetching')
            console.log(error)
            this.setState({getResponse: "ERROR!"})
        })
    }

    onSubmit(e) {
        e.preventDefault()
        this.setState({firstNameError: '', lastNameError: '', ssnError: ''})
        if (this.state.firstName.length === 0)
            this.setState({firstNameError: "Please enter the first name"})
        else if (this.state.firstName.length > 20)
            this.setState({firstNameError: "First name is too long"})
        else if (this.state.lastName.length === 0)
            this.setState({lastNameError: "Please enter the last name"})
        else if (this.state.lastName.length > 20)
            this.setState({lastNameError: "Last name is too long"})
        else if (this.state.ssn.length < 9)
            this.setState({ssnError: "Not enough digits in the 9 digit SSN"})
        else if (this.state.ssn.length > 9)
            this.setState({ssnError: "Too many digits in the 9 digit SSN"})
        else if (isNaN(this.state.ssn))
            this.setState({ssnError: "SSN is not a 9 digit number"})
        else {
            this.setState({postResponse: "Adding...", postErrorResponse: "", addingStudent: false})
            API.post('studentapi', '/student', {
                body: {
                    firstName: this.state.firstName,
                    lastName: this.state.lastName,
                    ssn: this.state.ssn,
                },
            }).then(response => {
                console.log(response)
                this.setState({postResponse: response.body, postErrorResponse: ""})
            }).catch(error => {
                console.log('error posting')
                console.log(error)
                this.setState({postResponse: "", postErrorResponse: "ERROR!"})
            })
            this.setState({firstName: '', lastName: '', ssn: ''})
        }
    }

    onAdd(e) {
        e.preventDefault()
        this.setState({addingStudent: true})
    }

    onCancel(e) {
        e.preventDefault()
        this.setState({addingStudent: false})
    }

    handleFirstName(e) {
        this.setState({firstName: e.target.value, firstNameError: ''})
    }

    handleLastName(e) {
        this.setState({lastName: e.target.value, lastNameError: ''})
    }

    handleSSN(e) {
        this.setState({ssn: e.target.value, ssnError: ''})
    }

    render() {
        return (
            <div>
                <h3>Professor View</h3>
                <p><Link to="/student">View as student</Link></p>
                <h4 style={{color: "#008000"}}>{this.state.postResponse}</h4>
                <h4 style={{color: "#800000"}}>{this.state.postErrorResponse}</h4>
                <h4>Students:</h4>
                {
                    this.state.students.map((student, i) => (
                        <p key={i}>{student.firstName} {student.lastName}, SSN: {student.ssn}</p>
                    ))
                }
                {this.state.addingStudent ? this.addStudentForm() : <button onClick={this.onAdd}>Add Student</button>}
            </div>
        )
    }


    addStudentForm() {
        return (
            <form onSubmit={this.onSubmit}>
                First Name: <input type="text" value={this.state.firstName} onChange={this.handleFirstName}/><br/>
                <span style={{color: "#800000"}}>{this.state.firstNameError}</span> <br/>
                Last Name: <input type="text" value={this.state.lastName} onChange={this.handleLastName}/><br/>
                <span style={{color: "#800000"}}>{this.state.lastNameError}</span><br/>
                SSN: <input type="text" value={this.state.ssn} onChange={this.handleSSN}/><br/>
                <span style={{color: "#800000"}}>{this.state.ssnError}</span><br/>
                <button type="submit">Submit</button>
                <button onClick={this.onCancel}>Cancel</button>
            </form>
        )
    }
}