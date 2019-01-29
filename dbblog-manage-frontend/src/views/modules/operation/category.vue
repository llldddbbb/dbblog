<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-select v-model="dataForm.type" clearable>
          <el-option v-for="type in typeList"
          :key="type.parKey"
          :value="type.parKey"
          :label="type.parValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.name" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('operation:category:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">
      <table-tree-column
        prop="name"
        header-align="center"
        width="150"
        label="名称">
      </table-tree-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        label="类型">
        <template slot-scope="scope">
          {{getSysParam('MODULE_TYPE',scope.row.type,typeList)}}
        </template>
      </el-table-column>
      <el-table-column
        prop="rank"
        header-align="center"
        align="center"
        label="级别">
        <template slot-scope="scope">
          {{getSysParam('CATEGORY_RANK', scope.row.rank)}}
        </template>
      </el-table-column>
      <el-table-column
        prop="parentName"
        header-align="center"
        align="center"
        label="上级级别">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import TableTreeColumn from '@/components/table-tree-column'
import AddOrUpdate from './category-add-or-update'
import { treeDataTranslate } from '@/utils'
export default {
  data () {
    return {
      dataForm: {
        name: '',
        type: ''
      },
      dataList: [],
      dataListLoading: false,
      addOrUpdateVisible: false,
      typeList: this.getSysParamArr('MODULE_TYPE')
    }
  },
  components: {
    AddOrUpdate,
    TableTreeColumn
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/operation/category/list'),
        method: 'get',
        params: this.$http.adornParams({
          name: this.dataForm.name,
          type: this.dataForm.type
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.dataList = treeDataTranslate(data.categoryList)
        } else {
          this.dataList = []
        }
        this.dataListLoading = false
      })
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle (id) {
      this.$confirm(`确定对[id=${id}]进行删除操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/operation/category/delete/' + id),
          method: 'delete',
          data: this.$http.adornData()
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
      })
    }
  }
}
</script>
