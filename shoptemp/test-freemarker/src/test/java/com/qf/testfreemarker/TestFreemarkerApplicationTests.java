package com.qf.testfreemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class TestFreemarkerApplicationTests {

    @Autowired
    private Configuration configuration;
    @Test
    void contextLoads() throws IOException, TemplateException {
        //准备数据
        Map<String,Object> map = new HashMap<>();
        map.put("username","admin");
        map.put("age",21);
        map.put("sex",1);

        //加载模板
        Template template =configuration.getTemplate("hello.ftl");

        //定义输出文件
        FileWriter writer = new FileWriter("./hello.html");

        //解析模板
        template.process(map,writer);
        System.out.println(template.getName());
    }

}
