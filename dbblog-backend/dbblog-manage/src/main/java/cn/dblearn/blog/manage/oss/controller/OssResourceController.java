package cn.dblearn.blog.manage.oss.controller;


import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.exception.MyException;
import cn.dblearn.blog.entity.oss.OssResource;
import cn.dblearn.blog.manage.oss.service.CloudStorageService;
import cn.dblearn.blog.manage.oss.service.OssResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 云存储资源表 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-11-30
 */
@RestController
@RequestMapping("/admin/oss/resource")
public class OssResourceController {

    @Autowired
    private OssResourceService ossResourceService;

    @Autowired
    private CloudStorageService cloudStorageService;

    @PostMapping("/upload")
    public Result uploadCover(MultipartFile file) throws Exception{
        if (file!=null && file.isEmpty()) {
            throw new MyException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url =cloudStorageService.uploadSuffix(file.getBytes(), suffix);
        OssResource resource=new OssResource(url,file.getOriginalFilename());
        ossResourceService.save(resource);
        return Result.ok().put("resource", resource);
    }
}
