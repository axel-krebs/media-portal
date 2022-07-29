import React from 'react';
import './Main.css'
import Menu from './Menu'
import Welcome from './Welcome'

/** Layout the center page */
function Main(props) {

    return (
        <main role="main" className="Main-style-flex">
            <Menu />
            <Welcome />
        </main>
    );
}

export default Main;