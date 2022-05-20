package com.atguigu.queue;


import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("以下是数组模拟环形队列");
        CircleArrayQ queue = new CircleArrayQ(3);

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

class CircleArrayQ{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQ(int maxsize){
        maxSize= maxsize;
        arr = new int[maxSize];
//        front = 0; //指向头部元素（也就是包含头部元素）  不写也可以，默认初始值为0
//        rear = 0;//指向尾部元素的下一个（也就是不包含尾部元素） 留一个空间做一个约定
    }
    public boolean isFull(){
       return  (rear+1) % maxSize ==front;
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public void addQueue(int data){
        if (isFull()){
            System.out.println("队列已经满了");
            return;
        }
        arr[rear] = data;
//        这里rear后移必须考虑取模
        rear=(rear+1)%maxSize;
    }


    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int i = arr[front];
        front=(front +1 )%maxSize;
        return i;
    }
//    获取队列数据的个数
    public int size(){
        return (rear-front+maxSize)%maxSize;
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("当前队列为空");
        }
        for (int i = front; i <front+size(); i++) {
            System.out.printf("arr[%d]=%d\t\n",i%maxSize,arr[i%maxSize]);
        }
    }

    public int getHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}








//    public void reverseList(Node headNode){
//        if (headNode.next==null || headNode.next.next == null )return;
//
//        Node reverseNode = new Node(null,"","");
//        Node temp = headNode.next;//指向当前节点
//        Node res =null;//指向当前节点的下一个
//        while (temp!=null){
//            res = temp.next;
//
//            temp.next=reverseNode.next;
//            reverseNode.next=temp;
//            temp=res;
//        }
//
//        headNode.next=reverseNode.next;
//    }
