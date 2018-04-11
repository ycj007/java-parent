package com.ycj.string.countsort;

import java.util.Arrays;

public class countSort {
    public static void  main(String[] args){
        int[] nums={2,3,4,1,2,4,3,1,2,2,1};
        countSort sort=new countSort();
        sort.indecCountIndex(nums);
    }

    public static void indecCountIndex(int[] nums){
        int[] count=new int[6];
         //计算频率
        for(int i=0;i<nums.length;i++){
            count[nums[i]+1]++;
        }
        System.out.println(Arrays.toString(count));
       //将频率转化为索引
        for(int i=1;i<count.length;i++){
            count[i]=count[i]+count[i-1];
        }
        System.out.println(Arrays.toString(count));
      //数据分类
        int[] aux=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            aux[count[nums[i]]++]=nums[i];
        }
        System.out.println(Arrays.toString(aux));
      //回写数据（我这里是打印）
        for(int i=0;i<nums.length;i++){
            System.out.print(aux[i]+" ");
        }
    }
}

