<template>
  <div class="album-preview-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="16" :lg="18">
        <album-preview></album-preview>
      </iv-col>
      <iv-col :xs="24" :sm="24" :md="8" :lg="6">
        <album-infos></album-infos>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import AlbumPreview from '@/components/views/AlbumPreview/AlbumPreview'
import AlbumInfos from '@/components/views/AlbumPreview/AlbumInfos'

export default {
  components: {
    'album-preview': AlbumPreview,
    'album-infos': AlbumInfos
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .album-preview-content
    position: absolute
    top 0
    left 0
    width 100%
    background #000
</style>
