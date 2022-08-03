import React from 'react';
import './Breadcrumbs.css'
import { Link } from "react-router-dom";
import HouseIcon from './media/solid/house.svg'

function Breadcrumbs(props) {

    return (
        <div className={`Breadcrumbs-container-flex ${props['theme']}`}>
            <Link to="/">
                <img className="nav_icon" src={ HouseIcon } alt="Home"/>
            </Link>
            &nbsp;/&nbsp;
            <Link to="#">Features</Link>
        </div>
    );
}

export default Breadcrumbs;