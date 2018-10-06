import Vue from 'vue'
import router from '@/router'
import store from '@/store'
/**
 * 获取uuid
 * @returns {string}
 */
export function getUUID () {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
  })
}
/**
 * @Description: 是否有权限
 * @author Bobbi
 * @date 18-9-28
*/
export function isAuth (key) {
  return JSON.parse(sessionStorage.getItem('permissions') || '[]').indexOf(key) !== -1 || false
}

/**
 * @Description: 清除登录信息
 * @author Bobbi
 * @date 18-9-28
*/
export function clearLoginInfo () {
  Vue.cookie.delete('token')
  store.commit('resetStore')
  router.options.isAddDynamicMenuRoutes = false
}
