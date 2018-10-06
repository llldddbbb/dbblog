<template>
  <div class="friend-links">
    <panel :title="'友情链接'">
      <ul class="link-list" slot="content">
        <li>
          <a href="">
            <img src="../../assets/avatar.png" alt="">
            <div class="right">
              <p class="title">百度的个人</p>
              <p class="link">www.baidu.com</p>
            </div>
          </a>
        </li>
        <li>
          <a href="">
            <img src="../../assets/avatar.png" alt="">
            <div class="right">
              <p class="title">百度的个人</p>
              <p class="link">www.baidu.com</p>
            </div>
          </a>
        </li>
        <li>
          <a href="">
            <img src="../../assets/avatar.png" alt="">
            <div class="right">
              <p class="title">百度的个人</p>
              <p class="link">www.baidu.com</p>
            </div>
          </a>
        </li>
        <li>
          <a href="">
            <img src="../../assets/avatar.png" alt="">
            <div class="right">
              <p class="title">百度的个人</p>
              <p class="link">www.baidu.com</p>
            </div>
          </a>
        </li>
      </ul>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from '@/components/utils/Panel'

export default {
  components: {
    'panel': Panel
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";

  .friend-links
    position relative
    ul.link-list
      padding 15px 0
      li
        padding 2px 25px
        a
          display flex
          transform: translate(0, 0)
          transition: all 0.2s ease-in
          &:hover
            transform: translate(30px, 0)
            transition: all 0.2s ease-in-out
            .link
              color $dark
          img
            flex 0 0 50px
            width 50px
            height 50px
            margin 5px 0
          .right
            display flex
            flex-direction column
            padding-left 12px
            height 60px
            line-height 60px
            p
              flex 1
              text-align left
              &.title
                height 35px
                line-height 45px
                font-size 17px
                color $color-main-primary
              &.link
                height 15px
                line-height 15px
                font-weight 100

</style>
