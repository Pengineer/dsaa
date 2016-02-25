package hust.times;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 如果数组长度为N，判断是否数组中所有的数字都只出现一次。按下列两个
 * 要求分别实现这一功能： 
 * 要求一：时间复杂度为O(N) 
 * 要求二：额外空间复杂度必须为O(1)的情况下，请做到时间复杂度尽量低。
 * 
 * 要求一的解法：
 * 1，在遍历str字符的过程中，用map记录某一种字符是否出现过，检查所有字符都不重复出现即可。 
 * 2，map的实现可以是固定长度的数组，也可以是哈希表。
 * 
 * 要求二的解法： 
 * 1，先排序，排序后相同的字符会贴在一起。 
 * 2，再遍历一次str进行检查即可。 
 *    关键在于对各种排序算法的深度理解。
 * 
 * @since 2015-11-29
 *
 */
public class JudgeAppearTimes {
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,10,4,5,6};
		System.out.println(judge1(arr, arr.length));
		
//		System.out.println(judge2(arr, arr.length));
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	//要求一解法：哈希表实现，时间复杂度为O(N)，额外空间复杂度是O(N)。
	public static boolean judge1(int[] arr, int N) {
//		int[] hashMap = new int[256]; 简单的哈希计频是不可能的，这里是数字，不是字符。（字符最多256个，数字是无限的）
//		排序也是行不通的，因为没有时间复杂度是O(N)的排序算法
		
		HashMap<Integer, Integer> ht = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			ht.put(i, ht.get(arr[i]) == null ? 1 : (ht.get(arr[i]) + 1));
		}
		
		Iterator<Entry<Integer, Integer>> iterator = ht.entrySet().iterator();
		while (iterator.hasNext()) { 
			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next(); 
			int val = (Integer) entry.getValue();
			if(val != 1) return false;
		} 
		
		return true;
	}
	
	//要求二解法：先排序，但是只能使用额外空间复杂度是O(1)的排序算法，只能使用插入排序和非递归的堆排序，显然后者更快
	public static boolean judge2(int[] arr, int N) {
		sortArray(arr, 0, N-1);
		for (int i = 1; i < arr.length; i++) {
			if(arr[i] == arr[i-1]) return false;
		}
		return true;
	}
	
	public static void sortArray(int[] arr, int start, int end) {
		buildMaxHeap(arr, end);
		while(end > 0) {
			int tmp = arr[0];
			arr[0] = arr[end];
			arr[end] = tmp;
			heapAdjust(arr, 0, --end);
		}
	}
	
	public static void buildMaxHeap(int[] arr, int len) {
		for(int i = len/2 -1; i>=0; i--) {
			heapAdjust(arr, i, len);
		}
	}
	
	public static void heapAdjust(int[] arr, int node, int len) {
		int left = getLeftChild(node);
		int right = getRightChild(node);
		int max = node;
		while(max <= len) {
			if(left <= len && arr[left] > arr[max])
				max = left;
			if(right <= len && arr[right] > arr[max])
				max = right;
			if(max != node) {
				int tmp = arr[max];
				arr[max] = arr[node];
				arr[node] = tmp;
				
				node = max;
				left = getLeftChild(node);
				right = getRightChild(node);
			} else
				break;
		}
	}
	
	public static int getLeftChild(int i) {
		return i*2 +1;
	}
	
	public static int getRightChild(int i) {
		return i*2 +2;
	}
	
}
