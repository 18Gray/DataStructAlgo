package com.eighteengray.datastructlib.tree.BTree;


class Node{
    public Node left;
    public Node right;
    public int data;

    public Node(int data){
        this.data = data;
    }

    public void setLChild(Node left){
        this.left = left;
    }

    public void setRChild(Node right){
        this.right = right;
    }

    public void setData(int data){
        this.data = data;
    }

    public int getData(){
        return this.data;
    }
    public Node getLChild(){
        return this.left;
    }
    public Node getRChild(){
        return this.right;
    }
}

