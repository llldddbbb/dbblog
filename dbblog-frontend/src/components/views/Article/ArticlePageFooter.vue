<template>
  <div class="article-page-footer">
    <license-tag></license-tag>
    <social-section :likeNum="likeNum" :commentList="commentList"></social-section>
  </div>
</template>

<script type="text/ecmascript-6">
import LicenseTag from '@/components/views/LicenseTag'
import SocialSection from '@/components/views/Comment/SocialSection'

export default {
  methods: {
    onSubmit () {
      console.log('submit!')
    }
  },
  components: {
    'license-tag': LicenseTag,
    'social-section': SocialSection
  },
  props: {
    likeNum: {},
    commentList: Array
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .article-page-footer
    text-align left
    height 200px
    .operate_menu
      margin-top 30px
    .comment-menu
      margin-top 30px
      .comment-menu-item
        margin-bottom 20px
      p.comment-menu-item
        a
          font-size 14px
          &:hover
            color $color-main-primary

</style>
