package org.px.modules.shop.vo;

import lombok.Data;

import java.util.List;


@Data
public class OrderSales {
    //店铺名称
    private String name;
    //支付时间
    List<OrderSalesH> ls;
}
