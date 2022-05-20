package com.atguigu6.search;

import java.util.Arrays;

/**
 * 顺序查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,1,5};
        int[] search = seqSearch(arr, 1);
        System.out.println(Arrays.toString(search));
    }
    public static int[] seqSearch(int[] arr,int value){
        int[] temp = new int[arr.length] ;
        int index = 0 ;
        for (int i = 0; i < arr.length; i++) {
         if (arr[i] == value){
                temp[index] = i+1;
                index++;
            }
        }
        return temp;
    }
}
