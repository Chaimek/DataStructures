package com.atguigu2.singlelink;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node2 = new Node(2, "吴用", "智多星");
        Node node3 = new Node(3, "李逵1", "黑旋风1");
        Node node4 = new Node(4, "李逵2", "黑旋风2");
        Node node5 = new Node(5, "李逵3", "黑旋风3");


//        singleLinkedList.addNode(node1);
//        singleLinkedList.addNode(node3);
//        singleLinkedList.addNode(node2);

        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node5);

//        singleLinkedList.addByOrder(node2);
        singleLinkedList.showLinkedList();

        System.out.println("这是不改变原来链表结构，逆序输出链表");
        singleLinkedList.reversePrint(singleLinkedList.getHeadNode());

        System.out.println("修改之后的英雄:");
        Node node6= new Node(3, "李逵~", "黑旋风~");
        singleLinkedList.update(node4);
        singleLinkedList.showLinkedList();

        singleLinkedList.delete(3);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(1);

        System.out.println("这是删除后的英雄:");
        singleLinkedList.showLinkedList();

        int count = singleLinkedList.Count(singleLinkedList.getHeadNode());
        System.out.println(count);
        Node lastIndexNode = singleLinkedList.findLastIndexNode(singleLinkedList.getHeadNode(), 1);
        System.out.println(lastIndexNode);

        System.out.println("~~~~~");
        singleLinkedList.reverseLinked(singleLinkedList.getHeadNode());
        singleLinkedList.showLinkedList();

    }
}

class SingleLinkedList{
    private Node headNode = new Node(null,"","");

    public Node getHeadNode() {
        return headNode;
    }
//    逆置单链表
//    public void reverseLinked(Node headNode){
//        if (headNode.next==null || headNode.next.next == null )return;
//
//        Node reverseNode = new Node(null,"","");
//        Node temp = headNode.next;
//        Node res = headNode.next.next;
//
//        while (true){
//            if (temp==null)break;
//
//            temp.next=reverseNode.next;
//            reverseNode.next = temp;
//
//            if (res==null){
//               break;
//            }else {
//                temp=res;
//                res=res.next;
//            }
//        }
//        headNode.next=reverseNode.next;
//    }

//    逆序输出单链表，用到栈的先进后出的特点，java中的栈封装了，java.util.Stack类就是栈
    public void reversePrint(Node headNode){
        if (headNode.next == null)return;

//    创建一个栈
        Stack<Node> stack = new Stack<Node>();
        Node cur = headNode.next;
//        只要当前节点保卫科就压入栈中
        while (cur != null){
            stack.push(cur);
//          每压入一个元素后移
            cur=cur.next;
        }
//        输出栈中所有元素
        while (stack.size() > 0 ){
            System.out.println(stack.pop());
        }
    }

//  逆置单链表代码优化
    public void reverseLinked(Node headNode){
        if (headNode.next==null || headNode.next.next == null )return;
//        指向当前节点
        Node temp = headNode.next;
//        指向当前节点的下一个
        Node next =null;
//        每取出一个元素插入到当前链表的第一个
        Node reverseHead = new Node(null,"","");

        while (temp!= null){
//            使用temp前需要保存temp原来所在链表的信息，即把temp的下一个节点存到next节点中
             next= temp.next;
             temp.next=reverseHead.next;
             reverseHead.next = temp;
//             让temp重新指向原来的节点
             temp=next;

        }
//        让原来的headNode指向逆置之后的链表
         headNode.next=reverseHead.next;
    }
    public Node findLastIndexNode(Node headNode ,int index){
        if (headNode == null) return null;

        int count = Count(headNode);
        Node temp = headNode.next;
        for (int i=0 ;i<count-index;i++){
            temp=temp.next;
        }
        return temp;
    }

    public int Count(Node headNode){
        if (headNode.next == null) return 0;

        Node temp = headNode.next;
        int count =0;

        while (temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }

    public void  addNode(Node node){
//        因为头指针（headNode）不能动,需要一个辅助变量，指向最后一个节点
        Node temp = headNode;
//        找到最后一个位置
        while (true){
            if (temp.next == null)break;

            temp=temp.next;
        }
        temp.next=node;
    }

//按排名插入（添加/创建）
    public void addByOrder(Node node){
        Node temp = headNode;
        boolean flag = false ;//判断元素是否已经存在
        while (true){
            if(temp.next == null)break;
            else if(temp.next.id > node.id)break;
            else if (temp.next.id == node.id){
                flag = true;
                break;
            }
            temp = temp.next ;
        }
        if (flag){
            System.out.printf("准备添加的英雄的编号：%d已经存在了\n" ,node.id);
        }else {
            node.next = temp.next;
            temp.next=node;
        }
    }

    public  void  update(Node newNode){
//        判断找没找到，方便后面操作
        boolean flag = false ;
         Node temp = headNode.next;
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
        Node temp = headNode;//temp是删除位置的前一个
        while (true){
            if (temp.next==null)break;
            else if (temp.next.id==no){
                flag =true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.printf("您想要删除编号为的：%d 的英雄不存在\n",no);
        }
    }

    public void showLinkedList(){
//        因为是输出数据，所以temp指向第一个节点
        if (headNode.next== null){
            System.out.println("链表为空");
            return;
        }

        Node temp = headNode.next;

        while (true){
            if (temp==null) break;

            System.out.println(temp);
            temp=temp.next;
        }

    }

}

class Node{
    public Integer id;
    public String name;
    public String nickName;
    public Node next;

    public Node(Integer id, String name, String nickName) {
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