package org.px.modules.demo.sensorrecord.service.impl;


import org.px.modules.demo.sensorrecord.entity.SensorRecord;
import org.px.modules.demo.sensorrecord.mapper.SensorRecordMapper;
import org.px.modules.demo.sensorrecord.service.ISensorRecordService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 传感记录表
 * @@author 品讯科技
 * @Date:   2023-02-10
 * @Version: V1.0
 */
@Service
public class SensorRecordServiceImpl extends ServiceImpl<SensorRecordMapper, SensorRecord> implements ISensorRecordService {

}
