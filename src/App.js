import React from "react";
import './App.css';
import RouterComponent from "./RouterComponent";

export default function App() {
    return (
        <div className="App">
            <div className="Container">
                <div id="page-wrapper">
                    <RouterComponent/>
                </div>
            </div>
            <footer>
                Website by Aaron Griffin. Backend developed by Aaron Griffin using Amazon Web Services.
            </footer>
        </div>
    )
}