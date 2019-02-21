<template>
  <div class="simple-header">
    <transition name="slide-fade">
      <div id="mobile-bar" v-show="show" >
        <a class="menu-button" ref="menubutton"></a>
        <router-link class="logo" to="/"></router-link>
      </div>
    </transition>
    <transition name="slide-fade">
      <div id="header"  v-show="show">
    <router-link id="logo" to="/">
      <img src="../../../assets/logo.png">
      <span class="title">DB's Blog</span>
      <span class="motto">success belongs to the persevering</span>
    </router-link>
    <ul id="nav">
      <li>
        <form id="search-form">
      <span class="algolia-autocomplete" style="position: relative; display: inline-block; direction: ltr;"><input
        type="text" id="search-query-nav" class="search-query st-default-search-input aa-input"
        autocomplete="off" spellcheck="false" role="combobox" aria-autocomplete="list" aria-expanded="false"
        aria-owns="algolia-autocomplete-listbox-0" dir="auto" style="position: relative; vertical-align: top;"><pre
        aria-hidden="true"
        style="position: absolute; visibility: hidden; white-space: pre; font-family: system-ui; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: auto; text-transform: none;"></pre><span
        class="aa-dropdown-menu" role="listbox" id="algolia-autocomplete-listbox-0"
        style="position: absolute; top: 100%; z-index: 100; display: none; left: 0px; right: auto;"><div
        class="aa-dataset-1"></div></span></span>
        </form>
      </li>

      <li><a href="/articles" class="nav-link contribute">文章</a></li>
      <li><a href="/bookNotes" class="nav-link contribute">阅读</a></li>
      <li><a href="/timeline" class="nav-link contribute">时光轴</a></li>
      <li><a href="/article/1" class="nav-link contribute">关于</a></li>
    </ul>
    </div>
    </transition>
    <sidebar ref="sidebar"></sidebar>
  </div>

</template>

<script type="text/ecmascript-6">
import SideBar from '@/components/header/SimpleHeader/SideBar'

export default {
  components: {
    'sidebar': SideBar
  },
  data () {
    return {
      show: true
    }
  },
  mounted: function () {
    this.$nextTick(function () {
      this.initMobileMenu()
    })
    // 给页面绑定滑轮滚动事件
    if (document.addEventListener) { // firefox
      document.addEventListener('DOMMouseScroll', this.watchScroll, false)
    }
    // 滚动滑轮触发scrollFunc方法  //ie 谷歌
    window.onmousewheel = document.onmousewheel = this.watchScroll
  },
  methods: {
    initMobileMenu () {
      // 显示手机端的菜单
      var sidebar = this.$refs.sidebar
      this.$refs.menubutton.addEventListener('click', function () {
        sidebar.openSideBar()
      })
    },
    watchScroll (e) {
      e = e || window.event
      if (e.wheelDelta) {
        if (e.wheelDelta > 0 && this.show === false) { // 当滑轮向上滚动
          this.show = true
        }
        if (e.wheelDelta < 0 && this.show === true) { // 当滑轮向下滚动
          this.show = false
        }
      } else if (e.detail) {
        if (e.detail < 0 && this.show === false) { // 当滑轮向上滚动
          this.show = true
        }
        if (e.detail > 0 && this.show === true) { // 当滑轮向下滚动
          this.show = false
        }
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "stylus/header.styl";
   // 添加导航栏显示动画
  .slide-fade-enter-active ,.slide-fade-leave-active {
    transition: all .8s ease;
  }
  .slide-fade-leave-to ,.slide-fade-enter {
    /* .slide-fade-leave-active for below version 2.1.8 */
    transform: translateY(-70px);
    opacity: 0;
  }
</style>
