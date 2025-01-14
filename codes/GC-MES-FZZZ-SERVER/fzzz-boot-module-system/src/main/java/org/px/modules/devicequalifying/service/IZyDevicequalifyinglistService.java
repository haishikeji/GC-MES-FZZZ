package org.px.modules.devicequalifying.service;

import org.px.modules.devicequalifying.entity.ZyDevicequalifyinglist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * @Description: 工艺排位
 * @@author 品讯科技
 * @Date:   2021-11-17
 * @Version: V1.0
 */
public interface IZyDevicequalifyinglistService extends IService<ZyDevicequalifyinglist> {
    public List<ZyDevicequalifyinglist> selectByType(String type);

}
