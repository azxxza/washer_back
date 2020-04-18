package com.seawaterbt.ssm.core.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Cascader {

    private String pId;
    private String value;
    private String label;
    private List<Cascader> children;

}
