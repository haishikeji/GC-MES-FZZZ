package org.px.modules.maker.service;

import org.px.modules.maker.entity.ZyMaker;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 制单管理
 * @@author 品讯科技
 * @Date:   2021-11-27
 * @Version: V1.0
 */
public interface IZyMakerService extends IService<ZyMaker> {

    //点击制单 点击后按产品模块分解成制单写入制单表
    int listMakerAdd();

    List<ZyMaker> ZyMakerVo(List<ZyMaker> records);
}
