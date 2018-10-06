<template>
  <div class="movie-list-cell">
    <a href="">
      <img src="../../../assets/movie/4.jpg" alt="">
      <div class="info">
        <p class="title">魔都最美展《蜷川实花展》 “漫步花海，情迷蜷川”</p>
        <p class="desc">
          本次展览将展出近1000件艺术作品，包含花朵系列（Flowers）、金鱼系列（Liquid Dreams）</p>
      </div>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">

</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .movie-list-cell
    a
      position relative
      display block
      padding-bottom: 135%
      width: 100%
      height: 0
      overflow hidden
      img
        width 100%
        transition: All 0.4s ease-in-out
        transform: scale(1.0)
        zoom: 1.0
      .info
        position absolute
        bottom 0
        left 0
        width 100%
        height 25%
        padding 10px
        background rgba($color-secondary-warning, 0.9)
        transition: All 0.4s ease-in-out
        opacity 1.0
        .title
          font-size 20px
          line-height 24px
          color $color-typegraphy-title
          margin-bottom 7px
        .desc
          font-size 14px
          line-height 18px
          color $color-gradually-gray-31
      &:hover
        img
          transition: All 0.4s ease-in-out
          transform: scale(1.05)
          zoom: 1.05
        .info
          height 30%
          background rgba($color-gradually-gray-101, 1.0)
          .title
            color $color-gradually-gray-11
          .desc
            color $color-gradually-gray-11
</style>
