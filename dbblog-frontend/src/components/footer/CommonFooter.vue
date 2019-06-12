<template>
  <div class="common-footer">
    <p class="copyright">
      <a href="http://www.miibeian.gov.cn/" target="_blank">{{ siteInfo.icp }}</a>
      <span>|</span>
      {{ siteInfo.copyright }}
      <span>|</span>
      <span v-html="siteInfo.copyright_desc"></span>
    </p>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      siteInfo: {
        icp: '粤ICP备17117756号',
        copyright: '版权所有 © 2008 - 2019',
        copyright_desc: '以商业目的使用本网站内容需获许可，非商业目的使用授权遵循CC BY-NC 4.0'
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl"
  .common-footer
    padding 15px 5px
    margin-top 50px
    text-align center
    background $default-background-color
    border-top 1px solid $default-border-color
    z-index 99
  .copyright
    margin 10px
    color $default-title-color
    a
      color $default-link-color
      &:hover
        color $default-link-hover-color
</style>
