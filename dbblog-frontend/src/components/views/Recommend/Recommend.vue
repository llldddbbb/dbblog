<template>
  <div class="recommend">
    <panel :title="'推荐阅读'">
      <div slot="content" class="content">
        <div class="top">
          <a href="">
            <p class="title">你以为毕业后还能去搬砖？别做梦了，机器人已经连房子都会盖啦</p>
            <div class="tags">
              <iv-tag color="blue">标签二</iv-tag>
              <iv-tag color="green">标签三</iv-tag>
              <iv-tag color="red">标签四</iv-tag>
              <iv-tag color="yellow">标签五</iv-tag>
            </div>
            <p class="info">
              <span class="time">2017-11-17 09:19</span>
              <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{article.likes}} </a></span>
              <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{article.comments}} </a></span>
              <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{article.readings}} </a></span>
            </p>
            <div class="img">
              <img src="../../../assets/recommend.jpg" alt="">
            </div>
            <p class="desc">希望不管是机器人的设计者还是使用者都能明白，使用机器的目的是帮助我们实现更多创造性的工作，保护我们免于危险，激发我们的人性和爱。</p>
          </a>
        </div>
        <ul class="others">
          <li>
            <a href="">
              <p class="title">你以为毕业后还能去搬砖？别做梦了，机器人已经连房子都会盖啦</p>
              <p class="info">
                <span class="time">2017-11-17 09:19</span>
                <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{article.likes}} </a></span>
                <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{article.comments}} </a></span>
                <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{article.readings}} </a></span>
              </p>
            </a>
          </li>
          <li>
            <a href="">
              <p class="title">你以为毕业后还能去搬砖？别做梦了，机器人已经连房子都会盖啦</p>
              <p class="info">
                <span class="time">2017-11-17 09:19</span>
                <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{article.likes}} </a></span>
                <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{article.comments}} </a></span>
                <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{article.readings}} </a></span>
              </p>
            </a>
          </li>
        </ul>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from '@/components/utils/Panel'

export default {
  data () {
    return {
      article: {
        'id': 1,
        'title': '被迫向现实低头，再爱也只能到此为止',
        'author': '男孩与榴莲',
        'publish_time': '2017-10-22 17:57:08',
        'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
        'readings': '148',
        'comments': '2',
        'likes': '20'
      }
    }
  },
  components: {
    'panel': Panel
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl";

  .recommend
    .content
      padding 5px 20px
    .top, .others
      a
        display block
        overflow hidden
        .tags
          margin-bottom 10px
        .title
          text-align justify
          color $color-gradually-gray-41
          font-size 16px
          line-height 23px
          margin-bottom 5px
        .info
          margin 5px 0 0px
          span
            font-size 13px
            line-height 18px
            font-weight 100
            color $color-secondary-info
            + span
              float right
              margin-left 10px
          a
            display inline-block
            color: #777
            cursor pointer
            &:hover
              color $color-main-primary
              text-decoration underline
        .img
          padding-bottom: 40%
          width: 100%
          height: 0
          margin 5px 0
          overflow hidden
          img
            width 100%
            transition: All 0.4s ease-in-out
            transform: scale(1.0)
            zoom: 1.0
        .desc
          text-align justify
          color $color-secondary-info
          font-size 13px
          line-height 20px
          margin 5px 0 0
        &:hover
          .title
            color $color-main-primary
          img
            transition: All 0.4s ease-in-out
            transform: scale(1.05)
            zoom: 1.05
    .others
      li
        margin-top 10px
        padding-top 10px
        border-top 1px solid $color-border

</style>
