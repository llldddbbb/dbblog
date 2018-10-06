<template>
  <div class="movie-recent-list-cell">
    <h4>最近观影<a href="">查看更多</a></h4>
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="16" :lg="16">
        <ul class="recent">
          <li>
            <a href="">
              <iv-row>
                <iv-col :xs="24" :sm="24" :md="7" :lg="6">
                  <div class="img">
                    <img src="../../../assets/movie/13.jpg" alt="">
                  </div>
                </iv-col>
                <iv-col :xs="24" :sm="24" :md="17" :lg="18">
                  <div class="info">
                    <p class="title">东方快车谋杀案</p>
                    <p class="link">2017-11-21 12:00:09</p>
                    <p class="content">终于赶上告别版的盗3了，可惜看不到末场的演出了！可惜看不到末场的演出了！</p>
                  </div>
                </iv-col>
              </iv-row>
            </a>
          </li>
          <li>
            <a href="">
              <iv-row>
                <iv-col :xs="24" :sm="24" :md="7" :lg="6">
                  <div class="img">
                    <img src="../../../assets/movie/14.jpg" alt="">
                  </div>
                </iv-col>
                <iv-col :xs="24" :sm="24" :md="17" :lg="18">
                  <div class="info">
                    <p class="title">东方快车谋杀案</p>
                    <p class="link">2017-11-21 12:00:09</p>
                    <p class="content">终于赶上告别版的盗3了，可惜看不到末场的演出了！可惜看不到末场的演出了！</p>
                  </div>
                </iv-col>
              </iv-row>
            </a>
          </li>
          <li>
            <a href="">
              <iv-row>
                <iv-col :xs="24" :sm="24" :md="7" :lg="6">
                  <div class="img">
                    <img src="../../../assets/movie/15.jpg" alt="">
                  </div>
                </iv-col>
                <iv-col :xs="24" :sm="24" :md="17" :lg="18">
                  <div class="info">
                    <p class="title">东方快车谋杀案</p>
                    <p class="link">2017-11-21 12:00:09</p>
                    <p class="content">终于赶上告别版的盗3了，可惜看不到末场的演出了！可惜看不到末场的演出了！</p>
                  </div>
                </iv-col>
              </iv-row>
            </a>
          </li>
        </ul>
      </iv-col>
      <iv-col :xs="24" :sm="24" :md="8" :lg="8">
        <div class="recommend">
          <h4>推荐观影</h4>
          <a href="">
            <p class="title">你以为毕业后还能去搬砖？别做梦了，机器人已经连房子都会盖啦</p>
            <div class="tags">
              <iv-tag color="blue">标签二</iv-tag>
              <iv-tag color="green">标签三</iv-tag>
              <iv-tag color="red">标签四</iv-tag>
              <iv-tag color="yellow">标签五</iv-tag>
            </div>
            <p class="info">
              <span class="time">2017-11-17 09:19</span>
              <span class="likes"><a href=""><iv-icon type="heart"></iv-icon> 10 </a></span>
              <span class="comments"><a href=""><iv-icon type="compose"></iv-icon> 12 </a></span>
              <span class="readings"><a href=""><iv-icon type="eye"></iv-icon> 12 </a></span>
            </p>
            <div class="img">
              <img src="../../../assets/recommend.jpg" alt="">
            </div>
            <p class="desc">希望不管是机器人的设计者还是使用者都能明白，使用机器的目的是帮助我们实现更多创造性的工作，使用机器的目的是帮助我们实现更多创造性的工作，希望不管是机器人的设计者还是使用者都能明白，使用机器的目的是帮助我们实现更多创造性的工作，保护我们免于危险，激发我们的人性和爱。</p>
          </a>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">

</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .movie-recent-list-cell
    border 1px solid $color-border
    h4
      font-size 26px
      line-height 30px
      padding 20px 20px 0 20px
      a
        font-size 16px
        float right
    .recent
      padding 20px 20px 0 20px
      li
        margin-bottom 10px
        a
          .img
            width 100%
            height 0
            padding-bottom 65%
            margin-bottom 10px
            overflow hidden
            img
              transition: All 0.4s ease-in-out
              width 100%
          .info
            @media only screen and (max-width: 768px)
              padding-bottom 10px
            @media screen and (min-width: 768px)
              padding-bottom 10px
            @media screen and (min-width: 992px)
              padding 0 0 10px 12px
            @media screen and (min-width: 1200px)
              padding 0 0 10px 12px
            p
              text-align left
              font-weight 100
              color $color-gradually-gray-61
              &.title
                line-height 40px
                font-size 24px
                font-weight 300
                color $color-typegraphy-title
              &.link
                font-size 18px
                line-height 23px
                font-weight 100
              &.content
                margin-top 5px
                font-size 17px
                font-weight 100
                line-height 22px
          &:hover
            img
              transform: scale(1.05)
              zoom: 1.05
            p.title
              font-weight 500
              color $color-secondary-warning
            p.link
              color $color-gradually-gray-61
            p.content
              color $color-gradually-gray-61
    .recommend
      margin 20px 0 10px 20px
      padding 0 25px 20px 20px
      border-left 1px solid $color-border
      @media only screen and (max-width: 768px)
        margin 0 0 20px 0
        padding 0 20px
        border-left none
      @media screen and (min-width: 768px)
        margin 0 0 20px 0
        padding 0 20px
        border-left none
      @media screen and (min-width: 992px)
        padding 0 25px 20px 20px
        border-left 1px solid $color-border
      @media screen and (min-width: 1200px)
        padding 0 25px 20px 20px
        border-left 1px solid $color-border
      h4
        font-size 20px
        padding 0 0 10px 0
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
</style>
