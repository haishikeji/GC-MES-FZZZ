package org.px.modules.shop.vo;
import lombok.Data;

import java.util.Date;

@Data
public class ZyOrderPayAndTime {

    private Integer totalMoney;

    private Date payTime;
}
