<template>
  <div class="article-page-header">
    <div class="tags">
      <iv-tag color="blue">标签二</iv-tag>
      <iv-tag color="green">标签三</iv-tag>
      <iv-tag color="red">标签四</iv-tag>
      <iv-tag color="yellow">标签五</iv-tag>
    </div>
    <p class="title">Action | 关于Action的解释</p>
    <iv-row>
      <iv-col :xs="24" :sm="10" :md="10" :lg="10" style="padding-left: 0;padding-right: 0;">
        <p class="info"><span class="author">By / <a href="">{{article.author}}</a></span><span
                class="publish-time">  At time / <a href="">{{article.publish_time}}</a></span></p>
      </iv-col>
      <iv-col :xs="24" :sm="14" :md="14" :lg="14" style="padding-left: 0;padding-right: 0;">
        <p class="operate_info">
          <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{article.readings}} 阅读</a></span> |
          <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{article.comments}} 评论</a></span> |
          <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{article.likes}} 喜欢</a></span>
        </p>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      article: {
        'id': 1,
        'title': '被太阳晒过的风 第四十四章',
        'author': '子夜晨星',
        'publish_time': '2017-10-22 17:57:08',
        'desc': '-1-见了男朋友回来之后喝了一杯没怎么泡好的红糖水和一杯满满的白开水。去上了次厕所，然后坐在床上的时候开始咳嗽不止，去洗了把脸，恍然地瞥见镜子中的自己看起来并不...',
        'readings': '148',
        'comments': '2',
        'likes': '20'
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl";

  .article-page-header
    text-align left
    padding 25px 5px 10px 5px
    @media only screen and (max-width: 768px)
      padding-top 10px
    @media screen and (min-width: 768px)
      padding-top 10px
    @media screen and (min-width: 992px)
      padding-top 25px
    .tags
      margin-bottom 18px
    .title
      font-size 27px
      line-height 33px
      font-weight 500
      color $color-typegraphy-title
      margin-bottom 23px
    .info
      margin-top 10px
      font-size 14px
      line-height 18px
      font-weight 200
      a
        color: #777
        cursor pointer
        &:hover
          color $color-main-primary
          text-decoration underline
      .publish-time
        margin-left 20px
    .operate_info
      text-align right
      font-size 14px
      margin 15px 0
      @media only screen and (max-width: 768px)
        text-align left
      span
        margin-right 10px
        + span
          margin-left 10px
        a
          cursor pointer
          &:hover
            color $color-main-primary
            text-decoration underline

</style>
