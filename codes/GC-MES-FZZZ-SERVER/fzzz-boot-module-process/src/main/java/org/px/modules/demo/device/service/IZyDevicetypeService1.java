package org.px.modules.demo.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.px.modules.demo.device.entity.ZyDevicetypel;

import java.util.List;

/**
 * @Description: 设备类型
 * @@author 品讯科技
 * @Date:   2021-11-11
 * @Version: V1.0
 */
public interface IZyDevicetypeService1 extends IService<ZyDevicetypel> {




    List<ZyDevicetypel> selectByid(String id);
}
