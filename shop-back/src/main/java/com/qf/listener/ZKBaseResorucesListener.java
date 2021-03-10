package com.qf.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

//@Component
public class ZKBaseResorucesListener {

//    @Autowired
    private ServletContext servletContext;

    // 静态资源服务地址发送改变后，zk会调用这个函数
    public void read(String url){

        // 替换之前放在servetContext中的url
        servletContext.setAttribute("base_resources_url",url);
    }
}
