<template>
  <div class="album-preview">
    <album-carousel></album-carousel>
  </div>
</template>

<script type="text/ecmascript-6">
import AlbumCarousel from '@/components/views/AlbumPreview/AlbumCarousel'

export default {
  components: {
    'album-carousel': AlbumCarousel
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .album-preview
    background #000
    width 100%
    height 100vmin
    @media only screen and (max-width: 768px)
      height 130vmin
    @media screen and (min-width: 768px)
      height 130vmin
    @media screen and (min-width: 992px)
      height 100vmin
    @media screen and (min-width: 1200px)
      height 100vmin
</style>
