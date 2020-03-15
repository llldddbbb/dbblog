  <template>
  <div>
    <el-form :model="article" label-width="80px" :rules="rules" ref="articleForm">
      <el-form-item label="博文标题" prop="title">
        <el-col :span="12">
          <el-input placeholder="博文标题" v-model="article.title"  clearable></el-input>
        </el-col>
      </el-form-item>
      <el-row>
        <el-col :span="6">
          <el-form-item label="博文分类">
            <el-cascader
              style="width: 100%;"
              clearable
              change-on-select
              :options="categoryOptions"
              v-model="categoryOptionsSelect"
              :props="categoryListTreeProps">
            </el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="博文标签">
            <el-select
              style="width: 100%"
              v-model="tagListSelect"
              multiple
              allow-create
              filterable
              default-first-option
              placeholder="请选择文章标签" @change="filterTagList">
              <el-option
                v-for="item in tagList"
                  :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="博文作者">
        <el-row>
          <el-col :span="4">
            <el-input placeholder="博文作者" v-model="article.author" clearable></el-input>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="是否推荐">
        <el-radio-group v-model="article.recommend">
          <el-radio :label="true" >是</el-radio>
          <el-radio :label="false" >否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="展示类型">
        <el-radio-group v-model="article.coverType">
          <el-radio v-for="type in coverTypeList" :key="type.parKey" :label="type.parKey" >{{type.parValue}}</el-radio>
        </el-radio-group>
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
        <mavon-editor ref=md v-model="article.content" @imgAdd="imgAdd" @change="mavonChangeHandle"></mavon-editor>
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
import { treeDataTranslate } from '@/utils'
import marked from 'marked'
export default {
  components: {
    'mavon-editor': MavonEditor.mavonEditor
  },
  data () {
    return {
      article: {
        recommend: false,
        tagList: [],
        type: 0,
        coverType: 2 // 默认无图片
      },
      coverTypeList: this.getSysParamArr('ARTICLE_COVER_TYPE'),
      url: '',
      file: [],
      rules: {
        title: {required: true, message: '请输入博文标题', trigger: 'change'}
      },
      tagList: [],
      tagListSelect: [],
      tagListNew: [],
      categoryOptions: [],
      categoryOptionsSelect: [],
      categoryListTreeProps: {
        label: 'name',
        children: 'children',
        value: 'id'
      }
    }
  },
  created () {
    this.init()
  },
  methods: {
    init () {
      // 获取博文分类
      this.$http({
        url: this.$http.adornUrl('/admin/operation/category/list'),
        method: 'get',
        params: this.$http.adornParams({type: 0})
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryOptions = treeDataTranslate(data.categoryList)
        }
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/operation/tag/select'),
          method: 'get',
          params: this.$http.adornParams({type: 0})
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.tagList = data.tagList
          }
        })
      }).then(() => {
        this.url = this.$http.adornUrl(`/admin/oss/resource/upload?token=${this.$cookie.get('token')}`)
        let id = this.$route.params.id
        if (id) {
          this.$http({
            url: this.$http.adornUrl('/admin/article/info/' + id),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.article = data.article
              this.file = [{url: data.article.cover}]
              // 转换tagList
              this.tagListSelect = this.article.tagList.map(tag => {
                return tag.id
              })
              // 转换categoryId
              this.categoryOptionsSelect = this.article.categoryId.split(',').map((data) => { return +data })
            }
          })
        }
      })
    },
    // 过滤标签
    filterTagList (selectValueList) {
      let tagList = []
      selectValueList.forEach(value => {
        let isInput = true
        for (let i = 0; i < this.tagList.length; i++) {
          let tag = this.tagList[i]
          if (tag.id === value || value.id) {
            isInput = false
            tagList.push({id: tag.id, name: tag.name, type: 0})
          }
        }
        if (isInput) {
          tagList.push({name: value, type: 0})
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
          // 转化categoryId
          this.article.categoryId = this.categoryOptionsSelect.join(',')
          this.$http({
            url: this.$http.adornUrl(`/admin/article/${!this.article.id ? 'save' : 'update'}`),
            method: !this.article.id ? 'post' : 'put',
            data: this.$http.adornData(this.article)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message.success('保存博文成功')
              // 关闭当前标签
              this.$emit('closeCurrentTabs')
              // 跳转到list
              this.$router.push('/article-article')
            } else {
              this.$message.error(data.msg)
            }
          })
        } else {
          return false
        }
      })
    },
    // 文章内容图片上传
    imgAdd (pos, $file) {
      // 第一步.将图片上传到服务器.
      let formData = new FormData()
      formData.append('file', $file)
      this.$http({
        url: this.url,
        method: 'post',
        data: formData,
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then(({data}) => {
        this.$refs.md.$img2Url(pos, data.resource.url)
      })
    },
    mavonChangeHandle (context, render) {
      this.article.contentFormat = marked(context, {breaks: true})
    }
  }
}
</script>
