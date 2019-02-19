<template>
  <div class="article-list-header">
    <classify-menu @listArticle="listArticleByCategory" :categorys="categorys" ></classify-menu>
    <section-title :mainTitle="'文章列表'" :subTitle="'Articles'">
      <title-menu-filter @refreshArticle="refreshArticle"  slot="menu" :menu-filter-list="articleFilterList"></title-menu-filter>
    </section-title>
  </div>
</template>

<script type="text/ecmascript-6">
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import ClassifyMenu from '@/components/views/Classify/ClassifyMenu'
import TitleMenuFilter from '@/components/views/SectionTitle/TitleMenuFilter'
import merge from 'lodash/merge' // 合并对象工具
export default {
  data () {
    return {
      articleFilterList: [
        {
          name: '最新',
          type: 'latest',
          active: true
        },
        {
          name: '点赞最多',
          type: 'favorite',
          active: false
        },
        {
          name: '评论最多',
          type: 'commentMost',
          active: false
        },
        {
          name: '推荐',
          type: 'recommend',
          active: false
        }
      ],
      categoryParam: {}
    }
  },
  components: {
    'section-title': SectionTitle,
    'classify-menu': ClassifyMenu,
    'title-menu-filter': TitleMenuFilter
  },
  props: {
    categorys: Array
  },
  methods: {
    refreshArticle (param) {
      let params = merge(param, this.categoryParam)
      this.$emit('listArticle', params)
    },
    listArticleByCategory (params) {
      this.categoryParam = params
      this.$emit('listArticle', params)
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">

</style>
