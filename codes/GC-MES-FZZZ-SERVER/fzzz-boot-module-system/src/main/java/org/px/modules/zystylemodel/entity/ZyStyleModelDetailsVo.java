package org.px.modules.zystylemodel.entity;

import lombok.Data;
import org.px.modules.zyclothsstyle.entity.ZyClothsStyle;

import java.util.List;
@Data
public class ZyStyleModelDetailsVo {
    /**服装款式*/
    private List<ZyClothsStyle> zyClothsStyles;
    /**子模块 款式型号 */
    private List<ZyStyleModel> zyStyleModels;
}
