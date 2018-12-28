<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="类型" prop="type">
      <el-input v-model="dataForm.type" placeholder="类型"></el-input>
    </el-form-item>
    <el-form-item label="级别" prop="rank">
      <el-input v-model="dataForm.rank" placeholder="级别"></el-input>
    </el-form-item>
    <el-form-item label="父主键" prop="parentId">
      <el-input v-model="dataForm.parentId" placeholder="父主键"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: 0,
        name: '',
        type: '',
        rank: '',
        parentId: ''
      },
      dataRule: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '类型不能为空', trigger: 'blur' }
        ],
        rank: [
          { required: true, message: '级别不能为空', trigger: 'blur' }
        ],
        parentId: [
          { required: true, message: '父主键不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || ''
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/admin/express/operationcategory/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.dataForm.name = data.operationcategory.name
              this.dataForm.type = data.operationcategory.type
              this.dataForm.rank = data.operationcategory.rank
              this.dataForm.parentId = data.operationcategory.parentId
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/admin/express/operationcategory/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'name': this.dataForm.name,
              'type': this.dataForm.type,
              'rank': this.dataForm.rank,
              'parentId': this.dataForm.parentId
            })
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
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
