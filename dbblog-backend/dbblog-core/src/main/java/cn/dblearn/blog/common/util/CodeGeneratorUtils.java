package cn.dblearn.blog.common.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * CodeGenerator
 *
 * @author bobbi
 * @date 2018/10/08 20:24
 * @email 571002217@qq.com
 * @description mybatis-plus提供的代码生成器
 */
public class CodeGeneratorUtils {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        String moduleName = scanner("请输入模块名");
        String tableName = scanner("请输入表名");
        String entityName = "";
        if(tableName.indexOf("_")>0){
            entityName = tableName.split("_")[1];
        }else {
            entityName = tableName;
        }

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("bobbi");;
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ID_WORKER_STR);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/dbblog?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.dblearn.blog.manage");
        pc.setModuleName(moduleName);
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.entityName 【可无】
        String finalEntityName = entityName;
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("entityName", finalEntityName);
                map.put("urlPrefix","/admin");
                map.put("basePath","cn.dblearn.blog");
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        //配置xml
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        //配置前端addOrUpdate页面
        focList.add(new FileOutConfig("/templates/add-or-update.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/views/" + pc.getModuleName()
                        + "/" + finalEntityName + "-add-or-update.vue";
            }
        });
        //配置前端list页面
        focList.add(new FileOutConfig("/templates/index.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/views/" + pc.getModuleName()
                        + "/" + finalEntityName + ".vue";
            }
        });
        //配置菜单SQL
        focList.add(new FileOutConfig("/templates/menu.sql.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/sql/" + pc.getModuleName()
                        + "/" + finalEntityName + ".sql";
            }
        });

        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("/templates/entity.java");
        tc.setXml(null);
        tc.setMapper("/templates/mapper.java");
        tc.setService("/templates/service.java");
        tc.setServiceImpl("/templates/serviceImpl.java");
        tc.setController("/templates/controller.java");

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableName);
        strategy.setSuperControllerClass("cn.dblearn.blog.manage.sys.controller.AbstractController");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
