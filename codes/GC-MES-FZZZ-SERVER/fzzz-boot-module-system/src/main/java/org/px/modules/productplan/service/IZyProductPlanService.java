package org.px.modules.productplan.service;

import org.px.modules.productplan.entity.ZyProductPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import org.px.modules.productplan.entity.vo.PaiWeiTuVo;

/**
 * @Description: 生产计划
 * @@author 品讯科技
 * @Date:   2022-12-12
 * @Version: V1.0
 */
public interface IZyProductPlanService extends IService<ZyProductPlan> {

    PaiWeiTuVo paiweitu(ZyProductPlan zyProductPlan);

    void create(ZyProductPlan zyProductPlan);
}
