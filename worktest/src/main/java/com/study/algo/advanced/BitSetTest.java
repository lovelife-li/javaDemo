package com.study.algo.advanced;


import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.BitSet;

/**
 * todo
 *
 * @author ldb
 * @date 2020/08/26
 */
public class BitSetTest {

    public static void main(String[] args) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);
        bits1.set(3);
        bits1.set(5);
        bits2.set(5);
        bits2.set(6);
        bits1.or(bits2);
        System.out.println(bits1);
        //输出的结果为{3,5,6}，也就是说满足bits1和bits2的文档都被返回。

        bits1 = new BitSet(16);
        bits2 = new BitSet(16);
        bits1.set(3);
        bits1.set(5);
        bits2.set(5);
        bits2.set(6);
        bits1.and(bits2);
        System.out.println(bits1);
        //输出的结果为{5}，只有同时满足bits1和bits2的文档才会被返回。

        int[] nums = {1, 4, 5, 6, 7, 2, 3};
        System.out.println(sortNums(nums));



    }

    public void testBloomFilter(){
        int total = 1000000; // 总数量
        BloomFilter<CharSequence> bf =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        // 初始化 1000000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        System.out.println("已匹配数量 " + count);
    }

    // 使用BitSet进行排序
    private static String sortNums(int[] nums) {
        long start = System.currentTimeMillis();
        System.out.println("开始排序");
        int len = nums.length;
        StringBuilder sb = new StringBuilder();
        BitSet bitSet = new BitSet(len);
        bitSet.set(0, len, false);
        for (int i = 0; i < len; i++) {
            bitSet.set(nums[i], true);
        }
        for (int i = 0; i < len; i++) {
            if (bitSet.get(i)) {
                sb.append(i).append(",");
            }
        }
        return sb.toString();
    }

}
