package com.atguigu.queue;

import java.util.Scanner;

public class ArrayForQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(2);

        Scanner scanner = new Scanner(System.in);
        char c =' ';
        boolean loof = true ;

        while (loof){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):向添加数据");
            System.out.println("g(get)：从队列中取出数据");
            System.out.println("h(head):取出队头信息");
            c = scanner.next().charAt(0);
            switch (c){
                case 's' :
                    queue.showQueue();
                        break;
                case 'a' :
                    System.out.println("请输入一个数");
                    int i = scanner.nextInt();
                    queue.addQueue(i);
                    break;
                case 'g' :
                try {
                    int res = queue.getQueue();
                    System.out.println("取出的数据是："+res);

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                    break;
                case 'h' :
                    try {
                        int res = queue.getHead();
                        System.out.println("队列头部元素是：" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' : loof=false;
                    scanner.close();
                    break;

            }


        }
        System.out.println("程序已经退出");
    }
}
class Queue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public Queue(int maxsize){
        maxSize= maxsize;
        arr = new int[maxSize];
        front = -1; //指向头部元素的前一个（也就是不包含头部元素）
        rear = -1;//指向尾部元素（也就是包含尾部元素）
    }

    public boolean isFull(){
        return rear==maxSize-1;
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public void addQueue(int data){
        if (isFull()){
            System.out.println("队列已经满了");
            return;
        }
        rear++;
        arr[rear]=data;

    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("当前队列为空");
        }
        for (int i = front+1; i <=rear; i++) {
            System.out.printf(arr[i]+" ");
        }
        System.out.println();
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    public int getHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }
}
