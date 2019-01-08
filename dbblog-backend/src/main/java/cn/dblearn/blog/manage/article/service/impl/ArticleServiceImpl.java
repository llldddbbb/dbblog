package cn.dblearn.blog.manage.article.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.article.entity.dto.ArticleDto;
import cn.dblearn.blog.manage.operation.entity.TagLink;
import cn.dblearn.blog.manage.article.mapper.ArticleMapper;
import cn.dblearn.blog.manage.article.entity.Article;
import cn.dblearn.blog.manage.operation.mapper.TagLinkMapper;
import cn.dblearn.blog.manage.operation.mapper.TagMapper;
import cn.dblearn.blog.manage.article.service.ArticleService;
import cn.dblearn.blog.manage.operation.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * articleAdminServiceImpl
 *
 * @author bobbi
 * @date 2018/11/21 12:48
 * @email 571002217@qq.com
 * @description
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagLinkMapper tagLinkMapper;

    /**
     * 分页查询博文列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<Article> page=baseMapper.selectPage(new Query<Article>(params).getPage(),
                new QueryWrapper<Article>().lambda().like(!StringUtils.isEmpty(title), Article::getTitle,title));
        return new PageUtils(page);
    }

    /**
     * 保存博文文章
     *
     * @param article
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDto article) {
        baseMapper.insert(article);
        this.saveTagAndNew(article);
    }

    /**
     * 更新博文
     *
     * @param article
     */
    @Override
    public void updateArticle(ArticleDto article) {
        // 删除多对多所属标签
        tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                .eq(TagLink::getForeignId,article.getArticleId()));
        // 更新所属标签
        this.saveTagAndNew(article);
        // 更新博文
        baseMapper.updateById(article);

    }

    /**
     * 获取文章对象
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleDto getArticle(Integer articleId) {
        ArticleDto articleDto = new ArticleDto();
        Article article = this.baseMapper.selectById(articleId);
        BeanUtils.copyProperties(article,articleDto);
        // 查询所属标签
        articleDto.setTagList(tagService.listByArticleId(articleId));
        return articleDto;
    }

    /**
     * 批量删除
     *
     * @param articleIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] articleIds) {
        //先删除博文标签多对多关联
        Arrays.stream(articleIds).forEach(articleId -> {
            tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                    .eq(articleId!=null, TagLink::getForeignId,articleId));
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }

    /**
     * 添加文章所属标签，包含新增标签
     * @param article
     */
    private void saveTagAndNew(ArticleDto article){
        if(!CollectionUtils.isEmpty(article.getTagList())){
            article.getTagList().forEach(tag -> {
                if(tag.getTagId() == null) {
                    tagService.save(tag);
                }
                TagLink articleTagLink=new TagLink(article.getArticleId(),tag.getTagId());
                tagLinkMapper.insert(articleTagLink);
            });
        }
    }


}
