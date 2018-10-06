<template>
  <div id="article-page-content">
    <slot name="content"></slot>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/article.styl";

  #article-page-content
    padding 5px
</style>
