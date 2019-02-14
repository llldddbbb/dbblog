package cn.dblearn.blog.manage.operation.service.impl;

import cn.dblearn.blog.manage.operation.entity.Link;
import cn.dblearn.blog.manage.operation.mapper.LinkMapper;
import cn.dblearn.blog.manage.operation.service.LinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.common.util.PageUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-02-14
 */
@Service
@Slf4j
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Link> page=baseMapper.selectPage(new Query<Link>(params).getPage(),
                new QueryWrapper<Link>().lambda());
        return new PageUtils(page);
    }

}
