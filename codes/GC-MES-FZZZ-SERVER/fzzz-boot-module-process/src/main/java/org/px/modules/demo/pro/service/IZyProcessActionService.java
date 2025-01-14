package org.px.modules.demo.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.px.modules.demo.base.entity.ZyClothAction;
import org.px.modules.demo.pro.entity.ZyProcessAction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: zy_process_action
 * @@author 品讯科技
 * @Date:   2021-11-03
 * @Version: V1.0
 */
public interface IZyProcessActionService extends IService<ZyProcessAction> {


    IPage<ZyProcessAction> page(Page<ZyProcessAction> page, String s, String t);

    IPage<ZyProcessAction> page1(Page<ZyProcessAction> page, String s, String t);

    List<ZyClothAction> selectByid(String id);

    void up(String id);

    int findSort(String id);

    void down(String id);
}
