<template>
  <div class="article-list-cell">
    <a href="">
      <iv-row type="flex">
        <iv-col :xs="24" :sm="24" :md="textSpan" :lg="textSpan" :order="textOrderType" style="padding-left: 0;padding-right: 0;">
          <div class="text-wrapper">
            <h4 class="title">
              <router-link to="/article">{{article.title}}</router-link>
            </h4>
            <div class="tags">
              <iv-tag color="blue">标签二</iv-tag>
              <iv-tag color="green">标签三</iv-tag>
              <iv-tag color="red">标签四</iv-tag>
              <iv-tag color="yellow">标签五</iv-tag>
            </div>
            <p class="desc">{{article.desc}}<a href=""> View More
              <iv-icon type="arrow-right-b"></iv-icon>
            </a></p>
            <p class="operate_info">
              <span class="publish-time">At time / <a href="">{{article.publish_time}}</a></span>
              <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{article.readings}} 阅读</a></span>
              <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{article.comments}} 评论</a></span>
              <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{article.likes}} 喜欢</a></span>
            </p>
          </div>
        </iv-col>
        <iv-col :xs="0" :sm="0" :md="imgSpan" :lg="imgSpan" :order="imgOrderType" style="padding-left: 0px;padding-right: 0px">
          <div class="img-wrapper" :class="themeClass">
            <img src="../../../assets/photowall/photowall_pic_4.jpg" alt="">
          </div>
        </iv-col>
      </iv-row>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">
const ARTICLE_TYPE_BIG_IMAGE = 2
const ARTICLE_TYPE_NO_IMAGE = 3

export default {
  props: {
    article: {
      Type: Object
    }
  },
  computed: {
    textOrderType: function () {
      return this.article.type === ARTICLE_TYPE_BIG_IMAGE ? 2 : 1
    },
    imgOrderType: function () {
      return this.article.type === ARTICLE_TYPE_BIG_IMAGE ? 1 : 2
    },
    textSpan: function () {
      if (this.article.type === ARTICLE_TYPE_BIG_IMAGE) {
        return 24
      } else if (this.article.type === ARTICLE_TYPE_NO_IMAGE) {
        return 24
      } else {
        return 17
      }
    },
    imgSpan: function () {
      if (this.article.type === ARTICLE_TYPE_BIG_IMAGE) {
        return 24
      } else if (this.article.type === ARTICLE_TYPE_NO_IMAGE) {
        return 0
      } else {
        return 7
      }
    },
    themeClass: function () {
      if (this.article.type === ARTICLE_TYPE_BIG_IMAGE) {
        return 'big-image'
      } else {
        return ''
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../../common/stylus/index.styl";

  .article-list-cell
    margin-bottom 15px
    > a
      display block
      cursor default
      border 1px solid $color-border
      &:hover
        border 1px solid $color-border-hover
        box-shadow 2px 2px 3px $color-border
      .text-wrapper
        padding 20px 20px 0 20px
        text-align left
        @media only screen and (max-width: 768px)
          padding 15px 15px 0 15px
        .title
          font-size 23px
          font-weight 100
          line-height 27px
          a
            color $color-typegraphy-title
            cursor pointer
            &:hover
              color $color-typegraphy-title-hover
              text-decoration underline
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
        .line
          width 50px
          margin-top 30px
          border-1px(rgba(7, 17, 27, 0.4))
          &::after
            margin-bottom 15px
        .tags
          margin: 8px 0
        .desc
          color #666
          font-size 14px
          line-height 20px
          font-weight 200
          a
            color $color-main-primary
            font-weight 500
            cursor pointer
            &:hover
              text-decoration underline
        .operate_info
          font-size 14px
          margin 15px 0 20px
          span
            margin-right 8px
            + span
              margin-left 8px
            a
              cursor pointer
              &:hover
                color $color-main-primary
                text-decoration underline
      .img-wrapper
        padding-bottom: 85%
        width: 100%
        height: 0
        overflow hidden
        &.big-image
          padding-bottom 26%
        img
          width 100%
</style>
