import React from 'react';
import { useMediaQuery } from 'react-responsive'
import Layout from './Layout'
import { DEVICE_TYPES_DISPLAY_PROPERTIES } from './Globals'

/**/
function App() {
    //const isMobileDevice = useMediaQuery({ query: '(max-width: 480px)'});
    const isTabletDevice = useMediaQuery({ query: '(max-width: 1224px)' });
    const isDesktopOrLaptop = useMediaQuery({ query: '(min-width: 1224px)' });
    //const isBigScreen = useMediaQuery({ query: '(min-width: 1824px)' });
    //const isPortrait = useMediaQuery({ query: '(orientation: portrait)' });
    //const isRetina = useMediaQuery({ query: '(min-resolution: 2dppx)' });
    const size = isDesktopOrLaptop ? DEVICE_TYPES_DISPLAY_PROPERTIES['notebook']['size'] : isTabletDevice ?
        DEVICE_TYPES_DISPLAY_PROPERTIES['tablet']['size'] : DEVICE_TYPES_DISPLAY_PROPERTIES['smartphone']['size'];
    return (<Layout dimension={ size } />);
}

export default App;
