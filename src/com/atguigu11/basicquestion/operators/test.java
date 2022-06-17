package com.atguigu11.basicquestion.operators;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String str ="11111111";
        byte b = (byte) Integer.parseInt(str, 2);
        System.out.println(b);
        PrintBinarySystem.printByteBinarySystem(b);
    }
    @Test
    public void test(){
        int temp =10 ;
        temp |= 256 ;

        String s = Integer.toBinaryString(temp);
        System.out.println(s);
    }
    @Test
    public void test1() throws Exception {
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("temp"));
        FileOutputStream fileOutputStream = new FileOutputStream("temp");
        fileOutputStream.write("write".getBytes());
    }
    @Test
    public void test2(){
        int a= 0;
        System.out.println(a++ + ++a); //2
        int b =0;
        System.out.println(++b + b++); //2
    }
    @Test
    public void test3(){
        for (int i = 0; i < 10; i++) {
            if (i==0 || i == 9){
                for (int j = 0; j < 10; j++) {
                    System.out.print("*");
                }
            }else {
                for (int j = 0; j < 10; j++) {
                    if ( j==0 || j==9){
                        System.out.print("*");
                    }else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }

    }
    @Test
    public void test4(){
//        Date d = new Date() ;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
//        String format = simpleDateFormat.format(d);
//        System.out.println(format);
        System.out.println("111");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println(next);
    }

    @Test
    public void test5(){
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i+"*" + j + "="+i*j + " ");
            }
            System.out.println();
        }
    }



}
