import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import createService from './service.js'

Vue.config.productionTip = false

Vue.prototype.$svc_example = createService('svc-example')

new Vue({
  render: h => h(App),
}).$mount('#app')
