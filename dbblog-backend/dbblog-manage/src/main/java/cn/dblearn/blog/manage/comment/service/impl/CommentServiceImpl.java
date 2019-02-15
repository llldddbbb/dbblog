package cn.dblearn.blog.manage.comment.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.entity.comment.Comment;
import cn.dblearn.blog.mapper.common.CommentMapper;
import cn.dblearn.blog.manage.comment.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 文章评论 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-23
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String type = (String) params.get("type");
        IPage<Comment> page=baseMapper.selectPage(new Query<Comment>(params).getPage(),
                new QueryWrapper<Comment>().lambda()
                        .eq(StringUtils.isNotEmpty(type),Comment::getType,type));
        return new PageUtils(page);
    }

}
