package org.px.modules.person.service.Impl;

import org.px.modules.person.mapper.ZyGetMachineMapper;
import org.px.modules.person.service.ZyGetMachineService;
import org.px.modules.person.vo.GetMachine;
import org.px.modules.person.vo.GetOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : OYYK
 * @date :  2021/10/22
 * public class ZyZyGetMachineServiceImpl extends ServiceImpl<ZyUserMapper, SysUser> implements IZyUserService {
 */
@Service
public class ZyZyGetMachineServiceImpl implements ZyGetMachineService {
    @Autowired
    private ZyGetMachineMapper zyGetMachineMapper;

    @Override
    public List<GetMachine> getAllMachine() {
        return zyGetMachineMapper.getAllMachine();
    }

    @Override
    public String getMachineName(String id) {
        return zyGetMachineMapper.getMachineName(id);
    }

    @Override
    public List<GetOrder> getAllorder() {
        return zyGetMachineMapper.getAllorder();
    }


}
