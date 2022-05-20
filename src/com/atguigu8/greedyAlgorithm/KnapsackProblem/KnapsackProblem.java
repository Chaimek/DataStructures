package com.atguigu8.greedyAlgorithm.KnapsackProblem;

import java.math.BigDecimal;
import java.util.*;

public class KnapsackProblem {


    public static void main(String[] args) {
        Goods a = new Goods(1,1,1);
        Goods b = new Goods(2,4,5);
        Goods c = new Goods(3,3,7);
        Goods d = new Goods(4,5,11);
        Goods[] goods = {a,b,c,d};
        int m = 5;
        knapsackProblem(goods,m);
    }

    /**
     *
     * @param goods   所有的商品
     * @param m      背包的总容量
     */
    public  static  void knapsackProblem(Goods[] goods,int m){
        //使用Array中的sort方法，在使用lambda风格让货物按照他的value属性递减排序
        Arrays.sort(goods, Comparator.comparingDouble((Goods good) -> good.getValue()).reversed());
        //剩余容量
        int  remainingCapacity = m ;
        //存放结果集，货物选择了多少
        List x = new ArrayList();
        //存放选择的货物的id
        List goodList = new ArrayList();
        //遍历每一个货物
        for (int i = 0; i < goods.length; i++) {
            if (remainingCapacity > goods[i].getWeight()){
                //  System.out.println("选择一个id为:" + goods[i].getId() + " 的货物" );
                goodList.add(goods[i].getId());
                x.add(1);
                //选择了一个货物，对应的剩余容量得减少
                remainingCapacity -= goods[i].getWeight();
            }else {
                BigDecimal remainingCapacityNum = new BigDecimal(remainingCapacity);
                BigDecimal goodsNum = new BigDecimal(goods[i].getWeight());
                //使用BigDecimal的divide进行两数相除，保留两位小数，向上取整
                BigDecimal ratio=remainingCapacityNum.divide(goodsNum,2,BigDecimal.ROUND_HALF_UP);
                // System.out.println("选择了"+temp+"个id为："+goods[i].getId()+"的货物");
                goodList.add(goods[i].getId());
                x.add(ratio);
                break;
            }
        }
        System.out.println("选择的货物的id："+ goodList);
        System.out.println("货物选择了多少："+ x);
    }
}
class Goods{
    //商品号
    private int id ;
    //商品重量
    private int weight;
    //商品价值
    private int price;
    //单位重量的价值
    private double value ;

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    //通过构造方法计算出value值
    public Goods(int id, int weight, int price) {
        this.id = id;
        this.weight = weight;
        this.price = price;
        BigDecimal  weightNum = new BigDecimal(weight);
        BigDecimal  priceNum = new BigDecimal(price);
        this.value = priceNum.divide(weightNum,2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
