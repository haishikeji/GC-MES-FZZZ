package org.px.modules.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.px.modules.shop.pojo.ZyShopShopRole;

import java.util.List;

/**
 * @Description: 店铺与店铺角色中间表
 * @@author 品讯科技
 * @Date:   2021-07-15
 * @Version: V1.0
 */
@Mapper
public interface ZyShopShopRoleMapper extends BaseMapper<ZyShopShopRole> {
    public boolean deleteByMainId(@Param("mainId") String mainId);

    public List<ZyShopShopRole> selectByMainId(@Param("mainId") String mainId);
}
