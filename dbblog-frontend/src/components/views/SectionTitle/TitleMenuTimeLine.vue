<template>
  <div class="title-menu-timeline">
    <div class="date-picker">
      <iv-date-picker type="daterange" confirm placement="bottom-end" placeholder="选择存档日期区间"></iv-date-picker>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">

</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../../common/stylus/theme.styl";

  .title-menu-timeline
    .date-picker
      width 175px
      margin-left 15px
      font-size 13px
</style>
