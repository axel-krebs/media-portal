import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import './Main.css'
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Menu from './Menu'
import Breadcrumbs from './Breadcrumbs.js'
import Welcome from './Welcome'
import Contact from './Contact'
import Login from './Login'

/** Layout the center page */
function Main(props) {
    let device = props['device'];
    let menu = (device['desc'] == 'mobile_XL') ? <Breadcrumbs /> : <Menu theme={`device['cssThemeClass']`} />;
    let navigation = (device['desc'] == 'mobile_XL') ? <Menu theme={`device['cssThemeClass']`} /> : <div></div>;
    return (
        <main role="main" className="Main-style-flex">
           {menu}
           <Routes>
             <Route index element={<Welcome />} />
             <Route path="/contact" element={<Contact />} />
             <Route path="/login" element={<Login />} />
           </Routes>
           {navigation}
        </main>
    );
}

export default Main;