package cn.dblearn.blog.portal.article.service.impl;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.manage.article.mapper.ArticleMapper;
import cn.dblearn.blog.portal.article.pojo.vo.ArticleVO;
import cn.dblearn.blog.portal.article.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HomeServiceImpl
 *
 * @author bobbi
 * @date 2018/11/07 20:37
 * @email 571002217@qq.com
 * @description
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ArticleMapper blogArticleMapper;

    /**
     * 获取首页数据
     *
     * @return
     */
    @Override
    public Result listHomeData() {
        List<ArticleVO> articleVoList=blogArticleMapper.listHomeArticle(null);
        return Result.ok().put("articleList",articleVoList);
    }
}
