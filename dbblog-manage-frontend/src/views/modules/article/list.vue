<template>
<div>
  <el-form :inline="true">
    <el-form-item>
      <el-input placeholder="标题" v-model="dataForm.title" clearable></el-input>
    </el-form-item>
    <el-form-item>
      <el-button @click="getDataList()">查询</el-button>
      <el-button type="danger" :disabled="dataListSelections.length <= 0" @click="deleteHandle()">批量删除</el-button>
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
      prop="title"
      header-align="center"
      align="center"
      label="博文标题">
    </el-table-column>
    <el-table-column
      prop="categoryListStr"
      header-align="center"
      align="center"
      label="分类">
    </el-table-column>
    <el-table-column
      prop="tagList"
      header-align="center"
      align="center"
      label="标签"
      width="300">
      <template slot-scope="scope">
        <el-row>
          <el-button v-for="tag in scope.row.tagList" :key="tag.id" type="primary" size="mini">{{tag.tagName}}</el-button>
        </el-row>
      </template>
    </el-table-column>
    <el-table-column
      prop="readNum"
      header-align="center"
      align="center"
      label="浏览">
    </el-table-column>
    <el-table-column
      prop="commentNum"
      header-align="center"
      align="center"
      label="评论">
    </el-table-column>
    <el-table-column
      prop="likeNum"
      header-align="center"
      align="center"
      label="喜欢">
    </el-table-column>
    <el-table-column
      prop="recommend"
      header-align="center"
      align="center"
      label="是否推荐">
      <template slot-scope="scope">
        <el-switch
          v-model="scope.row.recommend"
          active-color="#13ce66"
          @change="updateRecommend(scope.row.articleId,scope.row.recommend)">
        </el-switch>
      </template>
    </el-table-column>
    <el-table-column
      fixed="right"
      header-align="center"
      align="center"
      width="150"
      label="操作">
      <template slot-scope="scope">
        <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.articleId)">修改</el-button>
        <el-button type="text" size="small" @click="deleteHandle(scope.row.articleId)">删除</el-button>
      </template>
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
  computed: {
    menuActiveName: {
      get () { return this.$store.state.common.menuActiveName },
      set (val) { this.$store.commit('common/updateMenuActiveName', val) }
    },
    mainTabs: {
      get () { return this.$store.state.common.mainTabs },
      set (val) { this.$store.commit('common/updateMainTabs', val) }
    },
    mainTabsActiveName: {
      get () { return this.$store.state.common.mainTabsActiveName },
      set (val) { this.$store.commit('common/updateMainTabsActiveName', val) }
    }
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/article/list'),
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
    },
    // tabs, 删除tab
    removeTabHandle (tabName) {
      this.mainTabs = this.mainTabs.filter(item => item.name !== tabName)
      if (this.mainTabs.length >= 1) {
        // 当前选中tab被删除
        if (tabName === this.mainTabsActiveName) {
          this.$router.push({ name: this.mainTabs[this.mainTabs.length - 1].name }, () => {
            this.mainTabsActiveName = this.$route.name
          })
        }
      } else {
        this.menuActiveName = ''
        this.$router.push({ name: 'home' })
      }
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.removeTabHandle('article-update')
      this.$router.push({path: 'article/article/update/' + id})
    },
    // 删除
    deleteHandle (id) {
      let articleIds = id ? [id] : this.dataListSelections.map(item => {
        return item.articleId
      })
      this.$confirm(`确定对[id=${articleIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/article/delete'),
          method: 'delete',
          data: this.$http.adornData(articleIds, false)
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {})
    },
    // 更新文章推荐状态
    updateRecommend (id, value) {
      this.$http({
        url: this.$http.adornUrl(`/admin/article/update`),
        method: 'put',
        data: this.$http.adornData({
          articleId: id,
          recommend: value
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.$message.success('更新博文成功')
        } else {
          this.$message.error(data.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
