package com.atguigu10.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode  root =  new HeroNode(1,"宋江");
        HeroNode  node1  =  new HeroNode(2,"吴用");
        HeroNode  node2  =  new HeroNode(3,"卢俊义");
        HeroNode  node3 =  new HeroNode(4,"林冲");
        HeroNode  node4  =  new HeroNode(5,"关胜");
        binaryTree.setRoot(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);

        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.middleOrder();
        System.out.println("后序遍历");
        binaryTree.afterOrder();

        System.out.println("前序查找");
        HeroNode heroNode1 = binaryTree.preSearch(4);
        HeroNode heroNode2 = binaryTree.middleSearch(6);
        HeroNode heroNode3 = binaryTree.afterSearch(3);
        System.out.println(heroNode1);
        System.out.println(heroNode2);
        System.out.println(heroNode3);
    }
}
class BinaryTree{
    private  HeroNode root ;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("树为空，无法遍历");
        }
    }
    public void middleOrder(){
        if (this.root != null){
            this.root.middleOrder();
        }else {
            System.out.println("树为空，无法遍历");
        }
    }
    public void afterOrder(){
        if (this.root != null){
            this.root.afterOrder();
        }else {
            System.out.println("树为空，无法遍历");
        }
    }

    public  HeroNode preSearch(int num){
        if (root != null){
           return root.preSearch(num);
        }else {
            return null;
        }
    }
    public  HeroNode middleSearch(int num){
        if (root != null){
            return root.middleSearch(num);
        }else {
            return null ;
        }
    }
    public  HeroNode afterSearch(int num){
        if (root != null){
            return  root.afterSearch(num);
        }else {
            return null;
        }
    }
}

class HeroNode{
    private int no ;
    private String name ;
    private HeroNode left ;
    private  HeroNode right ;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void  preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    public void  middleOrder(){

        if (this.left != null){
            this.left.middleOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.middleOrder();
        }
    }

    public void  afterOrder(){
        if (this.left != null){
            this.left.afterOrder();
        }
        if (this.right != null){
            this.right.afterOrder();
        }
        System.out.println(this);
    }

    public HeroNode preSearch(int num){
        if (this.no == num){
            return this;
        }
        HeroNode temp = null;
        if (this.left != null){
            temp=this.left.preSearch(num);
        }
        if (temp != null){
            return temp ;
        }

        if (this.right != null){
            temp=this.right.preSearch(num);
        }
        return temp ;
    }
    public HeroNode middleSearch(int num){

        HeroNode temp = null;
        if (this.left != null){
            temp=this.left.middleSearch(num);
        }
        if (temp != null){
            return temp ;
        }

        if (this.no == num){
            return this;
        }

        if (this.right != null){
            temp=this.right.middleSearch(num);
        }
        return temp ;
    }
    public HeroNode afterSearch(int num){

        HeroNode temp = null;
        if (this.left != null){
            temp=this.left.afterSearch(num);
        }
        if (temp != null){
            return temp ;
        }

        if (this.right != null){
            temp = this.right.afterSearch(num);
        }
        if (temp != null){
            return temp ;
        }

        if (this.no == num){
            return this;
        }
        return temp ;
    }


}
