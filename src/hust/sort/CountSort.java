package hust.sort;

/**
 * 计数排序（使用场景有限，小范围的整数）
 * 时间复杂度：O(n)
 * 额外空间复杂度：O(1)
 * 
 * @author 2015-12-04
 *
 */

public class CountSort {
	
	public static void main(String[] args) {
		int[] arr = {1,2,5,2,4,1,3,2,5,1,3,4,9};
		sort(arr, arr.length);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void sort(int[] arr, int len) {
		if(arr == null || len <= 0)
			return;
		int max = arr[0], min = arr[0];
		for(int i=1; i < len; i++) {
			if(arr[i] > max) { 
				max = arr[i];
			} else if(arr[i] < min) {
				min = arr[i];
			}
		}
		
		int[] tmp = new int[max - min + 1];
		for (int i = 0; i < len; i++) {
			tmp[arr[i]-min]++;  //最小值放在tmp的0位置，依此类推
		}
		
		int index =0;
		for (int i = 0; i < tmp.length; i++) {
			for(int j=0; j < tmp[i]; j++) {
				arr[index++] = i + min;
			}
		}
	}
}
