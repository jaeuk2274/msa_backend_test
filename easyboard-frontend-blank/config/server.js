
const express = require('express');
const next = require('next');
const proxyMiddleware = require('http-proxy-middleware');


const devProxy = {
  // '/api/course': {
  //   target: 'http://localhost:9093',        // mock
  //   pathRewrite: { '/api/course': '' },
  //   changeOrigin: true,
  //   secure: false,
  // },
  //
  '/api/easyboard': {
    target: 'http://localhost:9020',
    pathRewrite: { '/api/easyboard': '' },
    changeOrigin: true,
    secure: false,
  },
};

const port = parseInt(process.env.PORT, 10) || 3500;
const env = process.env.NODE_ENV;
const dev = env !== 'production';
const app = next({
  dir: '.', // base directory where everything is, could move to src later
  dev,
});
const handle = app.getRequestHandler();

let server;

app
  .prepare()
  .then(() => {
    server = express();

    // Set up the proxy.
    if (devProxy) {
      Object.keys(devProxy).forEach((context) => {
        server.use(proxyMiddleware(context, devProxy[context]));
      });
    }

    // Default catch-all handler to allow Next.js to handle all other routes
    server.all('*', (req, res) => handle(req, res));

    server.listen(port, err => {
      if (err) {
        throw err;
      }
      console.log(`> Ready on port ${port} [${env}]`);
    });
  })
  .catch(err => {
    console.log('An error occurred, unable to start the server');
    console.log(err);
  });
