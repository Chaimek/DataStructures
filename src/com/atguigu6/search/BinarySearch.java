package com.atguigu6.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分法查找 : 传进来的数必须有序
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,2,2,2,3,4,5};
//        int i = binarySearch(arr, 0, arr.length, 2);
//        System.out.println(i);
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 2);
        System.out.println(integers);
    }

    /**
     *
     * @param arr 数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal   要查找的值
     * @return 返回-1时，表示没找到
     */
    public static int binarySearch(int[] arr, int left,int right,int findVal){
        int mid  = (right+left) /2 ;
        int midVal = arr[mid];

        if (left>right || findVal<arr[0] || findVal >arr[arr.length-1]){
            return -1 ;
        }
        if (findVal > midVal ){
           return binarySearch(arr, mid+1, right, findVal);
        }else if (findVal<midVal){
           return binarySearch(arr, left, mid-1, findVal);
        }else {
            return mid ;
        }

    }

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        int mid  = (right+left) /2 ;
        int midVal = arr[mid];

        if (left>right || findVal<arr[0] || findVal >arr[arr.length-1]){
            return new ArrayList<>() ;
        }
        if (findVal > midVal ){
           return binarySearch2(arr, mid+1, right, findVal);
        }else if (findVal<midVal){
           return binarySearch2(arr, left, mid-1, findVal);
        }else {
            List<Integer> arrayList= new ArrayList<>();
            arrayList.add(mid);
            int temp =mid - 1 ;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                arrayList.add(temp);
                temp--;
            }

            temp = mid+1;
            while (true){
                if (temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                arrayList.add(temp);
                temp ++;
            }
            return arrayList;
        }

    }
}
