// Import the plugins
const path = require('path');

module.exports = {
  entry: {
    uppy:'./src/uppy.js',
    index:'./src/index.js'
    },
  output: {
    filename: '[name].min.js',
    path: path.resolve(__dirname, './dist')
  }
};