package com.seawaterbt.ssm.core.common.model;

import com.seawaterbt.ssm.module.system.entity.SysPerm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Node{

    private Integer id;

    private String name;

    private String title;

    private Integer isMenu;

    private Integer parentId;

    private String icon;

    private String url;

    private Integer num;

    private boolean expand;

    private boolean checked;

    private boolean disabled;

    private boolean disableCheckbox;

    private boolean selected;

    private List<Node> children;

    public Node(Map<String, Object> perm) {
        this.setId((Integer) perm.get("id"));
        this.setExpand(true);
        this.setIsMenu((Integer) perm.getOrDefault(SysPerm.IS_MENU, 0));
        this.setIcon((String) perm.getOrDefault(SysPerm.ICON, ""));
        this.setName((String) perm.getOrDefault(SysPerm.NAME, ""));
        this.setTitle((String) perm.getOrDefault(SysPerm.TITLE, ""));
        this.setNum((Integer) perm.getOrDefault(SysPerm.NUM, 0));
        this.setUrl((String) perm.getOrDefault(SysPerm.URL, ""));
        this.setParentId((Integer) perm.getOrDefault(SysPerm.PARENT_ID, 0));
    }
}
