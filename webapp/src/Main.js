import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import './Main.css'
import Menu from './Menu'
import Breadcrumbs from './Breadcrumbs.js'
import Welcome from './Welcome'

/** Layout the center page */
function Main(props) {
    let device = props['device'];
    const menu = (device['desc'] == 'mobile_XL') ? <Breadcrumbs /> : <Menu />;
    return (
        <main role="main" className="Main-style-flex">
           {menu}
           <Welcome />
        </main>
    );
}

export default Main;