<template>
  <div class="movie-list-item">
    <h4>历史观影<a href="">查看更多</a></h4>
    <iv-row>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
      <iv-col :xs="12" :sm="12" :md="8" :lg="6">
        <div class="list">
          <movie-list-item></movie-list-item>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import MovieListItem from '@/components/views/Movie/MovieListItem'

export default {
  components: {
    'movie-list-item': MovieListItem
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .movie-list-item
    border 1px solid $color-border
    padding 10px
    h4
      font-size 26px
      line-height 30px
      padding 10px 20px 10px 10px
      a
        font-size 16px
        float right
    .list
      padding 10px
      a
        .info
          height 30%
          padding 8px
          @media only screen and (max-width: 768px)
            height 20%
          @media screen and (min-width: 768px)
            height 20%
          @media screen and (min-width: 992px)
            height 30%
          @media screen and (min-width: 1200px)
            height 30%
          .title
            margin-bottom 5px
            @media only screen and (max-width: 768px)
              font-size 16px
              line-height 18px
            @media screen and (min-width: 768px)
              font-size 18px
              line-height 21px
            @media screen and (min-width: 992px)
              font-size 18px
              line-height 21px
            @media screen and (min-width: 1200px)
              font-size 17px
              line-height 20px
          .desc
            @media only screen and (max-width: 768px)
              font-size 12px
              line-height 14px
            @media screen and (min-width: 768px)
              font-size 14px
              line-height 17px
            @media screen and (min-width: 992px)
              font-size 14px
              line-height 17px
            @media screen and (min-width: 1200px)
              font-size 13px
              line-height 15px
        &:hover
          .info
            @media only screen and (max-width: 768px)
              height 22%
            @media screen and (min-width: 768px)
              height 22%
            @media screen and (min-width: 992px)
              height 35%
            @media screen and (min-width: 1200px)
              height 35%

</style>
