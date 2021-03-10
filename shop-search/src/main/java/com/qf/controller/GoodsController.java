package com.qf.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;
import com.qf.entity.ResultEntity;
import com.qf.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodService goodService;

    @RequestMapping(value = "/addGoods")
    @ResponseBody
    public ResultEntity addGoods(@RequestBody Goods goods) throws Exception {
        boolean addGoodsFlag = goodService.addGoods(goods);
        return ResultEntity.response(addGoodsFlag);
    }

    @RequestMapping(value = "/getGoodsPage")
    @ResponseBody
    public ResultEntity getGoodsPage(@RequestBody Page<Goods> page) throws Exception {
        return ResultEntity.response(goodService.getGoodsPage(page.getCurrent(), page.getSize()));
    }

    @RequestMapping(value = "/deleteGoodsById/{id}")
    @ResponseBody
    public ResultEntity deleteGoodsById(@PathVariable Integer id) throws Exception {
        return ResultEntity.response(goodService.deleteGoodsById(id));
    }

    // 1.按照关键字搜索--》keyword
    // 2.按照那个属性搜索，可以多个字段--》searchField
    // 3、关键字高亮 -->gname
    // 4.排序字段，阿皮规则--》orderFiled,orderRule
    @RequestMapping("/searchGoods")
    public String searchGoods(@RequestParam Map<String, String> map, Model model) throws Exception{

        // 1.准备要查询的数据
        String keyword = null; // 手机
        String[] searchField  = "gname,gdesc".split(","); // 可能会有多个
        String highlighter = "gname";
        String orderFiled = null; // 没有给排序规则就按照默认值的
        String orderRule = null;

        // 2.获取传递的覆盖默认值
        if(!StringUtils.isEmpty(map.get("keyword"))){
            keyword = map.get("keyword");
        }

        if(!StringUtils.isEmpty(map.get("orderFiled"))){
            orderFiled = map.get("orderFiled");
        }

        if(!StringUtils.isEmpty(map.get("orderRule"))){
            orderRule = map.get("orderRule");
        }

        // 3.查询es
        List<Goods> goodsList = goodService.searchGoods(keyword,searchField,highlighter,orderFiled,orderRule);

        model.addAttribute("goodsList",goodsList);

        return "searchGoodsList";
    }


}
