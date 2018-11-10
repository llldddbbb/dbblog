<template>
  <div class="side-toc" ref="list">
    <panel :title="'目录'" id="side-toc">
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from '@/components/utils/Panel'
import SubSideToc from './SubSideToc'
export default {
  data () {
    return {
      directoryList: [{
        name: 'Action',
        class: 'tip0 toc-link',
        list: [{
          name: '分发 Action',
          class: 'tip1 toc-link'
        }, {
          name: '在组件中分发 Action',
          class: 'tip2 toc-link'
        }, {
          name: '组合 Action',
          class: 'tip3 toc-link',
          list: [{
            name: '现在你可以',
            class: 'tip4 toc-link',
            list: [{
              name: '另外一个',
              class: 'tip5 toc-link'
            }]
          }]
        }, {
          name: '后记',
          class: 'tip6 toc-link'
        }]
      }]
    }
  },
  methods: {
    recalcActive () {
      // 先计算list相对于页面的顶部距离
      var listScrollTop = this.$refs.list.getBoundingClientRect().top + document.documentElement.scrollTop
      // 再计算active相对于页面的顶部距离
      var activeAnode = this.$refs.list.querySelector('.active')
      if (activeAnode === null) {
        return
      }
      var activeANodeScrollTop = activeAnode.getBoundingClientRect().top + document.documentElement.scrollTop
      var activeIndicator = this.$refs.list.querySelector('.active-indicator')
      activeIndicator.style.top = activeANodeScrollTop - listScrollTop + 'px'
      activeIndicator.style.height = activeAnode.clientHeight + 'px'
    },
    beActive (event) {
      var activeANode = this.$refs.list.querySelector('.active')
      if (activeANode !== null) {
        activeANode.classList.remove('active')
      }
      event.target.classList.add('active')
      this.$nextTick(() => {
        this.recalcActive()
      })
    }
  },
  mounted: function () {
    this.recalcActive()
  },
  updated: function () {
    this.recalcActive()
  },
  components: {
    'panel': Panel,
    SubSideToc
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";

  .side-toc
    position relative
    background #fff
    h4
      font-size 18px
      padding 13px 13px 0
      line-height 18px
      text-align left
    .list
      position relative
      padding 0 25px 15px
      margin-top 10px
      .active-indicator
        position absolute
        left -1px
        right 0
        background-color $color-gradually-gray-101
        border-left 2px solid $color-main-primary
        z-index 1
        transition all 0.2s ease-out
      ul
        position relative
        padding-left: 1em
        z-index 2
        > li
          list-style-type: square
          line-height 2.2em
          > a
            cursor pointer
            display block
            height 100%
            width 100%
            margin 0
            padding 0
            font-size 16px
            color $color-gradually-gray-21
            &.active
              color $color-main-primary
          > ul > li > a
            font-size 16px
            color $color-gradually-gray-51

</style>
