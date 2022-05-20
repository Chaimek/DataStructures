package com.atguigu.sparsearray;

import org.junit.jupiter.api.Test;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        int chessArr1[][]= new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;

        int sum = 0 ;
        int s =0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
                s++;
                if(s%11==0)System.out.println();
                if(data!=0)sum++;
            }
            System.out.println();
        }
        System.out.println("------------------------------");
        sum=0;
        s=0;
        for (int i = 0; i <chessArr1.length ; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                s++;
                System.out.printf("%d\t",chessArr1[i][j]);
                if(s%11==0) {System.out.println();s=0;}
                if(chessArr1[i][j]!=0)sum++;
            }
        }
        System.out.println(sum);
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        System.out.println("下面是稀疏数组----------------");
        int count=0;
        for (int i = 0; i <chessArr1.length ; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }


        System.out.println("-------------------");


        System.out.println("稀疏数组第一种遍历方式");
        for (int i = 0; i <sparseArr.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println("稀疏数组第二种遍历");
        for (int i = 0; i <sparseArr.length ; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf( "%d\t",sparseArr[i][j]);
            }
            System.out.println();
        }
        System.out.println("遍历稀疏数组的第三种方式");
        for (int[] row: sparseArr) {
            for (int data:row) {
                System.out.printf( "%d\t",data);
            }
            System.out.println();
        }


        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

//        复原二维数组
        for (int i = 1; i < sparseArr.length; i++) {
                chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
//      复原之后的代码
        s=0;
        for (int i = 0; i <chessArr2.length ; i++) {
            for (int j = 0; j < chessArr2[i].length; j++) {
                s++;
                System.out.printf("%d\t",chessArr2[i][j]);
                if(s%11==0) {System.out.println();s=0;}
                if(chessArr1[i][j]!=0)sum++;
            }
        }

    }



    @Test
    public void outputSpaseArray(){
        int[][] sparseArrs =new int[2][3];
        sparseArrs[1][1] =1;
        File file = new File("sparseArr");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (int[] row: sparseArrs) {
                for (int data:row) {
                    String s = String.valueOf(data);
                    byte[] bytes = s.getBytes();
                    fileOutputStream.write(bytes);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int read = fileInputStream.read(bytes);
            System.out.println(new String(bytes ,0,read));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
