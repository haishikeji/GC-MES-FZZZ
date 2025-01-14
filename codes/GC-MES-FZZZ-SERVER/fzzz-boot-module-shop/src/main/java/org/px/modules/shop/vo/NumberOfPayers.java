package org.px.modules.shop.vo;

import lombok.Data;

@Data
public class NumberOfPayers {
    //店铺名称
    private String name;
    //已支付的人数
    private Integer NumberOfPayers;
    //未支付的人数
    private Integer UnpaidNumber;
    //下单率
    private String PayoutRate;
}
