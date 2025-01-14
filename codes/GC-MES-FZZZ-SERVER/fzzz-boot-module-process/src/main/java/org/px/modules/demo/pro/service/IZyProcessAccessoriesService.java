package org.px.modules.demo.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.px.modules.demo.accessories.entity.ZyAccessories;
import org.px.modules.demo.pro.entity.ZyProcessAccessories;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 面料表
 * @@author 品讯科技
 * @Date:   2022-08-18
 * @Version: V1.0
 */
public interface IZyProcessAccessoriesService extends IService<ZyProcessAccessories> {

    IPage<ZyProcessAccessories> page(Page<ZyProcessAccessories> page, String s, String t);

    IPage<ZyProcessAccessories> page1(Page<ZyProcessAccessories> page, String s, String t);

    List<ZyAccessories> selectByid(String id);
}
