<template>
  <div class="album-carousel">
    <p class="top-bar">
      <iv-icon type="android-expand"></iv-icon>
      <iv-icon type="android-close"></iv-icon>
      <iv-icon type="ios-download-outline"></iv-icon>
    </p>
    <span id="prevTop" class="btn prev" @click="prev" @mouseover="onmouseover" @mouseout="onmouseout"></span>
    <span id="nextTop" class="btn next" @click="next" @mouseover="onmouseover" @mouseout="onmouseout"></span>
    <div class="preview-box">
      <div id="picBox" class="picBox">
        <ul ref="picBox">
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_1.jpg">
            </a>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_2.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_3.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_4.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_5.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_2.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_3.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_4.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_5.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_2.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_3.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_4.jpg">
            </a>
          </li>
          <li>
            <a href="#" target="_blank">
              <img src="../../../assets/photowall/photowall_pic_5.jpg">
            </a>
          </li>
        </ul>
      </div>
    </div>
    <div class="thumb-box">
      <div id="listBox" class="listBox">
        <ul ref="listBox">
          <li class="on"><img src="../../../assets/photowall/photowall_pic_1.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_2.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_3.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_4.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_5.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_2.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_3.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_4.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_5.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_2.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_3.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_4.jpg"></li>
          <li><img src="../../../assets/photowall/photowall_pic_5.jpg"></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      index: 0,
      timer: null,
      picBox: null,
      listBox: null
    }
  },
  methods: {
    prev () {
      console.log('prev')
      this.index--
      this.index = this.index === -1 ? this.listBox.lis.length - 1 : this.index
      this.Change()
    },
    next () {
      console.log('next')
      this.index++
      this.index = this.index === this.listBox.lis.length ? 0 : this.index
      this.Change()
    },
    onmouseover () {
      console.log('onmouseover')
      clearInterval(this.timer)
    },
    onmouseout () {
      console.log('onmouseout')
      this.timer = setInterval(this.autoPlay, 4000)
    },
    getStyle (obj, attr) {
      if (obj.currentStyle) {
        return obj.currentStyle[attr]
      } else {
        return getComputedStyle(obj, false)[attr]
      }
    },
    Animate (el, options) {
      if (el.timer) {
        clearInterval(el.timer)
      }
      let that = this
      el.timer = setInterval(function () {
        for (let attr in options) {
          // 获取原属性值
          let iCur = parseInt(that.getStyle(el, attr))
          if (!iCur) iCur = 0
          // 使用现在的属性值和原属性质计算速度
          let iSpeed = (options[attr] - iCur) / 5
          iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed)
          el.style[attr] = iCur + iSpeed + 'px'
          if (iCur === options[attr]) {
            clearInterval(el.timer)
          }
        }
      }, 30)
    },
    Change () {
      let rowHalfCount = this.listBox.lis.length / 2
      let revertCount = Math.ceil(rowHalfCount / 2)

      this.Animate(this.picBox.el, {
        left: -this.index * this.picBox.liWidth
      })

      if (this.index < revertCount) {
        this.Animate(this.listBox.el, {
          left: 0
        })
      } else if (this.index + revertCount <= this.listBox.lis.length) {
        this.Animate(this.listBox.el, {
          left: -(this.index - revertCount + 1) * this.listBox.liWidth
        })
      } else {
        this.Animate(this.listBox.el, {
          left: -(this.listBox.lis.length - rowHalfCount) * this.listBox.liWidth
        })
      }

      for (let i = 0; i < this.listBox.lis.length; i++) {
        this.listBox.lis[i].className = ''
        if (i === this.index) {
          this.listBox.lis[i].className = 'on'
        }
      }
    },
    autoPlay () {
      this.index++
      this.index = this.index === this.listBox.lis.length ? 0 : this.index
      this.Change()
    },
    initPreview () {
      // 初始化一些值
      this.picBox = {}
      this.picBox.el = this.$refs.picBox
      this.picBox.lis = this.picBox.el.getElementsByTagName('li')
      this.picBox.liWidth = this.picBox.lis[0].offsetWidth

      this.listBox = {}
      this.listBox.el = this.$refs.listBox
      this.listBox.lis = this.listBox.el.getElementsByTagName('li')
      this.listBox.liWidth = this.listBox.lis[0].offsetWidth

      // 宽度
      this.picBox.el.style.width = this.picBox.lis.length * this.picBox.liWidth + 'px'
      this.listBox.el.style.width = this.listBox.lis.length * this.listBox.liWidth + 'px'

      // 重置大图li的默认宽度，因为CSS中给的100%，而此时ul宽度已改变
      var that = this
      Object.keys(this.picBox.lis).forEach(function (key) {
        that.picBox.lis[key].style.width = that.picBox.liWidth + 'px'
      })

      // 给小图添加点击事件
      for (let i = 0; i < this.listBox.lis.length; i++) {
        this.listBox.lis[i].index = i
        this.listBox.lis[i].onclick = function () {
          that.index = this.index
          that.Change()
        }
      }

      // 开启定时器
      this.timer = setInterval(this.autoPlay, 4000)
    }
  },
  mounted () {
    this.initPreview()
  }
}
</script>`

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .album-carousel
    position: relative
    width: 100%
    height 100%
    margin: 0 auto
    #prevTop
      position: absolute;
      height: 48px;
      width: 24px;
      left: 11px;
      top: 40%;
      background: url(../../../assets/photowall/prevBtn.png) no-repeat;
      cursor: pointer;
      z-index: 99;
    #nextTop
      position: absolute;
      height: 48px;
      width: 24px;
      top: 40%;
      right: 11px;
      background: url(../../../assets/photowall/nextBtn.png) no-repeat;
      cursor: pointer;
      z-index: 99;
    .top-bar
      height 60px
      line-height 60px
      font-size 30px
      width 100%
      padding 0 20px
      color $color-gradually-gray-81
      border-bottom 1px solid $color-gradually-gray-21
      i
        display inline-block
        line-height 60px
        cursor pointer
        + i
          margin 0 20px
          float right
    .preview-box
      width: 100%;
      height: 100%
      padding 60px 0 150px 0
      margin-top -60px
      @media only screen and (max-width: 768px)
        padding-bottom 90px
      @media screen and (min-width: 768px)
        padding-bottom 130px
      @media screen and (min-width: 992px)
        padding-bottom 150px
      @media screen and (min-width: 1200px)
        padding-bottom 150px
      .picBox
        position: relative;
        width: 100%;
        height: 100%
        overflow: hidden;
        ul
          position absolute
          left 0
          top 0
          right 0
          bottom 0
          width 100%;
          height: 100%;
          li
            position: relative;
            float: left;
            width: 100%;
            height: 100%
            a
              display: flex;
              width: 100%;
              height: 100%
              justify-content: center
              box-align: center
              align-items: center
              img
                max-height: 100%;
                max-width: 100%;
    .thumb-box
      width: 100%;
      height: 120px;
      margin-top: -150px;
      position: relative;
      @media only screen and (max-width: 768px)
        margin-top: -90px;
      @media screen and (min-width: 768px)
        margin-top: -130px;
      @media screen and (min-width: 992px)
        margin-top: -150px;
      @media screen and (min-width: 1200px)
        margin-top: -150px;
      .listBox
        position: relative;
        width: 100%
        height: 100%;
        padding 5px
        overflow: hidden;
        ul
          position absolute
          left 0
          top 0
          right 0
          bottom 0
          width 100%
          height: 100%;
          li
            float left
            width: 140px;
            height: 100%;
            cursor: pointer;
            padding 2px
            img
              border 1px solid #000;
              width: 100%;
              height: 100%;
            &.on
              img
                border 1px solid #fff;
                width: 100%;
                height: 100%;
              i
                display: block;
</style>
