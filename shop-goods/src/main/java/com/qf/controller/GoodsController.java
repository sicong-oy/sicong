package com.qf.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;
import com.qf.entity.ResultEntity;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value = "/addGoods")
    public ResultEntity addGoods(@RequestBody Goods goods){ // 没有id
        boolean insert = goodsService.insert(goods); // 有id

        return ResultEntity.response(goods); // 返回
    }

    @RequestMapping(value = "/getGoodsPage")
    public ResultEntity getGoodsPage(@RequestBody Page<Goods> page){
        return ResultEntity.response(goodsService.selectPage(page));
    }
}
