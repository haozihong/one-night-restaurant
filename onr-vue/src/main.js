import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui';
Vue.use(ElementUI);

import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'

import Vuex from 'vuex'
Vue.use(Vuex)

import router from './router'

import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)

// handle all errors here
axios.interceptors.response.use(success => {
  if (success.status && success.status === 200 && success.data.status === 500) {
    ElementUI.Message.error({message: success.data.msg})
  } else if (success.data.msg) {
    ElementUI.Message.success({message: success.data.msg})
  }
  return success.data;
}, error => {
  if (error.response.data.msg) {
    ElementUI.Message.error({message: error.response.data.msg})
  } else {
    ElementUI.Message.error({ message: `${error.response.status}: ${error.response.statusText}` })
  }
})


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
