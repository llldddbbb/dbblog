INSERT INTO dbblog.article (id, title, description, author, content, content_format, read_num, comment_num, like_num, cover_type, cover, create_time, update_time, recommend, category_id, publish, top) VALUES (1, '关于本站和博主', '关于本站和博主', 'Bobbi', '# 关于我
氧化钡，英文名Bobbi，是一名努力成长中的Java爱好者  
以下是微信，欢迎互相交流
![名片.jpg](http://oss.dblearn.cn/dbblog/20190303/63ba034bf1e24e918aa53cccb3fb66dc.jpg)
# 关于本站
本站前端Vue，后台是Java
项目还会持续更新，欢迎大家star,谢谢！
[>>点击进入](https://github.com/llldddbbb/dbblog)
', '<h1 id="-">关于我</h1>
<p>氧化钡，英文名Bobbi，是一名努力成长中的Java爱好者<br>以下是微信，欢迎互相交流
<img src="http://oss.dblearn.cn/dbblog/20190303/63ba034bf1e24e918aa53cccb3fb66dc.jpg" alt="名片.jpg"></p>
<h1 id="-">关于本站</h1>
<p>本站前端Vue，后台是Java
项目还会持续更新，欢迎大家star,谢谢！
<a href="https://github.com/llldddbbb/dbblog">&gt;&gt;点击进入</a></p>
', 51, 0, 1, 1, 'http://oss.dblearn.cn/dbblog/20190303/18a6c1d2ed47494396462901ffe02f30.jpg', '2019-03-09 17:09:38', '2019-03-04 17:41:50', 1, '1,2,3', 1, 1);
INSERT INTO dbblog.article (id, title, description, author, content, content_format, read_num, comment_num, like_num, cover_type, cover, create_time, update_time, recommend, category_id, publish, top) VALUES (3, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'Bobbi', '### Java基础
#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？
不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。
代码实例:
```java
String str1 = "通话";
String str2 = "重地";
System.out.println(String.format("str1：%d | str2：%d",  str1.hashCode(),str2.hashCode()));
System.out.println(str1.equals(str2));
```
执行的结果： 

str1：1179395 | str2：1179395

false

很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。

此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同
下面的话来自JDK：
> public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。
hashCode 的常规协定是： 
&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。

&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。

**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', '<h3 id="java-">Java基础</h3>
<h4 id="1-hashcode-equals-true-equals-true-hashcode-">1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？</h4>
<p>不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。
代码实例:</p>
<pre><code class="language-java">String str1 = &quot;通话&quot;;
String str2 = &quot;重地&quot;;
System.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));
System.out.println(str1.equals(str2));</code></pre>
<p>执行的结果： </p>
<p>str1：1179395 | str2：1179395</p>
<p>false</p>
<p>很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。</p>
<p>此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同
下面的话来自JDK：</p>
<blockquote>
<p>public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。
hashCode 的常规协定是： 
&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。</p>
</blockquote>
<p>&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。</p>
<p><strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong></p>
', 32, 0, 0, 0, 'http://oss.dblearn.cn/dbblog/20190304/b00399e2664b4c42a3e2b4b23fcf128b.jpeg', '2019-03-09 17:09:38', '2019-03-04 23:24:09', 1, '9,11,12', 1, 0);
INSERT INTO dbblog.article (id, title, description, author, content, content_format, read_num, comment_num, like_num, cover_type, cover, create_time, update_time, recommend, category_id, publish, top) VALUES (4, '用最简单的话告诉你什么是ElasticSearch', 'Elasticsearch 是一个分布式可扩展的实时搜索和分析引擎,一个建立在全文搜索引擎 Apache Lucene(TM) 基础上的搜索引擎.当然 Elasticsearch 并不仅仅是 Lucene 那么简单，下面就介绍ElasticSearch为什么是分布式的，可扩展，高性能，高可用。


作者：fallinjava
链接：https://juejin.im/post/5c790b4b51882545194f84f0
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。', 'Bobbi', '
> 转载:https://juejin.im/post/5c790b4b51882545194f84f0

## 1.什么是搜索
在我们想知道一些信息时，就会使用一些搜索引擎来获取我们想要的数据，比如搜索我们喜欢的一款游戏，或者喜欢的一本书等等，这就是提到搜索的的第一印象，说直白点就是在任何场景下找寻你想要知道的信息，这就是搜索。
- 现在的搜索也称为垂直搜索 垂直搜索引针对某一个行业的专业搜索引擎，比如说电商网站，新闻网站，各种app内部等等，他们都是搜索引擎的细分和延伸，在抽取出需要的数据进行处理后再以某种形式返回给用户。

## 2.如果用数据库来做搜索会怎么样
例如我们这里有一张商品表，现在我们要搜索"衣服"这个关键字，，执行了 select * from products where product_name like %衣服%，（假设这里没有其他任何提升效率的设置）来进行搜索，或者进行其他字段的匹配，可以分析一下这个方式的缺点。

1. 比如说，每条记录的指定字段的数据会很长，比如说“商品介绍”这个字段，可能会有几千或者几万个字符，那么搜索的时候就会去这些字符里面进行匹配是否包含要搜索的关键词。
2. 这种方式只能搜索到完全包含“衣服”这个两个字符的记录，但是可能会有一些特殊的情况，某几条记录里面的“衣服”关键词并不是连续的，可能衣服中间会插入某些字符，这个时候就搜索不出来这些记录了，但是这个商品又是我们希望搜索出来的，这个时候这种方式的弊端就十分明显了。

总的来说用数据库来实现搜索是不太靠谱的，性能会很差。

## 3.什么是全文检索
首先需要了解什么是倒排索引？我们这里先上一幅图，里面有4条记录。
![1.jpg](http://oss.dblearn.cn/dbblog/20190305/fb6809d46e7744e78c5a686bbd096506.jpg)
现在将这4条记录的内容进行拆分成一些词条，这个过程叫做分词
![2.jpg](http://oss.dblearn.cn/dbblog/20190305/0271f31d280f49b68ae9e942f286a3e4.jpg)
现在我们得到了这4条记录拆分出来词语，然后将这写词语放到一个列表中，并记录他们的ID，这个分析出来的就是 倒排索引
![3.jpg](http://oss.dblearn.cn/dbblog/20190305/567fa7ca43d34eab886a5b8dc4c83b4f.jpg)

现在我们输入 生化电影 这个关键词，这个时候搜素引擎将我们输入的内容分词为 生化 和 电影 这两个关键词，然后使用这个两个关键词去倒排索引里面匹配，发现包含 生化 这个关键词的记录有ID为 1,2,3,4这四条记录，包含 电影 这个关键词的有ID为1这条记录，由于ID为1这条记录已经被录入了，所以就被排除在外了， 这时候我们就得到了想要ID为1234这4条记录，同理，如果我们只输入 电影 这个关键词，那么符合条件的只有ID为1这条记录了。  

全文检索就是从拆分词语，存入倒排索引，然后分析用户输入的内容，在倒排索引里面进行匹配，这个过程就是全文检索。

## 4.什么是ElasticSearch
&emsp;&emsp;首先需要知道什么是Lucence，Lucence它就是一个Java的jar包，里面实现了倒排索引的算法和其他的全文检索相关的东西，ElasticSearch就是对Lunence进行了封装，为什么有Lucence了还要ElasticSeaearch来干什么呢？  

&emsp;当数据量很大的时候，比如有1PB的数据，这个时候数据放在同一台机器上基本就不行了，那么把数据分开来放在多台机器上呢？那就变成分布式了，这个时候数据前端获取数据的时候到底去那一台机器上面去获取数据呢？这个时候就很麻烦了，如果某一台机器宕机了，那么这个机器上的数据就获取不到了，这也就无法保证高可用性了，还有数据存储的时候怎么到底存入那台机器等等，这些都需要人为的处理和维护。这个时候ElasticSearch就应运而生了，它就将Lucence这些弊端给完全解决了。  

举例一些优点：

1. 高性能，自动维护数据分布到多个节点进行索引的建立，还有搜索请求分布到多个节点的执行。
2. 高可用，自动维护数据的冗余副本，保证说，一些机器宕机了，不会造成数据的丢失。
3. 封装了更多的高级功能，以给我们提供更多的高级支持，让我们快速的开发应用，开发更加复杂的应用，复杂的搜索功能，聚合分析的功能，基于地理位置的搜索(比如周围一公里内有几家咖啡厅)等等。
4. 动态扩容，当我们数据量急剧提升的时候，我们只需要增加机器就行了，比如两台机器存放1.2T数据，那么没台机器存放就是600G，但是如果600G对于服务器的压力太大了，这个时候就需要增加第三台机器，让他们每人负责400G的数据，这个过程不需要人为的去分配，只需要将汲取加入集群中就自动完成。


', '<blockquote>
<p>转载:<a href="https://juejin.im/post/5c790b4b51882545194f84f0">https://juejin.im/post/5c790b4b51882545194f84f0</a></p>
</blockquote>
<h2 id="1-">1.什么是搜索</h2>
<p>在我们想知道一些信息时，就会使用一些搜索引擎来获取我们想要的数据，比如搜索我们喜欢的一款游戏，或者喜欢的一本书等等，这就是提到搜索的的第一印象，说直白点就是在任何场景下找寻你想要知道的信息，这就是搜索。</p>
<ul>
<li>现在的搜索也称为垂直搜索 垂直搜索引针对某一个行业的专业搜索引擎，比如说电商网站，新闻网站，各种app内部等等，他们都是搜索引擎的细分和延伸，在抽取出需要的数据进行处理后再以某种形式返回给用户。</li>
</ul>
<h2 id="2-">2.如果用数据库来做搜索会怎么样</h2>
<p>例如我们这里有一张商品表，现在我们要搜索&quot;衣服&quot;这个关键字，，执行了 select * from products where product_name like %衣服%，（假设这里没有其他任何提升效率的设置）来进行搜索，或者进行其他字段的匹配，可以分析一下这个方式的缺点。</p>
<ol>
<li>比如说，每条记录的指定字段的数据会很长，比如说“商品介绍”这个字段，可能会有几千或者几万个字符，那么搜索的时候就会去这些字符里面进行匹配是否包含要搜索的关键词。</li>
<li>这种方式只能搜索到完全包含“衣服”这个两个字符的记录，但是可能会有一些特殊的情况，某几条记录里面的“衣服”关键词并不是连续的，可能衣服中间会插入某些字符，这个时候就搜索不出来这些记录了，但是这个商品又是我们希望搜索出来的，这个时候这种方式的弊端就十分明显了。</li>
</ol>
<p>总的来说用数据库来实现搜索是不太靠谱的，性能会很差。</p>
<h2 id="3-">3.什么是全文检索</h2>
<p>首先需要了解什么是倒排索引？我们这里先上一幅图，里面有4条记录。
<img src="http://oss.dblearn.cn/dbblog/20190305/fb6809d46e7744e78c5a686bbd096506.jpg" alt="1.jpg">
现在将这4条记录的内容进行拆分成一些词条，这个过程叫做分词
<img src="http://oss.dblearn.cn/dbblog/20190305/0271f31d280f49b68ae9e942f286a3e4.jpg" alt="2.jpg">
现在我们得到了这4条记录拆分出来词语，然后将这写词语放到一个列表中，并记录他们的ID，这个分析出来的就是 倒排索引
<img src="http://oss.dblearn.cn/dbblog/20190305/567fa7ca43d34eab886a5b8dc4c83b4f.jpg" alt="3.jpg"></p>
<p>现在我们输入 生化电影 这个关键词，这个时候搜素引擎将我们输入的内容分词为 生化 和 电影 这两个关键词，然后使用这个两个关键词去倒排索引里面匹配，发现包含 生化 这个关键词的记录有ID为 1,2,3,4这四条记录，包含 电影 这个关键词的有ID为1这条记录，由于ID为1这条记录已经被录入了，所以就被排除在外了， 这时候我们就得到了想要ID为1234这4条记录，同理，如果我们只输入 电影 这个关键词，那么符合条件的只有ID为1这条记录了。  </p>
<p>全文检索就是从拆分词语，存入倒排索引，然后分析用户输入的内容，在倒排索引里面进行匹配，这个过程就是全文检索。</p>
<h2 id="4-elasticsearch">4.什么是ElasticSearch</h2>
<p>&emsp;&emsp;首先需要知道什么是Lucence，Lucence它就是一个Java的jar包，里面实现了倒排索引的算法和其他的全文检索相关的东西，ElasticSearch就是对Lunence进行了封装，为什么有Lucence了还要ElasticSeaearch来干什么呢？  </p>
<p>&emsp;当数据量很大的时候，比如有1PB的数据，这个时候数据放在同一台机器上基本就不行了，那么把数据分开来放在多台机器上呢？那就变成分布式了，这个时候数据前端获取数据的时候到底去那一台机器上面去获取数据呢？这个时候就很麻烦了，如果某一台机器宕机了，那么这个机器上的数据就获取不到了，这也就无法保证高可用性了，还有数据存储的时候怎么到底存入那台机器等等，这些都需要人为的处理和维护。这个时候ElasticSearch就应运而生了，它就将Lucence这些弊端给完全解决了。  </p>
<p>举例一些优点：</p>
<ol>
<li>高性能，自动维护数据分布到多个节点进行索引的建立，还有搜索请求分布到多个节点的执行。</li>
<li>高可用，自动维护数据的冗余副本，保证说，一些机器宕机了，不会造成数据的丢失。</li>
<li>封装了更多的高级功能，以给我们提供更多的高级支持，让我们快速的开发应用，开发更加复杂的应用，复杂的搜索功能，聚合分析的功能，基于地理位置的搜索(比如周围一公里内有几家咖啡厅)等等。</li>
<li>动态扩容，当我们数据量急剧提升的时候，我们只需要增加机器就行了，比如两台机器存放1.2T数据，那么没台机器存放就是600G，但是如果600G对于服务器的压力太大了，这个时候就需要增加第三台机器，让他们每人负责400G的数据，这个过程不需要人为的去分配，只需要将汲取加入集群中就自动完成。</li>
</ol>
', 12, 0, 0, 0, 'http://oss.dblearn.cn/dbblog/20190305/d991537084cf42b2889b41838b59df0f.png', '2019-03-09 17:09:38', '2019-03-05 12:59:56', 0, '9,11,12', 1, 0);
INSERT INTO dbblog.article (id, title, description, author, content, content_format, read_num, comment_num, like_num, cover_type, cover, create_time, update_time, recommend, category_id, publish, top) VALUES (5, '性能分析利器《Arthas》总结', 'Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。', 'Bobbi', '## 性能分析利器《Arthas》总结
Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：

- 当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。
- 当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。
- 当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。
- 当你发现系统突然报出某个类的Exception，却无法找到这个类是从哪个Jar包加载的。
- 当你发现有时候排查一个问题需要上游重复调用这个方法，于是你只能想尽办法利用postman等工具重现这个请求。


下面我将会介绍一下Arthas的一些常用的命令和用法，看看是如何解决我们实际中的问题的，至于安装教程可以参考Arthas的github。
> https://github.com/alibaba/arthas

## 常用命令
dashboard 当前系统的实时数据面板  

jvm 查看当前JVM信息  

thread 可直接查看线程的cpu占用比  

sc sm 快速搜索类和方法信息  

getstatic 查看类静态变量  

jad 反编译class文件  

mc 编译.java文件生成.class  

redefine 加载外部class文件到应用程序中  

monitor 监测方法调用次数、成功次数、失败次数、平均RT等  

watch tt 观测方法执行的前、后、结束、异常、耗时过大时，入参（入参属性深度可调）、返回值、异常，支持实时监测每次方法执行和方法的所有调用执行。  

trace 查看方法调用树耗时  

stack 查看方法的所有调用树路径

## 1.奇怪的类加载错误
&emsp;&emsp;相信大家都遇到过NoSuchMethodError这个错误，一般老司机看见这个错误第一反应就是jar包版本号冲突，这种问题一般来说使用maven的一些插件就能轻松解决。  

&emsp;&emsp;之前遇到个奇怪的问题，我们有两个服务的client-jar包，有个类的包名和类名均是一致，在编写代码的时候没有注意到这个问题，在编译阶段由于包名和类名都是一致，所有编译阶段并没有报错，在线下的运行阶段没有问题，但是测试环境的机器中的运行阶段缺报出了问题。这个和之前的jar包版本号冲突有点不同，因为在排查的时候我们想使用A服务的client-jar包的这个类，但是这个jar包的版本号在Maven中的确是唯一的。
这个时候Arthas就可以大显神通了。
### 1.1 sc命令
找到对应的类，然后输出下面的命令(用例使用的是官方提供的用例):
``` shell
$ sc -d demo.MathGame
class-info        demo.MathGame
code-source       /private/tmp/arthas-demo.jar
name              demo.MathGame
isInterface       false
isAnnotation      false
isEnum            false
isAnonymousClass  false
isArray           false
isLocalClass      false
isMemberClass     false
isPrimitive       false
isSynthetic       false
simple-name       MathGame
modifier          public
annotation
interfaces
super-class       +-java.lang.Object
class-loader      +-sun.misc.Launcher$AppClassLoader@3d4eac69
                    +-sun.misc.Launcher$ExtClassLoader@66350f69
classLoaderHash   3d4eac69
 
Affect(row-cnt:1) cost in 875 ms.

```
可以看见打印出了code-source,当时发现了code-source并不是从对应的Jar包取出来的，于是发现了两个服务对于同一个类使用了同样的包名和类名，导致了这个奇怪的问题，后续通过修改包名和类名进行解决。

### 1.2 jad
Arthas还提供了一个命令jad用来反编译,对于解决类冲突错误很有用，比如我们想知道这个类里面的代码到底是什么，直接一个jad命令就能搞定:
```shell
$ jad java.lang.String
 
ClassLoader:
 
Location:
 
/*
* Decompiled with CFR 0_132.
*/
package java.lang;
 
import java.io.ObjectStreamField;
...
public final class String
implements Serializable,
Comparable<String>,
CharSequence {
    private final char[] value;
    private int hash;
    private static final long serialVersionUID = -6849794470754667710L;
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();
 
    public String(byte[] arrby, int n, int n2) {
        String.checkBounds(arrby, n, n2);
        this.value = StringCoding.decode(arrby, n, n2);
    }
...
```

一般通过这个命令我们就能发现和你所期待的类是否缺少了某些方法，或者某些方法有些改变，从而确定jar包冲突。

## 2.动态修改日志级别
有很多同学可能会觉得动态修改日志有什么用呢？好像自己也没怎么用过呢？
一般来说下面这几个场景可以需要:  

一般大家日志级别默认是info，有时候需要查看debug的日志可能需要重新上线。
当线上某个应用流量比较大的时候，如何业务出现问题，可能会短时间之内产生大量日志，由于日志会写盘，会消耗大量的内存和磁盘IO进一步加重我们的问题严重性，进而引起雪崩。
我们可以使用动态修改日志解决我们上面两个问题

### 2.1 ognl
ognl是一门表达式语言，在Arthas中你可以利用这个表达式语言做很多事，比如执行某个方法，获取某个信息。再这里我们可以通过下面的命令来动态的修改日志级别:
``` shell
$ ognl ''@com.lz.test@LOGGER.logger.privateConfig''
@PrivateConfig[
    loggerConfig=@LoggerConfig[root],
    loggerConfigLevel=@Level[INFO],
    intLevel=@Integer[400],
]
$ ognl ''@com.lz.test@LOGGER.logger.setLevel(@org.apache.logging.log4j.Level@ERROR)''
null
$ ognl ''@com.lz.test@LOGGER.logger.privateConfig''
@PrivateConfig[
    loggerConfig=@LoggerConfig[root],
    loggerConfigLevel=@Level[ERROR],
    intLevel=@Integer[200],
  
]

```


## 3.如何知道某个方法是否调用
很多时候我们方法执行的情况和我们预期不符合，但是我们又不知道到底哪里不符合，Arthas的watch命令就能帮助我们解决这个问题。

### 3.1 watch
watch命令顾名思义观察，他可以观察指定方法调用情况，定义了4个观察事件点， -b 方法调用前，-e 方法异常后，-s 方法返回后，-f 方法结束后。默认是-f
比如我们想知道某个方法执行的时候，参数和返回值到底是什么。注意这里的参数是方法执行完成的时候的参数，和入参不同有可能会发生变化。
``` shell
$ watch demo.MathGame primeFactors "{params,returnObj}" -x 2
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 44 ms.
ts=2018-12-03 19:16:51; [cost=1.280502ms] result=@ArrayList[
    @Object[][
        @Integer[535629513],
    ],
    @ArrayList[
        @Integer[3],
        @Integer[19],
        @Integer[191],
        @Integer[49199],
    ],
]
```

你能得到参数和返回值的情况，以及方法时间消耗的等信息。

## 4.如何知道某个方法耗时较多
当某个方法耗时较长，这个时候你需要排查到底是某一处发生了长时间的耗时，一般这种问题比较难排查，都是通过全链路追踪trace图去进行排查，但是在本地的应用中没有trace图，这个时候需要Arthas的trace命令来进行排查问题。
### 4.1 trace
trace 命令能主动搜索 class-pattern／method-pattern 对应的方法调用路径，渲染和统计整个调用链路上的所有性能开销和追踪调用链路。
但是trace只能追踪一层的调用链路，如果一层的链路信息不够用，可以把该链路上有问题的方法再次进行trace。
trace使用例子如下。
``` shell
$ trace demo.MathGame run
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 42 ms.
`---ts=2018-12-04 00:44:17;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@3d4eac69
    `---[10.611029ms] demo.MathGame:run()
        +---[0.05638ms] java.util.Random:nextInt()
        +---[10.036885ms] demo.MathGame:primeFactors()
        `---[0.170316ms] demo.MathGame:print()

```

可以看见上述耗时最多的方法是primeFactors，所以我们可以对其进行trace进行再一步的排查。

## 5.如何使用命令重发请求？
有时候排查一个问题需要上游再次调用这个方法，比如使用postMan等工具，当然Arthas提供了一个命令让替代我们来回手动请求。
### 5.1 tt
tt官方介绍: 方法执行数据的时空隧道，记录下指定方法每次调用的入参和返回信息，并能对这些不同的时间下调用进行观测。可以看见tt可以用于录制请求，当然也支持我们重放。
如果要录制某个方法，可以用下面命令:
```shell
$ tt -t demo.MathGame primeFactors
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 66 ms.
 INDEX   TIMESTAMP            COST(ms)  IS-RET  IS-EXP   OBJECT         CLASS                          METHOD
-------------------------------------------------------------------------------------------------------------------------------------
 1000    2018-12-04 11:15:38  1.096236  false   true     0x4b67cf4d     MathGame                       primeFactors
 1001    2018-12-04 11:15:39  0.191848  false   true     0x4b67cf4d     MathGame                       primeFactors
 1002    2018-12-04 11:15:40  0.069523  false   true     0x4b67cf4d     MathGame                       primeFactors
 1003    2018-12-04 11:15:41  0.186073  false   true     0x4b67cf4d     MathGame                       primeFactors
 1004    2018-12-04 11:15:42  17.76437  true    false    0x4b67cf4d     MathGame                       primeFactors

```

上面录制了5个调用环境现场，也可以看做是录制了5个请求返回信息。比如我们想选择index为1004个的请求来重放，可以输入下面的命令。
```shell
$ tt -i 1004 -p
 RE-INDEX       1004
 GMT-REPLAY     2018-12-04 11:26:00
 OBJECT         0x4b67cf4d
 CLASS          demo.MathGame
 METHOD         primeFactors
 PARAMETERS[0]  @Integer[946738738]
 IS-RETURN      true
 IS-EXCEPTION   false
 RETURN-OBJ     @ArrayList[
                    @Integer[2],
                    @Integer[11],
                    @Integer[17],
                    @Integer[2531387],
                ]
Time fragment[1004] successfully replayed.
Affect(row-cnt:1) cost in 14 ms.
```

注意重放请求需要关注两点:

ThreadLocal 信息丢失:由于使用的是Arthas线程调用，会让threadLocal信息丢失，比如一些TraceId信息可能会丢失
引用的对象:保存的入参是保存的引用，而不是拷贝，所以如果参数中的内容被修改，那么入参其实也是被修改的。

## 6.一些耗时的方法，经常被触发，如何知道谁调用的?
有时候有些方法非常耗时或者非常重要，需要知道到底是谁发起的调用，比如System.gc(),有时候如果你发现fullgc频繁是因为System.gc()引起的，你需要查看到底是什么应用调用的，那么你就可以使用下面的命令
我们可以输入下面的命令:
```shell
$ options unsafe true
 NAME    BEFORE-VALUE  AFTER-VALUE                                                                                                                                                                        
-----------------------------------                                                                                                                                                                       
 unsafe  false         true                                                                                                                                                                               
$ stack java.lang.System gc
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 50 ms.
ts=2019-01-20 21:14:05;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@14dad5dc
    @java.lang.System.gc()
        at com.lz.test.Test.main(Test.java:322)

```

首先输入options unsafe true允许我们对jdk增强，然后对System.gc进行进行监视，然后记录当前的堆栈来获取是什么位置进行的调用。
## 7.如何重定义某个类?
有些时候我们找了所有的命令，发现和我们的需求并不符合的时候，那么这个时候我们可以重新定义这个类，我们可以用使用下面的命令。
### 7.1 redefine
redefine命令提供了我们可以重新定义jvm中的class，但是使用这个命令之后class不可恢复。我们首先需要把重写的class编译出来，然后上传到我们指定的目录，进行下面的操作:
```shell
 redefine -p /tmp/Test.class
```

可以重定义我们的Test.class。从而修改逻辑，完成我们自定义的需求。
', '<h2 id="-arthas-">性能分析利器《Arthas》总结</h2>
<p>Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：</p>
<ul>
<li>当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。</li>
<li>当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。</li>
<li>当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。</li>
<li>当你发现系统突然报出某个类的Exception，却无法找到这个类是从哪个Jar包加载的。</li>
<li>当你发现有时候排查一个问题需要上游重复调用这个方法，于是你只能想尽办法利用postman等工具重现这个请求。</li>
</ul>
<p>下面我将会介绍一下Arthas的一些常用的命令和用法，看看是如何解决我们实际中的问题的，至于安装教程可以参考Arthas的github。</p>
<blockquote>
<p><a href="https://github.com/alibaba/arthas">https://github.com/alibaba/arthas</a></p>
</blockquote>
<h2 id="-">常用命令</h2>
<p>dashboard 当前系统的实时数据面板  </p>
<p>jvm 查看当前JVM信息  </p>
<p>thread 可直接查看线程的cpu占用比  </p>
<p>sc sm 快速搜索类和方法信息  </p>
<p>getstatic 查看类静态变量  </p>
<p>jad 反编译class文件  </p>
<p>mc 编译.java文件生成.class  </p>
<p>redefine 加载外部class文件到应用程序中  </p>
<p>monitor 监测方法调用次数、成功次数、失败次数、平均RT等  </p>
<p>watch tt 观测方法执行的前、后、结束、异常、耗时过大时，入参（入参属性深度可调）、返回值、异常，支持实时监测每次方法执行和方法的所有调用执行。  </p>
<p>trace 查看方法调用树耗时  </p>
<p>stack 查看方法的所有调用树路径</p>
<h2 id="1-">1.奇怪的类加载错误</h2>
<p>&emsp;&emsp;相信大家都遇到过NoSuchMethodError这个错误，一般老司机看见这个错误第一反应就是jar包版本号冲突，这种问题一般来说使用maven的一些插件就能轻松解决。  </p>
<p>&emsp;&emsp;之前遇到个奇怪的问题，我们有两个服务的client-jar包，有个类的包名和类名均是一致，在编写代码的时候没有注意到这个问题，在编译阶段由于包名和类名都是一致，所有编译阶段并没有报错，在线下的运行阶段没有问题，但是测试环境的机器中的运行阶段缺报出了问题。这个和之前的jar包版本号冲突有点不同，因为在排查的时候我们想使用A服务的client-jar包的这个类，但是这个jar包的版本号在Maven中的确是唯一的。
这个时候Arthas就可以大显神通了。</p>
<h3 id="1-1-sc-">1.1 sc命令</h3>
<p>找到对应的类，然后输出下面的命令(用例使用的是官方提供的用例):</p>
<pre><code class="language-shell">$ sc -d demo.MathGame
class-info        demo.MathGame
code-source       /private/tmp/arthas-demo.jar
name              demo.MathGame
isInterface       false
isAnnotation      false
isEnum            false
isAnonymousClass  false
isArray           false
isLocalClass      false
isMemberClass     false
isPrimitive       false
isSynthetic       false
simple-name       MathGame
modifier          public
annotation
interfaces
super-class       +-java.lang.Object
class-loader      +-sun.misc.Launcher$AppClassLoader@3d4eac69
                    +-sun.misc.Launcher$ExtClassLoader@66350f69
classLoaderHash   3d4eac69

Affect(row-cnt:1) cost in 875 ms.
</code></pre>
<p>可以看见打印出了code-source,当时发现了code-source并不是从对应的Jar包取出来的，于是发现了两个服务对于同一个类使用了同样的包名和类名，导致了这个奇怪的问题，后续通过修改包名和类名进行解决。</p>
<h3 id="1-2-jad">1.2 jad</h3>
<p>Arthas还提供了一个命令jad用来反编译,对于解决类冲突错误很有用，比如我们想知道这个类里面的代码到底是什么，直接一个jad命令就能搞定:</p>
<pre><code class="language-shell">$ jad java.lang.String

ClassLoader:

Location:

/*
* Decompiled with CFR 0_132.
*/
package java.lang;

import java.io.ObjectStreamField;
...
public final class String
implements Serializable,
Comparable&lt;String&gt;,
CharSequence {
    private final char[] value;
    private int hash;
    private static final long serialVersionUID = -6849794470754667710L;
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
    public static final Comparator&lt;String&gt; CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();

    public String(byte[] arrby, int n, int n2) {
        String.checkBounds(arrby, n, n2);
        this.value = StringCoding.decode(arrby, n, n2);
    }
...</code></pre>
<p>一般通过这个命令我们就能发现和你所期待的类是否缺少了某些方法，或者某些方法有些改变，从而确定jar包冲突。</p>
<h2 id="2-">2.动态修改日志级别</h2>
<p>有很多同学可能会觉得动态修改日志有什么用呢？好像自己也没怎么用过呢？
一般来说下面这几个场景可以需要:  </p>
<p>一般大家日志级别默认是info，有时候需要查看debug的日志可能需要重新上线。
当线上某个应用流量比较大的时候，如何业务出现问题，可能会短时间之内产生大量日志，由于日志会写盘，会消耗大量的内存和磁盘IO进一步加重我们的问题严重性，进而引起雪崩。
我们可以使用动态修改日志解决我们上面两个问题</p>
<h3 id="2-1-ognl">2.1 ognl</h3>
<p>ognl是一门表达式语言，在Arthas中你可以利用这个表达式语言做很多事，比如执行某个方法，获取某个信息。再这里我们可以通过下面的命令来动态的修改日志级别:</p>
<pre><code class="language-shell">$ ognl &#39;@com.lz.test@LOGGER.logger.privateConfig&#39;
@PrivateConfig[
    loggerConfig=@LoggerConfig[root],
    loggerConfigLevel=@Level[INFO],
    intLevel=@Integer[400],
]
$ ognl &#39;@com.lz.test@LOGGER.logger.setLevel(@org.apache.logging.log4j.Level@ERROR)&#39;
null
$ ognl &#39;@com.lz.test@LOGGER.logger.privateConfig&#39;
@PrivateConfig[
    loggerConfig=@LoggerConfig[root],
    loggerConfigLevel=@Level[ERROR],
    intLevel=@Integer[200],

]
</code></pre>
<h2 id="3-">3.如何知道某个方法是否调用</h2>
<p>很多时候我们方法执行的情况和我们预期不符合，但是我们又不知道到底哪里不符合，Arthas的watch命令就能帮助我们解决这个问题。</p>
<h3 id="3-1-watch">3.1 watch</h3>
<p>watch命令顾名思义观察，他可以观察指定方法调用情况，定义了4个观察事件点， -b 方法调用前，-e 方法异常后，-s 方法返回后，-f 方法结束后。默认是-f
比如我们想知道某个方法执行的时候，参数和返回值到底是什么。注意这里的参数是方法执行完成的时候的参数，和入参不同有可能会发生变化。</p>
<pre><code class="language-shell">$ watch demo.MathGame primeFactors &quot;{params,returnObj}&quot; -x 2
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 44 ms.
ts=2018-12-03 19:16:51; [cost=1.280502ms] result=@ArrayList[
    @Object[][
        @Integer[535629513],
    ],
    @ArrayList[
        @Integer[3],
        @Integer[19],
        @Integer[191],
        @Integer[49199],
    ],
]</code></pre>
<p>你能得到参数和返回值的情况，以及方法时间消耗的等信息。</p>
<h2 id="4-">4.如何知道某个方法耗时较多</h2>
<p>当某个方法耗时较长，这个时候你需要排查到底是某一处发生了长时间的耗时，一般这种问题比较难排查，都是通过全链路追踪trace图去进行排查，但是在本地的应用中没有trace图，这个时候需要Arthas的trace命令来进行排查问题。</p>
<h3 id="4-1-trace">4.1 trace</h3>
<p>trace 命令能主动搜索 class-pattern／method-pattern 对应的方法调用路径，渲染和统计整个调用链路上的所有性能开销和追踪调用链路。
但是trace只能追踪一层的调用链路，如果一层的链路信息不够用，可以把该链路上有问题的方法再次进行trace。
trace使用例子如下。</p>
<pre><code class="language-shell">$ trace demo.MathGame run
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 42 ms.
`---ts=2018-12-04 00:44:17;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@3d4eac69
    `---[10.611029ms] demo.MathGame:run()
        +---[0.05638ms] java.util.Random:nextInt()
        +---[10.036885ms] demo.MathGame:primeFactors()
        `---[0.170316ms] demo.MathGame:print()
</code></pre>
<p>可以看见上述耗时最多的方法是primeFactors，所以我们可以对其进行trace进行再一步的排查。</p>
<h2 id="5-">5.如何使用命令重发请求？</h2>
<p>有时候排查一个问题需要上游再次调用这个方法，比如使用postMan等工具，当然Arthas提供了一个命令让替代我们来回手动请求。</p>
<h3 id="5-1-tt">5.1 tt</h3>
<p>tt官方介绍: 方法执行数据的时空隧道，记录下指定方法每次调用的入参和返回信息，并能对这些不同的时间下调用进行观测。可以看见tt可以用于录制请求，当然也支持我们重放。
如果要录制某个方法，可以用下面命令:</p>
<pre><code class="language-shell">$ tt -t demo.MathGame primeFactors
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 66 ms.
 INDEX   TIMESTAMP            COST(ms)  IS-RET  IS-EXP   OBJECT         CLASS                          METHOD
-------------------------------------------------------------------------------------------------------------------------------------
 1000    2018-12-04 11:15:38  1.096236  false   true     0x4b67cf4d     MathGame                       primeFactors
 1001    2018-12-04 11:15:39  0.191848  false   true     0x4b67cf4d     MathGame                       primeFactors
 1002    2018-12-04 11:15:40  0.069523  false   true     0x4b67cf4d     MathGame                       primeFactors
 1003    2018-12-04 11:15:41  0.186073  false   true     0x4b67cf4d     MathGame                       primeFactors
 1004    2018-12-04 11:15:42  17.76437  true    false    0x4b67cf4d     MathGame                       primeFactors
</code></pre>
<p>上面录制了5个调用环境现场，也可以看做是录制了5个请求返回信息。比如我们想选择index为1004个的请求来重放，可以输入下面的命令。</p>
<pre><code class="language-shell">$ tt -i 1004 -p
 RE-INDEX       1004
 GMT-REPLAY     2018-12-04 11:26:00
 OBJECT         0x4b67cf4d
 CLASS          demo.MathGame
 METHOD         primeFactors
 PARAMETERS[0]  @Integer[946738738]
 IS-RETURN      true
 IS-EXCEPTION   false
 RETURN-OBJ     @ArrayList[
                    @Integer[2],
                    @Integer[11],
                    @Integer[17],
                    @Integer[2531387],
                ]
Time fragment[1004] successfully replayed.
Affect(row-cnt:1) cost in 14 ms.</code></pre>
<p>注意重放请求需要关注两点:</p>
<p>ThreadLocal 信息丢失:由于使用的是Arthas线程调用，会让threadLocal信息丢失，比如一些TraceId信息可能会丢失
引用的对象:保存的入参是保存的引用，而不是拷贝，所以如果参数中的内容被修改，那么入参其实也是被修改的。</p>
<h2 id="6-">6.一些耗时的方法，经常被触发，如何知道谁调用的?</h2>
<p>有时候有些方法非常耗时或者非常重要，需要知道到底是谁发起的调用，比如System.gc(),有时候如果你发现fullgc频繁是因为System.gc()引起的，你需要查看到底是什么应用调用的，那么你就可以使用下面的命令
我们可以输入下面的命令:</p>
<pre><code class="language-shell">$ options unsafe true
 NAME    BEFORE-VALUE  AFTER-VALUE                                                                                                                                                                        
-----------------------------------                                                                                                                                                                       
 unsafe  false         true                                                                                                                                                                               
$ stack java.lang.System gc
Press Ctrl+C to abort.
Affect(class-cnt:1 , method-cnt:1) cost in 50 ms.
ts=2019-01-20 21:14:05;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@14dad5dc
    @java.lang.System.gc()
        at com.lz.test.Test.main(Test.java:322)
</code></pre>
<p>首先输入options unsafe true允许我们对jdk增强，然后对System.gc进行进行监视，然后记录当前的堆栈来获取是什么位置进行的调用。</p>
<h2 id="7-">7.如何重定义某个类?</h2>
<p>有些时候我们找了所有的命令，发现和我们的需求并不符合的时候，那么这个时候我们可以重新定义这个类，我们可以用使用下面的命令。</p>
<h3 id="7-1-redefine">7.1 redefine</h3>
<p>redefine命令提供了我们可以重新定义jvm中的class，但是使用这个命令之后class不可恢复。我们首先需要把重写的class编译出来，然后上传到我们指定的目录，进行下面的操作:</p>
<pre><code class="language-shell"> redefine -p /tmp/Test.class</code></pre>
<p>可以重定义我们的Test.class。从而修改逻辑，完成我们自定义的需求。</p>
', 8, 0, 1, 2, null, '2019-03-09 17:09:38', '2019-03-07 12:53:42', 0, '9,11,12', 1, 0);