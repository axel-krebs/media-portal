import React from 'react';
import './Main.css'
import {
  Routes,
  Route,
} from "react-router-dom";
import Menu from './Menu'
import Breadcrumbs from './Breadcrumbs'
import Welcome from './Welcome'
import Contact from './Contact'
import Login from './Login'
import Notice from './Notice'

/** Layout the center page; on smaller devices this is the only visible part! */
function Main(props) {
    let device = props['device'];
    let theme = device['cssThemeClass'];
    let menu = (device['desc'] === 'mobile_XL') ? <Breadcrumbs theme={ theme } /> : <Menu theme={ theme } />;
    let navigation = (device['desc'] === 'mobile_XL') ? <Menu theme={ theme } /> : <div></div>;
    return (
        <main role="main" className={`Main-style-flex ${theme}`}>
           {menu}
           <div className="Main-center">
               <Routes>
                 <Route index element={ <Welcome /> } />
                 <Route path="/contact" element={ <Contact /> } />
                 <Route path="/login" element={ <Login /> } />
                 <Route path="notice" element={ <Notice /> }>
                    {/** TODO */}
                 </Route>
               </Routes>
           </div>
           {navigation}
        </main>
    );
}

export default Main;