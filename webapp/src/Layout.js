import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import logo from './media/logo.svg';
import Header from './Header'
import Main from './Main'
import Footer from './Footer'
import './Layout.css'

function Layout(props) {

    return (
        <div className="cover-container d-flex mx-auto flex-column">
            <Header />
            <Main />
            <Footer />
        </div>
    );
}

export default Layout;