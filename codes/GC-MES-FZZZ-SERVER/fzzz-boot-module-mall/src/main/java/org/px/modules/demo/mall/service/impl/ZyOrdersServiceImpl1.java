package org.px.modules.demo.mall.service.impl;

import org.px.modules.demo.mall.entity.ZyOrders;
import org.px.modules.demo.mall.entity.ZyGoods;
import org.px.modules.demo.mall.mapper.ZyGoodsMapper1;
import org.px.modules.demo.mall.mapper.ZyOrdersMapper1;
import org.px.modules.demo.mall.service.IZyOrdersService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: zy_orders
 * @@author 品讯科技
 * @Date:   2021-11-11
 * @Version: V1.0
 */
@Service
public class ZyOrdersServiceImpl1 extends ServiceImpl<ZyOrdersMapper1, ZyOrders> implements IZyOrdersService {

	@Autowired
	private ZyOrdersMapper1 zyOrdersMapper1;
	@Autowired
	private ZyGoodsMapper1 zyGoodsMapper1;

	@Override
	@Transactional
	public void saveMain(ZyOrders zyOrders, List<ZyGoods> zyGoodsList) {
		zyOrdersMapper1.insert(zyOrders);
		if(zyGoodsList!=null && zyGoodsList.size()>0) {
			for(ZyGoods entity:zyGoodsList) {
				//外键设置
				entity.setOrdersId(zyOrders.getId());
				zyGoodsMapper1.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ZyOrders zyOrders,List<ZyGoods> zyGoodsList) {
		zyOrdersMapper1.updateById(zyOrders);

		//1.先删除子表数据
		zyGoodsMapper1.deleteByMainId(zyOrders.getId());

		//2.子表数据重新插入
		if(zyGoodsList!=null && zyGoodsList.size()>0) {
			for(ZyGoods entity:zyGoodsList) {
				//外键设置
				entity.setOrdersId(zyOrders.getId());
				zyGoodsMapper1.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		zyGoodsMapper1.deleteByMainId(id);
		zyOrdersMapper1.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			zyGoodsMapper1.deleteByMainId(id.toString());
			zyOrdersMapper1.deleteById(id);
		}
	}

}
