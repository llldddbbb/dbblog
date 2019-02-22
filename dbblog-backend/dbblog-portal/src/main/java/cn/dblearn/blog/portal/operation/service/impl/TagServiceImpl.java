package cn.dblearn.blog.portal.operation.service.impl;

import cn.dblearn.blog.common.constants.RedisKeyConstants;
import cn.dblearn.blog.entity.operation.Tag;
import cn.dblearn.blog.entity.operation.vo.TagVo;
import cn.dblearn.blog.mapper.operation.TagMapper;
import cn.dblearn.blog.portal.operation.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TagServiceImpl
 *
 * @author bobbi
 * @date 2019/02/22 16:34
 * @email 571002217@qq.com
 * @description
 */
@Service("TagPortalService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    /**
     * 获取tagVoList
     *
     * @return
     */
    @Override
    @Cacheable(value = RedisKeyConstants.PORTAL_TAG_LIST)
    public List<TagVo> listTagVo() {
        return baseMapper.listTagVo();
    }
}
