  <template>
  <div>
    <el-form :model="bookNote" label-width="80px" :rules="rules" ref="bookNoteForm">
      <el-form-item label="笔记标题" prop="title">
        <el-col :span="12">
          <el-input placeholder="笔记标题" v-model="bookNote.title"  clearable></el-input>
        </el-col>
      </el-form-item>
      <el-row>
        <el-col :span="6">
          <el-form-item label="所属书本">
            <el-select
              style="width: 100%"
              v-model="bookNote.bookId"
              filterable
              placeholder="请选择所属书本" >
              <el-option
                v-for="item in bookList"
                :key="item.id"
                :label="item.title"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="所属章节">
            <el-input placeholder="所属章节" v-model="bookNote.chapter"  clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="笔记分类">
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
          <el-form-item label="笔记标签">
            <el-select
              style="width: 100%"
              v-model="tagListSelect"
              multiple
              allow-create
              filterable
              default-first-option
              placeholder="请选择笔记标签" @change="filterTagList">
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
      <el-form-item label="笔记作者">
        <el-row>
          <el-col :span="4">
            <el-input placeholder="笔记作者" v-model="bookNote.author" clearable></el-input>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="是否推荐">
        <el-radio-group v-model="bookNote.recommend">
          <el-radio :label="true" >是</el-radio>
          <el-radio :label="false" >否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="展示类型">
        <el-radio-group v-model="bookNote.coverType">
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
      <el-form-item label="笔记描述">
        <el-col :span="12">
          <el-input type="textarea" v-model="bookNote.description" placeholder="笔记描述" clearable></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="笔记内容">
        <mavon-editor ref=md v-model="bookNote.content" @imgAdd="imgAdd" @change="mavonChangeHandle"></mavon-editor>
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
      bookNote: {
        recommend: false,
        tagList: [],
        type: 2,
        coverType: 2 // 默认无图片
      },
      coverTypeList: this.getSysParamArr('ARTICLE_COVER_TYPE'),
      url: '',
      file: [],
      rules: {
        title: {required: true, message: '请输入笔记标题', trigger: 'change'}
      },
      tagList: [],
      bookList: [],
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
      // 获取笔记分类
      this.$http({
        url: this.$http.adornUrl('/admin/operation/category/list'),
        method: 'get',
        params: this.$http.adornParams({type: 1})
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryOptions = treeDataTranslate(data.categoryList)
        }
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/operation/tag/select'),
          method: 'get',
          params: this.$http.adornParams({type: 2})
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.tagList = data.tagList
          }
        })
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/admin/book/select'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.bookList = data.bookList
          }
        })
      }).then(() => {
        this.url = this.$http.adornUrl(`/admin/oss/resource/upload?token=${this.$cookie.get('token')}`)
        let id = this.$route.params.id
        if (id) {
          this.$http({
            url: this.$http.adornUrl('/admin/book/note/info/' + id),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.bookNote = data.bookNote
              this.file = [{url: data.bookNote.cover}]
              // 转换tagList
              this.tagListSelect = this.bookNote.tagList.map(tag => {
                return tag.id
              })
              // 转换categoryId
              this.categoryOptionsSelect = this.bookNote.categoryId.split(',').map((data) => { return +data })
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
            tagList.push({id: tag.id, name: tag.name, type: 2})
          }
        }
        if (isInput) {
          tagList.push({name: value, type: 2})
        }
      })
      this.bookNote.tagList = tagList
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
        this.bookNote.cover = response.resource.url
        this.file = [response.resource]
        this.$message.success('上传成功！')
      }
    },
    // 移除上传文件
    handleRemove (file, fileList) {
      this.file = []
      this.bookNote.cover = ''
    },
    // 保存笔记
    saveArticle () {
      this.$refs['bookNoteForm'].validate((valid) => {
        if (valid) {
          // 转化categoryId
          this.bookNote.categoryId = this.categoryOptionsSelect.join(',')
          this.$http({
            url: this.$http.adornUrl(`/admin/book/note/${!this.bookNote.id ? 'save' : 'update'}`),
            method: !this.bookNote.id ? 'post' : 'put',
            data: this.$http.adornData(this.bookNote)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message.success('保存笔记成功')
              // 关闭当前标签
              this.$emit('closeCurrentTabs')
              // 跳转到list
              this.$router.push('/book-note')
            } else {
              this.$message.error(data.msg)
            }
          })
        } else {
          return false
        }
      })
    },
    // 笔记内容图片上传
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
    mavonChangeHandle (value, render) {
      this.bookNote.contentFormat = marked(value, {breaks: true})
    }
  }
}
</script>
