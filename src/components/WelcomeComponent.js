import React, {Component} from 'react'
import {Link} from 'react-router-dom'

export default class WelcomeComponent extends Component {
    render() {
        return (
            <div>
                <p>Welcome to the catalog.</p>
                <p><Link to="/student">View as student</Link></p>
                <p><Link to="/professor">View as professor</Link></p>
            </div>
        )
    }
}