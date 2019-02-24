<template>
  <div class="timeline-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <timeline-header></timeline-header>
          <div v-for="year in timelineList" :key="year.year" v-if="year.months.length > 0">
            <archive-list-time-title :date="year.year" :count="year.count"></archive-list-time-title>
            <div v-for="month in year.months" :key="month.month" v-if="month.posts.length > 0">
              <archive-list-time-title :date="month.month + '月'" :count="month.count" :dateType="'month'"></archive-list-time-title>
              <archive-list-cell v-for="post in month.posts" :post="post"
                                 :key="post.title"></archive-list-cell>
            </div>
          </div>
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
      timelineList: []
    }
  },
  components: {
    'timeline-header': TimeLineHeader,
    'archive-list-time-title': ArchiveListTimeTitle,
    'archive-list-cell': ArchiveListCell,
    'recommend': Recommend,
    'tag-wall': TagWall
  },
  created () {
    this.listTimeline()
  },
  methods: {
    listTimeline () {
      this.$http({
        url: this.$http.adornUrl('/timeline'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.timelineList = data.timelineList
        }
      })
    },
    selectCategory (categoryId) {
      this.page = 0
    }
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
