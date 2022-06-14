package com.atguigu11.basicquestion.operators;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

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

}
