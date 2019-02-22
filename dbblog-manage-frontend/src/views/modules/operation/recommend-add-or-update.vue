<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-form-item>
        <el-popover v-model="recommendListShow"
                    placement="right"
                    width="500"
                    trigger="click">
          <el-form :inline="true"  @keyup.enter.native="listRecommend()">
            <el-form-item>
              <el-select v-model="listParams.type" >
                <el-option v-for="type in typeList" :key="type.parKey" :value="type.parKey" :label="type.parValue"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-input v-model="listParams.name" placeholder="文章标题" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="listRecommend()">查询</el-button>
            </el-form-item>
          </el-form>
          <el-table :data="recommendList" style="height: 500px;overflow: auto" >
            <el-table-column property="title" label="文章标题"></el-table-column>
            <el-table-column property="type"  label="文章类型">
              <template  slot-scope="scope" >
                {{getSysParam('MODULE_TYPE',scope.row.type,typeList)}}
              </template>
            </el-table-column>
            <el-table-column
              header-align="center"
              align="center"
              label="操作">
              <template slot-scope="scope">
                <el-button type="success" size="small" @click="selectRecommend(scope.row)">选择</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button slot="reference" @click="recommendListShow = true;  listRecommend()" >请选择推荐文章</el-button>
        </el-popover>
      </el-form-item>
      <el-form-item label="推荐文章" >
        {{dataForm.title}}
      </el-form-item>
      <el-form-item label="推荐类型" >
        {{getSysParam('MODULE_TYPE',dataForm.type,typeList)}}
      </el-form-item>
      <el-form-item label="顺序" >
        <el-input v-model="dataForm.orderNum" type="number" placeholder="顺序"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" :disabled="confirmButtonDisabled">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      confirmButtonDisabled: false,
      dataForm: {},
      dataRule: {
        linkId: [{ required: true, message: '推荐的文章Id不能为空', trigger: 'blur' }],
        type: [{ required: true, message: '推荐类型不能为空', trigger: 'blur' }]
      },
      recommendListShow: false,
      recommendList: [],
      typeList: this.getSysParamArr('MODULE_TYPE'),
      listParams: {}
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || ''
      this.visible = true
      this.confirmButtonDisabled = false
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/admin/operation/recommend/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.dataForm = data.recommend
            }
          })
        } else {
          this.dataForm = {}
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/admin/operation/recommend/${!this.dataForm.id ? 'save' : 'update'}`),
            method: !this.dataForm.id ? 'post' : 'put',
            data: this.$http.adornData(this.dataForm)
          }).then(({data}) => {
            this.confirmButtonDisabled = true
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1000,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
    listRecommend () {
      this.$http({
        url: this.$http.adornUrl(`/admin/operation/recommend/select`),
        method: 'get',
        params: this.$http.adornParams(this.listParams)
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.recommendList = data.recommendList
        }
      })
    },
    selectRecommend (recommend) {
      this.dataForm.linkId = recommend.linkId
      this.dataForm.title = recommend.title
      this.dataForm.type = recommend.type
      this.recommendListShow = false
    }
  }
}
</script>
