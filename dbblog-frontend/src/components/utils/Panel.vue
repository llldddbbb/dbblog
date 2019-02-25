<template>
  <div class="panel">
    <h4>{{title}}</h4>
    <slot name="content"></slot>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: {
    title: {
      default: ''
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";

  .panel
    position relative
    background #fff
    border-left 1px solid $color-border
    h4
      font-size 18px
      padding 13px 20px 13px
      line-height 18px
      text-align left
      border-left 5px solid $default-link-hover-color
</style>
