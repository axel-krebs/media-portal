import React from 'react';
import './Desktop.css';
import TabletLayout from '../tablet/Tablet'

function DesktopLayout(props) {

    return (
        <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo" />
                        <p>
                          Edit <code>src/App.js</code> and save to reload.
                        </p>
                        <a
                          className="App-link"
                          href="https://reactjs.org"
                          target="_blank"
                          rel="noopener noreferrer"
                        >
                          Dimension: {props.dimension}
                        </a>
                    </header>
                    <section>
                        <div>SECTION</div>
                    </section>
                    <footer>
                        <div>FOOTER</div>
                    </footer>
                </div>
    );
}

export default DesktopLayout;