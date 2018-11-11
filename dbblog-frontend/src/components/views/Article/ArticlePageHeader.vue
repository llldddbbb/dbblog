<template>
  <div class="article-page-header">
    <div class="tags">
      <iv-tag :color="getTagColor(index)" v-for="(tag , index) in article.tagList" :key="tag.tagId">{{tag.tagName}}</iv-tag>
    </div>
    <p class="title">{{article.title}}</p>
    <iv-row>
      <iv-col :xs="24" :sm="10" :md="10" :lg="10" style="padding-left: 0;padding-right: 0;">
        <p class="info"><span class="author">By / <a href="">{{article.author}}</a></span><span
                class="publish-time">  At time / <a href="">{{article.createTime}}</a></span></p>
      </iv-col>
      <iv-col :xs="24" :sm="14" :md="14" :lg="14" style="padding-left: 0;padding-right: 0;">
        <p class="operate_info">
          <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{article.readNum}} 阅读</a></span> |
          <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{article.commentNum}} 评论</a></span> |
          <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{article.likeNum}} 喜欢</a></span>
        </p>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import { mapTagColor } from '@/utils'
export default {
  props: {
    article: {}
  },
  methods: {
    getTagColor: function (index) {
      return mapTagColor(index)
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
