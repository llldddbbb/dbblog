<template>
  <div class="comment-list-cell">
    <iv-row>
      <iv-col :xs="cellSpan('xs')" :sm="cellSpan('sm')" :md="cellSpan('md')" :lg="cellSpan('lg')" :xl="cellSpan('xl')">
        <div class="comment-main">
          <iv-row :gutter="8">
            <iv-col :xs="cellLeftSpan('xs')" :sm="cellLeftSpan('sm')" :md="cellLeftSpan('md')"
                    :lg="cellLeftSpan('lg')" :xl="cellLeftSpan('xl')">
              <div class="avatar">
                <img src="../../../assets/avatar.png" alt="">
              </div>
            </iv-col>
            <iv-col :xs="cellRightSpan('xs')" :sm="cellRightSpan('sm')" :md="cellRightSpan('md')"
                    :lg="cellRightSpan('lg')" :xl="cellRightSpan('xl')">
              <div class="content">
                <p class="title">
                  <span class="name" :class="theme"><a href="">{{comment.nickName}}</a></span>
                  <span class="reply-icon" :class="theme" v-if="comment.parentNickName">&nbsp;<iv-icon type="forward"></iv-icon></span>
                  <span class="reply-name" :class="theme" v-if="comment.parentNickName"><a href="">{{comment.parentNickName}}</a></span>
                  <span class="time">{{comment.publishTime | socialDateFormat}}</span>
                </p>
                <p class="comment-content" :class="theme">
                  {{comment.content}}</p>
                <div class="operate-area" :class="theme">
                  <span class="like"><a><iv-icon type="thumbsup"></iv-icon>&nbsp;{{comment.likeNum}}</a></span>
                  <span class="unlike"><a><iv-icon type="thumbsdown"></iv-icon>&nbsp;{{comment.dislikeNum}}</a></span>
                  <span class="reply"><a @click="showEditor = !showEditor"><iv-icon type="forward"></iv-icon> 回复</a></span>
                </div>
                <div class="comment-area" v-show="showEditor">
                  <div class="reply-editor" :class="{spread: spreadEditor}">
                    <mavon-editor :theme="theme" @valueChanged="valueChanged"></mavon-editor>
                  </div>
                </div>
              </div>
            </iv-col>
          </iv-row>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import MavonEditor from '@/components/views/MavonEditor'
import { mixin } from '@/utils'

const CELL_LEFT_SPAN = {
  'xs': 3,
  'sm': 3,
  'md': 2,
  'lg': 2
}
const CELL_RIGHT_SPAN = {
  'xs': 24 - CELL_LEFT_SPAN['xs'],
  'sm': 24 - CELL_LEFT_SPAN['sm'],
  'md': 24 - CELL_LEFT_SPAN['md'],
  'lg': 24 - CELL_LEFT_SPAN['lg']
}

export default {
  props: {
    comment: {},
    count: '',
    tipText: {
      default: 'View All'
    },
    theme: {
      Type: String,
      default: ''
    }
  },
  mixins: [mixin],
  data () {
    return {
      showEditor: false,
      spreadEditor: false
    }
  },
  methods: {
    cellSpan (size) {
      var span = {}
      span['offset'] = CELL_LEFT_SPAN[size] * this.comment.commentLevel
      span['span'] = 24 - span['offset']
      return span
    },
    cellLeftSpan (size) {
      var span = {}
      span['span'] = CELL_LEFT_SPAN[size]
      return span
    },
    cellRightSpan (size) {
      var span = {}
      span['span'] = CELL_RIGHT_SPAN[size]
      return span
    },
    valueChanged (flag) {
      this.spreadEditor = flag
    }
  },
  components: {
    'mavon-editor': MavonEditor
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .comment-list-cell
    position relative
    text-align left
    .avatar
      img
        border-radius $border-radius
        width 100%
    .content
      margin 5px 0 8px
      .title
        font-size 0
        margin-bottom 5px
        line-height 18px
        .name
          a
            font-size 15px
            color $color-main-primary
            font-weight 700
            &:hover
              text-decoration underline
          &.dark-theme
            a
              color $color-secondary-warning
        .name-tag
          font-size 12px
          background-color $color-secondary-info
          padding 2px 5px
          margin 0 5px
          color #fff
          border-radius $border-radius
        .reply-icon
          font-size 15px
          color $light
        .reply-name
          font-size 15px
          margin 0 5px
          a
            color $dark
            &:hover
              color $color-main-primary
              text-decoration underline

          &.dark-theme
            a
              color $color-gradually-gray-71
              &:hover
                color $color-secondary-warning
                text-decoration underline
        .time
          font-size 13px
          color $light
          margin-left 8px
      .comment-content
        font-size 16px
        line-height 24px
        &.dark-theme
          color $color-gradually-gray-71
    .operate-area
      margin-top 8px
      font-size 14px
      span
        margin-right 10px
      .iv-dropdown-link
        cursor pointer
      .like, .unlike
        color $light
        font-weight 700
      .reply
        cursor pointer
      &.dark-theme
        .iv-dropdown-link
          &:hover
            color $color-secondary-warning
        .reply
          a
            color $color-secondary-warning
    .comment-area
      margin-bottom 10px
      .reply-editor
        margin-top 15px
        height 200px
        transition height 0.7s
        &.spread
          height 300px
      p.comment-tip
        a
          font-size 14px
          &:hover
            color $color-main-primary
</style>
