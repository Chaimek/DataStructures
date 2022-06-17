package com.atguigu10.tree.binarysorttree;

public class    BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i <arr.length ; i++) {
            binarySortTree.addNode(new Node(arr[i]));
        }
        binarySortTree.middleOrder();
        System.out.println("-----------------------------");
        binarySortTree.delNode(new Node(7));
        binarySortTree.middleOrder();
    }
}
class BinarySortTree{
    private Node root ;

    public int delMinNode(Node node){
        Node target = node;
        while (target.getLeft() != null){
            target=target.getLeft() ;
        }
        delNode(target);
        return target.getValue();
    }

    /**
     * 对于searchParent()方法来说，对于根节点来说，没有父节点 ，会出现空指针异常：
     *     （1） 假如根节点是叶子节点：
     *              直接把 root = null ;
     *      (2) 假如根节点有两个子节点：
     *              调用的是 delMinNode(targetNode.getRight());
     *              这个方法里面也有delNode方法，但是里面的target一定有父节点，所有调用不会出现空指针异常
     *      (3) 假如根节点只有一个叶子节点：
     *              里面的代码都必须判断 parentNode ！= null才执行
     *              如果parentNode == null 说明是根节点 ：此时执行 root = targetNode.getLeft()  ===》 这样就把根节点删除了
     *
     * @param node
     */
    public void delNode(Node node){
        Node target = node ;
        if (root==null){
            return;
        }
        Node targetNode = searchTarget(target.getValue());
        if (targetNode == null){
            return;
        }
        if (root.getLeft() == null  && root.getRight() ==null){
            root=null ;
            return;
        }

        Node parentNode= searchParent(targetNode.getValue());


        if (targetNode.getLeft() == null && targetNode.getRight() == null){
            if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == targetNode.getValue() ){
                parentNode.setLeft(null);
            }else  if (parentNode.getRight() != null && parentNode.getRight().getValue() == targetNode.getValue()){
                parentNode.setRight(null);
            }
        }else if (targetNode.getLeft() != null && targetNode.getRight() != null){
            int i = delMinNode(targetNode.getRight());
            /**
             * 这里的这个做法真的很牛逼 ！
             */
            targetNode.setValue(i);
        }else {
            if (targetNode.getLeft() != null){
                if (parentNode != null){
                    if (parentNode.getLeft().getValue() == targetNode.getValue()){
                        parentNode.setLeft(targetNode.getLeft());
                    }else {
                        parentNode.setRight(targetNode.getLeft());
                    }
                }else {
                    root = targetNode.getLeft();
                }

            }else {
                if(parentNode != null){
                    if (parentNode.getLeft().getValue() == targetNode.getValue()){
                        parentNode.setLeft(targetNode.getRight());
                    }else {
                        parentNode.setRight(targetNode.getRight());
                    }
                }else {
                    root = targetNode.getRight();
                }

            }
        }

    }

    /**
     *  找到当前节点
     * @param value
     * @return
     */
    public Node searchTarget(int value){
        if (root != null){
           return  root.searchTarget(value);
        }else {
            return null;
        }
    }

    /**
     * 找到当前节点的父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if (root != null){
            return root.searchParent(value);
        }else {
            return null ;
        }
    }
    public void middleOrder(){
        if (root != null){
            root.middleOrder();
        }else {
            System.out.println("当前树为空，无法遍历");
        }
    }

    public void addNode(Node node){
        if (root == null ){
            root = node ;
        }else {
            root.addNode(node);
        }
    }
}
class Node{
    private int value ;
    private Node left ;
    private Node right ;



    /**
     * 查找要删除节点的前一个节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) || (this.right!=null && this.right.value ==value)){
            return this;
        }else {
            if (this.left != null && this.value > value){
                return this.left.searchParent(value);
            }else if (this.right != null && this.value <= value){
                return this.right.searchParent(value) ;
            }else {
                return null;
            }
        }
    }
    /*
    public Node searchTarget1(int value){
        if (this.value == value){
            return this ;
        }else if (this.value > value ){
            if (this.left != null){
                return this.left.searchTarget1(value);
            }else {
                return null ;
            }
        }else {
            if (this.right != null){
                return this.right.searchTarget1(value);
            }else {
                return null ;
            }
        }
    }

     */

    /**
     *  查找当前节点
     * @param value 要查找节点的值
     * @return
     */
    public Node searchTarget(int value){
        if (this.value == value){
            return this ;
        }else if (this.value > value & this.left != null){
           return this.left.searchTarget(value);
        }else if (this.value < value & this.right != null){
          return  this.right.searchTarget(value);
        }else {
            return null ;
        }
    }

    public  void  middleOrder(){
        if (this.left != null){
            this.left.middleOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.middleOrder();
        }
    }
    public void  addNode(Node node){
        if (node == null){
            return;
        }else {
            if (this.value > node.value){
                if (this.left == null){
                    this.left = node ;
                }else {
                    this.left.addNode(node);
                }
            }else {
                if (this.right == null){
                    this.right = node ;
                }else {
                    this.right.addNode(node);
                }
            }
        }
    }
    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


}
