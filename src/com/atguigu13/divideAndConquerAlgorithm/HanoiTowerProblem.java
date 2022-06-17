package com.atguigu13.divideAndConquerAlgorithm;

/**
 *  分治法解决汉诺塔问题
 */
public class HanoiTowerProblem {
    public static void main(String[] args) {
        hanoiTower(4,'a','b','c');
    }

    /**
     *  此方法功能 ：
     *       把第二个参数a代表的柱子上的盘子移动到第四个参数c代表的柱子上
     * @param num  一共有多少个盘子
     * @param a   第一个塔
     * @param b   第二个塔
     * @param c   第三个塔
     */
    public static void hanoiTower(int num , char a,char b, char c){
        if (num == 1){
            System.out.println("第1个盘子从 " + a + "->" + c);
        }else {
            hanoiTower(num - 1 , a, c, b);
            System.out.println("第"+num+"个盘子从 " + a + "->" + c);
            hanoiTower(num - 1 ,b,a,c);
        }
    }
}
