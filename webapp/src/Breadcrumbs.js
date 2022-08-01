import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import './Breadcrumbs.css'

function Breadcrumbs(props) {
    return (
        <div className={`Breadcrumbs-container-flex ${props['theme']}`}>
            <a href="#">Home</a> / <a href="#">Features</a>
        </div>
    );
}

export default Breadcrumbs;