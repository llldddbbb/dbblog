package cn.dblearn.blog.manage.read.service.impl;

import cn.dblearn.blog.manage.read.entity.ReadBookSense;
import cn.dblearn.blog.manage.read.mapper.ReadBookSenseMapper;
import cn.dblearn.blog.manage.read.service.ReadBookSenseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 读后感 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-02-13
 */
@Service
@Slf4j
public class ReadBookSenseServiceImpl extends ServiceImpl<ReadBookSenseMapper, ReadBookSense> implements ReadBookSenseService {


    /**
     * 获取读后感
     *
     * @param bookId
     * @return
     */
    @Override
    public ReadBookSense getReadSense(Integer bookId) {
         ReadBookSense readSense = this.baseMapper.selectOne(new QueryWrapper<ReadBookSense>().lambda()
                .eq(bookId!=null,ReadBookSense::getBookId,bookId));
        return Optional.ofNullable(readSense)
                .orElse(new ReadBookSense());
    }
}
