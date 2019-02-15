package ${package.Controller};

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.basePath}.common.Result;
import ${cfg.basePath}.common.util.PageUtils;
import ${cfg.basePath}.common.validator.ValidatorUtils;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Slf4j
@RequestMapping("<#if cfg.urlPrefix??>${cfg.urlPrefix}</#if><#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${cfg.entityName}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${cfg.entityName}Service;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("${package.ModuleName}:${cfg.entityName}:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = ${cfg.entityName}Service.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("${package.ModuleName}:${cfg.entityName}:info")
    public Result info(@PathVariable("id") String id){
       ${entity} ${cfg.entityName} = ${cfg.entityName}Service.getById(id);

        return Result.ok().put("${cfg.entityName}", ${cfg.entityName});
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("${package.ModuleName}:${cfg.entityName}:save")
    public Result save(@RequestBody ${entity} ${cfg.entityName}){
        ValidatorUtils.validateEntity(${cfg.entityName});
        ${cfg.entityName}Service.save(${cfg.entityName});

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("${package.ModuleName}:${cfg.entityName}:update")
    public Result update(@RequestBody ${entity} ${cfg.entityName}){
        ValidatorUtils.validateEntity(${cfg.entityName});
        ${cfg.entityName}Service.updateById(${cfg.entityName});
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("${package.ModuleName}:${cfg.entityName}:delete")
    public Result delete(@RequestBody String[] ids){
        ${cfg.entityName}Service.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}
</#if>
