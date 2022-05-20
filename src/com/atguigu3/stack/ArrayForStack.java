package com.atguigu3.stack;

import java.util.Scanner;

public class ArrayForStack {
    public static void main(String[] args) {
        Stack stack = new Stack(3);

        Scanner scanner = new Scanner(System.in);
        char c=' ';
        boolean loof =true;

        while (loof){
            System.out.println("s:显示栈");
            System.out.println("a:添加元素");
            System.out.println("p:取出元素");
            System.out.println("e:退出栈");
            c=scanner.next().charAt(0);
            switch (c){
                case 's' :
                    stack.show();
                        break;
                    case 'p' :
                        try {
                            int pop = stack.pop();
                            System.out.printf("取出的元素是%d\n",pop);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'a' :
                        System.out.println("请输入一个数:");
                        int i = scanner.nextInt();
                        stack.add(i);
                        break;
                    case 'e' :
                     loof =false;
                        System.out.println("程序正在退出");
                        break;
            }
        }
        scanner.close();
        System.out.println("程序结束");
    }

}
class Stack{
    private int maxSize;
    private int top;
    private int[] arr;

    public Stack(int maxsize){
        top=-1;
        this.maxSize=maxsize;
        arr=new int[maxSize];
    }

    public void  add(int data){
        if (top==maxSize-1){
            System.out.println("栈已满");
            return;
        }
        top++;
        arr[top]=data;
    }

    public void show(){
        if (top==-1){
            System.out.println("栈已空");
            return;
        }
        for (int i =top; i >= 0 ; i--) {
            System.out.printf("数据为【%d】\n",arr[i]);
        }
    }

    public int pop(){
        if (top==-1){
            throw new RuntimeException("栈为空");
        }
        int i = arr[top];
        top--;
        return i;
    }

    public int peek(){
        return arr[top];
    }

}
