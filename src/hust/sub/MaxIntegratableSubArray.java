package hust.sub;

import java.util.HashSet;

/**
 * 先给出可整合数组的定义： 如果一个数组arr在排序之后，从最小值到最大值的顺序中，每相邻两个数之间差的绝对值都为1，则arr为可整合数组。 
 * 例如： arr = {5，3，4，6，2}，再排序之后为：{2，3，4，5，6}，排序后符合每相邻两个数之间差的绝对值都为1，所以arr是可整合数组。 
 * 
 * 给定一个整形数组arr，请返回其中长度最大的可整合子数组的长度。
 * [5,0,1,2,4,3,9]，最长可整合子数组为[5,0,1,2,4,3]，所以返回6
 * [6,7,3,0,1,2,4,7]，最长可整合子数组为[3,0,1,2,4]，所以返回5
 * 要求：如果数组长度为N，时间复杂度请达到O(N^2)
 * 
 * @author liangjian
 * @since 2016-01-29
 *
 */
public class MaxIntegratableSubArray {

	//观察可整合数组的特点：1，没有重复元素；2，最大值-最小值+1=数组的长度
	public static int getMaxLength(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<Integer>();// hash表判断重复元素（哈希表的增删操作时间复杂度可看做O(1)）
		int len =0;
		for(int i=0; i<arr.length; i++) { // 以i为起点，往后扩
			int max =Integer.MIN_VALUE;
			int min =Integer.MAX_VALUE;
			for(int j=i; j<arr.length; j++) {
				if(set.contains(arr[j])) { // 满足特性1
					break;
				}
				max = Math.max(max, arr[j]);
				min = Math.min(min, arr[j]);
				if(max - min == j - i) { // 满足特性2
					len = Math.max(len, j- i + 1);
				}
			}
			set.clear(); // 每一次外层遍历都需要清空集合
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] arr = {5,0,1,2,4,3,9};
		System.out.println(getMaxLength(arr));
	}
}
