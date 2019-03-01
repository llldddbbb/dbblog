  <template>
  <div>
    <el-form :model="book" label-width="80px" :rules="rules" ref="bookForm">
      <el-form-item label="图书标题" prop="title">
        <el-col :span="18">
          <el-input placeholder="图书标题" v-model="book.title"  clearable></el-input>
        </el-col>
      </el-form-item>
      <el-row>
        <el-col :span="6">
          <el-form-item label="图书分类">
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
          <el-form-item label="图书标签">
            <el-select
              style="width: 100%"
              v-model="tagListSelect"
              multiple
              allow-create
              filterable
              default-first-option
              placeholder="请选择图书标签" @change="filterTagList">
              <el-option
                v-for="item in tagList"
                  :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="出版日期">
            <el-date-picker value-format="yyyy-MM-dd" placeholder="出版日期" v-model="book.publishDate"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="图书作者">
            <el-input placeholder="图书作者" v-model="book.author" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="出版社">
            <el-input placeholder="出版社" v-model="book.publisher" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="评分">
            <el-rate v-model="book.grade" allow-half style="line-height: 2" ></el-rate>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="页数">
            <el-input-number v-model="book.pageNum" :min="1"  label="页数"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="是否推荐">
        <el-radio-group v-model="book.recommend">
          <el-radio :label="true" >是</el-radio>
          <el-radio :label="false" >否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="阅读进度">
            <el-slider v-model="book.progress" :step="10"></el-slider>
          </el-form-item>
        </el-col>
      </el-row>
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
      <el-form-item label="简介">
        <quill-editor v-model="book.description"></quill-editor>
      </el-form-item>
      <el-form-item label="目录">
        <quill-editor v-model="book.catalogue"></quill-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveBook()">保存</el-button>
        <el-button >重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// require styles 引入样式
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'
import { treeDataTranslate } from '@/utils'
export default {
  data () {
    return {
      book: {
        recommend: false,
        tagList: [],
        type: 0,
        status: 0,
        pageNum: 1
      },
      coverTypeList: this.getSysParamArr('BOOK_COVER_TYPE'),
      url: '',
      file: [],
      rules: {
        title: {required: true, message: '请输入图书标题', trigger: 'change'}
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
  components: {
    quillEditor
  },
  created () {
    this.init()
  },
  methods: {
    init () {
      // 获取图书分类
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
          params: this.$http.adornParams({type: 1})
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
            url: this.$http.adornUrl('/admin/book/info/' + id),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.book = data.book
              this.file = [{url: data.book.cover}]
              // 转换tagList
              this.tagListSelect = this.book.tagList.map(tag => {
                return tag.id
              })
              // 转换categoryId
              this.categoryOptionsSelect = this.book.categoryId.split(',').map((data) => { return +data })
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
            tagList.push({id: tag.id, name: tag.name, type: 1})
          }
        }
        if (isInput) {
          tagList.push({name: value, type: 1})
        }
      })
      this.book.tagList = tagList
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
        this.book.cover = response.resource.url
        this.file = [response.resource]
        this.$message.success('上传成功！')
      }
    },
    // 移除上传文件
    handleRemove (file, fileList) {
      this.file = []
      this.book.cover = ''
    },
    // 保存图书
    saveBook () {
      this.$refs['bookForm'].validate((valid) => {
        if (valid) {
          // 转化categoryId
          this.book.categoryId = this.categoryOptionsSelect.join(',')
          this.$http({
            url: this.$http.adornUrl(`/admin/book/${!this.book.id ? 'save' : 'update'}`),
            method: !this.book.id ? 'post' : 'put',
            data: this.$http.adornData(this.book)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message.success('保存图书成功')
              // 关闭当前标签
              this.$emit('closeCurrentTabs')
              // 跳转到list
              this.$router.push('/book-book')
            } else {
              this.$message.error(data.msg)
            }
          })
        } else {
          return false
        }
      })
    },
    // 图书内容图片上传
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
    }
  }
}
</script>
