package com.atguigu14.bruteforce;

/**
 *   暴力匹配解决字符串查找问题
 */
public class ViolentMatching {
    public static void main(String[] args) {
        String str1 = "abc123absdabcdcd";
        String str2 = "abcd" ;
        int i = violentMatching(str1, str2);
        System.out.println(i);
    }

    /**
     *  暴力匹配查找在 str1 中第一个出现 str2字符的下标
     * @param str1  待查找的字符串
     * @param str2   目标字符串
     * @return 返回在str1 中元素的下标
     */
    public static int violentMatching(String str1 , String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0; //指向 str1
        int j = 0; //指向 str2
        if (s1Len < s2Len) {
            return -1;
        }
        while (i < s1Len && j < s2Len) {

            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }

        }
        if ( j == s2Len){
            return i-j ;
        }else {
            return -1 ;
        }


    }
}
