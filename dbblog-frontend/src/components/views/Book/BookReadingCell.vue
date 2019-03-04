<template>
  <div class="book-reading-cell">
    <h4>正在阅读</h4>
    <a >
      <div class="img">
        <div class="container">
          <div class="bracket"></div>
          <div class="target">
            <img :src="book.cover" alt="">
          </div>
        </div>
      </div>
      <div class="book-info">
        <p class="title">
          <a :href="`/book/${book.id}`"> {{book.title}}</a>
        </p>
        <p class="desc"><span>作者：</span>{{ book.author }}</p>
        <iv-progress :percent="book.progress" :stroke-width="6">
          <iv-icon type="checkmark-circled"></iv-icon>
          <span>{{ book.progress }}%</span>
        </iv-progress>
        <p class="desc">{{ book.description | filterHtml | textLineBreak(140) }}</p>
        <iv-tag type="border" v-for="tag in book.tagList" :key="tag.id">{{ tag.name }}</iv-tag>
      </div>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">
import {mixin} from '@/utils'

export default {
  name: 'book-reading-cell',
  props: {
    book: {
      Type: Object,
      default: undefined
    }
  },
  mixins: [mixin],
  methods: {}
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .book-reading-cell
    border 1px solid $default-border-color
    padding 20px
    background-color $default-background-color
    @media only screen and (max-width: $responsive-sm)
      padding 10px 10px 15px
    @media screen and (min-width: $responsive-sm)
      padding 14px
    @media screen and (min-width: $responsive-md)
      padding 16px
    @media screen and (min-width: $responsive-lg)
      padding 20px
    h4
      color $default-title-color
      @media only screen and (max-width: $responsive-sm)
        font-size 22px
        line-height 24px
        margin-bottom 10px
      @media screen and (min-width: $responsive-sm)
        font-size 23px
        line-height 30px
        margin-bottom 15px
      @media screen and (min-width: $responsive-md)
        font-size 23px
        line-height 30px
        margin-bottom 18px
      @media screen and (min-width: $responsive-lg)
        font-size 23px
        line-height 30px
        margin-bottom 20px
    > a
      display flex
      .img
        position relative
        flex 0 0 140px
        width 140px
        overflow hidden
        margin 0 30px 10px 0
        border 1px solid $default-border-hover-color
        box-shadow 1px 1px 1px $default-border-color
        @media only screen and (max-width: $responsive-sm)
          display none
        @media screen and (min-width: $responsive-sm)
          display none
        @media screen and (min-width: $responsive-lg)
          display block
        .container
          width 100%
          position relative
          overflow hidden
          .bracket
            margin-top 140%
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
        margin-bottom 8px
        .title
          font-size 20px
          line-height 28px
          font-weight 500
          margin-bottom 5px
          text-align justify
          a
            color $default-title-color
            &:hover
              color $default-title-hover-color
        .desc
          font-size 13px
          font-weight 100
          line-height 20px
          color $default-desc-color
          text-align justify
          margin 10px 0 5px
          > span
            color $default-info-color
            font-weight 300
</style>
