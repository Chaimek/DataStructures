package com.atguigu11.basicquestion.operators;

public class PrintBinarySystem {
    public static void main(String[] args) {
        /**
         * 计算机的数据存储时以补码的方式进行存储
         * 对于正数 ：
         *     原码 = 反码 = 补码
         * 对于负数：
         *     补码  --取反-->  反码  --加 1--> 原码（我们人计算的）
         * 原码 ：是方便人来计算的
         * 补码 ：是方便计算机来运算的
         */
        printIntBinarySystem(20);
        printIntBinarySystem(-88);
        printIntBinarySystem(-5);
        printIntBinarySystem(Integer.MAX_VALUE);
        printIntBinarySystem(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
    public static void printIntBinarySystem(int num){
        for (int i = 31; i >=0 ; i--) {
            System.out.print((num & (1 << i))==0 ? "0" : "1" );
        }
        System.out.println();
    }

    public static void printByteBinarySystem(byte bytes){
        for (int i = 7; i >= 0 ; i--) {
            System.out.print((bytes & (1 << i)) == 0 ?"0":"1");
        }
    }
}
