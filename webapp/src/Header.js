import React from 'react';
import './Header.css'
import './media/logo.svg'
import { Link } from "react-router-dom";

/* This must appear on the the top of the page, but shall be hidden when device is too small. */
function Header(props) {

    return (
        <header className={`Header-style-flex ${props['theme']}`}>
            <div className="inner">
              <h3 className="masthead-brand">AFRTN</h3>
              <nav className="nav nav-masthead">
                <Link className="nav-link active" to="/">Home</Link>
                <Link className="nav-link" to="/login">Login</Link>
                <Link className="nav-link" to="/contact">Contact</Link>
              </nav>
            </div>
        </header>
    );
}

export default Header;