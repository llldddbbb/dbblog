<template>
  <div class="book-cell">
    <a>
      <iv-row type="flex">
        <iv-col :xs="24" :sm="24" :md="textSpan" :lg="textSpan" :order="textOrderType" style="padding-left: 0;padding-right: 0;">
          <div class="text-wrapper">
            <h4 class="title">
              <a :href="'/book/'+book.id">{{book.title}}</a>
              <span class="special" v-if="book.reading>0" title="正在阅读">正在阅读</span>
            </h4>
            <div class="tags">
              <iv-tag :color="tag.id | mapTagColor" :key="tag.id" type="border" v-for ="(tag) in book.tagList">{{tag.name}}</iv-tag>
            </div>
            <p class="desc">{{book.description | filterHtml | textLineBreak(70) }}<a :href="'/book/'+book.id"> 查看更多
              <iv-icon type="arrow-right-b"></iv-icon>
            </a></p>
            <p class="operate_info">
              <span class="publish-time">At time / <a >{{book.createTime | socialDate }}</a></span>
              <span class="readings"><a ><iv-icon type="eye"></iv-icon> {{book.readNum}} 阅读</a></span>
              <span class="likes"><a @click="likePost(book)"><iv-icon type="heart"></iv-icon> {{book.likeNum}} 喜欢</a></span>
            </p>
          </div>
        </iv-col>
        <iv-col :xs="0" :sm="0" :md="imgSpan" :lg="imgSpan" :order="imgOrderType" style="padding-left: 0px;padding-right: 0px">
          <div class="img-wrapper" :class="themeClass">
            <img :src="book.cover" alt="">
          </div>
        </iv-col>
      </iv-row>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">
import { mixin } from '@/utils'
const ARTICLE_TYPE_NO_IMAGE = 2

export default {
  props: {
    book: {
      Type: Object
    },
    type: ''
  },
  mixins: [mixin],
  computed: {
    textOrderType: function () {
      return ARTICLE_TYPE_NO_IMAGE
    },
    imgOrderType: function () {
      return ARTICLE_TYPE_NO_IMAGE
    },
    textSpan: function () {
      return 24
    },
    imgSpan: function () {
      return 0
    },
    themeClass: function () {
      return ''
    }
  },
  methods: {
    likePost (post) {
      this.$http({
        url: this.$http.adornUrl('/book/like/' + post.id),
        method: 'put',
        data: this.$http.adornData()
      }).then(({data}) => {
        if (data && data.code === 200) {
          post.likeNum += 1
          this.$Message.success('点赞成功')
        }
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../../common/stylus/index.styl";

  .book-cell
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
          span.special
            border-radius $border-radius
            font-size 12px
            font-weight 100
            padding 3px 5px
            margin-left 1px
            vertical-align top
            color $default-background-color
            background $iview-secondary-warning-color
            cursor pointer
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
