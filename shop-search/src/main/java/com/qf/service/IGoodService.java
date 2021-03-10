package com.qf.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;

import java.io.IOException;
import java.util.List;

public interface IGoodService {

    /**
     * 添加es中
     * @param goods 添加的商品信息
     * @return
     */
    public boolean addGoods(Goods goods) throws  Exception;

    /**
     * 从es中查询
     * @param current 当前页
     * @param size 每页显示的条数
     * @return 分页对象
     */
    public Page<Goods> getGoodsPage(Integer current, Integer size) throws  Exception;

    /**
     * 根据id从es中删除商品
     * @param id 被删除商品的id
     * @return
     */
    public boolean deleteGoodsById(Integer id) throws  Exception;

    /**
     * 按照条件查询
     * @param keyword 关键字
     * @param searchField 搜索的字段
     * @param highlighter 高亮的字段
     * @param orderFiled 排序的字段
     * @param orderRule 排序规则
     * @return
     */
    List<Goods> searchGoods(String keyword, String[] searchField, String highlighter, String orderFiled, String orderRule) throws Exception;
}
