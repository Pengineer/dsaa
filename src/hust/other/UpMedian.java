package hust.other;

/**
 * 给定两个有序数组arr1和arr2，两个数组长度都为N，求两个数组中所有数的上中位数。
 * 例如：
 * arr1 = {1,2,3,4};
 * arr2 = {3,4,5,6};
 * 一共8个数则上中位数是第4个数，所以返回3。
 * 
 * arr1 = {0,1,2};
 * arr2 = {3,4,5};
 * 一共6个数则上中位数是第3个数，所以返回2。
 * 
 * 要求：时间复杂度O(logN)
 * 
 * 思路：
 * 对两个数组分别不断的求上中位数，不断的缩小两个数组的大小，并保证各自的子数组长度仍然一样，直到最后一个数。
 * 因为每次缩小两个数组时，一定是分别去掉相同数目的最大的部分和最小的部分，也就是说所求的中位数一定在剩下的
 * 两个子数组中。
 * 
 * @author 2015-12-20
 *
 */
public class UpMedian {

	public static void main(String[] args) {
		int[] arr1 = {1,2,3,4};
		int[] arr2 = {3,4,5,6};
		System.out.println(getUpMedian(arr1, arr2));
		System.out.println(findProcess2(arr1, arr1.length, arr2, arr2.length));
	}

	//方式一：递归实现
	public static int getUpMedian(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
			throw new RuntimeException("Your arr is invalid!");
		}
		return findProcess(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
	}
	
	public static int findProcess(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {
		if (start1 == end1) {
			return Math.min(arr1[start1], arr2[start2]);
		}
		// 元素个数为奇数，则offset为0；元素个数为偶数，则offset为1；
		int offset = ((end1 - start1 + 1) & 1) ^ 1;
		int mid1 = (start1 + end1) / 2;
		int mid2 = (start2 + end2) / 2;
		if (arr1[mid1] > arr2[mid2]) {
			return findProcess(arr1, start1, mid1, arr2, mid2 + offset, end2);     
		} else if (arr1[mid1] < arr2[mid2]) {
			return findProcess(arr1, mid1 + offset, end1, arr2, start2, mid2);
		} else {
			return arr1[mid1];
		}
	}
	
	//方式二：非递归实现(效率更好，也更好理解)
	public static int findProcess2(int[] arr1, int len1, int[] arr2, int len2) {
		if (arr1 == null || arr2 == null || len1 != len2) {
			throw new RuntimeException("Your arr is invalid!");
		}
		
		int start1= 0, end1 = len1-1;
		int start2 = 0, end2 = len2 -1;
		int mid1 = (start1 + end1) >> 1, mid2 = (start2 + end2) >> 1;
		int offset = ((end1 - start1 + 1) & 1) ^ 1;  //判断奇偶数
		while(start1 != end1) {
			if(arr1[mid1] > arr2[mid2]) {  //第一个数组的中位数大于第二个数组的中位数，去掉第一个数组中比该数组中位数大的部分，去掉第二个数组中比该数组中位数小的部分
				end1 = mid1;
				start2 = mid2 + offset;
			} else if(arr1[mid1] < arr2[mid2]) {
				start1 = mid1 + offset;
				end2 = mid2;
			} else {  //两中位数相同，那么整体中位数就是该中位数
				return arr1[mid1];
			}
			mid1 = (start1 + end1) >> 1;
			mid2 = (start2 + end2) >> 1;
			offset = ((end1 - start1 + 1) & 1) ^ 1;
		}
		return Math.min(arr1[start1], arr2[start2]);
	}
}
