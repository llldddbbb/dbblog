import Vue from 'vue'
import router from '@/router'
import store from '@/store'
import 'iview/dist/styles/iview.css'
import '@/common/stylus/index.styl'
import App from './App.vue'
import httpRequest from '@/utils/httpRequest'

// iView UI 组件引入
import {
  Row,
  Col,
  Button,
  Progress,
  Tag,
  Icon,
  Affix,
  Rate,
  Modal,
  Message,
  Notice
} from 'iview'

Vue.component('iv-row', Row)
Vue.component('iv-col', Col)
Vue.component('iv-button', Button)
Vue.component('iv-progress', Progress)
Vue.component('iv-tag', Tag)
Vue.component('iv-icon', Icon)
Vue.component('iv-affix', Affix)
Vue.component('iv-rate', Rate)

Vue.prototype.$http = httpRequest // Ajax 请求方法
Vue.prototype.$Modal = Modal
Vue.prototype.$Message = Message
Vue.prototype.$Notice = Notice
Vue.prototype.$loadScript = (script, url, callback) => {
  script = script || document.createElement('script')
  if (script.readyState) {
    // IE浏览器
    script.onreadystatechange = function () {
      if (script.readyState === 'loaded' || script.readyState === 'complete') {
        script.onreadystatechange = null
        callback()
      }
    }
  } else {
    // 其他浏览器
    script.onload = function () {
      callback()
    }
  }
  script.src = url
  document.getElementsByTagName('head')[0].appendChild(script)
}
Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})

Vue.prototype.$Notice.config({
  top: 70,
  duration: 3
})
let vm = new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
})

Vue.use({
  vm
})
