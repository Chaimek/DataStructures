package com.atguigu.sparsearray;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class sparseToFile {
    public static void sparseToFile(int[][] sparseArrs){
        File file = new File("sparseArr");
        FileOutputStream out =null;
        try {
            out= new FileOutputStream(file);
            for (int[] row: sparseArrs) {
                for (int data:row) {
//                    这样写得到的byte数组中的数据是 asc码变成的byte类型的数据，因为是字符串转换
//                    String s = String.valueOf(data);
//                    byte[] bytes = s.getBytes();
                    byte bytes=(byte)data;
//                    写入的是二进制，不可显示，要用2进制格式的文件打开
                    out.write(bytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @Test
    public  void fileToChessArr(){
        File file = new File("sparseArr");
        FileInputStream in = null;
        try {
            in=new FileInputStream(file);
            byte[] b = new byte[1024];
            int count = in.read(b);
            int c =2;
            int arr[][] = new int[b[0]][b[1]];
            for (int i = 1; i <b[2]+1; i++) {
                for (int j = 0; j <3 ; j++) {

                    arr[b[i]][b[j]]= b[c];

                }
            }
            for (int i = 0; i <arr.length ; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.println(arr[i][j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
