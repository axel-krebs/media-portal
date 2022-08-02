import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import logo from './media/logo.svg';
import Header from './Header'
import Main from './Main'
import Footer from './Footer'
import './Layout.css'

/* Assure correct layout on diverse devices. Header-Main-Footer structure preserved on all devices. */
function Layout(props) {
    let device = props['device'];
    return (
        <div id="hmf_box" className={`Center-container-flex ${device['cssThemeClass']}`}>
            <Header theme={device['cssThemeClass']} />
            <Main device={device} />
            <Footer theme={device['cssThemeClass']} />
        </div>
    );
}

export default Layout;