<template>
  <div class="article-list-year-title" :class="styleType">
    <span class="date"><i class="ivu-icon" :class="iconType"></i></span>
    <span class="main-title">{{date}}</span>
    <span class="vertical-line"></span>
    <span class="sub-title">共 <a >{{count}}</a> 篇</span>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: {
    dateType: {
      default: 'year'
    },
    date: '',
    count: '',
    tipText: {
      default: 'View All'
    },
    hrefText: ''
  },
  computed: {
    styleType () {
      return 'style-date-' + this.dateType
    },
    iconType () {
      return {
        'ivu-icon-calendar': this.dateType === 'year',
        'ivu-icon-clock': this.dateType === 'month'
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl"
  @import "../../../common/stylus/theme.styl"

  .article-list-year-title
    position relative
    text-align left
    &.style-date-year
      padding 15px 0
      line-height 50px
      @media only screen and (max-width: 720px)
        padding 8px 0
      .date
        display inline-block
        font-size 40px
        color lighten($color-main-primary, 10%)
        vertical-align middle
        &::before
          content ''
          position absolute
          top 0px
          left 17px
          width 4px
          background-color: $color-main-primary
          height 20%
          border-radius 0 0 2px 2px
        &::after
          content ''
          position absolute
          bottom 0px
          left 17px
          width 4px
          background-color: $color-main-primary
          height 20%
          border-radius 2px 2px 0 0
      .main-title
        display inline-block
        vertical-align middle
        font-size 22px
        padding 0 10px
      .vertical-line
        display inline-block
        height 20px
        width 1px
        background-color #333
        vertical-align middle
      .sub-title
        display inline-block
        vertical-align middle
        font-size 18px
        padding-left 10px
      .view-more
        display inline-block
        vertical-align middle
        font-size 14px
        padding-left 10px
        a
          color $color-main-primary
          font-weight 500
          cursor pointer
          &:hover
            text-decoration underline
    &.style-date-month
      padding 15px 0
      line-height 30px
      @media only screen and (max-width: 720px)
        padding 8px 0
      .date
        display inline-block
        margin-left 8px
        font-size 24px
        color lighten($color-typegraphy-title, 20%)
        vertical-align middle
        &::before
          content ''
          position absolute
          top 0px
          left 17px
          width 4px
          background-color: $color-main-primary
          height 20%
          border-radius 0 0 2px 2px
        &::after
          content ''
          position absolute
          bottom 0px
          left 17px
          width 4px
          background-color: $color-main-primary
          height 20%
          border-radius 2px 2px 0 0
      .main-title
        display inline-block
        vertical-align middle
        font-size 18px
        padding 0 10px
      .vertical-line
        display inline-block
        height 20px
        width 1px
        background-color #333
        vertical-align middle
      .sub-title
        display inline-block
        vertical-align middle
        font-size 14px
        padding-left 10px
      .view-more
        display inline-block
        vertical-align middle
        font-size 14px
        padding-left 10px
        a
          color $color-main-primary
          font-weight 500
          cursor pointer
          &:hover
            text-decoration underline
</style>
