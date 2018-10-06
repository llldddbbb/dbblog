<template>
  <div class="classify-bar">
    <p class="level level-one">
      <span class="title">方向：</span>
      <span class="class">
        <a href="" class="active">全部</a>
        <a href="" class="name">前端开发</a>
        <a href="" class="name">后端开发</a>
        <a href="" class="name">移动开发</a>
        <a href="" class="name">数据库</a>
        <a href="" class="name">云计算&大数据</a>
        <a href="" class="name">UI设计</a>
      </span>
    </p>
    <p class="level level-two">
      <span class="title">分类：</span>
      <span class="class">
        <a href="" class="active">全部</a>
        <a href="" class="name">HTML/CSS</a>
        <a href="" class="name">JavaScript</a>
        <a href="" class="name">Html5</a>
        <a href="" class="name">CSS3</a>
        <a href="" class="name">jQuery</a>
        <a href="" class="name">Node.js</a>
        <a href="" class="name">Bootstrap</a>
        <a href="" class="name">AngularJS</a>
        <a href="" class="name">React.JS</a>
        <a href="" class="name">Vue.js</a>
        <a href="" class="name">Sass/Less</a>
        <a href="" class="name">WebApp</a>
        <a href="" class="name">前端工具</a>
        <a href="" class="name">PHP</a>
        <a href="" class="name">Java</a>
        <a href="" class="name">SpringBoot</a>
        <a href="" class="name">Python</a>
        <a href="" class="name">C</a>
        <a href="" class="name">C++</a>
        <a href="" class="name">Go</a>
        <a href="" class="name">C#</a>
        <a href="" class="name">Ruby</a>
        <a href="" class="name">Android</a>
        <a href="" class="name">iOS</a>
        <a href="" class="name">Unity3D</a>
      </span>
    </p>
    <p class="level level-three">
      <span class="title">类型：</span>
      <span class="class">
        <a href="" class="active">全部</a>
        <a href="" class="name">前端开发</a>
        <a href="" class="name">后端开发</a>
        <a href="" class="name">移动开发</a>
        <a href="" class="name">数据库</a>
        <a href="" class="name">云计算&大数据</a>
        <a href="" class="name">UI设计</a>
      </span>
    </p>
  </div>
</template>

<script type="text/ecmascript-6">

</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl";
  @import "../../../common/stylus/theme.styl";

  .classify-bar
    .level
      display flex
      padding 15px 0
      font-size 15px
      line-height 22px
      border-bottom 1px solid $color-border
      .title
        display inline-block
        flex 0 0 60px
        width 60px
        font-size 17px
        line-height 22px
        padding 4px 0
        font-weight 700
      .class
        a
          display inline-block
          margin-right 4px
          padding 3px 8px
          margin-bottom 2px
          font-weight 100
          border-radius $border-radius
          color $color-gradually-gray-31
          &.name
            &:hover
              color $color-main-primary
          &.active
            color $color-gradually-gray-101
            background $color-gradually-gray-31
</style>
