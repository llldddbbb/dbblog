<template>
  <div class="archive-list-cell">
    <p>
      <a href="">
        <iv-tag>{{article.publish_time | formateDate}}</iv-tag>
        <span class="title">{{article.title}}</span>
      </a>
    </p>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: {
    article: {
      Type: Object
    }
  },
  filters: {
    formateDate: function (date) {
      return date.slice(0, 10)
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl"

  .archive-list-cell
    position relative
    padding 5px 0
    &::after
      content ''
      position absolute
      min-height 25px
      top 0
      bottom 0
      left 17px
      width 4px
      background-color: $color-main-primary
    p
      font-size 17px
      line-height 25px
      margin-left 45px
      @media only screen and (max-width: 720px)
        font-size 13px
      a
        color $color-typegraphy-title
        &:hover
          color $color-typegraphy-title-hover
        .title
          margin-left 5px
          font-weight 100
</style>
