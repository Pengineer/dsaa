package hust.permutation.combination;

/**
 * 题目：
 * 一个数组中含有8个数字，判断有没有可能把这8个数字分别放到正方体的8个顶点上，使得正方体的三组相对的面上的4个顶点的和都相等。
 * 
 * 显然这就是个排列问题，输出符合条件的排列。
 * 
 * 由于正方体的旋转对称性，输出结果中有重复的。
 * 
 * @since 2015-12-09
 *
 */
public class CubePermutation {

	public static void main(String[] args) {
		//假设正方体上面的4个点是数组的0,1,2,3这4个元素，其中0在左上方，其它三个点顺时针对应
		int[] arr = {1,2,3,4,5,6,7,8};
		process(arr);
	}
	
	//递归中不需要每次都判断输入的合法性，因此单独提一个函数出来
	public static void process(int[] arr) {
		if(arr == null || arr.length != 8) {
			throw new RuntimeException("Invalid parameter");
		}
		permutation(arr, 0, arr.length);
	}
	
	public static void permutation(int[] arr, int i, int len) {
		if(i == len) {
			judge(arr);
		} else {
			for (int j = i; j < arr.length; j++) {
				swap(arr, i, j);
				permutation(arr, i+1, len);
				swap(arr, i, j);
			}
		}
	}
	
	//排列好后的条件判断
	public static void judge(int[] arr) {
		if( arr[0] + arr[1] + arr[2] + arr[3] == arr[4] + arr[5] + arr[6] + arr[7] && 
			arr[0] + arr[1] + arr[4] + arr[5] == arr[2] + arr[3] + arr[6] + arr[7] &&
			arr[0] + arr[2] + arr[4] + arr[6] == arr[1] + arr[3] + arr[5] + arr[7] ) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
