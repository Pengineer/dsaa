package hust.other;

/**
 * 寻找数组中最小元素，该数组是一个升序排序数组的一个旋转
 * 
 * 例如[3,4,5,1,2]，它是[1,2,3,4,5]的一个旋转.
 * 
 * 解答：
 * 本题可以使用顺序遍历的方式，时间复杂度为O(n)，但是显然这没有用到顺序数组的旋转特性。
 * 考察核心：排序数组的二分查找。
 * 
 * @author 2015-12-05  <剑指offer>
 *
 */
public class FindMinNumInRotateArray {

	public static void main(String[] args) {
		int[] arr = {6,7,1,2,3,4,5};
		System.out.println(findMin(arr, arr.length));

	}
	
	public static int findMin(int[] arr, int len) {
		if(arr == null || len <=0) {
			throw new RuntimeException("Invalid parameters");
		}
		
		//定义三个指针
		int start =0;
		int end = len-1;
		int mid = start;
		while(arr[start] >= arr[end]) {
			if(end - start == 1) {//最终start和end指针位置
				mid = end;
				break;
			}
			
			mid = (start + end) /2;
			if(arr[mid] == arr[start] && arr[mid] == arr[end]) { //特例：[1,1,0,1,1,1,1]  [1,1,1,1,0,1,1]只能顺序查找
				return findMinInOrder(arr, len);
			}
			
			if(arr[mid] >= arr[start]) {
				start = mid;
			} else if(arr[mid] <= arr[end]) {
				end = mid;
			}
		}
		return arr[mid];
	}
	
	public static int findMinInOrder(int[] arr, int len) {
		int min = arr[0];
		for (int i = 1; i < len; i++) {
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
	}

}
