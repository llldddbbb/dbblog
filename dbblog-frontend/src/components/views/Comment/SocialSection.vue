<template>
  <div class="social-section">
    <div class="content">
      <div class="likes">
        <a  v-for="comment in commentList" :key="comment.id"><img src="../../../assets/avatar.png" alt=""></a>
      </div>
    </div>

    <div class="comment-area">
      <div class="editor" :class="{spread: spreadEditor}">
        <mavon-editor :theme="theme" @valueChanged="valueChanged"></mavon-editor>
      </div>
    </div>

    <div class="comment-list">
      <comment-cell-list :theme="theme" :comment="comment" v-for="comment in commentList" :key="comment.id" ></comment-cell-list>
    </div>
    <!--<browse-more></browse-more>-->
  </div>
</template>

<script type="text/ecmascript-6">
import MavonEditor from '@/components/views/MavonEditor'
import CommentListCell from '@/components/views/Comment/CommentListCell'
import BrowseMore from '@/components/views/BrowseMore'

export default {
  props: {
    likeNum: {},
    commentList: Array,
    theme: {
      Type: String,
      default: ''
    }
  },
  data () {
    return {
      spreadEditor: false,
      name: '',
      select: 'email',
      email: '',
      mobile: ''
    }
  },
  components: {
    'mavon-editor': MavonEditor,
    'comment-cell-list': CommentListCell,
    'browse-more': BrowseMore
  },
  methods: {
    valueChanged (flag) {
      console.log(flag)
      this.spreadEditor = flag
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../.././../common/stylus/theme.styl";
  // #mavon-editor .operate .iv-dropdown-link
  .social-section
    .dark-theme
      background #000
      &::after
        display none
      .ivu-menu-item
        &:hover
          color $color-secondary-warning
          border-bottom 2px solid $color-secondary-warning
      .ivu-menu-item-active
        color $color-secondary-warning
        border-bottom 2px solid $color-secondary-warning
      .ivu-menu-submenu
        &:hover
          color $color-secondary-warning
          border-bottom 2px solid $color-secondary-warning
    .content
      margin 15px 0
      .likes
        margin-bottom 20px
        a
          display inline-block
          margin-right 10px
          img
            border-radius $border-radius
            width 40px
            height 40px
    .comment-area
      .editor
        margin 15px 0 10px
        height 200px
        transition height 0.7s
        &.spread
          height 450px

</style>
