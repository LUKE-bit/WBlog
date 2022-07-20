package com.wang.myblog.dto;

import lombok.Data;

/**
 * 返回BootstrapTable的数据
 */
@Data
public class TableList {
    private Integer total;
    private Object rows;
}
