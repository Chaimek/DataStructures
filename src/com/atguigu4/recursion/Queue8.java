package com.atguigu4.recursion;

public class Queue8 {
    int max = 8 ;
    int[] arr = new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }
    //放皇后
    public void check(int n){
//      递归出口，当n=8时说明前面已经判断了下标为0-7的皇后，也就是八皇后
        if (n==max){
            print();
            return;
        }else {
//            每一个皇后都要判断在她那一行的所有列，
            for (int i = 0; i < max; i++) {
//              n表示第几行的下标，开始从0开始，也就是第一行，这个i表示列数也就是这一行代码表示，让第一个皇后依次走完第一行的所有列
                arr[n] = i;
//                如果这个皇后能放下，就放下一个皇后
                if (judge(n)){
                    check(n+1);
                }
            }
        }

    }
//   判断这个位置是否能放
    public boolean judge(int n){//这里的n代表第n个皇后
        //第n个皇后的下标是n-1，与前面的皇后进行判断
        for (int i = 0; i < n; i++) {
            //这个算法针对于这个数组，
            //可用正方形的对角线思考
            if(arr[i] == arr[n] || Math.abs(n-i)==Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return  true;
    }

    public void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
    }

}
