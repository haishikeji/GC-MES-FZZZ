package org.px.modules.demo.warehousing.service.impl;

import org.px.modules.demo.warehousing.entity.Warehousing;
import org.px.modules.demo.warehousing.mapper.WarehousingMapper;
import org.px.modules.demo.warehousing.service.IWarehousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 入库表
 * @@author 品讯科技
 * @Date:   2023-01-10
 * @Version: V1.0
 */
@Service
public class WarehousingServiceImpl extends ServiceImpl<WarehousingMapper, Warehousing> implements IWarehousingService {
    @Autowired
    WarehousingMapper warehousingMapper;

    @Override
    public Double kucunsl(String needcode, String lxid) {
        return warehousingMapper.kucunsl(needcode,lxid);
    }

    @Override
    public void dokucun(String needcode, String lxid, Double sl, Double slcha) {
        warehousingMapper.dokucun(needcode,lxid,slcha);
    }

    @Override
    public Double yssj(String needcode, String lxid) {
        return warehousingMapper.yssj(needcode,lxid);
    }
}
