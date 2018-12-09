<template>
<div>
  <el-row  :gutter="20">
    <el-col :span="12">
      <el-card shadow="hover">
        <div slot="header">
          <span>分类方向</span>
        </div>
        <el-form :inline="true">
          <el-form-item>
            <el-input placeholder="名称" v-model="orientationData.dataForm.name" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="getOriDataList()">查询</el-button>
            <el-button type="danger" :disabled="orientationData.dataListSelections.length <= 0" @click="oriDeleteHandle()">批量删除</el-button>
          </el-form-item>
        </el-form>
        <el-table
          :data="orientationData.dataList"
          border
          v-loading="orientationData.dataListLoading"
          @selection-change="oriSelectionChangeHandle"
          style="width: 100%;">
          <el-table-column
            type="selection"
            header-align="center"
            align="center"
            width="50">
          </el-table-column>
          <el-table-column
            prop="orientationId"
            header-align="center"
            align="center"
            width="80"
            label="编号">
          </el-table-column>
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="名称">
          </el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="oriAddOrUpdateHandle(scope.row.orientationId)">修改</el-button>
              <el-button type="text" size="small" @click="oriDeleteHandle(scope.row.orientationId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="oriSizeChangeHandle"
          @current-change="oriCurrentChangeHandle"
          :current-page="orientationData.pageIndex"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="orientationData.pageSize"
          :total="orientationData.totalPage"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card shadow="hover">
        <div slot="header">
          <span>分类类别</span>
        </div>
        <el-form :inline="true">
          <el-form-item>
            <el-input placeholder="名称" v-model="categoryData.dataForm.name" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="getOriDataList()">查询</el-button>
            <el-button type="danger" :disabled="categoryData.dataListSelections.length <= 0" @click="oriDeleteHandle()">批量删除</el-button>
          </el-form-item>
        </el-form>
        <el-table
          :data="categoryData.dataList"
          border
          v-loading="categoryData.dataListLoading"
          @selection-change="oriSelectionChangeHandle"
          style="width: 100%;">
          <el-table-column
            type="selection"
            header-align="center"
            align="center"
            width="50">
          </el-table-column>
          <el-table-column
            prop="categoryId"
            header-align="center"
            align="center"
            width="80"
            label="编号">
          </el-table-column>
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="名称">
          </el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="oriAddOrUpdateHandle(scope.row.categoryId)">修改</el-button>
              <el-button type="text" size="small" @click="oriDeleteHandle(scope.row.categoryId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="oriSizeChangeHandle"
          @current-change="oriCurrentChangeHandle"
          :current-page="categoryData.pageIndex"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="categoryData.pageSize"
          :total="categoryData.totalPage"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
      </el-card>
    </el-col>
  </el-row>
  <el-card shadow="hover">
    <div slot="header">
      <span>分类方向</span>
    </div>
    <el-form :inline="true">
      <el-form-item>
        <el-input placeholder="名称" v-model="orientationData.dataForm.name" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getOriDataList()">查询</el-button>
        <el-button type="danger" :disabled="orientationData.dataListSelections.length <= 0" @click="oriDeleteHandle()">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="orientationData.dataList"
      border
      v-loading="orientationData.dataListLoading"
      @selection-change="oriSelectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="orientationId"
        header-align="center"
        align="center"
        width="80"
        label="编号">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="名称">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="oriAddOrUpdateHandle(scope.row.orientationId)">修改</el-button>
          <el-button type="text" size="small" @click="oriDeleteHandle(scope.row.orientationId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="oriSizeChangeHandle"
      @current-change="oriCurrentChangeHandle"
      :current-page="orientationData.pageIndex"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="orientationData.pageSize"
      :total="orientationData.totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </el-card>
</div>
</template>

<script>
export default {
  data () {
    return {
      orientationData: {
        dataForm: {
          name: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 5,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: []
      },
      categoryData: {
        dataForm: {
          name: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 5,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: []
      }
    }
  },
  activated () {
    this.getOriDataList()
    this.getCatDataList()
  },
  methods: {
    // 获取数据列表
    getOriDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/article/classify/orientation/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.orientationData.pageIndex,
          'limit': this.orientationData.pageSize,
          'name': this.orientationData.dataForm.name
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.orientationData.dataList = data.page.list
          this.orientationData.totalPage = data.page.totalCount
        } else {
          this.orientationData.dataList = []
          this.orientationData.totalPage = 0
        }
        this.orientationData.dataListLoading = false
      })
    },
    // 每页数
    oriSizeChangeHandle (val) {
      this.orientationData.pageSize = val
      this.orientationData.pageIndex = 1
      this.getOriDataList()
    },
    // 当前页
    oriCurrentChangeHandle (val) {
      this.orientationData.pageIndex = val
      this.getOriDataList()
    },
    // 多选
    oriSelectionChangeHandle (val) {
      this.orientationData.dataListSelections = val
    },
    // 新增 / 修改
    oriAddOrUpdateHandle (id) {
      this.$router.push({path: 'article/update/' + id})
    },
    // 删除
    oriDeleteHandle (id) {
      let oriIds = id ? [id] : this.orientationData.dataListSelections.map(item => {
        return item.orientationId
      })
      this.$confirm(`确定对[id=${oriIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/article/classify/orientation/delete'),
          method: 'delete',
          data: this.$http.adornData(oriIds, false)
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getOriDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {})
    },

    // 获取数据列表
    getCatDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/article/classify/category/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.categoryData.pageIndex,
          'limit': this.categoryData.pageSize,
          'name': this.categoryData.dataForm.name
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryData.dataList = data.page.list
          this.categoryData.totalPage = data.page.totalCount
        } else {
          this.categoryData.dataList = []
          this.categoryData.totalPage = 0
        }
        this.categoryData.dataListLoading = false
      })
    },
    // 每页数
    catSizeChangeHandle (val) {
      this.categoryData.pageSize = val
      this.categoryData.pageIndex = 1
      this.getOriDataList()
    },
    // 当前页
    catCurrentChangeHandle (val) {
      this.categoryData.pageIndex = val
      this.getOriDataList()
    },
    // 多选
    catSelectionChangeHandle (val) {
      this.categoryData.dataListSelections = val
    },
    // 新增 / 修改
    catAddOrUpdateHandle (id) {
      this.$router.push({path: 'article/update/' + id})
    },
    // 删除
    catDeleteHandle (id) {
      let oriIds = id ? [id] : this.categoryData.dataListSelections.map(item => {
        return item.categoryId
      })
      this.$confirm(`确定对[id=${oriIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/article/classify/category/delete'),
          method: 'delete',
          data: this.$http.adornData(oriIds, false)
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getOriDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {})
    }
  }
}
</script>

<style scoped>

</style>
