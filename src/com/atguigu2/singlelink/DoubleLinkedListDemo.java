package com.atguigu2.singlelink;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        Node2 node1 = new Node2(1, "宋江", "及时雨");
        Node2 node2 = new Node2(2, "吴用", "智多星");
        Node2 node3 = new Node2(3, "李逵1", "黑旋风1");
        Node2 node4 = new Node2(4, "李逵2", "黑旋风2");
        Node2 node5 = new Node2(5, "李逵3", "黑旋风3");

        doubleLinkedList.addNode(node1);
        doubleLinkedList.addNode(node2);
        doubleLinkedList.addNode(node4);
        doubleLinkedList.addNode(node5);
        doubleLinkedList.addNode(node3);


        doubleLinkedList.showLinkedList();

        Node2 newNode = new Node2(5,"万","黑旋风");
        doubleLinkedList.update(newNode);
        System.out.println("下面是修改的");
        doubleLinkedList.showLinkedList();

        System.out.println("下面是删除的");
        doubleLinkedList.delete(3);
        doubleLinkedList.showLinkedList();
    }

}
class DoubleLinkedList {
    private Node2 headNode2 = new Node2(null,"","");

    public Node2 getHeadNode2() {
        return headNode2;
    }
    public void  addNode(Node2 node){
//        因为头指针（headNode）不能动,需要一个辅助变量，指向最后一个节点
        Node2 temp = headNode2;
//        找到最后一个位置
        while (true){
            if (temp.next == null)break;

            temp=temp.next;
        }
        temp.next=node;
        node.pre=temp;
    }


    public  void  update(Node2 newNode){
//        判断找没找到，方便后面操作
        boolean flag = false ;
        Node2 temp = headNode2.next;
        while (true){
            if (temp == null) break;
            else if (temp.id ==newNode.id){
                flag = true ;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=newNode.name;
            temp.nickName=newNode.nickName;
        }else {
            System.out.printf("您要修改的编号%d英雄不存在\t",newNode.id);
        }
    }

    public void delete(int no){
        boolean flag =false ;
        Node2 temp = headNode2.next;//temp是删除位置
        while (true){
            if (temp==null)break;
            else if (temp.id==no){
                flag =true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.pre.next=temp.next;
            //这里必须判断temp是不是最后一个节点，因为上面的判断只知道temp不等于null,而temp.next不知道，不判断要是删除最后一个会出现空指针异常
            if (temp.next != null){
            temp.next.pre=temp.pre;
            }
        }else {
            System.out.printf("您想要删除编号为的：%d 的英雄不存在\n",no);
        }
    }

    public void showLinkedList(){
//        因为是输出数据，所以temp指向第一个节点
        if (headNode2.next== null){
            System.out.println("链表为空");
            return;
        }

        Node2 temp = headNode2.next;

        while (true){
            if (temp==null) break;

            System.out.println(temp);
            temp=temp.next;
        }

    }

}
class Node2{
    public Integer id;
    public String name;
    public String nickName;
    public Node2 next;
    public Node2 pre;

    public Node2(Integer id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                不能输出next，因为next里面有下一个node的信息，会把后面的节点都输出
//                ", next=" + next +
                '}';
    }
}
