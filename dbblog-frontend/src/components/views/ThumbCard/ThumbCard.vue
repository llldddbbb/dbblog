<template>
  <div class="thumb-card">
    <a >
      <div class="top-area">
        <a >
          <p class="desc">不望祁连山顶雪，错将张掖认江南</p>
          <div class="background"></div>
          <img src="../../../assets/photowall/photowall_pic_3.jpg" alt="">
          <img src="../../../assets/photowall/photowall_pic_3.jpg" alt="">
          <img src="../../../assets/photowall/photowall_pic_3.jpg" alt="">
        </a>
      </div>
      <div class="bottom-area">
        <h4 class="title"><a >{{article.title}}</a></h4>
        <p class="info"><span class="author">By / <a >{{article.author}}</a></span></p>
        <p class="info"><span class="publish-time">At time / <a >{{article.publish_time}}</a></span></p>
        <p class="operate_info">
          <span class="readings"><a > {{article.readings}}次阅读</a></span> |
          <span class="comments"><a > {{article.comments}}个评论</a></span> |
          <span class="likes"><a > {{article.likes}}个喜欢</a></span></p>
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
  @import "../../../common/stylus/index.styl";

  .thumb-card
    margin 0 20px 40px 20px
    > a
      display block
      cursor default
      .top-area
        padding-bottom: 110%
        width: 100%
        height: 0
        overflow hidden
        a
          position relative
          display: block
          width: 100%
          padding-bottom: 110%
          .desc, .background
            position absolute
            opacity 0.0
            transition: All 0.5s ease-in-out
          .desc
            width: 60%
            margin 0 20%
            text-align center
            font-size 22px
            font-weight 200
            line-height 35px
            color #fff
            padding-top: 42%
            z-index 11
          .background
            width: 100%
            border-radius 20px
            padding-top: 100%
            z-index 10
          img
            display block
            position absolute
            border-radius 20px
            box-shadow 0 10px 10px 0 rgba(0, 0, 0, 0.15)
            width 100%
            zoom: 1.0
            z-index 9
            + img
              margin-top 8%
              z-index 8
              transition: All 0.4s ease-in-out
              transform: scale(0.9) rotate(90deg)
              zoom: 0.9
              + img
                margin-top 16%
                box-shadow none
                z-index 7
                transition: All 0.4s ease-in-out
                transform: scale(0.8) rotate(270deg)
                zoom: 0.8
        &:hover
          .background, .desc
            opacity 1.0
            transition: All 0.5s ease-in-out
          .background
            background-color rgba(33, 33, 33, 0.4)
          img
            transition: All 0.4s ease-in-out
            + img
              transform translate(0, 8px) scale(0.9) rotate(90deg)
              + img
                transform translate(0, 15px) scale(0.8) rotate(270deg)
      .bottom-area
        position relative
        text-align center
        padding 15px 18px 18px 20px
        z-index 6
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
