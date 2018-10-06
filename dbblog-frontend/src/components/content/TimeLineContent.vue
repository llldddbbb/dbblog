<template>
  <div class="timeline-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <timeline-header></timeline-header>
          <archive-list-time-title :date="'2017年'" :count="'200'"></archive-list-time-title>
          <archive-list-time-title :date="'10月'" :count="'200'" :dateType="'month'"></archive-list-time-title>
          <archive-list-cell v-for="article in articles" :article="article" :key="article.title"></archive-list-cell>
          <archive-list-time-title :date="'9月'" :count="'200'" :dateType="'month'"></archive-list-time-title>
          <archive-list-cell v-for="article in articles" :article="article" :key="article.title"></archive-list-cell>
          <archive-list-time-title :date="'8月'" :count="'200'" :dateType="'month'"></archive-list-time-title>
          <archive-list-cell v-for="article in articles" :article="article" :key="article.title"></archive-list-cell>
          <archive-list-time-title :date="'2016年'" :count="'200'"></archive-list-time-title>
          <archive-list-time-title :date="'12月'" :count="'200'" :dateType="'month'"></archive-list-time-title>
          <archive-list-cell v-for="article in articles" :article="article" :key="article.title"></archive-list-cell>
          <archive-list-time-title :date="'11月'" :count="'200'" :dateType="'month'"></archive-list-time-title>
          <archive-list-cell v-for="article in articles" :article="article" :key="article.title"></archive-list-cell>
          <archive-list-time-title :date="'10月'" :count="'200'" :dateType="'month'"></archive-list-time-title>
          <archive-list-cell v-for="article in articles" :article="article" :key="article.title"></archive-list-cell>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <recommend></recommend>
          <tag-wall style="margin-top: 15px;"></tag-wall>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import TimeLineHeader from '@/components/views/TimeLine/TimeLineHeader'
import ArchiveListCell from '@/components/views/Archive/ArchiveListCell'
import ArchiveListTimeTitle from '@/components/views/Archive/ArchiveListTimeTitle'
import Recommend from '@/components/views/Recommend'
import TagWall from '@/components/views/TagWall'

export default {
  data () {
    return {
      articles: [
        {
          'id': 1,
          'title': '被迫向现实低头，再爱也只能到此为止',
          'author': '男孩与榴莲',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 1
        },
        {
          'id': 2,
          'title': '我曾经喜欢过一个人',
          'author': '不能吃糖的0240',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 1
        },
        {
          'id': 3,
          'title': '离开你以后我还是很喜欢很喜欢你',
          'author': 'JUNE.',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 2
        }, {
          'id': 1,
          'title': '被迫向现实低头，再爱也只能到此为止',
          'author': '男孩与榴莲',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 1
        },
        {
          'id': 4,
          'title': '思念如马，自别离，未停蹄！相思若柳，飘满城，尽飞絮！',
          'author': '每日小情书',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 3
        },
        {
          'id': 1,
          'title': '被迫向现实低头，再爱也只能到此为止',
          'author': '男孩与榴莲',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 1
        }
      ]
    }
  },
  components: {
    'timeline-header': TimeLineHeader,
    'archive-list-time-title': ArchiveListTimeTitle,
    'archive-list-cell': ArchiveListCell,
    'recommend': Recommend,
    'tag-wall': TagWall
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .timeline-content
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
    .layout-left, .layout-right
      padding 0
      @media only screen and (max-width: 768px)
        padding 0
      @media screen and (min-width: 768px)
        padding 0
      @media screen and (min-width: 992px)
        padding 0 10px
      @media screen and (min-width: 1200px)
        padding 0 10px
</style>
