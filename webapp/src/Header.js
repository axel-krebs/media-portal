import React from 'react';
import './Header.css'
import './media/logo.svg'
import { Link } from "react-router-dom";

/* This must appear on the the top of the page, but shall be hidden when device is too small. */
function Header(props) {

    return (
        <header className={`Header-style-flex ${props['theme']}`}>
            <div className="left">
              <h1>AFRTN</h1>
              <p>Make your life great again!</p>
            </div>
            <div className="middle">WERBUNG?</div>
            <div className="right">
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