package hust.permutation.combination;

/**
 * 今天学习了用递归来实现数组的排列组合。发现之前的八皇后问题就是一个数组的排列问题，说到底排列也是一个试探的过程，即通常所说的回溯法
 * 这才是正真的模板式编程。
 * 
 * 前面的处理是每处理一个元素，判断一次，这里是将每一个排列的结果进行综合判断。
 * 
 * 补充：八皇后问题同样可以用排列的思维来解决，定义一个长度为8的数组，数组中第i个元素的值j表示位于第i行的第j列有皇后，显然可用0-7
 *       对数组进行初始化。接下来就是对数组的全排列，因为我们是用不同的数字初始化数组，所以任意两个皇后肯定不同列，我们只需要判断每
 *       一个排列对应的8个皇后是不是在同一对角线，即对数组的两个下标i和j，i-j=arr[i]-arr[j] 或者 j-i=arr[i] -arr[j].
 *       
 * @since 2015-12-09
 */
public class BackTracking2 {
	
	public static int count =0;
	
	public static void main(String[] args) {
		//先对数组进行0-7的初始化
		int[] arr = {0,1,2,3,4,5,6,7};
		process(arr);
		System.out.println("种类：" + count);
	}
	
	public static void process(int[] arr) {
		if(arr == null || arr.length < 4) {
			return;
		}
		permutation(arr, 0, arr.length);
	}
	
	//排列思路见 @see StringPermutation
	public static void permutation(int[] arr, int i, int len) {
		if(i == len) {
			judge(arr);
		} else {
			for (int j = i; j < len; j++) {
				swap(arr, i, j);
				permutation(arr, i+1, len);
				swap(arr, i, j);
			}
		}
	}
	
	//判断arr是否满足互不攻击条件
	public static void judge(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if(i-j == arr[i]-arr[j] || i-j == arr[j]-arr[i]) {
					return;
				}
			}
		}
		//满足条件
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		count++;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}

