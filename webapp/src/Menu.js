import React from 'react';
import { Link } from "react-router-dom";
import i18n from './i18n';
import './Menu.css'

/* Menu is a JSON array. */
const menu = require('./menu.json')

/* 'Navigation'; converted to menu-style popup in CSS. */
function Menu(props) {
    return (
        <div className={`Menu-container-flex ${props['theme']}`}>
            { menu.map(
                (item,index) =>
                    <div className="menu_head_entry" key={ index }>
                        <h2>{ i18n.t(item['top']) }</h2>
                        <ul>
                            { item['entries'].map(
                                (itm) => <li key={ itm['entry'] }><Link to={ itm['link'] }>{ i18n.t( itm['entry'] ) }</Link></li>
                            )}
                        </ul>
                    </div>
              )
            }
        </div>
    );
}

export default Menu;