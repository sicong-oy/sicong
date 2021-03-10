package com.qf.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;
import com.qf.entity.ResultEntity;
import com.qf.feign.api.IGoodsService;
import com.qf.feign.api.ISearchGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/goods")
public class  GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISearchGoodsService searchGoodsService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange}")
    private String exchange;

    @RequestMapping(value = "/addGoods")
    @ResponseBody
    public ResultEntity addGoods(Goods goods) throws InvocationTargetException, IllegalAccessException {
        //添加商品到数据库
        ResultEntity resultEntity = goodsService.addGoods(goods);

        //添加商品到es
        if (ResultEntity.SUCEESS.equals(resultEntity.getStatus())) {

        try {
            Map map = (Map) resultEntity.getData();
            if (map!=null){
                map.get("id");
                goods.setId(Integer.parseInt(map.get("id").toString()));
                //添加到mq
                rabbitTemplate.convertAndSend(exchange,"goods.add",goods);
            }

        }catch (Exception e){
            log.error("添加商品到队列中失败"+goods,e);
        }


        }
        return resultEntity;
    }


    @RequestMapping(value = "/getGoodsPage")
    public String getGoodsPage(Page<Goods> page, Model model){
        ResultEntity resultEntity = goodsService.getGoodsPage(page);
        model.addAttribute("page",resultEntity.getData());
        model.addAttribute("url","http://localhost/shop-back/goods/getGoodsPage");
        return "goods/goodsList";
    }
}
