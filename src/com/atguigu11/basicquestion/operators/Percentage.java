package com.atguigu11.basicquestion.operators;

public class Percentage {
    /**
     *  表达式自动类型提升
     *      a * 1.0   ===》 double 类型，不是int 是 double 不会损失精度
     * @param args
     */
    public static void main(String[] args) {
        int a = 45 ;
        int b = 100;
        //精度丢失了
        int temp =a / b * 100 ;
        System.out.println(temp);
        double result = a * 1.0 / b * 100 ;
        System.out.println(result);
    }
}
