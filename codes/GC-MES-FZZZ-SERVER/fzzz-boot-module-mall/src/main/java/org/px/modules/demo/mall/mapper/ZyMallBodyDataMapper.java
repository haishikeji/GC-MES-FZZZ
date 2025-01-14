package org.px.modules.demo.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.px.modules.demo.mall.entity.ZyMallBodyData;

import java.util.List;

/**
 * @Description: 测体数据表
 * @@author 品讯科技
 * @Date:   2021-10-21
 * @Version: V1.0
 */
public interface ZyMallBodyDataMapper extends BaseMapper<ZyMallBodyData> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<ZyMallBodyData> selectByMainId(@Param("mainId") String mainId);

}
