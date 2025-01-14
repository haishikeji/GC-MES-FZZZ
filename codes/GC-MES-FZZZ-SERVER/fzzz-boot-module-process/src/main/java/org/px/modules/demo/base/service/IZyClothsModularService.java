package org.px.modules.demo.base.service;

import org.px.modules.demo.base.entity.ZyClothsModular;
import com.baomidou.mybatisplus.extension.service.IService;
import org.px.modules.demo.base.entity.vo.ZyClothsModularDetail;

import java.util.List;

/**
 * @Description: zy_cloths_modular
 * @@author 品讯科技
 * @Date:   2021-11-10
 * @Version: V1.0
 */
public interface IZyClothsModularService extends IService<ZyClothsModular> {
    public List<ZyClothsModular> selectByType(String cloths_type_id);

    /**nums，编号，varchar，18，非空，不重复，编码规则：服装类型（10）+序号（8）*/
    String generateNumber(String id);

    /**
     * 制衣模块详情
     * @param id 根据服装款式id进行查询
     */
    ZyClothsModularDetail queryDetail(String id);
}
