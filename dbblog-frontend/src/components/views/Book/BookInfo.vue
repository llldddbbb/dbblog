<template>
  <div class="book-info" v-if="bookDoubanInfo !== undefined">
    <panel :title="$t('book.bookInfo')" :titleAlign="'center'">
      <div slot="content" class="content">
        <div class="img">
          <div class="container">
            <div class="bracket"></div>
            <div class="target">
              <img :src="bookDoubanInfo.image" alt="">
            </div>
          </div>
        </div>
        <div class="info">
          <p class="title"><a :href="bookDoubanInfo.alt" target="_blank">{{ bookDoubanInfo.title }}</a></p>
          <p class="desc"><span>{{ $t('book.author') }}：</span>{{ bookDoubanInfo.author.join(' ') }}</p>
          <p class="desc"><span>{{ $t('book.publisher') }}：</span>{{ bookDoubanInfo.publisher }}</p>
          <p class="desc"><span>{{ $t('book.publishDate') }}：</span>{{ bookDoubanInfo.publish_date }}</p>
          <p class="desc"><span>{{ $t('book.pages') }}：</span>{{ bookDoubanInfo.pages }}</p>
          <p class="desc"><span>{{ $t('book.rating') }}：</span>
            <iv-rate v-model="bookDoubanInfo.rating * 0.5" :allowHalf="true" :disabled="true"></iv-rate>
          </p>
          <p class="desc">
            <i-tag type="border" v-for="tag in bookDoubanInfo.tags" :key="tag.name" class="border-tag">{{ tag.name }}</i-tag>
          </p>
        </div>
        <div class="rating"></div>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from '@/components/utils/Panel'

export default {
  name: 'book-info',
  props: {
    book: {
      Type: Object,
      default: undefined
    }
  },
  data () {
    return {
      bookDoubanInfo: undefined
    }
  },
  created () {
    this.getDoubanInfo(this.book)
  },
  methods: {
    getDoubanInfo (book) {
      if (book) {
        this.bookDoubanInfo = this.formatLocalBookInfo(book)
      } else {
        API.getDoubanInfo({
          id: book.douban_id,
          type: book.douban_type
        }).then((response) => {
          this.bookDoubanInfo = this.formatBookInfo(response.data)
        }).catch((error) => {
          console.log(error)
        })
      }
    },
    formatLocalBookInfo (book) {
      let bookInfo = {}
      bookInfo.title = book.book_name
      bookInfo.image = book.book_image
      bookInfo.author = book.book_author.split('，')
      bookInfo.publisher = book.book_publisher
      bookInfo.publish_date = book.publish_date
      bookInfo.pages = book.book_pages
      bookInfo.tags = book.book_tags.split('，').map((tagName) => { return {name: tagName} })
      bookInfo.rating = book.book_rating
      bookInfo.alt = book.book_url
      return bookInfo
    },
    formatBookInfo (book) {
      let bookInfo = {}
      bookInfo.title = book.title
      bookInfo.image = book.images.medium
      bookInfo.author = book.author
      bookInfo.author_intro = this.formatContent(book.author_intro)
      bookInfo.publisher = book.publisher
      bookInfo.publish_date = book.pubdate
      bookInfo.pages = book.pages
      bookInfo.summary = this.formatContent(book.summary)
      bookInfo.catalog = this.formatContent(book.catalog)
      bookInfo.tags = book.tags
      bookInfo.rating = book.rating
      bookInfo.alt = book.alt
      return bookInfo
    },
    formatContent (content) {
      return content.replace(/\n/g, '<br />')
    }
  },
  components: {
    'panel': Panel
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .book-info
    .content
      padding 15px 20px
      border-left 1px solid $default-border-color
      border-right 1px solid $default-border-color
      border-bottom 1px solid $default-border-color
    .img
      position relative
      margin 0 auto
      width 120px
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
            transition: All 0.4s ease-in-out
            transform: scale(1.0)
            zoom: 1.0
    .info
      padding 15px
      .title
        margin-bottom 5px
        text-align center
        a
          color $default-title-color
      .desc
        font-size 14px
        font-weight 100
        line-height 20px
        color $default-desc-color
        text-align center
        margin-bottom 3px
        > span
          color $default-info-color
          font-weight 300
    &:hover
      img
        transition: All 0.4s ease-in-out
        transform: scale(1.05)
        zoom: 1.05
      .info
        .desc
          color $default-desc-hover-color
</style>
