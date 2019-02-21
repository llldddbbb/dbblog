<template>
  <div id="mavon-editor" :class="theme">
    <div class="operate">
      <iv-row :gutter="15">
        <iv-col :xs="8" :sm="8" :md="6" :lg="6">
          <iv-input v-model="name" placeholder="请输入您的昵称" size="large">
            <span slot="prepend">昵称 </span>
          </iv-input>
        </iv-col>
        <iv-col :xs="16" :sm="16" :md="12" :lg="11">
          <iv-input v-model="email" placeholder="联系方式（邮箱或手机号）以评论" size="large">
            <iv-select v-model="select" slot="prepend" style="width: 80px">
              <iv-option value="email">邮箱 </iv-option>
              <iv-option value="mobile">手机号 </iv-option>
            </iv-select>
          </iv-input>
        </iv-col>
        <!--<iv-col :xs="24" :sm="24" :md="6" :lg="7" class-name="iv-dropdown-link">-->
          <!--<iv-dropdown>-->
            <!--<iv-icon type="log-in"></iv-icon> 或登录以评论 <iv-icon type="arrow-down-b"></iv-icon>-->
            <!--<iv-dropdown-menu slot="list">-->
              <!--<iv-dropdown-item>菜单</iv-dropdown-item>-->
              <!--<iv-dropdown-item>菜单</iv-dropdown-item>-->
              <!--<iv-dropdown-item>菜单</iv-dropdown-item>-->
              <!--<iv-dropdown-item disabled>菜单</iv-dropdown-item>-->
              <!--<iv-dropdown-item divided>菜单</iv-dropdown-item>-->
            <!--</iv-dropdown-menu>-->
          <!--</iv-dropdown>-->
        <!--</iv-col>-->
      </iv-row>
    </div>
    <div class="editor-area">
      <iv-spin size="large" v-if="!editable" fix style="z-index: 1001;">
        该文章已关闭评论
      </iv-spin>
      <mavon-editor class="editor-area"
                    style="height: 100%; min-height: 50px; min-width: 200px; z-index: 9;"
                    :toolbarsFlag="toolbarsFlag"
                    :subfield="subfield"
                    :placeholder="placeholder"
                    :toolbars="toolbars"
                    :editable="editable"
                    v-if="editable"
                    @change="change"></mavon-editor>
    </div>
    <div class="bottom-area">
      <div class="comment-tip">
        <a href="https://guides.github.com/features/mastering-markdown/" target="_blank"><iv-icon
          type="information-circled"></iv-icon> 支持MarkDown</a>
      </div>
      <div class="buttons">
        <iv-button size="default" @click="publish" :type="buttonType"  :disabled="!editable">发布</iv-button>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  props: {
    theme: {
      Type: String,
      default: ''
    },
    subfield: {
      default: false
    },
    placeholder: {
      default: '输入评论内容...'
    },
    toolbarsFlag: {
      default: false
    },
    editable: false
  },
  data () {
    return {
      name: '',
      select: 'email',
      email: '',
      mobile: '',
      valueChanged: false,
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: false, // 沉浸式阅读
        htmlcode: false, // 展示html源码
        help: false, // 帮助
        /* 1.3.5 */
        undo: false, // 上一步
        redo: false, // 下一步
        trash: true, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: false, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true // 预览
      }
    }
  },
  computed: {
    buttonType: function () {
      return this.theme === 'dark-theme' ? 'warning' : 'primary'
    }
  },
  name: 'editor',
  components: {
    'mavon-editor': MavonEditor.mavonEditor
  },
  methods: {
    change (value) {
      if (value.length > 0) {
        if (!this.valueChanged) {
          this.$emit('valueChanged', true)
          this.valueChanged = true
          this.toolbarsFlag = true
          this.toolbars['navigation'] = true
          this.listenWindowWidth()
        }
      } else {
        if (this.valueChanged) {
          this.$emit('valueChanged', false)
          this.valueChanged = false
          this.toolbarsFlag = false
          this.toolbars['navigation'] = false
          this.listenWindowWidth()
        }
      }
    },
    listenWindowWidth () {
      // 此方法用于监听窗口宽度变化,改变编辑器菜单
      var clientWidth = document.documentElement.clientWidth
      if (clientWidth < 900) {
        this.$set(this.toolbars, 'readmodel', false)
        this.$set(this.toolbars, 'htmlcode', false)
        this.$set(this.toolbars, 'navigation', false)
        this.$set(this.toolbars, 'subfield', false)
      } else {
        this.$set(this.toolbars, 'readmodel', true)
        this.$set(this.toolbars, 'htmlcode', true)
        this.$set(this.toolbars, 'navigation', true)
        this.$set(this.toolbars, 'subfield', true)
      }
    },
    publish () {
      console.log('publish')
    }
  },
  mounted () {
    var that = this
    window.onresize = function temp () {
      that.listenWindowWidth()
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";

  #mavon-editor
    height 100%
    width 100%
    display flex
    flex-direction column
    .operate
      margin-bottom 15px
      .iv-dropdown-link
        display block
        height 36px
        line-height 36px
        text-align right
        font-size 15px
        color $color-main-primary
        &:hover
          cursor pointer
    .editor-area
      position relative
      flex 1
      padding 2px
      height 100%
      min-height 50px
      min-width 200px
    .bottom-area
      flex 0 0 40px
      height 40px
      display flex
      padding-top 15px
      justify-content: space-between
    &.dark-theme
      .operate
        margin-bottom 15px
        .iv-dropdown-link
          display block
          height 36px
          line-height 36px
          text-align right
          font-size 15px
          color $color-gradually-gray-61
          &:hover
            color $color-secondary-warning
            border-bottom 2px solid $color-secondary-warning
            cursor pointer
      .bottom-area
        .comment-tip
          a
            color $color-gradually-gray-61
            &:hover
              color $color-secondary-warning

</style>
