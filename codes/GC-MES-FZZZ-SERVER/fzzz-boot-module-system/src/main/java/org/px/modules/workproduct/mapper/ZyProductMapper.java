package org.px.modules.workproduct.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.px.modules.workproduct.entity.ZyProduct;
import org.px.modules.workproduct.entity.ZyProductVo;

/**
 * @Description: 产品表
 * @@author 品讯科技
 * @Date:   2023-01-07
 * @Version: V1.0
 */
public interface ZyProductMapper extends BaseMapper<ZyProduct> {

    //自定义分页查询
    IPage<ZyProduct> selectListPage(Page<ZyProduct> page, @Param("model")ZyProductVo model);
}
