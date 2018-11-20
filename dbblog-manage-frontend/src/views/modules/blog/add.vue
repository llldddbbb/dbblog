<template>
  <div>
    <el-form :inline="true" label-width="80px">
      <el-form-item label="博客标题">
        <el-input placeholder="博客标题" v-model="article.title" clearable></el-input>
      </el-form-item>
      <el-form-item label="博客作者">
        <el-input placeholder="博客作者" clearable></el-input>
      </el-form-item>
    </el-form>
    <el-form label-width="80px">
      <el-form-item label="是否推荐">
        <el-radio-group v-model="article.isRecommend">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="展示类型">
        <el-select v-model="article.type" placeholder="请选择">
          <el-option
            v-for="item in articleTypeList"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上传封面">
        <el-upload
          drag
          :action="url"
          :before-upload="beforeUploadHandle"
          :on-success="successHandle">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只支持jpg、png、gif格式的图片！</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="博文描述">
        <el-col :span="12">
          <el-input type="textarea" v-model="article.description" placeholder="博文描述" clearable></el-input>
        </el-col>
      </el-form-item>
      <el-form-item>
        <mavon-editor></mavon-editor>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

export default {
  components: {
    'mavon-editor': MavonEditor.mavonEditor
  },
  data () {
    return {
      article: {
        isRecommend: 0
      },
      articleTypeList: [{
        label: '小图片',
        value: 0
      }, {
        label: '大图片',
        value: 1
      }, {
        label: '无图片',
        value: 2
      }],
      url: ''
    }
  },
  created () {
    this.init()
  },
  methods: {
    init () {
      this.url = this.$http.adornUrl(`/admin/blog/article/cover/upload?token=${this.$cookie.get('token')}`)
    },
    // 上传之前
    beforeUploadHandle (file) {
      if (file.type !== 'image/jpg' && file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
        this.$message.error('只支持jpg、png、gif格式的图片！')
        return false
      }
    },
    // 上传成功
    successHandle (response) {
      if (response && response.code === 200) {
        this.$message.info('上传成功！')
      }
    }
  }
}
</script>
