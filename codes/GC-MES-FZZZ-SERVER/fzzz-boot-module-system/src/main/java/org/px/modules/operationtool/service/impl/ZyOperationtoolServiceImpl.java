package org.px.modules.operationtool.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.px.modules.operationtool.entity.ZyOperationtool;
import org.px.modules.operationtool.mapper.ZyOperationtoolMapper;
import org.px.modules.operationtool.service.IZyOperationtoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 工序工具
 * @@author 品讯科技
 * @Date:   2021-12-08
 * @Version: V1.0
 */
@Service
public class ZyOperationtoolServiceImpl extends ServiceImpl<ZyOperationtoolMapper, ZyOperationtool> implements IZyOperationtoolService {

    @Autowired
    ZyOperationtoolMapper zyOperationtoolMapper;
    @Override
    public IPage<ZyOperationtool> page(Page<ZyOperationtool> page, String s, String t) {
        return zyOperationtoolMapper.page(page,s,t);
    }

    @Override
    public IPage<ZyOperationtool> page1(Page<ZyOperationtool> page, String s, String t) {
        return zyOperationtoolMapper.page1(page,s,t);
    }
}
