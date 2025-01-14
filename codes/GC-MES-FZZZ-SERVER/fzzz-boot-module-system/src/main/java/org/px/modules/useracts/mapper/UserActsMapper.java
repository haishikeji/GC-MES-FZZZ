package org.px.modules.useracts.mapper;

import org.apache.ibatis.annotations.Param;
import org.px.modules.useracts.entity.UserActs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 员工绩效
 * @@author 品讯科技
 * @Date:   2021-10-30
 * @Version: V1.0
 */
public interface UserActsMapper extends BaseMapper<UserActs> {
    public UserActs sumActual(@Param("workshop")String workshop,@Param("team")String team,@Param("date_yield")String date_yield);
}
