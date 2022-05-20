package com.atguigu7.dynamic.KnapsackProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * 以下是01背包问题
 */
public class ZeroOneKnapsackProblem {
    public static void main(String[] args) {

        int[] w = {1,5,3,4,6};
        int[] val ={2,3,4,5,10};
        int m = 7;
        knapsackProblem(w,val,m);
//        List list = knapsackProblem(w, val, m);
//        System.out.println(list);
    }

    /**
     *
     * @param val  所有物品的价值
     * @param w    所有物品的重量
     * @param m    背包的总容量
     * @return     所选的物品
     */
    public static List knapsackProblem(int[] w , int[] val ,int m){

        //物品的个数
        int n = w.length;

        /**
         * 注意 ：
         *     这里使用 n+1 和 m+1 有什么好处
         *     对于之前使用 int[][] v = new int[i][j];  -----> 表示前 i 个物品放到 容量为 j 的背包中的最大价值
         *     此时下标可能会越界，而使用下面的方法不会
         *
         */
        int[][] v = new int[n+1][m+1];

        //存放解
        int[][] path = new int[n+1][m+1] ;

        /**
         *  当物品个数为 0 或者 背包容量为0时，最大价值为0
         *  此步可以省略，因为定义数组之后默认为0
         */
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0 ;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0 ;
        }

        for (int i = 1; i <v.length ; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //如果当前物品的重量大于背包的重量
                if(w[i-1] > j){
                    //则取没选这个物品前的选择
                    v[i][j] =  v[i-1][j];
                }else {//如果能放下第i个物品
                    //则判断加入这个物品之后的总价值是否大于原来物品的总价值
                    if (v[i-1][j] < val[i-1] + v[i-1][j - w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j - w[i-1]] ;
                        //只有选择了第i个物品,才在path中赋值
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //初始表示所有物品
        int i = path.length - 1;
        //初始表示背包的最大容量 m
        int j = path[0].length - 1;
        List result = new ArrayList<>() ;
        /**
         * 必须反向遍历，否则会重复输出
         */
        while ( i > 0 && j >0){
            if (path[i][j] == 1){
                result.add(i);
                System.out.println("选择了第"+ i +"个物品");
                /**
                 *  注意 ：
                 *      当选进去了一个物品，背包j的容量就要减去这个物品的重量
                 */
                j -= w[i-1];
            }
            i--;
        }

        return result ;
    }

}
