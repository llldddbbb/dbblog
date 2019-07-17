<template>
  <div class="bookNote-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <bookNote-page-header :bookNote="bookNote"></bookNote-page-header>
          <bookNote-page-content>
            <article id="article-main-page" class="typo container" slot="content" ref="article" v-html="bookNote.contentFormat">
            </article>
          </bookNote-page-content>
          <bookNote-page-footer :post-id = "bookNote.id"></bookNote-page-footer>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <recommend></recommend>
          <iv-affix :offset-top="60" >
            <side-toc style="margin-top: 15px;"  ></side-toc>
          </iv-affix>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>
<script type="text/ecmascript-6">
import BookNotePageHeader from '@/components/views/BookNote/BookNotePageHeader'
import BookNotePageContent from '@/components/views/BookNote/BookNotePageContent'
import BookNotePageFooter from '@/components/views/BookNote/BookNotePageFooter'
import About from '@/components/views/About'
import FriendLinks from '@/components/views/FriendLinks'
import SideToc from '@/components/views/SideToc'
import Recommend from '@/components/views/Recommend'
import TOC from '@/common/js/MarkdownToc'
// TOC滚动监听
import TocScrollSpy from '@/common/js/TocScrollSpy'

export default {
  data () {
    return {
      bookNote: {}
    }
  },
  components: {
    'bookNote-page-header': BookNotePageHeader,
    'bookNote-page-content': BookNotePageContent,
    'bookNote-page-footer': BookNotePageFooter,
    'about': About,
    'friend-links': FriendLinks,
    'side-toc': SideToc,
    'recommend': Recommend
  },
  created: function () {
    this.getBookNote(this.$route.params.bookNoteId)
  },
  methods: {
    addCodeLineNumber () {
      // 添加行号
      let blocks = this.$refs.article.querySelectorAll('pre code')
      blocks.forEach((block) => {
        window.hljs.highlightBlock(block)
        // 去前后空格并添加行号
        block.innerHTML = '<ul><li>' + block.innerHTML.replace(/(^\s*)|(\s*$)/g, '').replace(/\n/g, '\n</li><li>') + '\n</li></ul>'
      })
    },
    getBookNote (bookNoteId) {
      this.$http({
        url: this.$http.adornUrl('/bookNote/' + bookNoteId),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.bookNote = data.bookNote
          // 更新目录、高亮代码
          this.$nextTick(function () {
            this.addCodeLineNumber()
            this.refreshDiectory()
            this.refreshMobileDirectory()
            document.title = this.bookNote.title + ' | Bobbi的个人博客 | 一个努力成长中的Java后端程序猿'
          })
        }
      })
    },
    refreshDiectory () {
      /* eslint-disable*/
      new TOC('article-main-page', {
        'level': 5,
        'top': 200,
        'class': 'list',
        'targetId': 'side-toc'
      })
      /* eslint-disable */
      new TocScrollSpy('article-main-page', 'side-toc', {
        'spayLevel': 5,
        'articleMarginTop': 0
      });
    },
    refreshMobileDirectory () {
      /* eslint-disable */
      new TOC('article-main-page', {
        'level': 5,
        'top': 200,
        'class': 'list',
        'targetId': 'sidebar-toc'
      });
      new TocScrollSpy('article-main-page', 'sidebar-toc', {
        'spayLevel': 5,
        'articleMarginTop': 15
      });
    }
  }

};
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .bookNote-content
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
</style>
