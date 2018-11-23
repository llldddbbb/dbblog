<template>
<div>
  <el-form :inline="true">
    <el-form-item>
      <el-input placeholder="标题" v-model="dataForm.title" clearable></el-input>
    </el-form-item>
    <el-form-item>
      <el-button>查询</el-button>
      <el-button type="danger" :disabled="dataListSelections.length <= 0">批量删除</el-button>
    </el-form-item>
  </el-form>
  <el-table
    :data="dataList"
    border
    v-loading="dataListLoading"
    @selection-change="selectionChangeHandle"
    style="width: 100%;">
    <el-table-column
      type="selection"
      header-align="center"
      align="center"
      width="50">
    </el-table-column>
    <el-table-column
      prop="articleId"
      header-align="center"
      align="center"
      width="80"
      label="编号">
    </el-table-column>
    <el-table-column
      prop="author"
      header-align="center"
      align="center"
      label="作者"
      width="80">
    </el-table-column>
    <el-table-column
      prop="title"
      header-align="center"
      align="center"
      label="博文标题">
    </el-table-column>
    <el-table-column
      prop="description"
      header-align="center"
      align="center"
      label="博文描述">
    </el-table-column>
  </el-table>
  <el-pagination
    @size-change="sizeChangeHandle"
    @current-change="currentChangeHandle"
    :current-page="pageIndex"
    :page-sizes="[10, 20, 50, 100]"
    :page-size="pageSize"
    :total="totalPage"
    layout="total, sizes, prev, pager, next, jumper">
  </el-pagination>
</div>
</template>

<script>
export default {
  data () {
    return {
      dataForm: {
        title: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: []
    }
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/blog/article/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'title': this.dataForm.title
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    }
  }
}
</script>

<style scoped>

</style>
