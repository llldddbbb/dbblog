<template>
  <ul v-if="directoryList" :class="menuRoot?'menu-root':''">
    <li v-for="directory in directoryList" :key="directory.class">
      <a :class="directory.class" >{{directory.name}}</a>
      <SubSideToc  :directoryList="directory.list"></SubSideToc>
    </li>
  </ul>
</template>

<script type="text/ecmascript-6">
import SubSideToc from './SubSideToc'

export default {
  name: 'SubSideToc',
  props: {
    directoryList: Array,
    menuRoot: Boolean
  },
  components: {
    SubSideToc
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
</style>
