import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import i18n from './i18n';
import './Menu.css'

/* 'Navigation'; converted to menu-style popup in CSS. */
function Menu(props) {

    return (
        <div className={`Menu-container-flex ${props['theme']}`}>
            <div className="menu_head_entry">
                <h3>{ i18n.t('menu_title_company') }</h3>
                <ul>
                    <li>{ i18n.t('menu_title_company_profile') }</li>
                </ul>
            </div>
        </div>
    );
}

export default Menu;