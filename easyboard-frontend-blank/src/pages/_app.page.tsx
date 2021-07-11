import React, { Component } from 'react';
import { Provider } from 'mobx-react';
import { AppProps } from 'next/app';
import { Helmet } from 'react-helmet';
import { autobind } from '@nara.drama/prologue';
import { store } from '~/comp/state';
import configureApp from './configureApp';

import '../pages/styles/boardStyle.css';

configureApp();

@autobind
class ChannelApp extends Component<AppProps> {
  //
  render() {
    //
    const { Component, pageProps } = this.props;

    return (
      <Provider
        {...store}
      >
        <Helmet>
          <title>{pageProps.pageName}</title>
          <link rel="icon" href="/images/favicon/favicon.ico" />
        </Helmet>
          <Component
            {...pageProps}
          />
      </Provider>
    );
  }
}

export default ChannelApp;
