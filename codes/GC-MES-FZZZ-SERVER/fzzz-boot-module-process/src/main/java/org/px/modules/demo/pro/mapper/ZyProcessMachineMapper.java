package org.px.modules.demo.pro.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.px.modules.demo.pro.entity.ZyProcessMachine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: zy_process_machine
 * @@author 品讯科技
 * @Date:   2021-11-10
 * @Version: V1.0
 */
@Mapper
public interface ZyProcessMachineMapper extends BaseMapper<ZyProcessMachine> {

    @Select("<script>select * from zy_process_machine <where><if test=\" p != null\">process_id in (select id from zy_process where process_name like #{p})</if><if test=\" m != null\"> and machine_id in (select id from zy_devicetype where name like #{m})</if></where></script>")
    IPage<ZyProcessMachine> page(Page<ZyProcessMachine> page, @Param("p") String processId, @Param("m") String machineId);
    @Select("<script>select a.*,b.process_code,c.code  from zy_process_machine as a,zy_process as b,zy_device as c <where> a.process_id=b.id and a.machine_id=c.id and a.process_id=#{p} <if test=\" m != null\"> and machine_id in (select id from zy_device where name like #{m})</if></where></script>")
    IPage<ZyProcessMachine> page1(Page<ZyProcessMachine> page, @Param("p") String s, @Param("m") String t);
}
