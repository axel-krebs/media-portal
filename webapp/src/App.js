import React from 'react';
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'
import Layout from './Layout'
import { useMediaQuery } from 'react-responsive'

/* Start the application: Initialize layout. */
function App() {
    const isMobileDevice = useMediaQuery({ query: 'hover: none'});
    const isTabletDevice = useMediaQuery({ query: 'only screen and (min-width: 600px)' });
    const isDesktopOrLaptop = useMediaQuery({ query: 'only screen and (min-width: 1224px)' });
    //const isBigScreen = useMediaQuery({ query: '(min-width: 1824px)' });
    //const isPortrait = useMediaQuery({ query: '(orientation: portrait)' });
    //const isRetina = useMediaQuery({ query: '(min-resolution: 2dppx)' });
    let device = DEVICE_TYPES_DISPLAY_PROPERTIES['smartphone']; // mobile first
    if (isTabletDevice){device = DEVICE_TYPES_DISPLAY_PROPERTIES['tablet'];}
    if (isDesktopOrLaptop){device = DEVICE_TYPES_DISPLAY_PROPERTIES['notebook'];}

    return (<Layout device={ device } />);
}

export default App;
