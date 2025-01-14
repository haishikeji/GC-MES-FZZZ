package org.px.modules.demo.mall.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.px.modules.demo.mall.entity.ZyMallStandardSize;

/**
 * @Description: 标准尺码表
 * @@author 品讯科技
 * @Date:   2021-10-22
 * @Version: V1.0
 */
public interface IZyMallStandardSizeService extends IService<ZyMallStandardSize> {

    IPage<ZyMallStandardSize> pageSize(IPage<ZyMallStandardSize> page, @Param(Constants.WRAPPER) Wrapper<ZyMallStandardSize> wrapper);

}
