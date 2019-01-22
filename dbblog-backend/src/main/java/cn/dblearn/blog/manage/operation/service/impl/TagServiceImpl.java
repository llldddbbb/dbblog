package cn.dblearn.blog.manage.operation.service.impl;

import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.operation.mapper.TagMapper;
import cn.dblearn.blog.manage.operation.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.common.util.PageUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-21
 */
@Service
@Slf4j
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Tag> page=baseMapper.selectPage(new Query<Tag>(params).getPage(),
                new QueryWrapper<Tag>().lambda());
        return new PageUtils(page);
    }

    /**
     * 根据文章Id获取列表
     *
     * @param articleId
     * @return
     */
    @Override
    public List<Tag> listByArticleId(Integer articleId) {
        return this.baseMapper.listByForeignId(articleId, ModuleEnum.ARTICLE.getValue());
    }


}
