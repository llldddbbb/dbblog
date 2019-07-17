<template>
  <div class="book-overview-content layout-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <div class="book-infos">
            <div class="book-base-info">
              <div class="img">
                <div class="container">
                  <div class="bracket"></div>
                  <div class="target">
                    <img :src="book.cover" alt="">
                  </div>
                </div>
              </div>
              <div class="info">
                <p class="title">{{ book.title }}</p>
                <p class="desc"><span>作者：</span>{{ book.author }}</p>
                <p class="desc"><span>出版社：</span>{{ book.publisher }}</p>
                <p class="desc"><span>出版日期：</span>{{ book.publishDate }}</p>
                <p class="desc"><span>页数：</span>{{ book.pageNum }}</p>
                <p class="desc"><span>评分：</span>
                  <iv-rate v-model="book.grade" disabled :allowHalf="true"></iv-rate>
                </p>
                <iv-tag type="border" v-for="tag in book.tagList" :key="tag.name"
                       class="border-tag">{{ tag.name }}
                </iv-tag>
              </div>
              <div class="rating"></div>
            </div>
            <vue-tabs class="book-detail-info" >
              <v-tab :title="'简介'">
                <p class="summary" v-html="book.description"></p>
              </v-tab>
              <v-tab :title="'读书笔记'">
                <book-catalog :book="book"></book-catalog>
              </v-tab>
              <v-tab :title="'原书目录'">
                <p class="catalog" v-html="book.catalogue"></p>
              </v-tab>
              <v-tab :title="'读后感'" >
                <div class="article-details" id="article-main-page" ref="book" v-if="book.bookSense">
                  <div class="detail">
                    <article class="typo container article-main-content"
                             v-html="book.bookSense.content">
                    </article>
                    <div class="detail-footer">
                      以上内容添加于：{{ book.bookSense.createTime | socialDate }} &nbsp;&nbsp;&nbsp;
                      更新于：{{ book.bookSense.updateTime | socialDate }}
                    </div>
                  </div>
                </div>
              </v-tab>
            </vue-tabs>
          </div>
          <social-section  :post-id="book.id" :type="'Book'" ></social-section>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <recommend></recommend>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import {VueTabs, VTab} from 'vue-nav-tabs'
import 'vue-nav-tabs/themes/paper.css'
import BookCatalog from '@/components/views/Book/BookCatalog'
import Recommend from '@/components/views/Recommend'
import SocialSection from '@/components/views/Comment/SocialSection'
import SideToc from '@/components/views/SideToc'
// mixin
import {mixin} from '@/utils'

export default {
  name: 'book-content',
  data () {
    return {
      book: {}
    }
  },
  mixins: [mixin],
  components: {
    'vue-tabs': VueTabs,
    'v-tab': VTab,
    'book-catalog': BookCatalog,
    'social-section': SocialSection,
    'recommend': Recommend,
    'side-toc': SideToc
  },
  created () {
    this.getBook(this.$route.params.bookId)
  },
  methods: {
    getBook (bookId) {
      this.$http({
        url: this.$http.adornUrl('/book/' + bookId),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.book = data.book
          document.title = this.book.title + ' | Bobbi的个人博客 | 一个努力成长中的Java后端程序猿'
        }
      })
    }
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  @import "../../common/stylus/article.styl";

  .book-overview-content
    width auto
    @media only screen and (max-width: 768px)
      margin 5px 5px 10px 5px
    @media screen and (min-width: 768px)
      margin 10px 10px 20px 10px
    @media screen and (min-width: 992px)
      margin 15px 35px 50px 35px
    @media screen and (min-width: 1200px)
      width 1200px
      margin 15px auto 0
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
    .book-infos
      .book-base-info
        display flex
        margin-bottom 15px
        .img
          position relative
          flex 0 0 140px
          width 140px
          overflow hidden
          .container
            width 100%
            position relative
            overflow hidden
            .bracket
              margin-top 140%
            .target
              position absolute
              top 0
              bottom 0
              left 0
              right 0
              border 1px solid $default-border-color
              > img
                width 100%
                height 100%
                transition All 0.4s ease-in-out
                transform scale(1.0)
                zoom 1.0
        .info
          padding-left 15px
          .title
            font-size 20px
            line-height 28px
            font-weight 500
            color $default-title-color
            margin-bottom 5px
            text-align justify
          .desc
            font-size 14px
            font-weight 100
            line-height 20px
            color $default-desc-color
            text-align justify
            margin-bottom 3px
            > span
              color $default-desc-color
              font-weight 700
      .book-detail-info
        p.summary, p.author, p.catalog
          font-size 14px
          font-weight 200
          line-height 23px
          text-align justify
          color $default-desc-color
        .nav-tabs-navigation
          border-bottom 1px solid $default-border-color
          margin-bottom 20px
          .nav-tabs
            li
              margin-bottom 0
              font-weight 400
              a
                color $default-info-color
                &.active_tab
                  color $default-info-hover-color
                &::before
                  bottom 2px
                  background transparent
                  border-bottom 11px solid $default-border-color
                &::after
                  bottom 1px
                  background transparent
                  border-bottom 11px solid $default-body-background-color
        .detail-footer
          text-align right
          font-size 12px
          color #444
          border-bottom 2px dashed #eee
          padding-bottom 10px
          margin 15px 0

</style>
