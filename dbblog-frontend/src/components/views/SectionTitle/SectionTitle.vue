<template>
  <div class="section-title">
    <div class="title">
      <span class="square"></span>
      <span class="main-title">{{mainTitle}}</span>
      <span class="vertical-line"></span>
      <span class="sub-title">{{subTitle}}</span>
      <span class="view-more"><a :href="tipHref" v-if="tipText">{{tipText}} <iv-icon type="arrow-right-b"></iv-icon></a></span>
    </div>
    <div class="menu">
      <slot name="menu"></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: {
    mainTitle: '',
    subTitle: '',
    tipText: '',
    tipHref: ''
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl"

  .section-title
    display flex
    justify-content space-between
    padding 20px 0
    height 90px
    line-height 50px
    text-align left
    box-sizing border-box
    @media only screen and (max-width: 768px)
      padding 10px 0
      height 70px
    .title
      .square
        display inline-block
        height 30px
        width 30px
        background #000000
        vertical-align middle
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
    .menu
      height 100%
      line-height inherit
      float right
      @media only screen and (max-width: 768px)
        display none

</style>
