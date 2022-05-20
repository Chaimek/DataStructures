package com.atguigu5.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = { 1,2,1,2,1};
//        quickSort(arr , 0 ,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        int[] arr= new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前："+format1);

        quickSort(arr,0,arr.length-1); //  0.05s

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format2);

    }

    /**
     *
     * @param arr
     * @param left 左下标 用 l接收，因为leaf不能变，一直指向最左边，而 l可以变，一个数一个数的往右遍历
     * @param right 右下标
     * 思想 ： 快速排序每一轮选择一个数（这里我选择的是中间的数pivot），进行一次quicksort后，左边的数都小于等于pivot，右边都大于等于pivot
     *        为什么这里还有等于？对于左边而言，等于pivot肯定是最大的，后一轮对左边递归，等于pivot肯定会排到最右边，也就是整体上的中间；
     *        对于右边而言，等于pivot肯定是最小的，在对右边递归的时候，等于pivot的数肯定会被排到最左边，也就是排到了中间
     */
    //利用了递归
    public static void quickSort(int[] arr,int left,int right){

        int l = left;
        int r = right ;
        int pivot =arr [ (( right + left ) / 2 )];
        int temp = 0 ;
        //这里使用保用while,只要l<r 说明还没遍历完
        while (l<r){
            while (arr[l] < pivot ) {
                l++ ;
            }
            while (arr[r] > pivot){
                r--;
            }
            if (l>=r){
                break;
            }
            //找到了对应两个数就交换
            temp =arr[l];
            arr[l]=arr[r];
            arr[r]=temp ;

            //如果没有这个，则会卡死，
            if (arr[r] == pivot ){
                l++;
            }
            if (arr[l] == pivot ){
                r--;
            }
        }
        if(l==r){
            l+=1;
            r-=1;
        }
        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}
