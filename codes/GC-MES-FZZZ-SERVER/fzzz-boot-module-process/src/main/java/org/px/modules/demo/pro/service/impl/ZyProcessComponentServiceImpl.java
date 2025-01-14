package org.px.modules.demo.pro.service.impl;

import org.px.modules.demo.base.mapper.ZyClothsModularMapper;
import org.px.modules.demo.pro.entity.ZyProcessComponent;
import org.px.modules.demo.pro.mapper.ZyProcessComponentMapper;
import org.px.modules.demo.pro.service.IZyProcessComponentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: zy_process_component
 * @@author 品讯科技
 * @Date: 2021-11-11
 * @Version: V1.0
 */
@Service
public class ZyProcessComponentServiceImpl extends ServiceImpl<ZyProcessComponentMapper, ZyProcessComponent> implements IZyProcessComponentService {

    @Resource
    private ZyProcessComponentMapper zyProcessComponentMapper;
    @Resource
    private ZyClothsModularMapper zyClothsModularMapper;

    @Override
    public List<ZyProcessComponent> detail(String id) {
        List<ZyProcessComponent> zyProcessComponents = zyProcessComponentMapper.queryListByComponentId(id);
        if (ObjectUtils.isEmpty(zyProcessComponents)) {
            return zyProcessComponents;
        }
        for (ZyProcessComponent processComponent : zyProcessComponents) {
            String processName = zyClothsModularMapper.getProcessNameByProcessId(processComponent.getProcessId());
            if (StringUtils.hasText(processName)){
                processComponent.setProcessId(processName);
            }else{
                processComponent.setProcessId("null");
            }
            String componentName = zyProcessComponentMapper.getComponentNameByComponentId(processComponent.getComponentId());
            if (StringUtils.hasText(componentName)){
                processComponent.setComponentId(componentName);
            }else{
                processComponent.setComponentId("null");
            }
//            if (processComponent.getBottleneck().equals("1")){
//                processComponent.setBottleneck("是");
//            }else if (processComponent.getBottleneck().equals("2")){
//                processComponent.setBottleneck("否");
//            }else {
//                processComponent.setBottleneck(null);
//            }
        }
        return zyProcessComponents;
    }
}
