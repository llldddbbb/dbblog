<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-form-item label="参数键" prop="parKey">
        <el-input v-model="dataForm.parKey" placeholder="参数键"></el-input>
      </el-form-item>
      <el-form-item label="参数值" prop="parValue">
        <el-input v-model="dataForm.parValue" placeholder="参数值"></el-input>
      </el-form-item>
      <el-form-item label="参数url" prop="menuUrl">
        <el-input v-model="dataForm.menuUrl" disabled placeholder="参数url"></el-input>
      </el-form-item>
      <el-form-item label="参数类型" prop="type">
        <el-input v-model="dataForm.type" placeholder="参数类型"></el-input>
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
      dataForm: {
      },
      dataRule: {
        id: [{ required: true, message: '主键不能为空', trigger: 'blur' }],
        parKey: [{ required: true, message: '参数键不能为空', trigger: 'blur' }],
        parValue: [{ required: true, message: '参数值不能为空', trigger: 'blur' }],
        type: [{ required: true, message: '参数类型不能为空', trigger: 'blur' }]
      }
    }
  },
  methods: {
    init (id, menuUrl) {
      this.dataForm.id = id || ''
      this.visible = true
      this.confirmButtonDisabled = false
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/admin/sys/param/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.dataForm = data.param
            }
          })
        } else {
          this.dataForm = {
            menuUrl: menuUrl,
            type: menuUrl ? (menuUrl.replace('/', '')).toUpperCase() : ''
          }
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/admin/sys/param/${!this.dataForm.id ? 'save' : 'update'}`),
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
    }
  }
}
</script>
