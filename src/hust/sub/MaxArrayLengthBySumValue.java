package hust.sub;

import java.util.HashMap;

/**
 * 题目：未排序数组中累加和为给定值的最长子数组长度
 * 问题描述：给定一个数组 arr，该数组无序，每个值可正，可负，可0，再给定一个正数 k。求 arr 所有子数组中所有元素相加和为 k 的最长子数组长度。
 * 例如： arr = [1,2,3,3,1],k=6. 累加和为6的最长子数组为[1,2,3],所以返回结果为 3。
 * 
 * 补充题目【1】： 
 * 问题描述：给定一个数组 arr，该数组无序，每个值可正，可负，可0。求 arr 所有子数组中正数与负数个数相等的最长子数组长度。
 * 解法：可以把原数组中的正数变成1，负数变成-1 , 0 保持不变，最终问题求累加和为0的最长子数组
 * 
 * 补充题目【2】： 
 * 问题描述：给定一个数组 arr，该数组无序，每个值只有1或者0两种取值。求 arr 所有子数组中0和1个数相等的最长子数组长度。
 * 解法：可以把原数组中的0变成-1，1保持不变，最终问题求累加和为0的最长子数组。
 * 
 * @author liangjian
 * @since 2016-01-29
 * 
 * @note ***** 本题属于一大类题型的经典原型题，需研究透彻。
 * 
 */
public class MaxArrayLengthBySumValue {

	/*
	 * 假设sum[i]是arr[0..i]上的和。sum[i] + k = sum[j]，其中k=sum[i+1..j]，也就是我们要求的子数组长度。
	 * 声明一个map集合，用来存放第一次出现任意sum[i]的位置，那么当遍历到j位置时，要想得到j之前的一个子数组的和为k=sum[i+1..j]，那么我们
	 * 只需要判断map集合中是否有和和sum[i] = sum[j] - k的元素即可。最终关系就是：sum[j] - (sum(j) - k) = k，j是当前遍历到的位置。
	 */
	public static int getMaxLength(int[] arr, int k) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		int len =0;
		int sum=0;
		map.put(0, -1); // 最需要注意的地方：1，为什么放一个key=0的元素？因为如果我们所求的子数组是从数组第一个元素开始的，那么我们的map需要一个为0来满足if进入条件；2，为什么值是-1？因为脚标从0开始的。
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
			if(map.containsKey(sum - k)) { // 如果包含key
				len = Math.max(i - map.get(sum-k), len);
			}
			if(!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,3,1};
		System.out.println(getMaxLength(arr, 6));
	}
}
