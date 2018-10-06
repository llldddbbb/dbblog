<template>
  <div class="home-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
        <div class="layout-left">
          <section-title :mainTitle="'文章'" :subTitle="'Articles'">
            <title-menu-filter slot="menu"></title-menu-filter>
          </section-title>
          <article-list-cell v-for="article in articles" :article="article" :key="article.title"></article-list-cell>
          <section-title :mainTitle="'主题'" :subTitle="'Topics'"></section-title>
          <div class="topic-cards">
            <iv-row :gutter="10">
              <iv-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8">
                <topic-card></topic-card>
              </iv-col>
              <iv-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8">
                <topic-card></topic-card>
              </iv-col>
              <iv-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8">
                <topic-card></topic-card>
              </iv-col>
              <iv-col :xs="24" :sm="12" :md="8" :lg="8" :xl="8">
                <topic-card></topic-card>
              </iv-col>
            </iv-row>
          </div>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <about></about>
          <friend-links style="margin-top:15px;"></friend-links>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import ArticleListCell from '@/components/views/Article/ArticleListCell'
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import TitleMenuFilter from '@/components/views/SectionTitle/TitleMenuFilter'
import TopicCard from '@/components/views/TopicCard'
import ArticlePageHeader from '@/components/views/Article/ArticlePageHeader'
import ArticlePageContent from '@/components/views/Article/ArticlePageContent'
import ArchiveListTimeTitle from '@/components/views/Archive/ArchiveListTimeTitle'
import ArchiveListCell from '@/components/views/Archive/ArchiveListCell'
import About from '@/components/views/About'
import FriendLinks from '@/components/views/FriendLinks'
import SideToc from '@/components/views/SideToc'

export default {
  data () {
    return {
      articles: [
        {
          'id': 1,
          'title': '被迫向现实低头，再爱也只能到此为止',
          'author': '男孩与榴莲',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 1
        },
        {
          'id': 2,
          'title': '我曾经喜欢过一个人',
          'author': '不能吃糖的0240',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 1
        },
        {
          'id': 3,
          'title': '离开你以后我还是很喜欢很喜欢你',
          'author': 'JUNE.',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 2
        },
        {
          'id': 4,
          'title': '思念如马，自别离，未停蹄！相思若柳，飘满城，尽飞絮！',
          'author': '每日小情书',
          'publish_time': '2017-10-22 17:57:08',
          'desc': '离奇消失亚伯勒郡有一条出名的“S”形单向公路，路面狭窄，两侧耸立着高大的石墙，只能容一辆车子通行',
          'readings': '148',
          'comments': '2',
          'likes': '20',
          'type': 3
        }
      ]
    }
  },
  components: {
    'article-list-cell': ArticleListCell,
    'section-title': SectionTitle,
    'title-menu-filter': TitleMenuFilter,
    'topic-card': TopicCard,
    'article-page-header': ArticlePageHeader,
    'article-page-content': ArticlePageContent,
    'archive-list-time-title': ArchiveListTimeTitle,
    'archive-list-cell': ArchiveListCell,
    'about': About,
    'friend-links': FriendLinks,
    'side-toc': SideToc
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .home-content
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
