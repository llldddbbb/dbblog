import Vue from 'vue'
import Router from 'vue-router'
import {LoadingBar} from 'iview'
import HelloWorld from '@/components/HelloWorld'
import Index from '@/components/index/Index'

// 公共区域
// import CommonHeader from '@/components/header/CommonHeader';
import SimpleHeader from '@/components/header/SimpleHeader/SimpleHeader'
import CommonFooter from '@/components/footer/CommonFooter'

// 首页
import HomeContent from '@/components/content/HomeContent'
import ArticleContent from '@/components/content/ArticleContent'
import BookNoteContent from '@/components/content/BookNoteContent'
import BookContent from '@/components/content/BookContent'
import ArticleListContent from '@/components/content/ArticleListContent'
import BookNoteListContent from '@/components/content/BookNoteListContent'
import PhotographContent from '@/components/content/PhotographContent'
import MovieContent from '@/components/content/MovieContent'
import MovieListContent from '@/components/content/MovieListContent'
import AlbumPreviewContent from '@/components/content/AlbumPreviewContent'
import TimeLineContent from '@/components/content/TimeLineContent'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        {
          path: '/',
          name: 'index',
          components: {
            header: SimpleHeader,
            content: HomeContent,
            footer: CommonFooter
          }
        },
        {
          path: 'article/:articleId',
          name: 'article',
          components: {
            header: SimpleHeader,
            content: ArticleContent,
            footer: CommonFooter
          }
        },
        {
          path: 'articles',
          name: 'articles',
          components: {
            header: SimpleHeader,
            content: ArticleListContent,
            footer: CommonFooter
          }
        },
        {
          path: 'bookNote/:bookNoteId',
          name: 'bookNote',
          components: {
            header: SimpleHeader,
            content: BookNoteContent,
            footer: CommonFooter
          }
        },
        {
          path: 'bookNotes',
          name: 'bookNotes',
          components: {
            header: SimpleHeader,
            content: BookNoteListContent,
            footer: CommonFooter
          }
        },
        {
          path: 'book/:bookId',
          name: 'book',
          components: {
            header: SimpleHeader,
            content: BookContent,
            footer: CommonFooter
          }
        },
        {
          path: 'photograph',
          name: 'photograph',
          components: {
            header: SimpleHeader,
            content: PhotographContent,
            footer: CommonFooter
          }
        },
        {
          path: 'album',
          name: 'album',
          components: {
            content: AlbumPreviewContent
          }
        },
        {
          path: 'movie',
          name: 'movie',
          components: {
            header: SimpleHeader,
            content: MovieContent,
            footer: CommonFooter
          }
        },
        {
          path: 'movielist',
          name: 'movielist',
          components: {
            header: SimpleHeader,
            content: MovieListContent,
            footer: CommonFooter
          }
        },
        {
          path: 'timeline',
          name: 'timeline',
          components: {
            header: SimpleHeader,
            content: TimeLineContent,
            footer: CommonFooter
          }
        }
      ]
    },
    {
      path: '/hello',
      name: 'Hello',
      component: HelloWorld
    }
  ]
})

// 配置加载进度条
LoadingBar.config({
  color: '#5cb85c',
  failedColor: '#f0ad4e',
  height: 2
})

router.beforeEach((to, from, next) => {
  LoadingBar.start()
  next()
})

router.afterEach((to, from, next) => {
  LoadingBar.finish()
  window.scrollTo(0, 0)
})

export default router
