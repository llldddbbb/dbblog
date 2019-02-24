<template>
  <div class="book-catalog">
    <ul class="book-toc-list">
      <li class="book-toc-list-item" v-for="bookNote in book.bookNoteList" :key="bookNote.id">
        <a class="book-toc-link" :href="`/bookNote/${bookNote.id}`">
          <span><iv-icon :type="'android-checkbox-outline'"></iv-icon></span>
          {{ bookNote.title }}
        </a>
      </li>
    </ul>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'book-catalog',
  props: {
    book: {
      Type: Object,
      default: undefined
    }
  },
  data () {
    return {
      showMoreToc: false,
      showToggleMoreMenu: false
    }
  },
  mounted () {
    this.$nextTick(() => {
      let toc = document.querySelector('.book-catalog ul')
      if (toc && toc.clientHeight >= 360) {
        this.showToggleMoreMenu = true
      } else {
        this.showToggleMoreMenu = false
      }
    })
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .book-catalog
    li
      list-style: none
    .book-toc-list
      margin 0
      padding-left 25px
      overflow-y hidden
    > .book-toc-list
      max-height 360px
      padding-left 8px
    .book-toc-list-item
      a.book-toc-link
        height 100%
        line-height 30px
        padding 5px 0
        color $default-desc-color
        i
          font-size 16px
        &.is-active-link
          font-weight: 700
          color $default-link-hover-color
        &:hover
          color $default-link-hover-color
    &.is-collapsible
      max-height 1000px
      overflow hidden
      transition all 300ms ease-in-out
    &.is-collapsed
      max-height 0
    .toggle-more
      position relative
      display block
      color $default-link-hover-color
      background-color transparent
      margin-top -40px
      padding 60px 0 10px
      text-align center
      span
        position relative
      .mask
        position absolute
        left 0
        right 0
        top 0
        bottom 0
        opacity 1
        background-image linear-gradient(rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 1))
  &.open
    > .book-toc-list
      max-height inherit
      overflow hidden
      transition all 300ms ease-in-out
    .toggle-more
      .mask
        opacity 0
</style>
