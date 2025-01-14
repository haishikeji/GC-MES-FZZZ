package org.px.modules.orderbymakeclothplan.vo;

import java.io.Serializable;

/**
 * @author 品讯科技
 * @date 2024-08
 */
public class TodayOrderAndQIriVo implements Serializable {

    private Integer todayNum;
    private Integer weekTotal;

    public Integer getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(Integer todayNum) {
        this.todayNum = todayNum;
    }

    public Integer getWeekTotal() {
        return weekTotal;
    }

    public void setWeekTotal(Integer weekTotal) {
        this.weekTotal = weekTotal;
    }
}
