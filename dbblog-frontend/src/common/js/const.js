// 文字省略模式
export const LineBreakMode = {
  WrappingTruncatingTail: 1, // 显示头部文字内容，其他直接截断。
  WrappingTruncatingHead: 2, // 显示尾部文字内容，其他直接截断。
  EllipsisTruncatingTail: 3, // 结尾部分的内容以……方式省略，显示头的文字内容。
  EllipsisTruncatingMiddle: 4, // 中间的内容以……方式省略，显示头尾的文字内容。
  EllipsisTruncatingHead: 5 // 前面部分文字以……方式省略，显示尾部文字内容。
}

// 响应式断点
export const ResponsivePoint = {
  Sm: 768,
  Md: 992,
  Lg: 1200
}

// 默认请求数据时的limit size
export const DefaultLimitSize = 5

// section title默认的menus
export const DefaultFilterList = [
  {
    name: '最新',
    type: 'latest',
    active: true
  },
  {
    name: '点赞最多',
    type: 'favorite',
    active: false
  },
  {
    name: '评论最多',
    type: 'commentMost',
    active: false
  },
  {
    name: '推荐',
    type: 'recommend',
    active: false
  }
]
