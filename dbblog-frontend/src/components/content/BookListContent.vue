<template>
  <div class="book-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <div class="book-gallery">
            <div class="swiper-wrapper">
              <div class="swiper-slide" v-for="book in readingBookList" :key="book.id">
                <book-reading-cell :book="book"></book-reading-cell>
              </div>
            </div>
            <div class="swiper-pagination" slot="pagination"></div>
          </div>
          <book-list-header @filterByMenu="filterByBookMenu"
                               @filterByCategory="filterByCategory"
                               :categorys="categoryList"
                               :defaultCategory="selected_category"
                               :mainTitle="'图书列表'" :sub-title="'Books'" ></book-list-header>
          <book-list-cell v-for="book in bookList" :book="book" :key="book.name"></book-list-cell>
          <book-note-list-header @filterByMenu="filterByBookNoteMenu"
                                 :filterList = bookNoteFilterList
                                :mainTitle="'笔记列表'" :sub-title="'BookNotes'" ></book-note-list-header>
          <book-note-list-cell v-for="bookNote in bookNoteList" :bookNote="bookNote" :key="bookNote.id"></book-note-list-cell>
          <browse-more @browseMore="browseMore" :noMoreData="noMoreData"  ref="browseMore"></browse-more>
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
import BookListHeader from '@/components/views/Book/BookListHeader'
import BookNoteListHeader from '@/components/views/BookNote/BookNoteListHeader'
import BookPageContent from '@/components/views/Book/BookPageContent'
import BookPageFooter from '@/components/views/Book/BookPageFooter'
import BookListCell from '@/components/views/Book/BookListCell'
import BookNoteListCell from '@/components/views/BookNote/BookNoteListCell'
import BookReadingCell from '@/components/views/Book/BookReadingCell'
import Recommend from '@/components/views/Recommend'
import TagWall from '@/components/views/TagWall'
import BrowseMore from '@/components/views/BrowseMore'
import merge from 'lodash/merge'
import {treeDataTranslate} from '@/utils'
import {DefaultFilterList} from '@/common/js/const'

export default {
  data () {
    return {
      bookList: [],
      bookNoteList: [],
      categoryList: [],
      currentPage: 1,
      pageSize: 15,
      categoryId: this.$route.query.categoryId,
      selected_category: this.$route.query.categoryId,
      menuBookParams: {
        latest: true
      },
      menuBookNoteParams: {
        latest: true
      },
      noMoreData: false,
      readingBookList: [],
      bookNoteFilterList: JSON.parse(JSON.stringify(DefaultFilterList))
    }
  },
  created () {
    this.listBook()
    this.listBookNote()
    this.listCategory()
  },
  methods: {
    listBook () {
      let params = {
        categoryId: this.categoryId,
        limit: this.pageSize,
        page: 1
      }
      params = merge(params, this.menuBookParams)
      this.$http({
        url: this.$http.adornUrl('/books'),
        params: this.$http.adornParams(params),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.bookList = data.page.list
          this.readingBookList = this.bookList.filter(book => {
            if (book.reading) {
              return book
            }
          })
        }
      })
    },
    listBookNote () {
      let params = {
        categoryId: this.categoryId,
        limit: this.pageSize,
        page: this.currentPage
      }
      params = merge(params, this.menuBookNoteParams)
      this.$http({
        url: this.$http.adornUrl('/bookNotes'),
        params: this.$http.adornParams(params),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          if (data.page.totalPage <= data.page.currPage) {
            this.noMoreData = true
          } else {
            this.noMoreData = false
          }
          this.bookNoteList = data.page.list
        }
      })
    },
    listCategory () {
      let params = {}
      params.type = 1
      this.$http({
        url: this.$http.adornUrl('/operation/categories'),
        method: 'get',
        params: this.$http.adornParams(params)
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryList = treeDataTranslate(data.categoryList)
        }
      })
    },
    filterByBookMenu (params) {
      this.resetCurrentPage()
      this.menuBookParams = params
      this.listBook()
    },
    filterByBookNoteMenu (params) {
      this.resetCurrentPage()
      this.menuBookNoteParams = params
      this.listBookNote()
    },
    filterByCategory (params) {
      this.resetCurrentPage()
      this.categoryId = params
      this.listBook()
      this.listBookNote()
    },
    resetCurrentPage () {
      this.currentPage = 1
    },
    browseMore () {
      this.currentPage++
      let params = {
        categoryId: this.categoryId,
        limit: this.pageSize,
        page: this.currentPage
      }
      params = merge(params, this.menuBookNoteParams)
      this.$http({
        url: this.$http.adornUrl('/bookNotes'),
        params: this.$http.adornParams(params),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          if (data.page.totalPage <= data.page.currPage) {
            this.noMoreData = true
          } else {
            this.noMoreData = false
          }
          this.bookNoteList = this.bookNoteList.concat(data.page.list)
        }
      }).then(response => {
        this.$refs.browseMore.stopLoading()
      }).catch(error => {
        this.$refs.browseMore.stopLoading()
        console.log(error)
      })
    }
  },
  components: {
    'book-list-header': BookListHeader,
    'book-page-content': BookPageContent,
    'book-page-footer': BookPageFooter,
    'book-list-cell': BookListCell,
    'book-reading-cell': BookReadingCell,
    'recommend': Recommend,
    'tag-wall': TagWall,
    'browse-more': BrowseMore,
    'book-note-list-cell': BookNoteListCell,
    'book-note-list-header': BookNoteListHeader
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .book-list-content
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
      margin-bottom 50px
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
