package com.atguigu2.singlelink;

public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.creat(25);
        circleSingleLinkedList.show();

        circleSingleLinkedList.play(1,2,25);
    }
}

class CircleSingleLinkedList{
    private Boy first = null;
    Boy curBoy = null;
    public void creat(int size){
        if (size<1){
            System.out.println("请输入有效的人数");
        }
        for (int i = 1; i < size+1; i++) {
            Boy boy = new Boy(i);
            if (i==1){
//                注意：这个first不是指向boy,而是实实在在的就是boy，跟他使用的是堆内存中的同一个对象
                first=boy;
                curBoy=first;
                boy.setNext(boy);
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy=curBoy.getNext();
        }
    }
    // startNo：第几个小孩开始，count：一共数几下，nums：有多少个小孩
    public void play(int startNo,int count,int nums){
        if (startNo<1||startNo>nums||count>nums){
            System.out.println("请输入正确的数字");
        }
//        定义一个辅助指针temp，指向first的前一个位置
        Boy temp =first;
        while (true){
            if (temp.getNext()==first)break;
            temp=temp.getNext();
        }
//        数数前，first移动到第startNo位置，从那个地方开始数,移动startNo-1次
        for (int i = 0; i <startNo-1; i++) {
            first=first.getNext();
            temp=temp.getNext();
        }
//        数数时，每一次都要数count下，每一次都移动count-1次，因为自己也要数
        while (true){
//            只剩一个小孩时
            if(first==temp){
                System.out.printf("最后一个小孩%d出圈\n",first.getId());
                break;
            }
//            移动到数数到的那个位置
            for (int i = 0; i < count-1; i++) {
                first=first.getNext();
                temp=temp.getNext();
            }
//            出圈
            System.out.printf("出圈的小孩是%d\n",first.getId());
//            出圈之后，删除出圈的小孩
            first=first.getNext();
//            私有的，通过set方法赋值
            temp.setNext(first);
        }

    }

    public void show(){
        if (first==null){
            System.out.println("当前链表为空");
            return;
        }
        Boy curBoy = first ;
        while (true){
            System.out.printf("玩游戏的孩子是:%d\n",curBoy.getId());
            if (curBoy.getNext()==first){
                break;
            }
            curBoy=curBoy.getNext();
        }
    }
}



class Boy{
    private int id ;
    private Boy next;

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "boy{" +
                "id=" + id +
                ", next=" + next +
                '}';
    }
}
