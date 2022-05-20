package com.atguigu6.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1 ;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 33);
        System.out.println(i);
    }

    /**
     * 与传统的二分法相比，插值插值使用了自适应 mid
     *           mid = left +(right-left)*(findVal -arr[left])/ (arr[right] - arr[left])
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return  返回-1 表示没找到
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        int l = left;
        int r =right;

        int mid = left +(right-left)*(findVal -arr[left])/ (arr[right] - arr[left]);

//      findVal<arr[0]||findVal>arr[arr.length-1]这个必须有，不仅仅是优化，也是为了防止mid越界
        if (left>right ||findVal<arr[0]||findVal>arr[arr.length-1]){
            return -1;
        }
        if (findVal>arr[mid]){
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal<arr[mid]){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return arr[mid];
        }
    }
}
