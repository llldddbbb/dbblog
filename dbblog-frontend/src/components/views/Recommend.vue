<template>
  <div class="recommend">
    <panel :title="'推荐阅读'">
      <div slot="content" class="content">
        <div class="top" v-if="topRecommend">
          <a :href="'/' + topRecommend.urlType + '/' + topRecommend.linkId">
            <p class="title">{{topRecommend.title}}</p>
            <div class="tags">
              <iv-tag  :color="index | mapTagColor" v-for="(tag,index) in topRecommend.tagList" :key="tag.id">{{tag.name}}</iv-tag>
            </div>
            <p class="info">
              <span class="time">{{topRecommend.createTime | socialDate }}</span>
              <span class="likes"><a><iv-icon type="heart"></iv-icon> {{topRecommend.likeNum}} </a></span>
              <span class="comments"><a><iv-icon type="compose"></iv-icon> {{topRecommend.commentNum}} </a></span>
              <span class="readings"><a><iv-icon type="eye"></iv-icon> {{topRecommend.readNum}} </a></span>
            </p>
            <!--<div class="img">-->
              <!--<img :src="topRecommend.cover" alt="">-->
            <!--</div>-->
            <p class="desc">{{topRecommend.description | textLineBreak(60)}}</p>
          </a>
        </div>
        <ul class="others">
          <li v-for="recommend in recommendList" :key="recommend.id">
            <a :href="'/' + recommend.urlType + '/' +recommend.linkId">
              <p class="title">{{recommend.title}}</p>
              <p class="info">
                <span class="time">{{recommend.createTime | socialDate }}</span>
                <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> {{recommend.likeNum}} </a></span>
                <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> {{recommend.commentNum}} </a></span>
                <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> {{recommend.readNum}} </a></span>
              </p>
            </a>
          </li>
        </ul>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import {mixin} from '@/utils/index'
import Panel from '@/components/utils/Panel'

export default {
  data () {
    return {
      recommendList: [],
      topRecommend: {}
    }
  },
  mixins: [mixin],
  created () {
    this.listRecommend()
  },
  methods: {
    listRecommend () {
      this.$http({
        url: this.$http.adornUrl('/operation/recommends'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.recommendList = data.recommendList
          this.topRecommend = this.recommendList.shift()
        }
      })
    }
  },
  components: {
    'panel': Panel
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/index.styl";

  .recommend
    .content
      padding 5px 20px
    .top, .others
      a
        display block
        overflow hidden
        .tags
          margin-bottom 10px
        .title
          text-align justify
          color $color-gradually-gray-41
          font-size 16px
          line-height 23px
          margin-bottom 5px
        .info
          margin 5px 0 0px
          span
            font-size 13px
            line-height 18px
            font-weight 100
            color $color-secondary-info
            + span
              float right
              margin-left 10px
          a
            display inline-block
            color: #777
            cursor pointer
            &:hover
              color $color-main-primary
              text-decoration underline
        .img
          padding-bottom: 40%
          width: 100%
          height: 0
          margin 5px 0
          overflow hidden
          img
            width 100%
            transition: All 0.4s ease-in-out
            transform: scale(1.0)
            zoom: 1.0
        .desc
          text-align justify
          color $color-secondary-info
          font-size 13px
          line-height 20px
          margin 5px 0 0
        &:hover
          .title
            color $color-main-primary
          img
            transition: All 0.4s ease-in-out
            transform: scale(1.05)
            zoom: 1.05
    .others
      li
        list-style-type none
        margin-top 10px
        padding-top 10px
        border-top 1px solid $color-border

</style>
