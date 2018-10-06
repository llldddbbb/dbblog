<template>
  <div class="title-menu-timeline">
    <ul class="list clearfix">
      <li><a class="active" href="">最新</a></li>
      <li><a href="">点赞最多</a></li>
      <li><a href="">评论最多</a></li>
      <li><a href="">推荐</a></li>
    </ul>
    <div class="refresh">
      <a @click="refresh" title="刷新">
        <iv-icon type="refresh"></iv-icon>
      </a>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  methods: {
    refresh () {
      console.log('refresh')
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../../common/stylus/theme.styl";

  .title-menu-timeline
    display flex
    ul
      li
        float left
        margin 0 6px
        a
          padding 6px 0
          &:hover, &.active
            color $color-main-primary
            border-bottom 2px solid $color-main-primary
    .refresh
      margin-left 15px
      line-height inherit
      a
        font-size 20px
        color $color-gradually-gray-41
        cursor pointer
        &:hover
          color $color-main-primary

</style>
