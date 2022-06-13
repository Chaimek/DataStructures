package com.atguigu8.greedyAlgorithm;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CannonProblem {
    /**
     * 问题描述 ：
     *      给定一个数组arr，表示从早到晚，依次会出现的导弹的高度 大炮打导弹的时候，如果一旦大炮定了某个高度去打，那么这个大炮每次打的高
     *      度都必须下降一点
     *       问 ： 如果所有的导弹都必须拦截，返回最少的大炮数量？
     *     对于一个数组 arr 里面存放了从早到晚打过来的炮弹的高度 ，例如 [22,55,77,88] 第一枚打过来的炮弹的高度是22 ，第二枚是55 ，以此类推
     *     现要使用大炮发射导弹去拦截打过来的炮弹，每次拦截到之后，大炮的高度减 1 ，求 想要把所有炮弹拦截下来，最少需要几门大炮 ？
     *   使用贪心算法 ，贪心的点在对于拦截一个炮弹，选择高度离他最近的一个大炮，如果都没有，则另开一门大炮
     *   那如何选择离他最近的那门大炮呢？
     *         使用 Treemap  他是一个有序的集合  其中的 ceilingKey(ele) 方法用于返回第一个大于或等于 ele 的 key。
     *
     */

    public static void main(String[] args) {
        //要拦截的导弹
        int[] arr = {55,67,11,23,63,90,3,100};

//        TreeMap treeMap = new TreeMap<>() ;
//        treeMap.put(1,4);
//        treeMap.put(44,4);
//        treeMap.put(53,4);
//        treeMap.put(22,4);
//        treeMap.put(33,4);
//        System.out.println(treeMap.ceilingKey(21));// 22
        int count = numberCount(arr);

        System.out.println(count);
    }

    public static int numberCount(int[] arr){
        /**
         *  key  : 大炮能打到的最大高度
         *  value : 能打这么高的大炮有几门
         *  例：  90 3    ===》 表示有3门能打高度为90的炮弹
         */
        TreeMap<Integer , Integer>  cannonTree = new TreeMap<>() ;

        for (int i = 0; i < arr.length; i++) {
            //没有能打的大炮，就得另起一门
            if (cannonTree.ceilingKey(arr[i]) == null){
                //把大炮添加到map中得先判断map中有没有，因为treeMap是会去重的
                if (cannonTree.containsKey(arr[i])){
                    cannonTree.put(arr[i] - 1 ,cannonTree.get(arr[i]-1) +1) ;
                }else {
                    cannonTree.put(arr[i]-1,1);
                }
            }else {    //如果有能打的大炮，应该选择离他最近的刚好大于他的那门大炮
                Integer integer = cannonTree.ceilingKey(arr[i]);
                //判断能打这个高度的大炮有几门
                if (cannonTree.get(integer) !=1){
                    //有多门就 -1
                    cannonTree.put(integer,cannonTree.get(integer) -1);
                }else {
                    //只有一门就直接移除这个键
                    cannonTree.remove(integer);
                }
                //拦截完这门大炮后，这门大炮并没有立即消失
                //这门大炮还能继续打，能打的高度是拦截到的导弹高度-1
                //把大炮添加到map中得先判断map中有没有
                if (cannonTree.containsKey(arr[i])){
                    cannonTree.put(arr[i] - 1 ,cannonTree.get(arr[i]-1) +1) ;
                }else {
                    cannonTree.put(arr[i]-1,1);
                }
            }

        }
        //把 map 中的 value 取出来直接相加 ，就是所需要的最少大炮数量
        int count = 0 ;
        Set<Map.Entry<Integer, Integer>> set = cannonTree.entrySet();

        for (Map.Entry<Integer, Integer> integerIntegerEntry : set) {
            count += integerIntegerEntry.getValue() ;
        }
        return count;
    }
}
