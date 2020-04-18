package com.seawaterbt.ssm.core.common.excel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 行对象
 *
 * @author azx
 */
@Getter
@Setter
public class Line {
    // 一行每列集合
    private Map<String, String> columns;
}
