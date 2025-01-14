package org.px.modules.demo.zyorders.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderSource {
    private Integer sourceType;
    List<SourceType> ls;
}
