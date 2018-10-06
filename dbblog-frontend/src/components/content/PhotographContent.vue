<template>
  <div class="photograph-content">
    <iv-carousel :value="0" :radius-dot="true" loop autoplay style="padding-bottom: 30%; width: 100%; height: 0; overflow:hidden;">
      <iv-carousel-item>
        <img width="100%" src="../../assets/photowall/carousel1.jpg" alt="">
      </iv-carousel-item>
      <iv-carousel-item>
        <img width="100%" src="../../assets/photowall/carousel2.jpg" alt="">
      </iv-carousel-item>
      <iv-carousel-item>
        <img width="100%" src="../../assets/photowall/carousel3.jpg" alt="">
      </iv-carousel-item>
    </iv-carousel>
    <classify-wall></classify-wall>
    <section-title :mainTitle="'游记'" :subTitle="'天下之美'"></section-title>
    <div class="thumb-cards">
      <iv-row>
        <iv-col :xs="24" :sm="12" :md="8" :lg="8">
          <thumb-card></thumb-card>
        </iv-col>
        <iv-col :xs="24" :sm="12" :md="8" :lg="8">
          <thumb-card></thumb-card>
        </iv-col>
        <iv-col :xs="24" :sm="12" :md="8" :lg="8">
          <thumb-card></thumb-card>
        </iv-col>
        <iv-col :xs="24" :sm="12" :md="8" :lg="8">
          <thumb-card></thumb-card>
        </iv-col>
        <iv-col :xs="24" :sm="12" :md="8" :lg="8">
          <thumb-card></thumb-card>
        </iv-col>
        <iv-col :xs="24" :sm="12" :md="8" :lg="8">
          <thumb-card></thumb-card>
        </iv-col>
      </iv-row>
    </div>
    <browse-more></browse-more>
  </div>
</template>

<script type="text/ecmascript-6">
import ClassifyWall from '@/components/views/Classify/ClassifyWall'
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import ThumbCard from '@/components/views/ThumbCard'
import BrowseMore from '@/components/views/BrowseMore'

export default {
  components: {
    'classify-wall': ClassifyWall,
    'section-title': SectionTitle,
    'thumb-card': ThumbCard,
    'browse-more': BrowseMore
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .photograph-content
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
    .carousel
      padding-bottom: 70%
      width: 100%
      height: 0
      overflow hidden
    .thumb-cards
      margin-top 15px
</style>
