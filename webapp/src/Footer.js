import React from 'react';
import './Footer.css'
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import i18n from './i18n';

/* Pass the 'theme' property to adjust display, e.g. 'Tablet'. */
function Footer(props) {

    return (
        <footer className={`Footer-style-flex ${props['theme']}`}>
            <div className="mastfoot">
                <p>{ i18n.t('footer_legal') }</p>
                <nav className="nav nav-mastfoot">
                    <a className="nav-link-mastfoot active" href="#">{ i18n.t('link_legal_notice') }</a>
                    <a className="nav-link-mastfoot" href="#">{ i18n.t('link_jobs_career') }</a>
                    <a className="nav-link-mastfoot" href="#">{ i18n.t('link_impressum') }</a>
                </nav>
            </div>
        </footer>
    );
}

export default Footer;