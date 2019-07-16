<template>
  <div class="social-section">
     <div id="SOHUCS" :sid="type+postId"></div>
  </div>
</template>

<script type="text/ecmascript-6">
import BrowseMore from '@/components/views/BrowseMore'

export default {
  props: {
    type: String,
    postId: Number
  },
  components: {
    'browse-more': BrowseMore
  },
  mounted () {
    window.changyan = undefined
    window.cyan = undefined
    this.$loadScript(null, 'https://changyan.sohu.com/upload/changyan.js', () => {
      window.changyan.api.config({
        appid: 'cyukGLEb1', // 此处换成你的畅言应用的appid,
        conf: 'prod_4bfbafbc1dfd8e1854aa10135fef9bf9' // 此处换成你畅言应用的conf。
      })
    })
  },
  // 此方法是路由跳转时移除畅言插件
  beforeDestroy () {
    try {
      // eslint-disable-next-line no-useless-escape
      const removeRep = /^http(s)?\:\/\/changyan\./
      const $head = document.getElementsByTagName('head')[0] || document.head || document.documentElement
      const $script = $head.querySelectorAll('script')
      $script.forEach((item, index) => {
        const src = item.getAttribute('src')
        if (src && removeRep.test(src)) {
          $head.removeChild(item)
        }
      })
      for (const key in window) {
        if (/^(changyan(\d)?|cyan)/.test(key)) {
          window[key] = undefined
        }
      }
    } catch (error) {}
  }
}
</script>
