<template>
  <div class="mod-config">
    <el-row>
      <el-col :span="9">
        <el-card>
          <div slot="header">
            <span>菜单列表</span>
          </div>
          <div>
            <el-tree
              :data="menuList"
              :props="menuListTreeProps"
              node-key="menuId"
              ref="menuListTree"
              @current-change="menuListTreeCurrentChangeHandle"
              :default-expand-all="true"
              :highlight-current="true"
              :expand-on-click-node="false">
            </el-tree>
          </div>
        </el-card>
      </el-col>
      <el-col :span="15">
        <el-card>
        <div slot="header">
          <span>参数列表</span>
        </div>
        <div>
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-input v-model="dataForm.type" placeholder="类型" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="getDataList()">查询</el-button>
              <el-button v-if="isAuth('sys:param:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
              <el-button v-if="isAuth('sys:param:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
              prop="parKey"
              header-align="center"
              align="center"
              label="参数键">
            </el-table-column>
            <el-table-column
              prop="parValue"
              header-align="center"
              align="center"
              label="参数值">
            </el-table-column>
            <el-table-column
              prop="menuUrl"
              header-align="center"
              align="center"
              label="参数url">
            </el-table-column>
            <el-table-column
              prop="type"
              header-align="center"
              align="center"
              label="参数类型">
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
        </el-card>
      </el-col>
    </el-row>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './param-add-or-update'
import { treeDataTranslate } from '@/utils'
export default {
  data () {
    return {
      dataForm: {
        type: '',
        menuUrl: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      menuList: [],
      menuListTreeProps: {
        label: 'name',
        children: 'children'
      },
      dynamicMenuRoutes: JSON.parse(sessionStorage.getItem('dynamicMenuRoutes') || '[]')
    }
  },
  components: {
    AddOrUpdate
  },
  activated () {
    this.getDataList()
    this.initMenu()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/sys/param/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'menuUrl': this.dataForm.menuUrl,
          'type': this.dataForm.type
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
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, this.dataForm.menuUrl)
      })
    },
    // 删除
    deleteHandle (id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对这${ids.length}条数据进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/sys/param/delete'),
          method: 'delete',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1000,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    },
    // 初始化菜单
    initMenu () {
      this.$http({
        url: this.$http.adornUrl('/admin/sys/menu/select'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        this.menuList = treeDataTranslate(data.menuList, 'menuId')
      })
    },
    // 菜单树选中
    menuListTreeCurrentChangeHandle (data, node) {
      let route = this.dynamicMenuRoutes.filter(item => item.meta.menuId === data.menuId)
      if (route.length >= 1) {
        this.dataForm.menuUrl = '/' + route[0].path
      } else {
        this.dataForm.menuUrl = ''
      }
      this.getDataList()
    }
  }
}
</script>
