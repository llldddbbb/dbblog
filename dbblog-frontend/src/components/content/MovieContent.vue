<template>
  <div class="movie-content">
    <div class="header-wrapper">
      <img src="../../assets/background.jpg" alt="">
      <movie-page-header></movie-page-header>
    </div>
    <div class="content-wrapper">
      <iv-row>
        <iv-col :xs="24" :sm="24" :md="24" :lg="17">
          <div class="layout-left">
            <movie-page-content>
              <article id="article-main-page" class="typo container" slot="content" ref="article">
                <h1>Action</h1>
                <p>Action 类似于 mutation，不同在于：</p>
                <ul>
                  <li>
                    Action 提交的是 mutation，而不是直接变更状态。
                  </li>
                  <li>
                    Action 可以包含任意异步操作。
                  </li>
                </ul>
                <p>让我们来注册一个简单的 action：</p>
                <pre><code class='language-js' lang='js'>
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  },
  actions: {
    increment (context) {
      context.commit(&#39;increment&#39;)
    }
  }
})
</code></pre>
                <p>Action 函数接受一个与 store 实例具有相同方法和属性的 context 对象，因此你可以调用 <code>context.commit</code> 提交一个 mutation，或者通过
                  <code>context.state</code>
                  和 <code>context.getters</code> 来获取 state 和 getters。当我们在之后介绍到 <a href='modules.md'>Modules</a> 时，你就知道
                  context
                  对象为什么不是 store 实例本身了。</p>
                <p>实践中，我们会经常用到 ES2015 的 <a href='https://github.com/lukehoban/es6features#destructuring'>参数解构</a>
                  来简化代码（特别是我们需要调用 <code>commit</code> 很多次的时候）：</p>
                <pre><code class='language-js' lang='js'>
actions: {
  increment ({ commit }) {
    commit(&#39;increment&#39;)
  }
}
</code></pre>
                <h3>分发 Action</h3>
                <p>Action 通过 <code>store.dispatch</code> 方法触发：</p>
                <pre><code class='language-js' lang='js'>
store.dispatch(&#39;increment&#39;)
</code></pre>
                <p>乍一眼看上去感觉多此一举，我们直接分发 mutation 岂不更方便？实际上并非如此，还记得 <strong>mutation 必须同步执行</strong>这个限制么？Action 就不受约束！我们可以在
                  action 内部执行<strong>异步</strong>操作：</p>
                <pre><code class='language-js' lang='js'>
actions: {
  incrementAsync ({ commit }) {
    setTimeout(() =&gt; {
      commit(&#39;increment&#39;)
    }, 1000)
  }
}
</code></pre>
                <p>Actions 支持同样的载荷方式和对象方式进行分发：</p>
                <pre><code class='language-js' lang='js'>
// 以载荷形式分发
store.dispatch(&#39;incrementAsync&#39;, {
  amount: 10
})

// 以对象形式分发
store.dispatch({
  type: &#39;incrementAsync&#39;,
  amount: 10
})
</code></pre>
                <p>来看一个更加实际的购物车示例，涉及到<strong>调用异步 API</strong> 和<strong>分发多重 mutation</strong>：</p>
                <pre><code class='language-js' lang='js'>
actions: {
  checkout ({ commit, state }, products) {
    // 把当前购物车的物品备份起来
    const savedCartItems = [...state.cart.added]
    // 发出结账请求，然后乐观地清空购物车
    commit(types.CHECKOUT_REQUEST)
    // 购物 API 接受一个成功回调和一个失败回调
    shop.buyProducts(
      products,
      // 成功操作
      () =&gt; commit(types.CHECKOUT_SUCCESS),
      // 失败操作
      () =&gt; commit(types.CHECKOUT_FAILURE, savedCartItems)
    )
  }
}
</code></pre>
                <p>注意我们正在进行一系列的异步操作，并且通过提交 mutation 来记录 action 产生的副作用（即状态变更）。</p>
                <h3>在组件中分发 Action</h3>
                <p>你在组件中使用 <code>this.$store.dispatch(&#39;xxx&#39;)</code> 分发 action，或者使用 <code>mapActions</code> 辅助函数将组件的
                  methods 映射为 <code>store.dispatch</code> 调用（需要先在根节点注入 <code>store</code>）：</p>
                <pre><code class='language-js' lang='js'>
import { mapActions } from &#39;vuex&#39;

export default {
  // ...
  methods: {
    ...mapActions([
      &#39;increment&#39;, // 将 `this.increment()` 映射为 `this.$store.dispatch(&#39;increment&#39;)`

      // `mapActions` 也支持载荷：
      &#39;incrementBy&#39; // 将 `this.incrementBy(amount)` 映射为 `this.$store.dispatch(&#39;incrementBy&#39;, amount)`
    ]),
    ...mapActions({
      add: &#39;increment&#39; // 将 `this.add()` 映射为 `this.$store.dispatch(&#39;increment&#39;)`
    })
  }
}
</code></pre>
                <h3>组合 Action</h3>
                <p>Action 通常是异步的，那么如何知道 action 什么时候结束呢？更重要的是，我们如何才能组合多个 action，以处理更加复杂的异步流程？</p>
                <p>首先，你需要明白 <code>store.dispatch</code> 可以处理被触发的 action 的处理函数返回的 Promise，并且 <code>store.dispatch</code> 仍旧返回
                  Promise：</p>
                <pre><code class='language-js' lang='js'>
actions: {
  actionA ({ commit }) {
    return new Promise((resolve, reject) =&gt; {
      setTimeout(() =&gt; {
        commit(&#39;someMutation&#39;)
        resolve()
      }, 1000)
    })
  }
}
</code></pre>
                <p>现在你可以：</p>
                <pre><code class='language-js' lang='js'>
store.dispatch(&#39;actionA&#39;).then(() =&gt; {
  // ...
})
</code></pre>
                <p>在另外一个 action 中也可以：</p>
                <pre><code class='language-js' lang='js'>
actions: {
  // ...
  actionB ({ dispatch, commit }) {
    return dispatch(&#39;actionA&#39;).then(() =&gt; {
      commit(&#39;someOtherMutation&#39;)
    })
  }
}
</code></pre>
                <h4>现在你可以</h4>
                <p>现在你可以：</p>
                <pre><code class='language-js' lang='js'>
store.dispatch(&#39;actionA&#39;).then(() =&gt; {
  // ...
})
</code></pre>
                <p>在另外一个 action 中也可以：</p>
                <pre><code class='language-js' lang='js'>
actions: {
  // ...
  actionB ({ dispatch, commit }) {
    return dispatch(&#39;actionA&#39;).then(() =&gt; {
      commit(&#39;someOtherMutation&#39;)
    })
  }
}
</code></pre>
                <h5>另外一个</h5>
                <p>Action 通常是异步的，那么如何知道 action 什么时候结束呢？更重要的是，我们如何才能组合多个 action，以处理更加复杂的异步流程？</p>
                <p>首先，你需要明白 <code>store.dispatch</code> 可以处理被触发的 action 的处理函数返回的 Promise，并且 <code>store.dispatch</code> 仍旧返回
                  Promise：</p>
                <h3>后记</h3>
                <p>最后，如果我们利用 <a href='https://tc39.github.io/ecmascript-asyncawait/'>async / await</a> 这个 JavaScript
                  即将到来的新特性，我们可以像这样组合 action：</p>
                <pre><code class='language-js' lang='js'>
// 假设 getData() 和 getOtherData() 返回的是 Promise

actions: {
  async actionA ({ commit }) {
    commit(&#39;gotData&#39;, await getData())
  },
  async actionB ({ dispatch, commit }) {
    await dispatch(&#39;actionA&#39;) // 等待 actionA 完成
    commit(&#39;gotOtherData&#39;, await getOtherData())
  }
}
</code></pre>
                <blockquote><p>一个 <code>store.dispatch</code> 在不同模块中可以触发多个 action 函数。在这种情况下，只有当所有触发函数完成后，返回的 Promise 才会执行。
                </p>
                </blockquote>
              </article>
            </movie-page-content>
          </div>
        </iv-col>
        <iv-col :xs="0" :sm="0" :md="0" :lg="7">
          <div class="layout-right">
            <recommend></recommend>
            <iv-affix :offset-top="60">
              <side-toc style="margin-top: 15px;"></side-toc>
            </iv-affix>
          </div>
        </iv-col>
      </iv-row>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import MoviePageHeader from '@/components/views/Movie/MoviePageHeader'
import MoviePageContent from '@/components/views/Movie/MoviePageContent'
import SideToc from '@/components/views/SideToc'
import Recommend from '@/components/views/Recommend'
// highlight.js引入
import hljs from 'highlight.js'
// 样式文件
import 'highlight.js/styles/zenburn.css'
// TOC滚动监听
import TocScrollSpy from '@/common/js/TocScrollSpy'

var HLJS = hljs

export default {
  components: {
    'movie-page-header': MoviePageHeader,
    'movie-page-content': MoviePageContent,
    'side-toc': SideToc,
    'recommend': Recommend
  },
  mounted: function () {
    this.addCodeLineNumber()
    this.addTocScrollSpy()
  },
  methods: {
    addTocScrollSpy () {
      /* eslint-disable */
      new TocScrollSpy('article-main-page', 'side-toc', {
        'spayLevel': 5,
        'articleMarginTop': 60
      });
    },
    addCodeLineNumber() {
      // 添加行号
      let blocks = this.$refs.article.querySelectorAll('pre code');
      blocks.forEach((block) => {
        HLJS.highlightBlock(block);
        // 去前后空格并添加行号
        block.innerHTML = '<ul><li>' + block.innerHTML.replace(/(^\s*)|(\s*$)/g, '').replace(/\n/g, '\n</li><li>') + '\n</li></ul>';
      });
    }
  }
};
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .movie-content
    .header-wrapper
      position relative
      overflow hidden
      > img
        position absolute
        left 0
        top 0
        width 100%
        height 100%
        filter blur(5px)
        transform  scale(1.5)
        zoom 1.5
    .content-wrapper
      width auto
      @media only screen and (max-width: 768px)
        margin 5px 5px 0 5px
      @media screen and (min-width: 768px)
        margin 10px 10px 0 10px
      @media screen and (min-width: 992px)
        margin 15px 35px 0 35px
      @media screen and (min-width: 1200px)
        width 1200px
        margin 15px auto 0
        margin-bottom 200px
      .layout-left, .layout-right
        padding 0
        @media only screen and (max-width: 768px)
          padding 0
        @media screen and (min-width: 768px)
          padding 0
        @media screen and (min-width: 992px)
          padding 0 10px
        @media screen and (min-width: 1200px)
          padding 0 10px
</style>
