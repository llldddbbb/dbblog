<template>
  <div class="movie-list-content">
    <movie-recomend-list-cell></movie-recomend-list-cell>
    <movie-recent-list-cell style="margin-top: 20px;"></movie-recent-list-cell>
    <movie-list-cell style="margin-top: 20px;"></movie-list-cell>
  </div>
</template>

<script type="text/ecmascript-6">
import MovieRecomendListCell from '@/components/views/Movie/MovieRecomendListCell'
import MovieRecentListCell from '@/components/views/Movie/MovieRecentListCell'
import MovieListCell from '@/components/views/Movie/MovieListCell'

export default {
  components: {
    'movie-recomend-list-cell': MovieRecomendListCell,
    'movie-recent-list-cell': MovieRecentListCell,
    'movie-list-cell': MovieListCell
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .movie-list-content
    width auto
    @media only screen and (max-width: 768px)
      margin 5px 5px 0 5px
    @media screen and (min-width: 768px)
      margin 10px 10px 0 10px
    @media screen and (min-width: 992px)
      margin 15px 35px 0 35px
    @media screen and (min-width: 1200px)
      width 1200px
      margin 15px auto 0
      margin-bottom 200px
</style>
