package com.paolotalks.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNode implements Iterable<TreeNode> {

    private final List<TreeNode> children;
    private final int data;

    public TreeNode(int data){
        this.data = data;
        this.children = new ArrayList<>();
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode node){
        this.children.add(node);
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return children.iterator();
    }
}
