package hust.other;

/**
 * 定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，如果arr[0]<arr[1]，那么arr[0]是局部
 * 最小；如果arr[N-1]<arr[N-2]，那么arr[N-1]是局部最小；如果0<i<N-1，既有arr[i]<arr[i-1]又有arr[i]<arr[i+1]，
 * 那么arr[i]是局部最小。给定无序数组arr，已知arr中任意两个相邻的数都不相等，写一个函数，只需返回arr中任意一个局
 * 部最小出现的位置即可。（O(n)的算法就不用考虑了）
 * 
 * 思路：
 * 利用数组的二分法和局部单调性。（非排序数组的二分法例子）
 * 
 * @since 2015-12-20
 *
 */
public class LocalLeast {

	public static void main(String[] args) {
		int[] arr = {6, 4, 3, 7, 8, 9};
		
		System.out.println(getTheLocalLeast(arr, arr.length));
	}

	public static int getTheLocalLeast(int arr[], int length) {
		if(arr == null || length <=0) {
			return -1;
		}
		
		if(arr[0] < arr[1] || length == 1) {
			return 0;
		}
		if(arr[length-1] < arr[length-2]) {
			return length -1;
		}
		
		/*
		 * 第一个数大于第二个数，该范围（只有两个元素）局部递减；最后一个数大于倒数第二个数，该范围也局部递减。
		 * 然后二分，看二分点处的局部单调性，如果左边局部单调递减（该二分点的值大于左边的那个数），那么该二分
		 * 点的左边一定存在一个满足条件的局部最小值。右边同理。
		 */
		int start = 1;
		int end = length - 2;
		int mid = (start + end) >> 1;
		while(start < end) {
			if(arr[mid] > arr[mid -1]) {
				end = mid - 1;
			} else if(arr[mid] > arr[mid + 1]) {
				start = mid + 1;
			} else {
				return mid;
			}
			mid = (start + end) >> 1;
		}
		return start;
	}
}
