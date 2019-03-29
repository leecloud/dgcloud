package com.lee.cloudadmin.utils;

import com.lee.cloudadmin.domain.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTree {
    public static<T> Tree<T> build(List<Tree<T>> nodes){
        if (nodes == null){
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        for (Tree<T> child : nodes){
            if (child.getChildren() == null || "".equals(child.getChildren())){
                topNodes.add(child);
                continue;
            }
            for (Tree<T> parent : nodes){
                if (parent.getId() != null && parent.getId().equals(child.getChildren())){
                    parent.getChildren().add(child);
                    child.setHasparent(true);
                    parent.setHaschildren(true);
                    continue;
                }
            }
        }
        Tree<T> root = new Tree<T>();
        if (topNodes.size() == 1){
            root = topNodes.get(0);
        }else {
            root.setId("-1");
            root.setParentId("");
            root.setHasparent(false);
            root.setChildren(topNodes);
            root.setChecked(true);
            root.setHaschildren(true);
            root.setText("顶级节点");
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened",true);
        }
        return root;
    }
    public static<T> List<Tree<T>> bulidList(List<Tree<T>> nodes, String idParam){
        if (nodes == null){
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        for (Tree<T> child : nodes){
            if (child.getParentId() == null || idParam.equals(child.getParentId())){
                topNodes.add(child);
                continue;
            }
            for (Tree<T> parent : nodes){
                if (parent.getId() != null && parent.getId().equals(child.getParentId())){
                    parent.getChildren().add(child);
                    child.setHaschildren(true);
                    child.setHasparent(true);
                    continue;
                }
            }
        }
        return topNodes;
    }
}
