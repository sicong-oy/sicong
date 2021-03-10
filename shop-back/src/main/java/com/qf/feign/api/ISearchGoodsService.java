package com.qf.feign.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;
import com.qf.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SHOP-SEARCH")
public interface ISearchGoodsService {

    @RequestMapping(value = "/goods/addGoods")
    public ResultEntity addGoods(@RequestBody Goods goods);

    @RequestMapping(value = "/goods/getGoodsPage")
    public ResultEntity getGoodsPage(@RequestBody Page<Goods> page);

    @RequestMapping(value = "/goods/deleteGoodsById/{id}")
    public ResultEntity deleteGoodsById(@PathVariable("id") Integer id);
}
