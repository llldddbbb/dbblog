<template>
  <div class="bookNote-cell">
      <iv-row type="flex">
        <iv-col :xs="24" :sm="24" :md="textSpan" :lg="textSpan" :order="textOrderType" style="padding-left: 0;padding-right: 0;">
          <div class="text-wrapper">
            <h4 class="title">
              <a :href="'/bookNote/'+bookNote.id">{{bookNote.title}}</a>
              <span class="special" v-if="bookNote.top>0" title="置顶">置顶</span>
            </h4>
            <p class="book-info">
              <iv-icon type="ios-book"></iv-icon>&nbsp;&nbsp;{{bookNote.book.title}}&nbsp;
              <iv-icon type="paintbrush"></iv-icon>&nbsp;&nbsp;{{bookNote.book.author }}
            </p>
            <p class="book-info">
              <iv-icon type="document-text"></iv-icon>&nbsp;&nbsp;Chapter：{{bookNote.chapter | textLineBreak(70) }}
            </p>
            <div class="tags">
              <iv-tag :color="tag.id | mapTagColor" :key="tag.id" type="border" v-for ="(tag) in bookNote.tagList">{{tag.name}}</iv-tag>
            </div>
            <p class="desc">{{bookNote.description | filterHtml | textLineBreak(70) }}<a :href="'/bookNote/'+bookNote.id"> 查看更多
              <iv-icon type="arrow-right-b"></iv-icon>
            </a></p>
            <p class="operate_info">
              <span class="publish-time">At time / <a>{{bookNote.createTime | socialDate }}</a></span>
              <span class="readings"><a><iv-icon type="eye"></iv-icon> {{bookNote.readNum}} 阅读</a></span>
              <span class="likes"><a @click="likePost(bookNote)" ><iv-icon type="heart"></iv-icon> {{bookNote.likeNum}} 喜欢</a></span>
            </p>
          </div>
        </iv-col>
        <iv-col :xs="0" :sm="0" :md="imgSpan" :lg="imgSpan" :order="imgOrderType" style="padding-left: 0px;padding-right: 0px">
          <div class="img-wrapper" :class="themeClass">
            <img :src="bookNote.cover" alt="">
          </div>
        </iv-col>
      </iv-row>
      <a class="toggle-arrow" @click="toggleBookInfo" :class="{show: showBookInfo}">
        {{ this.showBookInfo ? '隐藏《'+ bookNote.book.title + '》':'查看《' + bookNote.book.title + '》'}} &nbsp;<iv-icon type="chevron-up" :class="{show: showBookInfo}"></iv-icon>
      </a>
      <a :href="'/book/'+ bookNote.book.id">
      <div class="book-infos" :class="{show: showBookInfo}"  >
        <div class="book-infos-wrapper">
          <div class="img">
            <div class="container">
              <div class="bracket"></div>
              <div class="target">
                <img :src="bookNote.book.cover" alt="">
              </div>
            </div>
          </div>
          <div class="book-info">
            <p class="desc"><span>作者：</span>{{ bookNote.book.author }}</p>
            <iv-progress :percent="bookNote.book.progress" :stroke-width="6">
              <iv-icon type="checkmark-circled"></iv-icon>
              <span class="progress">{{bookNote.book.progress}}%</span>
            </iv-progress>
            <p class="desc">{{ bookNote.book.description | filterHtml | textLineBreak(140) }}</p>
            <iv-tag type="border" v-for="tag in bookNote.book.tagList" :key="tag.name" class="border-tag">{{ tag.name }}</iv-tag>
          </div>
        </div>
      </div>
      </a>
  </div>
</template>

<script type="text/ecmascript-6">
import { mixin } from '@/utils'
const ARTICLE_TYPE_BIG_IMAGE = 1
const ARTICLE_TYPE_NO_IMAGE = 2

export default {
  props: {
    bookNote: {
      Type: Object
    },
    type: ''
  },
  data () {
    return {
      showBookInfo: false
    }
  },
  mixins: [mixin],
  computed: {
    textOrderType: function () {
      return this.bookNote.coverType === ARTICLE_TYPE_BIG_IMAGE ? 2 : 1
    },
    imgOrderType: function () {
      return this.bookNote.coverType === ARTICLE_TYPE_BIG_IMAGE ? 1 : 2
    },
    textSpan: function () {
      if (this.bookNote.coverType === ARTICLE_TYPE_BIG_IMAGE) {
        return 24
      } else if (this.bookNote.coverType === ARTICLE_TYPE_NO_IMAGE) {
        return 24
      } else {
        return 17
      }
    },
    imgSpan: function () {
      if (this.bookNote.coverType === ARTICLE_TYPE_BIG_IMAGE) {
        return 24
      } else if (this.bookNote.coverType === ARTICLE_TYPE_NO_IMAGE) {
        return 0
      } else {
        return 7
      }
    },
    themeClass: function () {
      if (this.bookNote.coverType === ARTICLE_TYPE_BIG_IMAGE) {
        return 'big-image'
      } else {
        return ''
      }
    }
  },
  methods: {
    toggleBookInfo () {
      this.showBookInfo = !this.showBookInfo
    },
    likePost (post) {
      this.$http({
        url: this.$http.adornUrl('/bookNote/like/' + post.id),
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
  .bookNote-cell
    margin-bottom 10px
    background-color $default-cell-background-color
    border 1px solid $default-border-color
    img
      width 100%
      transition: All 0.4s ease-in-out
      transform: scale(1.0)
      zoom: 1.0
    &:hover
      border 1px solid $default-border-hover-color
      img
        transform: scale(1.05)
        zoom: 1.02
    .text-wrapper
      padding 20px 20px 0 20px
      text-align left
      @media only screen and (max-width: 768px)
        padding 10px 10px 0 10px
      .title
        font-size 23px
        font-weight 100
        line-height 27px
        @media only screen and (max-width: 768px)
          font-size 17px
          line-height 23px
          word-wrap break-word
        a
          color $default-title-color
          cursor pointer
          &:hover
            color $default-title-hover-color
            text-decoration underline
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
      .book-info
        font-size 14px
        font-weight 300
        margin-top 8px
        color $default-info-color
        word-wrap break-word
        > i
          font-size 14px
      .tags
        margin 8px 0
      .desc
        font-size 14px
        line-height 20px
        font-weight 200
        color $default-desc-color
        @media only screen and (max-width: 768px)
          font-size 13px
        a
          font-weight 500
          color $default-desc-hover-color
          cursor pointer
          &:hover
            text-decoration underline
      .operate_info
        font-size 14px
        margin 15px 0 20px
        span
          margin-right 8px
          a
            color $default-info-color
            cursor pointer
            &:hover
              color $default-info-hover-color
              text-decoration underline
          + span
            margin-left 8px
        @media only screen and (max-width: 768px)
          font-size 13px
          margin 10px 0
          span
            margin-right 4px
            + span
              margin-left 4px
    .img-wrapper
      padding-bottom: 85%
      width 100%
      height 0
      overflow hidden
      &.big-image
        padding-bottom 26%
        box-shadow 1px 1px 1px $default-border-color
    .toggle-arrow
      display block
      text-align center
      padding 10px 0
      background-color $default-cell-toggle-background-color
      &:hover
        color $default-info-hover-color
      > i
        transition: All 0.4s ease-in-out
        transform rotateX(180deg)
        &.show
          transform rotateX(0deg)
    .book-infos
      max-height 0
      transition All 0.5s ease-in-out
      overflow hidden
      cursor pointer
      &.show
        max-height 350px
      .book-infos-wrapper
        display flex
        padding 20px
        @media only screen and (max-width: 768px)
          padding 10px
        .img
          position relative
          display block
          flex 0 0 140px
          width 140px
          overflow hidden
          margin-right 20px
          border 1px solid $default-border-color
          box-shadow 1px 1px 1px $default-border-color
          @media only screen and (max-width: 768px)
            display none
            flex 0 0 0
            width 0
          .container
            width 100%
            position relative
            overflow hidden
            .bracket
              margin-top 135%
            .target
              position absolute
              top 0
              bottom 0
              left 0
              right 0
              > img
                width 100%
                height 100%
                transition: All 0.4s ease-in-out
                transform: scale(1.0)
                zoom: 1.0
        .book-info
          flex-grow 1
          .title
            font-size 20px
            line-height 28px
            font-weight 500
            color $default-title-color
            margin-bottom 5px
            text-align justify
          .desc
            font-size 13px
            font-weight 100
            line-height 20px
            color $default-desc-color
            text-align justify
            margin 10px 0 5px
</style>
