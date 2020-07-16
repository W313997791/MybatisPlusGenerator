package com.example.demo.generator;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import jodd.util.StringPool;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhangshiyuan
 * 一、添加引擎依赖：
 *         <!--mybatisPlus代码生成器依赖      start  -->
 *         <!--https://mp.baomidou.com/guide/generator.html#添加依赖-->
 *         <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-generator</artifactId>
 *             <version>3.3.2</version>
 *         </dependency>
 *         <!--添加 模板引擎 依赖-->
 *         <dependency>
 *             <groupId>org.freemarker</groupId>
 *             <artifactId>freemarker</artifactId>
 *             <version>2.3.30</version>
 *         </dependency>
 *         <!--end-->
 * 二、运行main方法
 * @Date 2020/7/15 14:08
 */
public class MybatisPlusGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
//    public static String scanner(String tip) {
//
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }

    public static void main(String[] args) {

        final String DATA_URL= "jdbc:oracle:thin:@192.168.xxx.xxx:端口号:数据库名称";
        final String DATA_DRIVER= "oracle.jdbc.OracleDriver";  // 驱动
        final String USER_NAME= "xxx";
        final String PASSWORD= "xxx";

        //在哪个包下面创建文件（最后一个tjData是文件生成的地方）
        final String PARENT_PACKAGE = "com.example.demo.tjData";
        //具体的包名（不太需要）
        //final String PACKAGE_NAME = "tjData";
        //具体的当前的模块名称 ,如果不是子模块，则默认为空
        final String MODULE_NAME = "";
        //具体的表名
        final String[] TABLE_NAME = {"table1","table2"};



        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + MODULE_NAME + "/src/main/java");

        //如果是多模块。需要在src前注好是哪个模块
//        gc.setOutputDir(projectPath + "/moduleName/src/main/java");

        gc.setAuthor("zsy");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATA_URL);
//        dsc.setUrl("jdbc:mysql://localhost:3306/ant?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName(DATA_DRIVER);
//        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
//        pc.setModuleName(PACKAGE_NAME);
//        pc.setModuleName(scanner("请输入包名"));
        pc.setParent(PARENT_PACKAGE);
        //原控制器层包名为web，自定义为controller
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
//         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + MODULE_NAME +"/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper.xml";

//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper.xml";

                //如果是多模块。需要在src前注好是哪个模块
//                return projectPath + "/moduleName/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setSuperEntityClass(null);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass(null);
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(TABLE_NAME);
        //该值为false则表示映射中的名称中用驼峰法
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();


        //自动生成器最后可将该方法打开，删除此文件
//        File file = new File(MybatisPlusGenerator.class.getResource("").getPath());
//        System.out.println(file);
//
//        String pathPackage  = file.getPath().replace("target\\classes","src\\main\\java");
//        File filePackage = new File(pathPackage);
//
//        //先删除文件
//        String path = pathPackage +"\\MybatisPlusGenerator.java";
//        File fileDelete = new File(path);
//        fileDelete.delete();
//        System.out.println("代码生成文件已删除");
//        //如果文件夹为空，则删除文件夹
//        if (filePackage.list().length == 0) {
//            filePackage.delete();
//            System.out.println("空文件夹已删除");
//        }

    }
}
