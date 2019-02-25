<template>
  <div class="side-bar" @touchmove.stop.prevent :class="{ open: show }">
    <div class="main-area">
      <div class="top-wrapper" @touchmove.stop>
        <div class="top-area">
          <img src="../../../assets/background.jpg" alt="">
          <div class="site-info">
            <h1 class="site-name">DB'Blog</h1>
            <h1 class="site-desc">Write the code, Change the World</h1>
          </div>
        </div>
        <div class="sidebar-menus">
          <div class="site-nav">
            <p>
              <iv-icon type="map"></iv-icon>
              文章导航
            </p>
          </div>
          <ul class="nav-menu">
            <!-- 类别导航 -->
            <li class="nav-dropdown-container" v-for="category_level1 in categorys" :key="category_level1.id">
              <iv-icon type="minus-round"></iv-icon>&nbsp;
              <a class="nav-link" :href="'/articles/category/'+category_level1.id" >{{category_level1.name}}<span class="arrow"></span>
              </a>
              <ul class="nav-dropdown" v-for="category_level2 in category_level1.children" :key="category_level2.id">
                <li >
                  <iv-icon type="minus-round"></iv-icon>
                  <a class="nav-link" :href="'/articles/category/'+category_level2.id" >{{ category_level2.name}}</a>
                  <ul class="nav-dropdown">
                    <li>
                      <iv-icon type="minus-round"></iv-icon>&nbsp;
                      <a class="nav-link" :href="'/articles/category/'+category_level3.id" v-for="category_level3 in category_level2.children" :key="category_level3.id">{{ category_level3.name }}</a>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
            <!-- 自定义的导航 -->
            <!--<li class="nav-dropdown-container" >-->
              <!--<iv-icon type="minus-round"></iv-icon>&nbsp;-->
              <!--<a class="nav-link" >-->
                <!--自定义的导航-->
              <!--</a>-->
            <!--</li>-->
          </ul>
        </div>
      </div>
    </div>
    <div class="mask" @click.prevent="toggleSideBar"></div>
  </div>
</template>

<script type="text/ecmascript-6">
import {mixin} from '@/utils'

export default {
  name: 'side-bar',
  data () {
    return {
      show: false,
      showNav: false
    }
  },
  props: {
    categorys: Array
  },
  mixins: [mixin],
  beforeRouteUpdate (to, from, next) {
    next()
  },
  methods: {
    rootRouterLink (category) {
      let router = {}
      router.name = category.category_type
      return router
    },
    routerLink (category) {
      let router = {}
      router.name = category.category_type
      router.params = {}
      router.params['id'] = category.id
      return router
    },
    toggleSideBar () {
      this.show = !this.show
      this.showNav = !(this.$route.name === 'article' ||
          this.$route.name === 'book' ||
          this.$route.name === 'book/note' ||
          this.$route.name === 'movie' ||
          this.$route.name === 'album'
      )
    }
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "./stylus/sidebar.styl";
</style>
