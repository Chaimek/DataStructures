package com.atguigu10.tree.huffmantree;

import java.util.*;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);

    }
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("霍夫曼树为空，无法遍历");
        }
    }

    /**
     *  注意 ： 以下算法与霍夫曼树（最优二叉树）如何创建有关
     * @param arr  要转化为霍夫曼树的数组
     * @return   返回霍夫曼树的根节点
     */
    public static Node createHuffmanTree(int[] arr){
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            //将权值封装在node节点中，便于操作
            list.add(new Node(arr[i]));
        }
        while (list.size() > 1){
            //从低到高排序，每次取数据前都要排序
            Collections.sort(list);
            //取出最小的
            Node leftNode  = list.get(0);
            //取出第二小的
            Node rightNode = list.get(1);
            //创建他们的父节点
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            //让parent的子节点指向leftNode 和 rightNode
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            //把用过的权值删掉
            list.remove(leftNode);
            list.remove(rightNode);
            //把新创建的权值加入list中
            list.add(parent);
        }
        return list.get(0);
    }

}
class Node implements Comparable<Node>{
    private Integer value ;
    private Node left ;
    private Node right;

    public  void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }



    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.value  -  node.value;
    }
}
