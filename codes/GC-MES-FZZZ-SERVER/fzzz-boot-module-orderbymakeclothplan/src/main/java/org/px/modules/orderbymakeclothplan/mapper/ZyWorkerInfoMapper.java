package org.px.modules.orderbymakeclothplan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.px.modules.orderbymakeclothplan.pojo.ZyWorkerInfo;
import org.px.modules.orderbymakeclothplan.vo.WorkerInfoVo;

import java.util.List;

/**
 * @author 品讯科技
 * @date 2024-08
 */
public interface ZyWorkerInfoMapper extends BaseMapper<ZyWorkerInfo> {
    List<WorkerInfoVo> getWorkerInfo();
}
