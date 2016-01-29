package hust.sub;

/**
 * 求最大子矩阵和
 * 
 * 充分利用一维数组求最大子数组的求解方式，本题时间复杂度O(n^3)
 * 
 * @author liangjian
 * @since 2016-01-29
 * 
 * {1   2  -3}, 
 * {3   4  -5}, 
 * {-5 -6  -7}
 */
public class MaxSubMatrixSum {

	public static int getMaxSum(int[][] arr, int col, int row) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[] temp = new int[row]; // 记录第i行到第j行，每一列元素的和
		clear(temp);
		
		for(int i = 0 ;i < col; i++) {
			for(int j = i; j < row; j++) {
				//滚动求第i行到第j行，每一列元素的和
				for(int k = 0; k < row; k++) {
					temp[k] += arr[j][k];
				}
				//利用一维数组求最大子数组的方式求max
				int cur = 0;
				for(int k=0; k < row; k++) {
					cur += temp[k];
					max = Math.max(max, cur);
					cur = cur < 0 ? 0 : cur;
				}
			}
			clear(temp);
		}
		
		return max;
	}

	public static void clear(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
	}
	
	public static void main(String[] args) {
		int[][] arr = {{1,2,-3}, {3,4,-5}, {-5,-6,-7}};
		System.out.println(getMaxSum(arr, 3, 3));
	}
	
}