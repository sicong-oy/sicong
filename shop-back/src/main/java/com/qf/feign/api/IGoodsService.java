package com.qf.feign.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;
import com.qf.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("SHOP-GOODS")
public interface IGoodsService {

    @RequestMapping(value = "/goods/addGoods")
    public ResultEntity addGoods(@RequestBody Goods goods);

    @RequestMapping(value = "/goods/getGoodsPage")
    public ResultEntity getGoodsPage(@RequestBody Page<Goods> page);
}
