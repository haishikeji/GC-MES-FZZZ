package org.px.modules.orderbymakeclothplan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.px.modules.orderbymakeclothplan.pojo.ZyMakeOrder;

/**
 * @author 品讯科技
 */
@Mapper
public interface ZyMakeOrderMapper extends BaseMapper<ZyMakeOrder> {
    int updateAudit(ZyMakeOrder zyMakeOrder);

    ZyMakeOrder findById(String id);

    Integer getOrderTypeOnToday(int statu);
    Integer getOrderTypeOnTodayTotal();
}
