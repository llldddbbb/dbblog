<template>
  <div class="article-list-header">
    <classify-menu></classify-menu>
    <section-title :mainTitle="'文章列表'" :subTitle="'Articles'"></section-title>
  </div>
</template>

<script type="text/ecmascript-6">
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import ClassifyMenu from '@/components/views/Classify/ClassifyMenu'

export default {
  components: {
    'section-title': SectionTitle,
    'classify-menu': ClassifyMenu
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">

</style>
