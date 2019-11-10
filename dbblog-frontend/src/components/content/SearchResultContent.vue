<template>
  <div class="article-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <section-title :mainTitle="'搜索结果'" :subTitle="this.$route.query.keywords"> </section-title>
        <article-list-cell v-for="article in articleList" :article="article" :key="article.id"></article-list-cell>
        <div v-if="noResult">暂无相关文章</div>
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
// mixin
import {mixin} from '@/utils'
import ArticlePageContent from '@/components/views/Article/ArticlePageContent'
import ArticlePageFooter from '@/components/views/Article/ArticlePageFooter'
import ArticleListCell from '@/components/views/Article/ArticleListCell'
import Recommend from '@/components/views/Recommend'
import TagWall from '@/components/views/TagWall'
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
export default {
  data () {
    return {
      articleList: [],
      noResult: false
    }
  },
  mixins: [mixin],
  created () {
    this.listSearchArticle()
  },
  methods: {
    listSearchArticle () {
      this.$http({
        url: this.$http.adornUrl('articles/search'),
        type: 'get',
        params: this.$http.adornParams({keywords: this.$route.query.keywords})
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.articleList = data.articleList
          if (this.articleList.length > 0) {
            this.articleList.map(article => {
              article.coverType = 2
            })
          } else { this.noResult = true }
        }
      })
    }
  },
  components: {
    'article-page-content': ArticlePageContent,
    'article-page-footer': ArticlePageFooter,
    'article-list-cell': ArticleListCell,
    'recommend': Recommend,
    'tag-wall': TagWall,
    'section-title': SectionTitle
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  .article-list-content
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
