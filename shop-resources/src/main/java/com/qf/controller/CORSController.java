package com.qf.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 处理woff和ttf跨域的问题
 */
@Controller
@Slf4j
public class CORSController {

    @CrossOrigin("*") // 所有
    @RequestMapping(value = "lib/Hui-iconfont/1.0.1/iconfont.woff") //
    public void woff(HttpServletResponse response) throws  Exception{
        copyFile("classpath:static/lib/Hui-iconfont/1.0.1/iconfont.woff",response);

    }

    @RequestMapping(value = "lib/Hui-iconfont/1.0.1/iconfont.ttf ")
    @CrossOrigin("*")
    public void ttf(HttpServletResponse response) throws  Exception{
        copyFile("classpath:static/lib/Hui-iconfont/1.0.1/iconfont.ttf",response);
    }

    public void copyFile(String filePath,HttpServletResponse response) throws  Exception{
        // 1.先要找到资源文件
        File file = ResourceUtils.getFile(filePath);

        // 2.把文件转成流
        FileInputStream ips = null;
        try {
            ips = new FileInputStream(file);

            // 3.拷贝
            IOUtils.copy(ips,response.getOutputStream());
        }catch (IOException e){
            log.error("文件读取失败",e);
        }finally {
            IOUtils.closeQuietly(ips);
        }
    }
}
