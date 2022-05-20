package com.atguigu9.hashtab;

public class HashtabDemo {
    public static void main(String[] args) {
        Hashtab hashtab = new Hashtab(7);
        hashtab.addByIdSize(new Emp(14,"tom"));
        hashtab.addByIdSize(new Emp(21,"tom"));
        hashtab.addByIdSize(new Emp(7,"tom"));
        hashtab.addByIdSize(new Emp(0,"tom"));
        hashtab.addByIdSize(new Emp(28,"tom"));
        hashtab.list();
    }

}

class Hashtab{
    private EmpLinkedList[] empLinkedLists ;
    private int size ;
    public Hashtab(int size){
        this.size = size ;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public int  funTab(int no){
        return no % size;
    }

    public void add(Emp emp){
       int curHashTableNum =funTab(emp.id);
        empLinkedLists[curHashTableNum].add(emp);
    }

    public void addByIdSize(Emp emp){
        int curHashTableNum =funTab(emp.id);
        empLinkedLists[curHashTableNum].addByIdSize(emp);
    }

    public void list(){
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void  find(int id){
        int curHashTableNum =funTab(id);
        Emp emp = empLinkedLists[curHashTableNum].findEmpById(id);
        System.out.println(emp);
    }

}
class Emp{
    public int id ;
    public String name;
    public Emp next ;
    public Emp(int id ,String name){
        this.id = id ;
        this.name = name ;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class EmpLinkedList{
    private Emp head;

    public void  add(Emp emp){
        if(head==null){
            head = emp;
            return;
        }
        Emp curEmp = head ;
        while (true){
            if (curEmp.next == null){
                curEmp.next = emp;
                return;
            }
            curEmp = curEmp.next;
        }
    }

    public  void  list(int no){
        if (head == null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        Emp curEmp = head ;
        System.out.print("第"+(no+1)+"链表员工有：");
        while (true){


            System.out.printf("==> id: %d name: %s ",curEmp.id,curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public  Emp  findEmpById(int id){
        if (head == null){
            System.out.println("没有此人");
            return null;
        }
        Emp curEmp = head ;
        while (true){
            if (curEmp.id == id){
                return curEmp;
            }
            curEmp=curEmp.next;
        }
    }

    public void addByIdSize(Emp emp){
        if (head == null){
            head= emp;
            return;
        }
        Emp temp = new Emp(-10,"");
        temp.next = head;
        Emp curEmp = temp;
        while (true){
            if (curEmp.next == null || curEmp.next.id >= emp.id){
                break;
            }
            curEmp=curEmp.next;
        }
        emp.next=curEmp.next;
        curEmp.next=emp;


    }
}


