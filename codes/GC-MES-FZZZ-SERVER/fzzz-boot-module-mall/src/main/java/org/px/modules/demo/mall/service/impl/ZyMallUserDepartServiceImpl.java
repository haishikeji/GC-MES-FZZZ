package org.px.modules.demo.mall.service.impl;

import org.px.modules.demo.mall.entity.ZyMallUserDepart;
import org.px.modules.demo.mall.mapper.ZyMallUserDepartMapper;
import org.px.modules.demo.mall.service.IZyMallUserDepartService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 部门用户中间表
 * @@author 品讯科技
 * @Date:   2021-10-21
 * @Version: V1.0
 */
@Service
public class ZyMallUserDepartServiceImpl extends ServiceImpl<ZyMallUserDepartMapper, ZyMallUserDepart> implements IZyMallUserDepartService {
	
	@Autowired
	private ZyMallUserDepartMapper zyMallUserDepartMapper;
	
	@Override
	public List<ZyMallUserDepart> selectByMainId(String mainId) {
		return zyMallUserDepartMapper.selectByMainId(mainId);
	}
}
