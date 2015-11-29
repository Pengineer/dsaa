package hust.string;

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
 * 1，先排序，排序后相同的字符会贴在⼀起。 
 * 2，再遍历一次str进⾏检查即可。 
  *   关键在于对各种排序算法的深度理解。
 * 
 * @author 2015-11-29
 *
 */
public class JudgeAppearTimes {
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,10,4,5,6};
		System.out.println(judge1(arr, arr.length));
		System.out.println(judge2(arr, arr.length));
	}
	
	//要求一解法：哈希表实现，时间复杂度为O(N)，额外空间复杂度是O(N)。
	public static boolean judge1(int[] arr, int N) {
//		int[] hashMap = new int[256]; 简单的哈希计频是不可能的，这里是数字，不是字符。（字符最多256个，数字是无限的）
//		排序也是行不通的，因为没有时间复杂度是O(N)的排序算法
		
		int[] hashTable = new int[N];
		for (int i = 0; i < arr.length; i++) {
			hashTable[arr[i]%N]++;
		}
		for (int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] > 1) return false;
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
		
	}
	
//	public static void sortArray(int[] arr, int start, int end) {
//		if(start < end) {
//			int low = start, high = end, privot = arr[start];
//			while(low < high) {
//				while(low < high && arr[high] >= privot)
//					high--;
//				arr[low] = arr[high];
//				while(low < high && arr[low] <= privot)
//					low++;
//				arr[high] = arr[low];
//			}
//			arr[low] = privot;
//			sortArray(arr, start, low-1);
//			sortArray(arr, low+1, end);
//		}
//	}
	
}
