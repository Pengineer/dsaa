package hust;

/**
 * 题目：
 * 	给定一个整型数组arr，返回如果排序之后，相邻两数的最大差值。
 * 举例：
 * 	arr=[9,3,1,10]。如果排序，结果为[1,3,9,10]，9和3的差为最大差值，故返回6.
 * 解答：
 * 	本题如果用排序来做，时间复杂度是O(N*logN)，而如果利用桶排序的思想（不是直接进行桶排序），尅做到时间复杂度O(N)，额外空间复杂度O(N)。遍历
 * arr找到最小值和最大值，分别记为min和max。如果arr的长度为N，那么我们准备N+1个桶，把max单独放在第N+1号桶里。arr中在[min,max)范围上的数放在
 * 1~N号桶里，对于1~N号桶来说，负责的区间大小为(max-min)/N。比如长度为10的数组arr中，最小值为10，最大值为110。那么就准备11个桶，arr中等于
 * 110的数全部放在11号桶里面...，区间[100,110)的数全部放在10号桶里面。那么如果一个数为num，它应该分配进(num-min)*len/(max-min)号桶里。
 * arr一共有N个数，min一定会放进1号桶里，max一定会放进最后的桶里，所以如果把所有的数放入N+1个桶中，必然有桶是空的。如果arr经过排序，相邻的数
 * 有可能此时在同一个桶中，也可能在不同的桶中。在同一个桶中的任何两个数的差值都不会大于区间值，而在空桶左右两边不空的桶里，相邻数的差值肯定大
 * 于区间值。所以产生最大差值的两个相邻数肯定来自不同的桶。所以只要计算桶之间数的间距就可以了，也就是只用记录每个桶的最大值和最小值，最大差值
 * 只可能来自某个非空桶的最小值减去前一个非空桶的最大值。
 * 
 * @author liangjian
 * @date 2016-01-18
 */
public class MaxGap {
	
	public static void main(String[] args) {
		int[] arr = {9,3,1,10};
		System.out.println(maxGap(arr));
	}
	
	public static int maxGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		boolean[] hasNum = new boolean[len + 1]; // 正常情况下这里应该需要初始化数组
		int[] maxs = new int[len + 1];
		int[] mins = new int[len + 1];
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max); // 找到元素nums[i]所在的桶
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i]; // nice
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = 0;
		int i = 0;
		while (i <= len) {
			if (hasNum[i++]) { // 找到第一个不为空的桶
				lastMax = maxs[i - 1];
				break;
			}
		}
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}
	
	// 使用long类型是为了防止相乘时溢出
	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}
}
