<template>
  <div class="mod-home">
    <h3>博客后台管理系统</h3>
    <panel-group></panel-group>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
import lineChart from './components/lineChart'
import panelGroup from './components/panelGroup'
const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}
export default {

  components: {
    panelGroup,
    lineChart
  },
  data () {
    return {
      lineChartData: lineChartData.newVisitis
    }
  }
}
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
</style>
