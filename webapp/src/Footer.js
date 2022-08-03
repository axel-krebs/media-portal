import React from 'react';
import { Link } from "react-router-dom";
import './Footer.css'
import i18n from './i18n';

/* Pass the 'theme' property to adjust display, e.g. 'Tablet'. */
function Footer(props) {

    return (
        <footer className={`Footer-style-flex ${props['theme']}`}>
            <div className="mastfoot">
                <nav className="nav nav-mastfoot">
                    <Link className="nav-link-mastfoot active" to="notice/legal">{ i18n.t('link_legal_notice') }</Link>
                    <Link className="nav-link-mastfoot" to="jobs">{ i18n.t('link_jobs_career') }</Link>
                    <Link className="nav-link-mastfoot" to="notice/imprint">{ i18n.t('link_imprint') }</Link>
                </nav>
                <p>{ i18n.t('footer_legal') }</p>
            </div>
        </footer>
    );
}

export default Footer;