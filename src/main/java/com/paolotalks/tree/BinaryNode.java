package com.paolotalks.tree;

public class BinaryNode {

    private BinaryNode left;
    private BinaryNode right;
    private final int data;

    public BinaryNode(int data){
        this.data = data;
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
