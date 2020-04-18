package com.seawaterbt.ssm.core.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Tree {

    private String pId;
    private String id;
    private String title;
    private List<Tree> children;

}
