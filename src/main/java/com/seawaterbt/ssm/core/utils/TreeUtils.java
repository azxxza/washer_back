package com.seawaterbt.ssm.core.utils;


import com.seawaterbt.ssm.core.common.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtils {

    public static List<Node> tree(List<Map<String, Object>> permList, List<Integer> list) {
        List<Node> treeNodes = new ArrayList<>();
        List<Node> treeNodeList = new ArrayList<>();
        for (Map<String, Object> perm : permList) {
            Node treeNode = new Node(perm);
            if (list != null && list.size() > 0) {
                int isLeaf = Integer.parseInt(perm.get("isLeaf").toString());
                if (isLeaf == 1 && list.size() > 0) {
                    for (int permId : list) {
                        if (permId == treeNode.getId()) {
                            treeNode.setChecked(true);
                            break;
                        }
                    }
                }
            }
            treeNodes.add(treeNode);
        }
        for (Node treeNode : treeNodes) {
            if (treeNode.getParentId() == 0) {
                treeNodeList.add(treeNode);
            }
            for (Node node : treeNodes) {
                if (node.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.getChildren().add(node);
                }
            }
        }
        return treeNodeList;
    }
}
