package hust.times;

/**
 * 统计一个数字在排序数组中出现的次数
 * 
 * 因为是排序数组，因此首先就应该想到用二分查找法，但是一个数字可能连续存在多次，即使找到这个数字，也需要确定它第一次出现的
 * 位置和最后一次出现的位置，这也决定了算法的时间复杂度。
 * 如果找到了指定的数字，发现它左边的数字比它小，那么它就是第一次出现的位置，如果左边的数字还是它，那么就在用二分法递归查找
 * 左半部分，依次类推。
 * 
 * 其它非高效解法：
 * 1，顺序遍历，时间复杂度O(n)；
 * 2，哈希表，时间复杂度O(n)，空间复杂度O(n)。
 * 
 * 补充：对比@see JudgeAppearTimes 要求。
 * 
 * @since 2015-12-11
 *
 */

public class FindTimesOfNumber {

	public static void main(String[] args) {
		int[] arr = {3,3,3,3,4,6,8,8,9};
		System.out.println(getNumberOfK(arr, 9, 8));
	}
	
	public static int getNumberOfK(int[] arr, int length, int k) {
		int number = 0;
		if(arr != null && length > 0) {
			int first = getFirstK(arr, length, k, 0, length -1);
			int last = getLastK(arr, length, k, 0, length -1);
			if (first > -1 && last > -1)
				number = last - first + 1;
		}
		return number;
	}
	
	//找到k第一次出现的位置(注意和二分法找到k的位置有区别了)
	public static int getFirstK(int[] arr, int length, int k, int start, int end) {
		if(start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		int midData = arr[mid];
		if(midData == k) {
			if(mid == 0 || arr[mid - 1] != k) {
				return mid;
			} else {
				end = mid - 1;
			}
		} else if (midData > k) {
			end = mid - 1;
		} else {
			start = mid + 1;
		}
		return getFirstK(arr, length, k, start, end); //递归函数放在最后一行，那么整个递归结束时返回的结果就是最后一次递归的结果
	}
	
	//找到k最后一次出现的位置
	public static int getLastK(int[] arr, int length, int k, int start, int end) {
		if(start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		int midData = arr[mid];
		if(midData == k) {
			if(mid == length-1 || arr[mid + 1] != k) {
				return mid;
			} else {
				start = mid + 1;
			}
		} else if (midData > k) {
			end = mid - 1;
		} else {
			start = mid + 1;
		}
		return getLastK(arr, length, k, start, end);
	}
	
}
