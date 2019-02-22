<template>
  <div class="bookNote-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <bookNote-list-header @filterByMenu="filterByMenu"
                               @filterByCategory="filterByCategory"
                               :categorys="categoryList"
                               :mainTitle="'笔记列表'" :sub-title="'BookNotes'" ></bookNote-list-header>
          <bookNote-list-cell v-for="bookNote in bookNoteList" :bookNote="bookNote" :key="bookNote.id"></bookNote-list-cell>
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
import BookNoteListHeader from '@/components/views/BookNote/BookNoteListHeader'
import BookNotePageContent from '@/components/views/BookNote/BookNotePageContent'
import BookNotePageFooter from '@/components/views/BookNote/BookNotePageFooter'
import BookNoteListCell from '@/components/views/BookNote/BookNoteListCell'
import Recommend from '@/components/views/Recommend'
import TagWall from '@/components/views/TagWall'
import BrowseMore from '@/components/views/BrowseMore'
import merge from 'lodash/merge'
import {treeDataTranslate} from '@/utils'
import {DefaultLimitSize} from '@/common/js/const'

export default {
  data () {
    return {
      bookNoteList: [],
      categoryList: [],
      currentPage: 1,
      pageSize: DefaultLimitSize,
      categoryId: undefined,
      menuParams: {},
      noMoreData: false
    }
  },
  created () {
    this.listBookNote()
    this.listCategory()
  },
  methods: {
    listBookNote () {
      let params = {
        categoryId: this.categoryId,
        limit: this.pageSize,
        page: this.currentPage
      }
      params = merge(params, this.menuParams)
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
    filterByMenu (params) {
      this.resetCurrentPage()
      this.menuParams = params
      this.listBookNote()
    },
    filterByCategory (params) {
      this.resetCurrentPage()
      this.categoryId = params
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
      params = merge(params, this.menuParams)
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
    'bookNote-list-header': BookNoteListHeader,
    'bookNote-page-content': BookNotePageContent,
    'bookNote-page-footer': BookNotePageFooter,
    'bookNote-list-cell': BookNoteListCell,
    'recommend': Recommend,
    'tag-wall': TagWall,
    'browse-more': BrowseMore
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .bookNote-list-content
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
