<template>
  <div class="tag-wall">
    <panel :title="'相关标签'">
      <div slot="content" class="content">
        <iv-tag v-for="tag in tags" type="dot" color="blue" :key="tag" style="margin: 0 5px 5px 0;">{{ tag }}
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
      tags: ['iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'iOS', '大数据', 'python', 'iOS', '大数据', 'python', '流式计算', 'Linux', 'Big Date', 'PHP', 'Java', 'Spring', 'HTML', 'CSS', '流式计算', 'Linux', 'Big Date', 'PHP', '流式计算', 'Linux', 'Big Date', 'PHP']
    }
  },
  components: {
    'panel': Panel
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/index.styl";

  .tag-wall
    .content
      padding 5px 20px

</style>
