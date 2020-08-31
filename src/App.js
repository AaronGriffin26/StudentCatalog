import React from "react";
import './App.css';
import RouterComponent from "./RouterComponent";
import {config} from "./config";

export default function App() {
    return (
        <div className="App">
            <div className="Container">
                <div id="page-wrapper">
                    {config.maintenance ? <p>Website currently down for maintenance!</p> : <RouterComponent/>}
                </div>
            </div>
            <footer>
                Website by Aaron Griffin. Backend developed by Aaron Griffin using Amazon Web Services.<br/>
                <b>Disclaimer</b>: Currently having connection issues with Amazon RDS.
            </footer>
        </div>
    )
}