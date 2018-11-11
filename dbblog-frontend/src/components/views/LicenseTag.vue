<template>
  <div class="license-tag">
    <a :href="link" target="_blank">
      <span class="name">License</span>
      <span class="type">{{type}}</span>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: {
    link: {
      default: 'https://creativecommons.org/licenses/by-sa/4.0/deed.zh'
    },
    type: {
      default: 'CC BY-SA 4.0'
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl"

  .license-tag
    margin 10px 0
    height 30px
    font-size 0
    a
      display inline-block
      &:hover
        cursor pointer
        text-decoration none
      .name, .type
        display inline-block
        padding 5px 8px
        font-size 15px
        text-align center
      .name
        background-color $color-main-primary
        color $white
        border 1px solid $color-main-primary
        font-weight 700
      .type
        background-color $white
        color $color-main-primary
        border 1px solid $color-main-primary

</style>
