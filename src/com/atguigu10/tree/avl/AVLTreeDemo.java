package com.atguigu10.tree.avl;

public class AVLTreeDemo{
    public static void main(String[] args) {
        //测试左旋转
//        int[] arr = {4,3,6,5,7,8};
        //测试右旋转
//        int[] arr ={10,12,8,9,7,6};
        //测试双旋转
        int[] arr ={10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < arr.length; i++) {
            avlTree.addNode(new Node(arr[i]));
        }
        avlTree.middleOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
    }
}

/**
 *  AVL 树是在二叉排序树的基础上进行优化，使其不会变的跟单链表一样一条线串起来
 *    即在创建树的时候进行旋转
 */
class AVLTree{
    private Node root ;

    public Node getRoot() {
        return root;
    }


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


    //右旋转
    private void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.right=this.right;
        newNode.left=this.left.right;
        this.value=this.left.value;
        this.left=this.left.left;
        this.right=newNode;
    }
    //左旋转
    private void leftRotate(){
        //创建一个newNode新节点，值是当前节点的值
        Node newNode =  new Node(this.value);
        //newNode的左子树指向原节点的左子树
        newNode.left = this.left ;
        //newNode的右子树指向原节点的右子树的左子树
        newNode.right = this.right.left ;
        //将原节点的值替换为原节点的右子树的值
        this.value = this.right.value;
        //将替换后的原节点的右树指向原右子树的右子树
        this.right=this.right.right;
        //将替换后的节点的左子树指向newNode
        this.left = newNode;
    }

    //计算当前节点为根节点的左子树的高度
    public int leftHeight(){
        if (left==null){
            return 0;
        }else {
            return left.height();
        }
    }
    //计算当前节点为根节点的右子树的高度
    public int  rightHeight(){
        if (right == null){
            return 0;
        }else {
            return right.height();
        }
    }
    /**
     *  返回以当前节点为根节点的树的高度
     */
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }


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

        //什么时候需要平衡二叉树
        if ((this.rightHeight()-this.leftHeight())>1){
            //双旋转
            if(this.right != null && this.right.leftHeight()>this.right.rightHeight()){
                this.right.rightRotate();
                this.leftRotate();;
            }{
                this.leftRotate();
            }
            return;
        }

        if ((this.leftHeight() - this.rightHeight())>1){
            if (this.left !=null && this.left.leftHeight() < this.left.rightHeight()){
                this.left.leftRotate();
                this.rightRotate();
            }else {
                this.rightRotate();
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
