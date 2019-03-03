<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.title" placeholder="标题" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('book:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        prop="cover"
        header-align="center"
        align="center"
        label="封面"
        width="150">
        <template slot-scope="scope">
          <img :src="scope.row.cover" style="width: 50px;height: 100px">
        </template>
      </el-table-column>
    <el-table-column
        prop="title"
        header-align="center"
        align="center"
        label="标题">
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
            <el-button v-for="tag in scope.row.tagList" :key="tag.id" size="mini">{{tag.name}}</el-button>
          </el-row>
        </template>
      </el-table-column>
    <el-table-column
        prop="progress"
        header-align="center"
        align="center"
        width="200"
        label="进度">
      <template slot-scope="scope">
        <el-slider v-model="scope.row.progress" :step="10" @change="updateProgress(scope.row.id,scope.row.progress)"></el-slider>
      </template>
    </el-table-column>
      <el-table-column
        prop="recommend"
        header-align="center"
        align="center"
        label="推荐"
        width="80">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.recommend"
            active-color="#13ce66"
            @change="updateRecommend(scope.row.id,scope.row.recommend)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="reading"
        header-align="center"
        align="center"
        label="阅读"
        width="80">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.reading"
            active-color="#13ce66"
            @change="updateReading(scope.row.id,scope.row.reading)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="recommend"
        header-align="center"
        align="center"
        label="状态"
        width="100">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="点击发布" v-if="!scope.row.publish" placement="top">
            <el-button type="info" size="mini" @click="updatePublish(scope.row.id, true)">未发布</el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="点击下架" v-if="scope.row.publish" placement="top">
            <el-button type="success" size="mini" @click="updatePublish(scope.row.id, false)" v-if="scope.row.publish === true">已发布</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="读后感"
        width="80">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="修改读后感" placement="top">
            <el-button icon="el-icon-edit" circle @click="getReadSense(scope.row.id)"></el-button>
          </el-tooltip>
        </template>
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
    <!-- 弹窗, 读后感 -->
    <el-dialog title="读后感" :visible.sync="bookSenseVisible">
      <el-form>
        <el-form-item label="作者">
          <el-input v-model="bookSense.author"/>
        </el-form-item>
        <el-form-item>
          <quill-editor v-model="bookSense.content"></quill-editor>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateReadSense()">保存</el-button>
          <el-button @click="bookSenseVisible = false" >取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
// require styles 引入样式
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'
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
      dataListSelections: [],
      bookSenseVisible: false,
      bookSense: {
        content: '',
        author: ''
      }
    }
  },
  components: {
    quillEditor
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/admin/book/list'),
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
    // 新增 / 修改
    addOrUpdateHandle (id) {
      this.$router.push({path: 'book/book/update/' + id})
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
          url: this.$http.adornUrl('/admin/book/delete'),
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
    // 更新图书推荐状态
    updateRecommend (id, value) {
      let data = {
        id: id,
        recommend: value
      }
      this.updateStatus(data)
    },
    // 更新图书发布状态
    updatePublish (id, value) {
      let data = {
        id: id,
        publish: value
      }
      this.updateStatus(data)
    },
    // 更新图书阅读进度
    updateProgress (id, value) {
      let data = {
        id: id,
        progress: value
      }
      this.updateStatus(data)
    },
    // 更新阅读状态
    updateReading (id, value) {
      let data = {
        id: id,
        reading: value
      }
      this.updateStatus(data)
    },
    // 更新状态
    updateStatus (data) {
      this.$http({
        url: this.$http.adornUrl(`/admin/book/update/status`),
        method: 'put',
        data: this.$http.adornData(data)
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.$message.success('更新成功')
          this.getDataList()
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    // 更新读后感
    getReadSense (id) {
      this.$http({
        url: this.$http.adornUrl('/admin/book/sense/' + id),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.bookSense = data.bookSense
          this.bookSense.bookId = id
        }
      }).then(() => {
        this.bookSenseVisible = true
      })
    },
    // 更新读后感
    updateReadSense () {
      this.$http({
        url: this.$http.adornUrl(`/admin/book/sense/${!this.bookSense.id ? 'save' : 'update'}`),
        method: !this.bookSense.id ? 'post' : 'put',
        data: this.$http.adornData(this.bookSense)
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.bookSenseVisible = false
            }
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    }
  }
}
</script>
