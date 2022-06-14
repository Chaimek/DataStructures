package com.atguigu10.tree.huffmantree.decoding;



import java.util.*;

public class HuffmanDecoding {
    public static void main(String[] args) {
        String temp ="abc";
        System.out.println(temp.substring(1,3));
    }

    /**
     *  功能 ：
     *      将 bytes 数组转化为二进制
     *      注意：
     *          最后一个数，根本不需要移 7 位
     * @param
     * @return
     */

    public  StringBuilder byteDecodingToStr(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <bytes.length -1 ; i++) {
            for (int j = 7; j >=0 ; j--) {
                stringBuilder.append((bytes[i] & (1 << j))==0?"0":"1");
            }
        }
        //转化为二进制toBinaryString()方法不带前缀
        String s = Integer.toBinaryString(bytes[bytes.length - 1]);
        stringBuilder.append(s);
        return stringBuilder;
    }


    /**
     * 第一种
     *  ·功能：
     *      将 bytes 数组转化为二进制
     * @param bytes
     * @return
     */
    public  StringBuilder  byteToBinaryStr(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag =true;
        for (int i = 0 ; i < bytes.length ; i ++){
//            flag = (i == bytes.length -1);
            if (i==bytes.length-1){
                flag=false;
            }
            stringBuilder.append( byteToBinaryStr(flag,bytes[i])) ;
        }
        return stringBuilder ;
    }

    /**
     *   功能 ： 把一个 byte变成二进制
     * @param flag 判断要不要补位
     * @param b
     * @return
     */
    private String byteToBinaryStr(boolean flag , byte b){
        int temp =  b ;
        if (flag == true){
            temp |= 256 ;
        }
        //toBinaryString()这个方法会把给的数转化为二进制，没有前缀，
        String str = Integer.toBinaryString(temp);
        if (flag){
            //这里有前缀的原因是做了与运算
            return str.substring(str.length()-8);
        }else {
            return str ;
        }
    }

    /**
     *
     * @param huffmanCode  哈夫曼编码
     * @param bytes   需要解码的数据
     * @return  解码好的 byte[]
     */
    public byte[] decoding(Map<Byte,String> huffmanCode , byte[] bytes){
        //第一步、先把压缩的byte数组转化为二进制
//        StringBuilder stringBuilder = byteToBinaryStr(bytes);
        StringBuilder stringBuilder = byteDecodingToStr(bytes);
        //第二步：逆转哈夫曼编码
        Map<String , Byte>  map = new HashMap<String,Byte>();

        for (Map.Entry<Byte,String> data: huffmanCode.entrySet()){
            map.put(data.getValue(),data.getKey());
        }

        List<Byte> list = new ArrayList<>();

        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte temp= null;

            while (flag){
                String str = stringBuilder.substring(i,i+count);
                 temp=map.get(str);
                 if (temp != null){
                     flag=false;
                 }else {
                     count++;
                 }
            }
            list.add(temp);
            i += count;

        }

        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result ;

    }
}
