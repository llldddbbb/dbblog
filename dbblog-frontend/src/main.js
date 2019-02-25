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
  Input,
  Progress,
  Tag,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Menu,
  Submenu,
  MenuItem,
  Icon,
  LoadingBar,
  Affix,
  Select,
  Option,
  Carousel,
  CarouselItem,
  DatePicker,
  DatePickerCell,
  Spin,
  Rate,
  Modal,
  Message,
  Notice
} from 'iview'

Vue.component('iv-row', Row)
Vue.component('iv-col', Col)
Vue.component('iv-button', Button)
Vue.component('iv-input', Input)
Vue.component('iv-progress', Progress)
Vue.component('iv-tag', Tag)
Vue.component('iv-dropdown', Dropdown)
Vue.component('iv-dropdown-menu', DropdownMenu)
Vue.component('iv-dropdown-item', DropdownItem)
Vue.component('iv-menu', Menu)
Vue.component('iv-submenu', Submenu)
Vue.component('iv-menu-item', MenuItem)
Vue.component('iv-icon', Icon)
Vue.component('iv-loadingBar', LoadingBar)
Vue.component('iv-affix', Affix)
Vue.component('iv-select', Select)
Vue.component('iv-option', Option)
Vue.component('iv-carousel', Carousel)
Vue.component('iv-carousel-item', CarouselItem)
Vue.component('iv-date-picker', DatePicker)
Vue.component('iv-date-picker-cell', DatePickerCell)
Vue.component('iv-spin', Spin)
Vue.component('iv-rate', Rate)

Vue.prototype.$http = httpRequest // Ajax 请求方法
Vue.prototype.$Modal = Modal
Vue.prototype.$Message = Message
Vue.prototype.$Notice = Notice

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
