package org.px.modules.shop.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.px.modules.shop.pojo.ZyShopShopRole;

import java.util.List;

/**
 * @Description: 店铺与店铺角色中间表
 * @@author 品讯科技
 * @Date:   2021-08-27
 * @Version: V1.0
 */
public interface IZyShopShopRoleService extends IService<ZyShopShopRole> {

	public List<ZyShopShopRole> selectByMainId(String mainId);
}
