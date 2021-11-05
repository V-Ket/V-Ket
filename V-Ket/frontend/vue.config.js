module.exports = {
  transpileDependencies: ['vuetify'],
  devServer: {
    disableHostCheck: true,
    https: true,
    open: true,
    proxy: {
      '/session': {
        target: 'https://k5a404.p.ssafy.io:8011/',
      },
    },
    historyApiFallback: true,
    hot: true,
  },
};
