package com.atguigu12.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList ;   //顶点集合
    private int[][] edges ; // 边集合
    private  int edgeCount; //多少条边
    private boolean[] isVisited ; //判断节点是否被访问

    public static void main(String[] args) {
        String[] vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(5);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0,1);
        graph.insertEdge(0,2);
        graph.insertEdge(1,2);
        graph.insertEdge(1,3);
        graph.insertEdge(1,4);

        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先");
        graph.bfs();
    }
    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        edgeCount= 0 ;
    }

    //返回节点的个数
    public int getNumberOfVertex(){
        return vertexList.size();
    }
    //返回边的个数
    public int getNumberOfEdgeCount(){
        return edgeCount;
    }
    //返回下标为i的节点信息
    public String getValueByIndex(int i ){
        return  vertexList.get(i);
    }

    //返回v1 -> v2的权值
    public int getWeight(int v1 , int v2){
        return edges[v1][v2] ;
    }

    //显示图
    public void showGraph(){
        for (int[] row : edges){
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     *    方法：
     *       获取下标为index的第一个临接节点的下标
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] >  0){
                return i ;
            }
        }
        return -1 ;
    }

    /**
     *  根据前一个临接节点获取下一个临接节点
     * @return 返回 -1 表示没找到
     */
    public int getNextNeighbor(int v1 ,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0 ){
                return i ;
            }
        }
        return -1 ;
    }


    /**
     * 对 i 这个节点进行深度优先遍历
     *      能把与i直接或间接连接的所有节点都输出
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited , int i){
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] =  true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            w=getNextNeighbor(i,w);
        }
    }
    public void dfs(){
        isVisited=new boolean[vertexList.size()];
        /**
         * 这里必须对所有的节点都使用 ，因为有的节点可能不连通 ！
         */
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    public void bfs(boolean[] isVisited , int i){
        int u = 0 ;  //表示队列的头结点对应的下标
        int w = 0 ;  //表示邻接节点
        /**
         *   使用 LinkedList 实现队列
         *         里面有 removeFirst() 与 addLast()方法
         *   这个队列的作用：
         *          只要有元素输出，就入队列，这个队列保存的是已经输出了的元素的序列
         */
        LinkedList queue = new LinkedList<>();

        System.out.print(getValueByIndex(i)+ "->");
        isVisited[i] = true ;
        //根据规则 ，输出之后就入队
        queue.addLast(i);

        while (!queue.isEmpty()){
            u = (int)queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1){
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true ;
                    queue.addLast(w);
                }
                w =  getNextNeighbor(u,w);

            }
        }

    }
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        /**
         * 这里必须对所有的节点都使用 ，因为有的节点可能不连通 ！
         */
        for (int i = 0; i < vertexList.size(); i++) {
            /**
             *  调用前必须判断是否已经被访问了
             */
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }


    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     *
     * @param v1 第一个顶点对应的下标
     * @param v2 第二个顶点对应的下标
     *           里面的 1 不是代表权值 ，而是表示 是否直接相连
     *           而0表示不相连
     */
    public void insertEdge(int v1 ,int v2 ){
        edges[v1][v2] = 1;
        edges[v2][v1] = 1;
        edgeCount ++ ;
    }
    //weight表示权值
    public void insertEdgeAndWeight(int v1 ,int v2 ,int weight){
        edges[v1][v2] =weight;
        edges[v2][v1] =weight;
        edgeCount ++ ;
    }


}
