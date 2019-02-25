<template>
  <div class="tag-wall">
    <panel :title="'标签墙'">
      <div slot="content" class="content">
        <iv-tag v-for="(tag , index) in tagList" type="dot" color="blue" :key="index" style="margin: 0 5px 5px 0;" class="dot-tag">{{ tag.name +' ['+ tag.linkNum + ']'}}
        </iv-tag>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from '@/components/utils/Panel'

export default {
  data () {
    return {
      tagList: []
    }
  },
  components: {
    'panel': Panel
  },
  created () {
    this.listTag()
  },
  methods: {
    listTag () {
      this.$http({
        url: this.$http.adornUrl('/operation/tags'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.tagList = data.tagList
        }
      })
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/index.styl";

  .tag-wall
    .content
      padding 5px 20px

</style>
