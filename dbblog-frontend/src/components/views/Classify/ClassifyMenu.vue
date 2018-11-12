<template>
  <div class="classify-bar">
    <p class="level level-one">
      <span class="title">方向：</span>
      <span class="class">
        <a @click="listOrientation()" :class="allOrientation?'active':''">全部</a>
        <a :class="orientation.active?'active':''" @click="listArticle('orientation',orientation.orientationId);listCategoryByOrId(orientation)" class="name" v-for="orientation in orientationList" :key="orientation.orientationId">{{orientation.name}}</a>
      </span>
    </p>
    <p class="level level-two" v-if="categoryList.length">
      <span class="title">分类：</span>
      <span class="class">
        <a href="" :class="allCategory?'active':''" ref="allCategory">全部</a>
        <a :class="category.active?'active':''" @click="listArticle('category',category.categoryId);listTagByCaId(category)" class="name" v-for="category in categoryList" :key="category.categoryId">{{category.name}}</a>
      </span>
    </p>
    <p class="level level-three" v-if="tagList.length">
      <span class="title">标签：</span>
      <span class="class">
        <a href="" :class="allTag?'active':''" ref="allTag">全部</a>
        <a :class="tag.active?'active':''" @click="listArticle('tag',tag.tagId)" class="name" v-for="tag in tagList" :key="tag.tagId">{{tag.tagName}}</a>
      </span>
    </p>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      orientationList: [],
      categoryList: [],
      tagList: [],
      allOrientation: true,
      allCategory: true,
      allTag: true
    }
  },
  created () {
    this.listOrientation()
  },
  methods: {
    listArticle (type, classifyId) {
      let params = {}
      switch (type) {
        case 'orientation':
          params.orientationId = classifyId
          break
        case 'category':
          params.categoryId = classifyId
          break
        case 'tag':
          params.tagId = classifyId
          break
      }
      this.$emit('listArticle', params)
    },
    listOrientation () {
      this.$http({
        url: this.$http.adornUrl('/article/classify/orientations'),
        params: this.$http.adornParams(),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.orientationList = data.orientationList
          this.allOrientation = true
          this.categoryList = []
          this.tagList = []
        }
      })
      this.listArticle()
    },
    listCategoryByOrId (orientation) {
      this.$http({
        url: this.$http.adornUrl('/article/classify/categories'),
        params: this.$http.adornParams({
          orientationId: orientation.orientationId
        }),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryList = data.categoryList
          this.allOrientation = false
          this.allCategory = true
          this.tagList = []
        }
      })
      this.replaceActive(this.orientationList, orientation)
    },
    listTagByCaId (category) {
      this.$http({
        url: this.$http.adornUrl('/article/classify/tags'),
        params: this.$http.adornParams({
          categoryId: category.categoryId
        }),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.allCategory = false
          this.tagList = data.tagList
        }
      })
      this.replaceActive(this.categoryList, category)
    },
    replaceActive (mappingList, mapObj) {
      mappingList.forEach(item => {
        if (item === mapObj) {
          item.active = true
        } else {
          item.active = false
        }
      })
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl";
  @import "../../../common/stylus/theme.styl";

  .classify-bar
    .level
      display flex
      padding 15px 0
      font-size 15px
      line-height 22px
      border-bottom 1px solid $color-border
      .title
        display inline-block
        flex 0 0 60px
        width 60px
        font-size 17px
        line-height 22px
        padding 4px 0
        font-weight 700
      .class
        a
          display inline-block
          margin-right 4px
          padding 3px 8px
          margin-bottom 2px
          font-weight 100
          border-radius $border-radius
          color $color-gradually-gray-31
          &.name
            &:hover
              color $color-main-primary
          &.active
            color $color-gradually-gray-101
            background $color-gradually-gray-31
</style>
