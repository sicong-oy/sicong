package com.qf.shopback;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.Goods;
import com.qf.entity.ResultEntity;
import com.qf.feign.api.IGoodsService;
import com.qf.feign.api.ISearchGoodsService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ShopBackApplicationTests {
	@Autowired
	private IGoodsService goodsService;

	@Autowired
	private ISearchGoodsService searchGoodsService;

	@Test
	void contextLoads() throws  Exception{
		Page<Goods> page = new Page<>();

		// 1.调用goodsServier
		ResultEntity resultEntity = goodsService.getGoodsPage(page);

		// 2.返回是map
		Map<String,Object> map = (Map<String,Object>)resultEntity.getData();

		// 3.获取的所有的商品信息，还是一个map
		List<Map<String,Object>> list = (List) map.get("records");
		for (Map<String, Object> stringObjectMap : list) {
			Goods goods = new Goods();
			// 把Map拷贝goods
			org.apache.commons.beanutils.BeanUtils.populate(goods,stringObjectMap);
			System.out.println(goods);

			searchGoodsService.addGoods(goods);
		}

	}


}
