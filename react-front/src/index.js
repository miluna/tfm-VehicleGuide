import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'bulma/css/bulma.css';
import 'font-awesome/css/font-awesome.min.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import { IntlProvider } from 'react-intl';
import { BrowserRouter } from 'react-router-dom'

ReactDOM.render(
  <BrowserRouter>
    <IntlProvider locale="en">
      <App />
    </IntlProvider>
  </BrowserRouter>,
  document.getElementById('root')
);
registerServiceWorker();
