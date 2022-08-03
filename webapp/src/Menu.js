import React from 'react';
import { Link } from "react-router-dom";
import i18n from './i18n';
import './Menu.css'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
/* bootstrap.min.css imported in index.js */

/* Menu is a JSON array. */
const menu = require('./menu.json')

/* 'Navigation'; converted to menu-style popup in CSS. */
function Menu(props) {
    let theme = props['theme'];
    return (
        (theme === 'Tablet') ?
            <Navbar bg="light" expand="lg">
            <Container>
             <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
             <Navbar.Toggle aria-controls="basic-navbar-nav" />
             <Navbar.Collapse id="basic-navbar-nav">
               <Nav className="me-auto">
                 <Nav.Link href="#home">Home</Nav.Link>
                 <Nav.Link href="#link">Link</Nav.Link>
                 <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                   <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
                   <NavDropdown.Item href="#action/3.2">
                     Another action
                   </NavDropdown.Item>
                   <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                   <NavDropdown.Divider />
                   <NavDropdown.Item href="#action/3.4">
                     Separated link
                   </NavDropdown.Item>
                 </NavDropdown>
               </Nav>
             </Navbar.Collapse>
            </Container>
            </Navbar>
         :
            <div id="menu_box" className={`Menu-container-flex ${props['theme']}`}>
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