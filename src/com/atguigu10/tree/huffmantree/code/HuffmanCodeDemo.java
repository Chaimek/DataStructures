package com.atguigu10.tree.huffmantree.code;

import java.util.*;

public class HuffmanCodeDemo {
    public static void main(String[] args) {
        //第二步：把字符串封装为byte[]
        String str = "i like like like java do you like a java";
//        byte[] bytes = str.getBytes();
//        List<Node> list = HuffmanCode.getList(bytes);
//        Node huffmanTree = HuffmanCode.createHuffmanTree(list);
//        huffmanTree.preOrder();

        Node huffmanCodeRoot = HuffmanCode.createHuffmanTree(str);

        huffmanCodeRoot.preOrder();
        HuffmanCode huffmanCode = new HuffmanCode();
        Map<Byte, String> codes = huffmanCode.getCodes(huffmanCodeRoot);
        System.out.println(codes);
        byte[] zip = HuffmanCode.zip(str.getBytes(), codes);
        System.out.println(Arrays.toString(zip));

        String str2 = "i " ;
        Node huffmanTree = HuffmanCode.createHuffmanTree(str2);
       huffmanTree.preOrder();
        HuffmanCode huffmanCode2 = new HuffmanCode();
        Map<Byte, String> codes1 = huffmanCode2.getCodes(huffmanTree);

        System.out.println(codes1);



    }

}

class HuffmanCode{



     Map<Byte,String> codeBuffer = new HashMap<>();
     StringBuilder stringBuilder = new StringBuilder();



     public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
         StringBuilder stringBuilder = new StringBuilder();

         for (byte b : bytes){
             //将 byte 中的数据添加到缓冲区中
             stringBuilder.append(huffmanCodes.get(b));
         }
         //计算数组长度
         int len  = (stringBuilder.length() + 7) / 8;

         byte[] huffmanToCodes = new byte[len];
         int index = 0;
         String substring;
         for (int i = 0; i < stringBuilder.length(); i+=8) {
             //防止下标越界
             if (i+8 >stringBuilder.length()){
                  substring= stringBuilder.substring(i);
             }else {
                 substring=stringBuilder.substring(i,i+8);
             }
             //将二进制转化为十进制
             huffmanToCodes[index] = (byte) Integer.parseInt(substring,2);
             index++ ;
         }
         return huffmanToCodes;
     }
    /**
     * 方法重载 ，不能设置为静态！
     * @param node 节点
     * @return  对应的编码
     */
    public  Map<Byte,String> getCodes(Node node){
        getCodes(node,"",stringBuilder);
        return codeBuffer ;
    }

    /**
     * 递归思想 ：
     *
     * @param node 当前节点
     * @param code  规定 ：向左为 0 ，向右为 1
     * @param stringBuilder 没一次递归，把上一次的结果传进来
     */
    private  void getCodes(Node node,String code , StringBuilder stringBuilder){
        /**
         * 注意 ：
         *    这里必须重新 new StringBuilder() ，因为对没一种情况，编码都是不一样的，不能只用一个，否则会把上一次的编码连着一起
         */
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        //判断当前节点是否为空
        if (node != null){
            //判断是不是叶子节点
            if (node.getData() == null){
                getCodes(node.getLeft(),"0",stringBuilder1);
                getCodes(node.getRight(),"1",stringBuilder1);
            }else {
                //是叶子节点，就说明这个节点的编码已经完成，加入的Map中
                codeBuffer.put(node.getData(),stringBuilder1.toString());
            }
        }
    }



    /**
     *  方法重载
     * @param str
     * @return
     */
    public static Node createHuffmanTree(String str){
        //第二步：把字符串封装为byte[]
        byte[] bytes = str.getBytes();
        //第三步 ：把byte[] 封装到 list中 ，并统计个数
        List<Node> list = HuffmanCode.getList(bytes);
        //第四步 ： 创建霍夫曼树
        Node huffmanTree = HuffmanCode.createHuffmanTree(list);
        return huffmanTree ;
    }
    //第三步 ：把byte[] 封装到 list中 ，并统计个数
    private static List<Node> getList(byte[] bytes){
        List<Node> list = new ArrayList<>() ;
        Map<Byte , Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.get(b) == null){
                map.put(b,1);
            }else {
                map.put(b,map.get(b)+1);
            }
        }
        for(Map.Entry<Byte,Integer> entry : map.entrySet()){
            list.add(new Node(entry.getKey(),entry.getValue()));
        }
        return list ;
    }
    //第四步 ： 创建霍夫曼树
    private static Node createHuffmanTree(List<Node> list){
        while (list.size() >1){
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(null,leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }
    public void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("当前霍夫曼树为空，无法遍历");
        }
    }
}
//第一步 ： 创建Node 节点
class Node implements Comparable<Node>{
    private Byte data ;
    private int weight ;
    private Node left ;
    private Node right ;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        //按权值从小到大排序
        return this.weight - node.weight;
    }
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}