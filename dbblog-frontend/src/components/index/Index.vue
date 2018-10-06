<template>
  <div class="main-wrapper">
    <router-view name="header"></router-view>
    <router-view name="content"></router-view>
    <router-view name="footer"></router-view>
  </div>
</template>

<script>
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .main-wrapper
    background-color #fff
    width 100%
    margin 0 auto
</style>
