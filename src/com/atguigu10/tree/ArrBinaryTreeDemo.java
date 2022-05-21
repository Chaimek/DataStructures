package com.atguigu10.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println("-----------");
        arrBinaryTree.middleOrder();
        System.out.println("-----------");
        arrBinaryTree.afterOrder();
    }
}
class  ArrBinaryTree{
    private  int[]  arr ;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     *   使用重载，简化每次输入参数的时候都要输入0
     */
    public void preOrder(){
        this.preOrder(0);
    }
    public  void middleOrder(){
        this.middleOrder(0);
    }
    public void  afterOrder(){
        this.afterOrder(0);
    }

    /**
     * 注意 ：
     *       要想遍历整个二叉树，则调用的时候参数为0 --->可以使用重载，简化每次输入参数的时候都要输入0
     *       换句话说，以下代码遍历的是输入参数(下标)节点和他的子节点
     * @param index  遍历的元素的下标 ，
     */
    public void preOrder(int index){
        if(arr != null && arr.length != 0){
            System.out.println(arr[index]);
            //向左递归
            /**
             *   注意 ：
             *      1、一定要判断数组下标越界
             *      2、下标为index存储的节点，
             *          他的 ： 左孩子  index * 2 +1
             *                 右孩子  index * 2 + 2
             *
             */
            if (index * 2 + 1 <arr.length){
                preOrder(index * 2 +1);
            }
            //向右递归
            if (index * 2 + 2 < arr.length){
                preOrder(index * 2 + 2);
            }
        }else {
            System.out.println("数组为空，无法遍历");
        }
    }


    public void middleOrder(int index){
        if(arr == null && arr.length == 0){
            System.out.println("顺序二叉树为空，无法遍历");
            return;
        }
            //向左递归
            /**
             *   注意 ：
             *      1、一定要判断数组下标越界
             *      2、下标为index存储的节点，
             *          他的 ： 左孩子  index * 2 +1
             *                 右孩子  index * 2 + 2
             *
             */
            if (index * 2 + 1 <arr.length){
                middleOrder((index * 2) +1);
            }

            System.out.println(arr[index]);

            //向右递归
            if (index * 2 + 2 < arr.length){
                middleOrder(index * 2 + 2);
            }

    }



    public void afterOrder(int index){
        if(arr != null && arr.length != 0){
            //向左递归
            /**
             *   注意 ：
             *      1、一定要判断数组下标越界
             *      2、下标为index存储的节点，
             *          他的 ： 左孩子  index * 2 +1
             *                 右孩子  index * 2 + 2
             *
             */
            if (index * 2 + 1 <arr.length){
                middleOrder(index * 2 +1);
            }


            //向右递归
            if (index * 2 + 2 < arr.length){
                middleOrder(index * 2 + 2);
            }

            System.out.println(arr[index]);
        }else {
            System.out.println("数组为空，无法遍历");
        }
    }



}
