<template>
  <div class="movie-page-header">
    <p class="title">东方快车谋杀案<span>（Murder on the Orient Express）</span></p>
    <p class="desc">影片讲述大侦探在东方快车上巧破一桩谋杀奇案的故事。</p>
    <div class="infos">
      <div class="img">
        <img src="../../../../assets/movie.jpg" alt="">
      </div>
      <div class="info">
        <p class="director"><span>导演：</span>肯尼思·布拉纳</p>
        <p class="director"><span>主演：</span>肯尼思·布拉纳,约翰尼·德普,佩内洛普·克鲁兹,黛茜·雷德利,威廉·达福,朱迪·丹奇,米歇尔·菲佛,乔什·盖德,小莱斯利·奥多姆,王千源,俞飞鸿
        </p>
        <p class="director"><span>类型：</span>犯罪,剧情,悬疑</p>
        <p class="director"><span>制片国家/地区：</span>美国</p>
        <p class="director"><span>语言：</span>英语</p>
        <p class="director"><span>片长：</span>114分钟</p>
        <p class="director"><span>剧情介绍：</span>影片讲述大侦探在东方快车上巧破一桩谋杀奇案的故事。波洛乘上东方快车，夜间三次被吵醒，第二天清晨便发现同车的美国富商雷切尔被人谋杀，死者被戳了12刀。波洛根据他所观察到的各...
        </p>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
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
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../../common/stylus/theme.styl";

  .movie-page-header
    position relative
    width auto
    z-index 10
    @media only screen and (max-width: 768px)
      margin 0 5px 0 5px
      padding-top 5px
    @media screen and (min-width: 768px)
      margin 0 10px 0 10px
      padding-top 10px
    @media screen and (min-width: 992px)
      margin 0 35px 0 35px
      padding-top 15px
    @media screen and (min-width: 1200px)
      width 1200px
      margin 0 auto
      padding-top 20px
    .title
      color $color-gradually-gray-91
      font-size 22px
      font-weight 700
      line-height 25px
      margin-bottom 5px
    .desc
      color $color-gradually-gray-91
      font-size 15px
      line-height 20px
      padding-bottom 10px
      border-bottom 1px solid rgba(222, 222, 222, 0.3)
    .infos
      padding 10px 0 10px
      .img
        float left
        margin-bottom 10px
        height: 0
        overflow hidden
        @media only screen and (max-width: 768px)
          width: 30%
          padding 0 10px 39% 0
          margin-bottom 5px
        @media screen and (min-width: 768px)
          width: 25%
          padding 0 10px 33% 0
          margin-bottom 5px
        @media screen and (min-width: 992px)
          width: 28%
          padding 0 15px 39% 0
        @media screen and (min-width: 1200px)
          width: 25%
          padding 0 15px 35% 0
        img
          width 100%
      .info
        p
          color $color-gradually-gray-91
          font-size 15px
          line-height 22px
          margin-bottom 8px
          font-weight 100
          span
            font-weight 700

</style>
