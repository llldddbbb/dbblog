<template>
  <div class="classify-bar" v-if="categorys !== undefined">
    <p class="level level-one">
      <span class="title">一级：</span>
      <span class="class">
        <a class="active" data-level="1" @click="choseLevel(categorys[0].parentId, $event)">全部</a>
        <a class="name" :id="'id' + category_level1.id" :data-level="category_level1.rank"
           @click="choseLevel(category_level1, $event)"
           v-for="category_level1 in this.categorys" :key="category_level1.id">{{ category_level1.name }}</a>
      </span>
    </p>
    <p class="level level-two" v-if="sub_category !== undefined">
      <span class="title">二级：</span>
      <span class="class">
        <a class="active" data-level="2" @click="choseLevel(sub_category[0].parentId, $event)">全部</a>
        <a class="name" :id="'id' + category_level2.id" :data-level="category_level2.rank"
           @click="choseLevel(category_level2, $event)"
           v-for="category_level2 in this.sub_category" :key="category_level2.id">{{ category_level2.name}}</a>
      </span>
    </p>
    <p class="level level-three" v-if="sub_sub_category !== undefined">
      <span class="title">三级：</span>
      <span class="class">
        <a class="active" data-level="3" @click="choseLevel(sub_sub_category[0].parentId, $event)">全部</a>
        <a class="name" :id="'id' + category_level3.id" :data-level="category_level3.rank"
           @click="choseLevel(category_level3, $event)"
           v-for="category_level3 in this.sub_sub_category" :key="category_level3.id">{{ category_level3.name }}</a>
      </span>
    </p>
  </div>
</template>

<script type="text/ecmascript-6">
import {mixin} from '@/utils'
export default {
  name: 'classify-menu',
  mixins: [mixin],
  props: {
    defaultCategory: {
      Type: Number,
      default: null
    },
    categorys: Array
  },
  data () {
    return {
      sub_category: undefined,
      sub_sub_category: undefined,
      selectedCategory: undefined,
      selectedRecursiveCategorys: []
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.setDefaultCategory(parseInt(this.defaultCategory))
    })
  },
  methods: {
    choseLevel (category, event) {
      // 更新子菜单
      let level = parseInt(event.target.dataset.level)
      if (category instanceof Object) {
        this.selectCategory(category.id)
        if (level >= 0) {
          // 选择了某个类别
          if (category.children && category.children.length > 0) {
            if (level === 0) {
              this.sub_category = category.children
              this.sub_sub_category = undefined
            } else if (level === 1) {
              this.sub_sub_category = category.children
            }
          } else {
            // 点击了全部
            if (level === 0) {
              this.sub_category = undefined
              this.sub_sub_category = undefined
            } else if (level === 1) {
              this.sub_sub_category = undefined
            }
          }
        }
      } else {
        // 选择了第一级的全部
        if (level === 1) {
          this.sub_category = undefined
          this.sub_sub_category = undefined
        } else if (level === 2) {
          this.sub_sub_category = undefined
        }
        this.selectCategory(category)
      }
      // 更新样式
      let pNode = event.target.parentNode
      let activeNode = pNode.querySelector('.active')
      if (activeNode) {
        activeNode.classList.remove('active')
      }
      event.target.classList.add('active')
    },
    setDefaultCategory (categoryId) {
      let recursiveCategorys = []
      let recursiveCategoryIds = []
      let recursiveCategory = function (categorys, selectCategoryId) {
        if (categoryId === null || categoryId === undefined || isNaN(categoryId)) return null
        for (let index = 0; index < categorys.length; index++) {
          let category = categorys[index]
          if (category.id === selectCategoryId) {
            // 如果id相同，表示命中，记录该category和category的id
            recursiveCategorys.push(category)
            recursiveCategoryIds.push(category.id)
            return category
          } else if (category.children && category.children.length > 0) {
            // 如果id不同，表示没命中，则在其sub_category中寻找
            let result = recursiveCategory(category.children, selectCategoryId)
            if (result) {
              // 如果在sub_category中找到了，则需要将自己的id也记录，组成一条树路径，以便后面使用
              recursiveCategorys.push(category)
              recursiveCategoryIds.push(category.id)
              return result
            }
          }
        }
      }
      this.selectedCategory = recursiveCategory(this.categorys, categoryId)
      recursiveCategorys = recursiveCategorys.reverse()
      recursiveCategoryIds = recursiveCategoryIds.reverse()
      // 第一级
      if (recursiveCategorys[0]) {
        // 第二级
        this.sub_category = recursiveCategorys[0].children
      } else {
        this.sub_category = undefined
      }
      if (recursiveCategorys[1]) {
        // 第三级
        this.sub_sub_category = recursiveCategorys[1].children
      } else {
        this.sub_sub_category = undefined
      }
      this.selectedRecursiveCategorys = recursiveCategoryIds
    },
    selectCategory (categoryId) {
      this.$emit('filterByCategory', categoryId === -1 ? undefined : categoryId)
    }
  },
  watch: {
    categorys: function (newCategorys) {
      if (newCategorys) {
        this.setDefaultCategory(parseInt(this.defaultCategory))
      }
    },
    defaultCategory: function (newDefaultCategory) {
      this.setDefaultCategory(parseInt(newDefaultCategory))
    },
    selectedRecursiveCategorys: function (newSelectedRecursiveCategorys) {
      // 更新样式
      this.$nextTick(() => {
        if (newSelectedRecursiveCategorys.length === 0) {
          // 表示没有默认选择
          let targets = document.getElementsByClassName('active')
          for (let index = 0; index < targets.length; index++) {
            let target = targets[index]
            target.classList.remove('active')
            let pNode = target.parentNode
            let activeNode = pNode.childNodes[0]
            if (activeNode) {
              activeNode.classList.add('active')
            }
          }
          return
        }
        newSelectedRecursiveCategorys.map((id) => {
          let target = document.getElementById('id' + id)
          let pNode = target.parentNode
          let activeNode = pNode.querySelector('.active')
          if (activeNode) {
            activeNode.classList.remove('active')
          }
          target.classList.add('active')
        })
      })
    }
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .classify-bar
    .level
      display flex
      padding 10px 0
      font-size 15px
      line-height 22px
      border-bottom 1px solid $default-border-color
      .title
        display inline-block
        flex 0 0 60px
        width 60px
        font-size 17px
        line-height 22px
        padding 4px 0
        font-weight 700
        color $default-title-color
      .class
        a
          display inline-block
          margin-right 4px
          padding 3px 8px
          margin-bottom 2px
          font-weight 300
          border-radius $border-radius
          color $default-link-color
          &.name
            &:hover
              color $default-link-hover-color
          &.active
            color $default-select-color
            background $default-select-background-hover-color
</style>
