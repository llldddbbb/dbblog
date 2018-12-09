<template>
  <div>
    <el-form :model="article" label-width="80px" :rules="rules" ref="articleForm">
      <el-form-item label="博文标题" prop="title">
        <el-col :span="12">
          <el-input placeholder="博文标题" v-model="article.title"  clearable></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="博文作者">
        <el-col :span="4">
          <el-input placeholder="博文作者" v-model="article.author" clearable></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="博文分类">
        <el-col :span="4">
          <el-select placeholder="请选择分类方向" filterable v-model="article.orientationId" @change="listCategory">
            <el-option
              v-for="orientation in orientationList"
              :key="orientation.orientationId"
              :label="orientation.name"
              :value="orientation.orientationId">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-select placeholder="请选择分类类别" v-model="article.categoryId" @change="listTag">
            <el-option
              v-for="category in categoryList"
              :key="category.categoryId"
              :label="category.name"
              :value="category.categoryId">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-select
            v-model="tagListTemp"
            multiple
            allow-create
            filterable
            default-first-option
            placeholder="请选择文章标签" @change="adornTagList">
            <el-option
              v-for="item in tagList"
              :key="item.tagId"
              :label="item.tagName"
              :value="item.tagId">
            </el-option>
          </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="是否推荐">
        <el-radio-group v-model="article.isRecommend">
          <el-radio :label="true" >是</el-radio>
          <el-radio :label="false" >否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="展示类型">
        <el-select v-model="article.type" placeholder="请选择展示类型">
          <el-option
            v-for="item in articleTypeList"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上传封面">
        <el-col :span="12">
          <el-upload
            drag
            :action="url"
            list-type="picture"
            :multiple="false"
            :before-upload="beforeUploadHandle"
            :file-list="file"
            :on-remove="handleRemove"
            :on-success="successHandle">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只支持jpg、png、gif格式的图片！</div>
          </el-upload>
        </el-col>
      </el-form-item>
      <el-form-item label="博文描述">
        <el-col :span="12">
          <el-input type="textarea" v-model="article.description" placeholder="博文描述" clearable></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="博文内容">
        <mavon-editor v-model="article.content"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveArticle()">保存</el-button>
        <el-button >重置</el-button>
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
        isRecommend: false,
        tagList: []
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
      url: '',
      file: [],
      rules: {
        title: {required: true, message: '请输入博文标题', trigger: 'change'}
      },
      orientationList: [],
      categoryList: [],
      tagList: [],
      tagListTemp: []
    }
  },
  created () {
    this.init()
  },
  methods: {
    init () {
      this.url = this.$http.adornUrl(`/admin/oss/resource/upload?token=${this.$cookie.get('token')}`)
      let articleId = this.$route.params.articleId
      this.listOrientation()
      if (articleId) {
        this.$http({
          url: this.$http.adornUrl('/article/' + articleId),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.article = data.article
            this.file = [{url: data.article.cover}]
            this.listCategory(this.article.orientationId)
            this.listTag(this.article.categoryId)
            this.tagListTemp = this.article.tagList.map(tag => {
              return tag.tagId
            })
          }
        })
      }
    },
    // 获取博文分类方向
    listOrientation () {
      this.$http({
        url: this.$http.adornUrl('/article/classify/orientations'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.orientationList = data.orientationList
        }
      })
    },
    // 获取博文分类
    listCategory (orientationId) {
      this.$http({
        url: this.$http.adornUrl('/article/classify/categories'),
        method: 'get',
        params: this.$http.adornParams({
          orientationId: orientationId
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryList = data.categoryList
        }
      })
    },
    listTag (categoryId) {
      this.$http({
        url: this.$http.adornUrl('/article/classify/tags'),
        method: 'get',
        params: this.$http.adornParams({
          categoryId: categoryId
        })
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.tagList = data.tagList
        }
      })
    },
    // 过滤标签
    adornTagList (selectValueList) {
      let tagList = []
      selectValueList.forEach(value => {
        let isInput = true
        this.tagList.forEach(tag => {
          if (tag.tagId === value || value.tagId) {
            isInput = false
            tagList.push({'tagId': tag.tagId, 'tagName': tag.tagName})
          }
        })
        if (isInput) {
          tagList.push({'tagName': value})
        }
      })
      this.article.tagList = tagList
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
        this.article.cover = response.resource.url
        this.file = [response.resource]
        this.$message.success('上传成功！')
      }
    },
    // 移除上传文件
    handleRemove (file, fileList) {
      this.file = []
      this.article.cover = ''
    },
    // 保存文章
    saveArticle () {
      this.$refs['articleForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/admin/article/${!this.article.articleId ? 'save' : 'update'}`),
            method: !this.article.articleId ? 'post' : 'put',
            data: this.$http.adornData(this.article)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message.success('添加博文成功')
              this.$router.push('/article-list')
            } else {
              this.$message.error(data.msg)
            }
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>
