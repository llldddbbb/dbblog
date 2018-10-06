<template>
  <div class="album-infos">
    <p class="operate_info">
      <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> 10 阅读</a></span>
      <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> 10 评论</a></span>
      <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> 10 喜欢</a></span>
    </p>
    <div class="author">
      <a href="">
        <img src="../../../assets/logo.png" alt="">
        <div class="right">
          <p class="name">北岛极光</p>
          <p class="time">2017-11-21 19:01:23</p>
        </div>
      </a>
    </div>
    <div class="infos">
      <p class="title">极光飞舞</p>
      <p class="desc">格陵兰岛南部峡湾，漫天飞舞的极光。</p>
      <p class="tags">
        <span class="tag"><a href=""><span class="sharp"># </span>我要上开屏</a></span>
        <span class="tag"><a href=""><span># </span>旅行</a></span>
        <span class="tag"><a href=""><span># </span>格陵兰</a></span>
        <span class="tag"><a href=""><span># </span>广角</a></span>
        <span class="tag"><a href=""><span class="sharp"># </span>色彩</a></span>
        <span class="tag"><a href=""><span># </span>极光风光</a></span>
        <span class="tag"><a href=""><span># </span>情绪</a></span>
        <span class="tag"><a href=""><span># </span>尼康</a></span>
        <span class="tag"><a href=""><span># </span>后期</a></span>
      </p>
    </div>
    <div class="camera">
      <p class="version">Nikon D750</p>
      <p class="setting">AF-S Zoom-Nikkor 14-24mm f/2.8G ED</p>
      <p class="env">f/2.8, 15s, ISO1600</p>
    </div>
    <div class="social">
      <social-section :theme="'dark-theme'"></social-section>
    </div>
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
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl";
  @import "../../../common/stylus/theme.styl";

  .album-infos
    width 100%
    @media only screen and (max-width: 768px)
      height inherit
      overflow-y visible
      border-left none
    @media screen and (min-width: 768px)
      height inherit
      overflow-y visible
      border-left none
    @media screen and (min-width: 992px)
      height 100vh
      overflow-y auto
      border-left 1px solid $color-gradually-gray-21
    @media screen and (min-width: 1200px)
      height 100vh
      overflow-y auto
      border-left 1px solid $color-gradually-gray-21
    .operate_info
      height 60px
      line-height 60px
      font-size 15px
      span
        display inline-block
        width 32%
        text-align center
        a
          color $color-gradually-gray-91
          &:hover
            color $color-secondary-warning
    .author
      padding 10px 20px 20px
      border-bottom 1px solid $color-gradually-gray-21
      a
        display flex
        &:hover
          .link
            color $dark
        img
          flex 0 0 50px
          width 50px
          height 50px
          margin 5px 0
        .right
          display flex
          flex-direction column
          padding-left 12px
          height 60px
          line-height 60px
          p
            flex 1
            text-align left
            color $color-gradually-gray-91
            &.name
              height 35px
              line-height 42px
              font-size 17px
            &.time
              height 15px
              line-height 20px
              font-weight 100
    .infos
      padding 20px 20px 20px
      border-bottom 1px solid $color-gradually-gray-21
      .title
        font-size 20px
        line-height 25px
        font-weight 700
        margin-bottom 10px
        color $color-gradually-gray-91
      .desc
        font-size 16px
        line-height 23px
        font-weight 500
        margin-bottom 10px
        color $color-gradually-gray-91
      .tags
        line-height 25px
        span
          font-size 14px
          font-weight 100
          &.tag
            margin-right 8px
            a
              color $color-gradually-gray-91
              &:hover
                color $color-secondary-warning
          &.sharp
            color $color-secondary-warning
    .camera
      padding 20px
      border-bottom 1px solid $color-gradually-gray-21
      .version
        font-size 15px
        line-height 22px
        color $color-gradually-gray-81
      .setting, .env
        font-size 14px
        line-height 22px
        color $color-gradually-gray-61

    .social
      padding 0 20px 20px
      @media only screen and (max-width: 768px)
        padding 0 5px 20px
      @media screen and (min-width: 768px)
        padding 0 10px 20px
      @media screen and (min-width: 992px)
        padding 0 15px 20px
      @media screen and (min-width: 1200px)
        padding 0 20px 20px
</style>
