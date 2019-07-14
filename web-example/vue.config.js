module.exports = {
  lintOnSave: false,
  devServer: {
    host: 'localhost',
    port: 9080,
    proxy: {
      '/api/svc-example': {
        target: 'http://localhost:9301/',
        changeOrigin: true,
        pathRewrite: {
          '^/api/svc-example': ''
        }
      }
    }
  },

  publicPath: '',
  outputDir: undefined,
  assetsDir: undefined,
  runtimeCompiler: undefined,
  productionSourceMap: undefined,
  parallel: undefined,
  css: {
    extract: false
  }
}
