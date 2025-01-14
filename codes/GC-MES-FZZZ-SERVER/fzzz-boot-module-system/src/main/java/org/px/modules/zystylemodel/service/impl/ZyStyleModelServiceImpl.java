package org.px.modules.zystylemodel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.math.NumberUtils;
import org.px.modules.zyclothsstyle.entity.vo.AllClothStyle;
import org.px.modules.zyclothsstyle.service.IZyClothsStyleService;
import org.px.modules.zystylefabric.mapper.ZyStyleFabricMapper;
import org.px.modules.zystylemodel.entity.ZyStyleModel;
import org.px.modules.zystylemodel.entity.ZyStyleModelDetailsVo;
import org.px.modules.zystylemodel.mapper.ZyStyleModelMapper;
import org.px.modules.zystylemodel.service.IZyStyleModelService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;


/**
 * @Description: zy_style_model
 * @@author 品讯科技
 * @Date: 2022-09-17
 * @Version: V1.0
 */
@Service
public class ZyStyleModelServiceImpl extends ServiceImpl<ZyStyleModelMapper, ZyStyleModel> implements IZyStyleModelService {

    @Autowired
    private IZyClothsStyleService zyClothsStyleService;

    @Autowired
    private ZyStyleModelMapper zyStyleModelMapper;
    @Autowired
    private ZyStyleFabricMapper zyStyleFabricMapper;

    @Override
    public List<ZyStyleModel> detail(String id) {
        List<ZyStyleModel> zyStyleModels = zyStyleModelMapper.listByStyleId(id);
        for (ZyStyleModel zy : zyStyleModels) {
            String styleName = zyStyleFabricMapper.getStyleNameByStyleId(zy.getStyleId());
            if (StringUtils.hasText(styleName)) {
                zy.setStyleId(styleName);
            } else {
                zy.setStyleId(null);
            }
            //是否默认尺码
            Integer isDefault = zy.getIsdefault();
            if (isDefault == 0) {
                zy.setIsDefaultSize("否");
            } else if (isDefault == 1) {
                zy.setIsDefaultSize("是");
            } else {
                zy.setIsDefaultSize(null);
            }
            //型号编码
            if (StringUtils.hasText(zy.getModelNumber())){
                zy.setModelNumber(handleModelNumber(zy.getModelNumber()));
            }
            //码数
            if (!ObjectUtils.isEmpty(zy.getSize())){
                zy.setSize(zy.getSize()+36);
            }
            //型
            if (!ObjectUtils.isEmpty(zy.getAnumbers())){
                zy.setAnumbers(aHandleNumberA(zy.getAnumbers()));
            }
            //号
            if (!ObjectUtils.isEmpty(zy.getBnumbers())){
                zy.setBnumbers(bHandleNumberB(zy.getBnumbers()));
            }

        }
        return zyStyleModels;
    }

    public String handleModelNumber(String modelNumber) {
        if (NumberUtils.isNumber(modelNumber)){
            if (modelNumber.equals("001")){
                modelNumber = "S";
            }
            if (modelNumber.equals("002")){
                modelNumber = "M";
            }
            if (modelNumber.equals("003")){
                modelNumber = "L";
            }
            if (modelNumber.equals("004")){
                modelNumber = "XL";
            }
            if (modelNumber.equals("005")){
                modelNumber = "2XL";
            }
            if (modelNumber.equals("006")){
                modelNumber = "3XL";
            }
            if (modelNumber.equals("007")){
                modelNumber = "4XL";
            }
        }
        return modelNumber;
    }

    @Override
    public ZyStyleModelDetailsVo queryDetails(String id) {
        ZyStyleModel model = getById(id);
        AllClothStyle detail = zyClothsStyleService.queryDetail(model.getStyleId());
        ZyStyleModelDetailsVo detailsVo = new ZyStyleModelDetailsVo();
        detailsVo.setZyClothsStyles(detail.getZyClothsStyles());
        detailsVo.setZyStyleModels(detail.getZyStyleModels());
        return detailsVo;
    }

    @Override
    public boolean dataTypeProblem(@NotNull ZyStyleModel zyStyleModel) {
//        Pattern pattern = Pattern.compile("/[^\\d]/g");
        //Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        Pattern pattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        //  /^\d+(\.{0,1}\d+){0,1}$/
        //正则表达式判断是否是数字字符串（可判断正数，负数和小数）
        if (!ObjectUtils.isEmpty(zyStyleModel.getCollarLarge())){
            return pattern.matcher(zyStyleModel.getCollarLarge().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getBust())){
            return pattern.matcher(zyStyleModel.getBust().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getSleeveLength())){
            return pattern.matcher(zyStyleModel.getSleeveLength().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getShslLength())){
            return pattern.matcher(zyStyleModel.getShslLength().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getTsWidth())){
            return pattern.matcher(zyStyleModel.getTsWidth().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getCuff())){
            return pattern.matcher(zyStyleModel.getCuff().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getWaistline())){
            return pattern.matcher(zyStyleModel.getWaistline().toString()).matches();
        }
        if (!ObjectUtils.isEmpty(zyStyleModel.getHem())){
            return pattern.matcher(zyStyleModel.getHem().toString()).matches();
        }
        return true;
    }


    @Contract(pure = true)
    private String aHandleNumberA(@NotNull String num){
        switch (num) {
            case "1":
                return "80A";
            case "2":
                return "84A";
            case "3":
                return "88A";
            case "4":
                return "92A";
            case "5":
                return "96A";
            case "6":
                return "100A";
            case "7":
                return "104A";
            case "8":
                return "108A";
            case "9":
                return "112A";
        }
        return num;
    }

    /**
     * @ Contract(pure = true) 声明这个方法为纯函数
     *
     */
    @Contract(pure = true)
    private String bHandleNumberB(@NotNull String num){
        switch (num) {
            case "1":
                return "160";
            case "2":
                return "165";
            case "3":
                return "170";
            case "4":
                return "175";
            case "5":
                return "180";
            case "6":
                return "185";
        }
        return num;
    }
}
