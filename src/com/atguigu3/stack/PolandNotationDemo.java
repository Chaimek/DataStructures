package com.atguigu3.stack;

import org.junit.jupiter.api.Test;

public class PolandNotationDemo {
    public static void main(String[] args) {
        PolandNotation polandNotation = new PolandNotation();
        int res = polandNotation.PolandNotations();
        System.out.printf("结果为：%d" ,res);
    }
}

class PolandNotation{

    public int PolandNotations() {
        String expression = "2+3+3*5-1*2";
        Stack2 operStack = new Stack2(10);
        Stack2 numStack = new Stack2(10);
        int num1=0;
        int num2=0;
        int res=0;
        int oper =0;
        int index =0;
        char ch = ' ';
        String keepNum ="" ;
        while (true){
            ch=expression.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)){
                if (operStack.isEmpty()){
                    operStack.push(ch);
                }else {
                    if (operStack.property(ch) <= operStack.property(operStack.peek())){
                        num1 = numStack.pop();
                        num2= numStack.pop();
                        oper=operStack.pop();
                        res = operStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }
            }else {
            keepNum += ch;
            if (index==expression.length()-1){
                numStack.push(Integer.valueOf(keepNum));
            }else {
                if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    numStack.push(Integer.valueOf(keepNum));
                    keepNum="";
                }
            }
            }
            index++;
            if (index == expression.length()){
                break;
            }
        }
        while (!operStack.isEmpty()){
//            这里有一个逻辑错误，当最后栈顶有两个连续的减号的时候，不能直接弹出来相减，要从最下面开始减，
            num1 = numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res = operStack.cal(num1,num2,oper);
            numStack.push(res);
        }
       return res;
    }

}

class Stack2 {
    private int maxSize;
    private int top;
    private int[] arr;

    public Stack2(int maxsize) {
        top = -1;
        this.maxSize = maxsize;
        arr = new int[maxSize];
    }

    public void push(int data) {
        if (top == maxSize - 1) {
            System.out.println("栈已满");
            return;
        }
        top++;
        arr[top] = data;
    }

    public void show() {
        if (top == -1) {
            System.out.println("栈已空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("数据为【%d】\n", arr[i]);
        }
    }

    public int pop() {
        if (top == -1) {
            throw new RuntimeException("栈为空");
        }
        int i = arr[top];
        top--;
        return i;
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    public int peek() {
        return arr[top];
    }

    //    设置优先级,假定数字越大，优先级越高
    public int property(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }


    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//左边减去右边的，也就是栈下面的减去栈上面的
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

    public boolean isOper(int oper) {
        if (oper == '+' || oper == '-' || oper == '*' || oper == '/') {
            return true;
        }
        return false;
    }
}
