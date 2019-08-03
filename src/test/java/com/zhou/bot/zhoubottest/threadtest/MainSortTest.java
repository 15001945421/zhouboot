package com.zhou.bot.zhoubottest.threadtest;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName MainSortTest
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/11 9:17
 */
public class MainSortTest {

    //参考链接：https://blog.csdn.net/jianyuerensheng/article/details/51254415

    /*冒泡思想:相邻2个元素做比较，每次交换后，用交换后的元素与后面的元素依次做比较。
    比如：abcd a>b,那么a与b交换，然后b与c比较，b>c，那么b与c交换，然后c与d比较，依次类推，每个元素依次做这样的比较与交换。
    int[] a = {3,2,5,1,4,6,9,8};
    23514698 2&3  i=0时，第1次循环 j=1
    23514698 2&5  i=0时，第2次循环 j=2
    13524698 2&1  i=0时，第3次循环 j=3
    13524698 1&4  i=0时，第4次循环 j=4
    13524698 1&6  i=0时，第5次循环 j=5
    13524698 1&9  i=0时，第6次循环 j=6
    13524698 1&8  i=0时，第7次循环 j=7

    13524698 3&5  i=1时，第1次循环 j=2
    12534698 3&2  i=1时，第2次循环 j=3
    12534698 2&4  i=1时，第3次循环 j=4
    12534698 2&6  i=1时，第4次循环 j=5
    12534698 2&8  i=1时，第5次循环 j=6
    12534698 2&9  i=1时，第6次循环 j=7

    12354698 5&3 i=2时，第1次循环 j=3
    ......
    */
    public static void maoPao() {
        int[] a = {3, 2, 5, 1, 4, 6, 9, 8};
        for (int i = 0; i < a.length; i++) {
            for (int k = i + 1; k <= a.length - 1; k++) {
                if (a[i] > a[k]) {
                    int temp = a[i];//3       i=0  k=1
                    a[i] = a[k];//a[0] = 1    i=0  k=1
                    a[k] = temp;//a[1] = 3    i=0  k=1
                }
            }
        }
        System.out.println(JSON.toJSONString(a));
    }

    /*插入思想：设有序集合A，无序集合B，待排序集合C，将C中第1个元素放入到A中，
      从C中第2个元素开始所有元素都放入B，将B中的元素依次一个个插入到A中，
      向A中每插入一个元素时则先与A中其他元素作比较，将最小的元素插入到最前面。
      int[] a = {3,2,5,1,4,6,9,8};
      23 514698 i=1  n=0  2&3
      235 14698 i=2  n=1  3&5
      2315 4689 i=3  n=2  5&1
      2135 4698 i=3  n=1  3&1
      1235 4698 i=3  n=0  1&2
     */
    public static void chaRu() {
        int[] a = {3, 2, 5, 1, 4, 6, 9, 8};

        //**********方式1**********************
        /*int n=0,insert=0;// 要插入的数据
        for(int i=1;i<a.length;i++){//从数组的第二个元素开始循环将数组中的元素插入
            insert = a[i];// 设置数组中的第2个元素为第一次循环要插入的数据
            n = i-1;
            while (n>=0 && insert<a[n]){
                a[n + 1] = a[n];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                n--;
            }
            a[n + 1] = insert;// 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
        }*/

        //**********方式2**********************
        int index = 0;//插入位置索引下标
        int insert = 0;// 要插入的数据
        for (int i = 0; i < a.length; i++) {//从数组的第1个元素开始循环插入
            insert = a[i];// 从第1个元素开始，第1个默认为有序的，while不会走到
            index = i;
            while (index > 0 && insert < a[index - 1]) {
                a[index] = a[index - 1];// 如果要插入的元素小于前面的元素，将其插入到前面
                index--;//交换后，依次再和前面有序数据集合中的数据一个个做次比较
            }
            a[index] = insert;//插入元素到索引位置
        }
        System.out.println(JSON.toJSONString(a));
    }

    //选择：设待排序集合A，有序集合B，每次将A中的查找到的最小元素放入B中即可。
    // int[] a = {3,2,5,1};
    /* 1325 第1次 循环查找最小的元素为1
     *1235 第2次 循环查找最小的元素为2
     *......
     **/
    public static void xuanZhe() {
        int[] a = {3, 2, 1, 4, 5};
        int index = 0;//最小值索引位置
        int min = 0;//最小值
        for (int i = 0; i < a.length; i++) {//从0开始，一个个找
            min = a[i];//默认当前下标为最小值
            index = i;//默认当前下标为最小值的索引下标
            for (int j = i + 1; j <= a.length-1; j++) {
                if (min > a[j]) {
                    min = a[j];
                    index = j;
                }
            }
            //已经找到了最小值，那么下一步就是交换值：最小值与当前值交换
            int tp = a[i];
            a[i] = a[index];
            a[index] = tp;
        }
        System.out.println(JSON.toJSONString(a));
        System.out.println(1/2);
    }

    public static void zheBanPaiXu(){//https://blog.csdn.net/weixin_42245157/article/details/80458542
        int[] a = {3, 2, 1, 4, 5, 7};
        for(int i = 0; i < a.length; i++){
            int avg = a.length/2;
            int st = 0;
            int en = 0;
            if(a[i]>a[avg]){


            }else{

            }

        }
    }
    public static void main(String[] args) {
        xuanZhe();
    }
}
