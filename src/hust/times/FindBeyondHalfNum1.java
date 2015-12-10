package hust.times;

/**
 * 在数组中打印出现次数大于一半的数。如果不存在，什么也不打印。 
 * 
 * 说明：
 * 写这篇算法的目的其实最主要是想说明如何查找一个无序数组的中位数。因为在FindBeyondHalfNum2中已经有对本问题更好一点的解法。
 * 此处也顺便扩展一下思路吧。（本算法改变了数组元素的位置）
 * 
 * 题目思路：
 * a.基于快排的Partition函数的算法实现，时间复杂度O(n)，如果数组中有出现次数大于一半的数，那么数组排序后中间的数字一定是该数，
 * 该数值就是统计学上的中位数。现在就转为求数组的中位数问题。
 * b.在快排中，我们会随机选择一个数，然后找到该数在数组中的位置，比该数小的放左边，比该数大的放右边，如果该数的下标刚好是n/2，
 * 那么该数就是中位数，如果该数的下标比n/2大，那么中位数在它的左边，如果该数的下标比n/2小，那么中位数在它的右边。
 * 
 * 推广：
 * patition函数，可用于求与大小相关的问题：可以求数组中任意第K大的数字；可以求数组中最小的k个数。
 * 
 * @author 2015-12-09
 * 
 */
public class FindBeyondHalfNum1 {

	public static void main(String[] args) {
		int[] arr = {1,2,3,2,1,2,2};
		System.out.println(MoreThanHalfNum(arr, arr.length));
	}
	
	public static int MoreThanHalfNum(int[] arr, int len) {
		if(arr == null || len <= 0) {
			throw new RuntimeException("Invalid input");
		}
		
		int start =0;
		int end = len-1;
		int mid = len >> 1;
		int location = patition(arr, start, end);
		while(location != mid) { //有序数组的中位数一定在数组角标的正中间
			if(location > mid) {
				end = location-1;
				location = patition(arr, start, end);
			} else {
				start = location + 1;
				location = patition(arr, start, end);
			}
		}
		int result = arr[location];
		return checkMoreThanHalf(arr, result, len) ? result : -1;
	}
	
	//所找的中位数不一定满足条件，需要进一步校验
	public static boolean checkMoreThanHalf(int[] arr, int result, int len) {
		int times = 0;
		for (int i = 0; i < len; i++) {
			if(arr[i] == result) {
				times++;
			}
		}
		return times > (len >> 1) ? true : false;
	}

	public static int patition(int[] arr, int start, int end) {
		int pivot = arr[start];
		while(start < end) {
			while(start < end && arr[end] >= pivot)
				end--;
			arr[start] = arr[end];
			while(start < end && arr[start] <= pivot)
				start++;
			arr[end]=arr[start];
		}
		arr[start] = pivot;
		return start;
	}

}
