import React from 'react';
import './Main.css'
import Menu from './Menu'
import Welcome from './Welcome'

function Main(props) {

    return (
        <div>
            <Menu />
            <Welcome />
        </div>
    );
}

export default Main;