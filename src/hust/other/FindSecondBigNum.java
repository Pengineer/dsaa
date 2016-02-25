package hust.other;

/**
 * 找到无序数组中第二大的数
 * 
 * @since 2015-12-15
 *
 */
public class FindSecondBigNum {

	public static void main(String[] args) {
		int[] arr = {6,3,6,7,2,9,9};
		System.out.println(find(arr));
	}
	
	public static int find(int[] arr) {
		int max = arr[0];
		int sec_max = Integer.MIN_VALUE;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				sec_max = max;
				max = arr[i];
			} else if (arr[i] < max && arr[i] > sec_max) {
				sec_max = arr[i];
			}
		}
		
		return sec_max;
	}
}
