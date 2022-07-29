import React from 'react';
import './Header.css'
import './media/logo.svg'

/* This must appear on the the top of the page, but shall be hidden when device is too small. */
function Header(props) {

    return (
        <header className="Header-style-flex">
            <div className="inner">
              <h3 className="masthead-brand">Cover</h3>
              <nav className="nav nav-masthead justify-content-center">
                <a className="nav-link active" href="#">Home</a>
                <a className="nav-link" href="#">Features</a>
                <a className="nav-link" href="#">Contact</a>
              </nav>
            </div>
        </header>
    );
}

export default Header;