import { scrollTop } from './SmoothScroll'

var TocScrollSpy = function (articleId, tocId, options) {
  this.articleElement = document.getElementById(articleId)
  this.tocElement = document.getElementById(tocId)
  if (!this.articleElement || !this.tocElement || !this.articleElement.children || !this.tocElement.children) return

  this.options = options || {}

  // 监听等级
  this.spayLevel = parseInt(options.level) || 5
  // 文章scroll时顶部margin
  this.articleMarginTop = parseInt(options.articleMarginTop) || 0
  // 初始化的
  this.currentTitleElement = null
  this._init()
}

TocScrollSpy.prototype._init = function () {
  this._collectTitleElementScrollTops()
  this._bindSpy()
  this._bindTitleAndToc()
  // 先执行一次，获取初始的位置
  this._handleScroll.bind(this)()
}

TocScrollSpy.prototype._collectTitleElementScrollTops = function () {
  this._elTitlesNames = []
  this.elTitleElements = []
  this.elTitleElementScrollTops = []

  // 先筛选有哪些h标签名
  for (let i = 1; i < 7; i++) {
    if (this.articleElement.getElementsByTagName('h' + i).length) {
      this._elTitlesNames.push('h' + i)
    }
  }
  if (this._elTitlesNames.length === 0) return

  // 剔除超过限定级别的h标签
  this._elTitlesNames.length = this._elTitlesNames.length > this.spayLevel ? this.spayLevel : this._elTitlesNames.length

  // 获取所有需要的h标签
  let articleElementChildren = this.articleElement.children
  for (let i = 0; i < articleElementChildren.length; i++) {
    this._elChildName = articleElementChildren[i].tagName.toLowerCase()
    if (this._elTitlesNames.toString().match(this._elChildName)) {
      this.elTitleElements.push(articleElementChildren[i])
      this.elTitleElementScrollTops.push(this._getElementTop(articleElementChildren[i]))
    }
  }
}

TocScrollSpy.prototype._getElementTop = function (element) {
  let actualTop = element.offsetTop
  let current = element.offsetParent
  while (current !== null) {
    actualTop += current.offsetTop
    current = current.offsetParent
  }
  return actualTop
}

TocScrollSpy.prototype._getPageScroll = function () {
  let xScroll, yScroll
  if (self.pageYOffset) {
    yScroll = self.pageYOffset
    xScroll = self.pageXOffset
  } else if (document.documentElement && document.documentElement.scrollTop) {
    // Explorer 6 Strict
    yScroll = document.documentElement.scrollTop
    xScroll = document.documentElement.scrollLeft
  } else if (document.body) {
    // all other explorers
    yScroll = document.body.scrollTop
    xScroll = document.body.scrollLeft
  }
  return {left: xScroll, top: yScroll}
}

TocScrollSpy.prototype._bindSpy = function () {
  window.addEventListener('scroll', this._handleScroll.bind(this), false)
}

TocScrollSpy.prototype._handleScroll = function () {
  // 获取滚动距离
  let windowScrollTop = this._getPageScroll().top
  // 筛选哪个H标签离得近
  let fitDistance = windowScrollTop + this.articleMarginTop
  let distances = []
  this.elTitleElementScrollTops.map(function (scrollTop, index) {
    // 对数组各个数值求差值
    let distance = scrollTop - fitDistance
    distances.push(Math.abs(distance))
  })
  let index = distances.indexOf(Math.min.apply(null, distances))
  let calcElement = this._getCurrentTitleElement(index)
  if (calcElement !== this.currentTitleElement) {
    this.currentTitleElement = calcElement
    this._updateTocStatus(this.currentTitleElement)
  }
}

TocScrollSpy.prototype._getCurrentTitleElement = function (index) {
  if (!this.elTitleElements || index > this.elTitleElements.length - 1) return null
  return this.elTitleElements[index]
}

TocScrollSpy.prototype._updateTocStatus = function (currentTitleElement) {
  let activeId = currentTitleElement.id
  let activeTocEl = this.tocElement.querySelector('.' + activeId)
  let activeANode = this.tocElement.querySelector('.active')
  if (activeANode !== null) {
    activeANode.classList.remove('active')
  }
  activeTocEl.classList.add('active')

  // 先计算list相对于页面的顶部距离
  let listNode = this.tocElement.querySelector('.list')
  let listScrollTop = listNode.getBoundingClientRect().top + document.documentElement.scrollTop
  // 再计算active相对于页面的顶部距离
  let activeAnode = listNode.querySelector('.active')
  if (activeAnode === null) {
    return
  }
  let activeANodeScrollTop = activeAnode.getBoundingClientRect().top + document.documentElement.scrollTop
  let activeIndicator = listNode.querySelector('.active-indicator')
  activeIndicator.style.top = activeANodeScrollTop - listScrollTop + 'px'
  activeIndicator.style.height = activeAnode.clientHeight + 'px'
}

TocScrollSpy.prototype._bindTitleAndToc = function () {
  let that = this
  this.elTitleElements.map(function (element) {
    let activeId = element.id
    let activeTocEl = that.tocElement.querySelector('.' + activeId)
    activeTocEl.addEventListener('click', function () {
      const pageScroll = that._getPageScroll().top
      const toScroll = that._getElementTop(element) - that.articleMarginTop
      const duration = Math.min(Math.abs(toScroll - pageScroll) / window.innerHeight * 500, 1000)
      scrollTop(window, pageScroll, toScroll, duration)
    })
  })
}

export default TocScrollSpy
