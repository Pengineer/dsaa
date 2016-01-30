package hust.sub;

/**
 * 题目：由正整数构成的未排序数组中累加和为给定值的最长子数组长度
 * 
 * 
 * @see MaxArrayLengthBySumValue 多的唯一的条件就是正整数，额外空间复杂度降为O(1)
 * @see hust.other.FindContinuousSequenceByAppointedSum
 * @author liangjian
 *
 */
public class MaxArrayLengthBySumValue_2 {
	
	public static int getMaxLen(int[] arr, int k) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int left =0;
		int right =0;
		int sum =arr[0]; // sum 为从arr[left]到arr[right]的和
		int len =0;
		for(int i=0; i<arr.length; i++) {
			if(sum == k) {
				len = Math.max(right - left + 1, len);
			} else if(sum < k) { // right右移
				right++;
				if(right == arr.length) {
					break;
				}
				sum += arr[right];
			} else { // left右移
				left++;
				if(left > right) {
					break;
				}
				sum -= arr[left-1];
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,1};
		System.out.println(getMaxLen(arr, 6));
	}

}
