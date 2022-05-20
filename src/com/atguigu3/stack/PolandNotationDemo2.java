package com.atguigu3.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotationDemo2 {
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 - ";
        System.out.println(getListString(suffixExpression));
        int col = col(getListString(suffixExpression));
        System.out.println(col);
    }

    //    不用一个一个的扫描，直接存入list集合中
    public static List<String> getListString(String suffixExpression) {
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s1 : s) {
            list.add(s1);
        }
        return list;
    }

    public static int col(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
//            正则表达式判断是不是数字
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(s)) {
                    res = num1 + num2;
                } else if ("-".equals(s)) {
                    res = num2 - num1;
                } else if ("*".equals(s)) {
                    res = num1 * num2;
                } else if ("/".equals(s)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("符号不正确");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
