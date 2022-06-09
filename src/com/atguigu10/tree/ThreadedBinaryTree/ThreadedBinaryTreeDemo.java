package com.atguigu10.tree.ThreadedBinaryTree ;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root =  new HeroNode(1,"宋江");
        HeroNode node1  =  new HeroNode(2,"吴用");
        HeroNode node2  =  new HeroNode(3,"卢俊义");
        HeroNode node3 =  new HeroNode(4,"林冲");
        HeroNode node4  =  new HeroNode(5,"关胜");
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);

        threadedBinaryTree.threadedNode();
//        threadedBinaryTree.threadedByNode();
        HeroNode left = node4.getLeft();
        HeroNode right = node4.getRight();

        System.out.println(left);
        System.out.println(right);
        System.out.println("*****");
        threadedBinaryTree.middleThreadedOrder();
    }
}
class ThreadedBinaryTree{
    private HeroNode root ;
    //当前节点的前一个节点
    private HeroNode pre = null ;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 中序遍历二叉树
     */
    public  void   middleThreadedOrder(){
        //因为根节点不能变，需要一个辅助节点帮助我们遍历
        HeroNode node = root ;
        while (node != null){
            //找到一个线索化的节点
            while (node.getLeftType() == 0){
                node = node.getLeft() ;
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight() ;
        }
    }

    /**
     *   中序线索化二叉树
     */
    public void threadedNode(){
        threadedNode(root);
    }

    public void threadedNode(HeroNode node){
        if (node == null){
            return;
        }
        //第一步 ：线索化左子树
        threadedNode(node.getLeft());
        //第二步 ：线索化当前节点
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node ;
        //第三步 ：线索化右子树
        threadedNode(node.getRight());
    }


//    //为什么不行 ？ 因为pre不共享
//    public void threadedByNode(){
//        root.threadedByNode(pre);
//    }

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

    public HeroNode preSearch(int num){
        if (root != null){
            return root.preSearch(num);
        }else {
            return null;
        }
    }
    public HeroNode middleSearch(int num){
        if (root != null){
            return root.middleSearch(num);
        }else {
            return null ;
        }
    }
    public HeroNode afterSearch(int num){
        if (root != null){
            return  root.afterSearch(num);
        }else {
            return null;
        }
    }

    public void delNode(int no){
        //第一个逻辑的判断
        if (root != null){
            //第二个逻辑的判断
            if(root.getNo() == no){
                root =null ;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树无法删除");
        }
    }


    public  void  delNodePlus(int no){
        if (root != null){
            if (root.getNo() ==no){
                root=null ;
            }else {
                root.delNodePlus(no);
            }
        }else {
            System.out.println("空树无法遍历");
        }
    }


    public void  ToArrBinaryTree(){
        if (root != null){
            System.out.println(root);
            root.ToArrBinaryTree();
        }else {
            System.out.println("二叉树为空无法转换");
        }
    }
}
class HeroNode{
    private int no ;
    private String name ;
    private HeroNode left ;
    private HeroNode right ;
    private int leftType  ;
    private int rightType  ;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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
//    public void threadedByNode(HeroNode pre){
//        if (this == null){
//            return;
//        }
//            if (this.left != null){
//
//                this.left.threadedByNode(pre);
//            }
//
//        if (this.getLeft() == null ){
//            this.setLeft(pre);
//            this.setLeftType(1);
//        }
//        if (pre != null && pre.getRight() == null){
//            pre.setRight(this);
//            pre.setRightType(1);
//        }
//        pre = this ;
//
//        if (this.right != null){
//            this.right.threadedByNode(pre);
//        }
//    }

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

    /**
     * 以下逻辑：
     *    1、而且能调用这个方法说明 ，this ！= null ，也就是说这一步的逻辑也要在调用的时候判断
     *    2、这个this是要删除的父节点，
     *      也就是说，当要删除的是根节点的时候，下列代码无法删除根节点，所以要在调用这个方法的时候判断
     * @param no
     */
    public void  delNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null ;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null ;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    /**
     * 在前面条件的基础上
     * 对上述代码进行改进，如果是非叶子节点则不删除下面的子节点
     * @param no
     */
    public  void  delNodePlus(int no){
        if (this.left != null && this.left.no == no){
            //判断是不是叶子节点
            if (this.left.left ==null && this.left.right ==null){
                this.left = null ;
                return;
            }else {
                if (this.left.left != null && this.left.right != null){
                    /**
                     * 当要删除的节点左右子树都不为空时：
                     *      这里必须使用一个临时变量temp保存另外那个分支的值，否则在删除节点之后，另外那个分支的值就找不到了
                     */
                    HeroNode temp = null ;
                    temp=this.left.right;
                    this.left =this.left.left;
                    this.left.right = temp ;
                }else if (this.left.left != null){
                    this.left =this.left.left ;
                }else {
                    this.left=this.left.right ;
                }
            }
            return;
        }

        if (this.right != null && this.right.no == no){
            /**
             * 注意两种判断的逻辑区别
             */
            if (this.right.left == null && this.right.right ==null){
                this.right =null ;
                return;
            }else {
                if (this.right.left != null && this.right.right ==null){
                    this.right =this.right.left ;
                }else if (this.right.left ==null && this.right.right != null){
                    this.right = this.right.right ;
                }else {
                    HeroNode temp = null ;
                    temp = this.right.right ;
                    this.right =this.right.left ;
                    this.right.right =temp ;
                }
            }
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    //按层遍历，要顺序二叉树一般是指完全二叉树转化为数组存储
    public void  ToArrBinaryTree(){
        if (this.left != null){
            System.out.println(this.left);
        }
        if (this.right != null){
            System.out.println(this.right);
        }
        if (this.left != null){
            this.left.ToArrBinaryTree();
        }
        if (this.right != null){
            this.right.ToArrBinaryTree();
        }
    }
}