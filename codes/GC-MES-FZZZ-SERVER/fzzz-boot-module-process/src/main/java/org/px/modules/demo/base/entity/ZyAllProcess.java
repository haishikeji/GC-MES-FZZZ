package org.px.modules.demo.base.entity;

import lombok.Data;
import org.px.modules.demo.accessories.entity.ZyAccessories;
import org.px.modules.demo.device.entity.ZyDevicetypel;
import org.px.modules.demo.fabric.entity.ZyFabric;
import org.px.modules.demo.tools.entity.ZyToolTypes;

import java.util.List;

@Data
public class ZyAllProcess {
    private ZyProcess zyProcess;
    private List<ZyClothAction> zyClothActions;//基础动作
    private List<ZyAccessories> zyAccessories;//辅料管理
    private List<ZyFabric> zyFabrics;//面料管理
    private  List<ZyDevicetypel> zyDevicetypes;//设备管理
    private List<ZyToolTypes> zyToolTypes; //工具管理
}
