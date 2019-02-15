package cn.dblearn.blog.portal.service.impl;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.mapper.article.ArticleMapper;
import cn.dblearn.blog.portal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
//        List<ArticleVO> articleVoList=blogArticleMapper.selectList(null);
        return Result.ok();
    }
}
