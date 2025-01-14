package org.px.modules.orderbymakeclothplan.vo;

import java.io.Serializable;

/**
 * @author 品讯科技
 * @date 2024-08
 */
public class OrderProgressVo implements Serializable {

    private Integer totalNum;
    private Integer todayNum;
    private Integer orderType;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(Integer todayNum) {
        this.todayNum = todayNum;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
