<template>
  <div class="topic-card">
    <a href="">
      <div class="top-area">
        <a href="">
          <img src="../../assets/photowall/photowall_pic_3.jpg" alt="">
        </a>
      </div>
      <div class="bottom-area">
        <h4 class="title"><a href="">{{article.title}}</a></h4>
        <p class="info"><span class="author">By / <a href="">{{article.author}}</a></span></p>
        <p class="info"><span class="publish-time">At time / <a href="">{{article.publish_time}}</a></span></p>
        <p class="operate_info">
          <span class="readings"><a href=""> {{article.readings}}次阅读</a></span> |
          <span class="comments"><a href=""> {{article.comments}}个评论</a></span> |
          <span class="likes"><a href=""> {{article.likes}}个喜欢</a></span></p>
      </div>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      article: {
        'id': 1,
        'title': '被太阳晒过的风 第四十四章',
        'author': '子夜晨星',
        'publish_time': '2017/10/22 17:57:08',
        'desc': '-1-见了男朋友回来之后喝了一杯没怎么泡好的红糖水和一杯满满的白开水。去上了次厕所，然后坐在床上的时候开始咳嗽不止，去洗了把脸，恍然地瞥见镜子中的自己看起来并不...',
        'readings': '148',
        'comments': '2',
        'likes': '20'
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/index.styl"

  .topic-card
    margin-bottom 10px
    > a
      display block
      cursor default
      border 1px solid $color-border
      &:hover
        border 1px solid $color-border-hover
        box-shadow 2px 2px 3px $color-border
      .top-area
        padding-bottom: 100%
        width: 100%
        height: 0
        overflow hidden
        a
          display: block
          width: 100%
          img
            width 100%
            transition: All 0.4s ease-in-out
            transform: scale(1.0)
            zoom: 1.0
        &:hover
          img
            transition: All 0.4s ease-in-out
            transform: scale(1.05)
            zoom: 1.05
      .bottom-area
        text-align left
        padding 20px 18px 18px 20px
        .title
          font-size 18px
          margin-bottom 10px
          a
            color $color-typegraphy-title
            &:hover
              text-decoration underline
              color $color-typegraphy-title-hover
        .info
          margin-bottom 10px
          font-size 14px
          font-weight 200
          a
            color: #777
            cursor pointer
            &:hover
              color $color-main-primary
              text-decoration underline
        .operate_info
          font-size 14px
          margin-top 15px
          span
            margin-right 5px
            + span
              margin-left 5px
            a
              cursor pointer
              &:hover
                color $color-main-primary
                text-decoration underline
</style>
