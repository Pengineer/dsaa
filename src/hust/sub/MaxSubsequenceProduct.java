package hust.sub;

/**
 * 数组中子数组的最大累乘积
 * 
 * 题目:
 * 给定一个double类型的数组arr，其中的元素可正可负可0，返回子数组累乘的最大乘积。
 * 例如arr=[-2.5，4，0，3，0.5，8，-1]，子数组[3，0.5，8]累乘可以获得最大的乘积12，所以返回12。
 * 
 * @author 2015-12-20
 *
 */
public class MaxSubsequenceProduct {

	public static void main(String[] args) {
		
	}
	
	/*
	 * 本题是一道动态规划题目。要求i结尾的子序列的最大乘积，可通过以i-1结尾的子序列乘积来求。共有3中情况：
	 * 1，max(i) = max(i-1) * arr[i]
	 * 2，max(i) = min(i-1) * arr[i]
	 * 3，max(i) = arr[i]
	 */
	public static double getMaxProduct(double[] arr, int length) {
		if (arr == null || arr.length == 0) {
			throw new RuntimeException("Invalid parameter");
		}
		double max = arr[0]; //以i-1结尾的子序列的最大乘积
		double min = arr[0]; //以i-1结尾的子序列的最小乘积
		double result = arr[0];
		double maxEnd = 0; //以i结尾的子序列的最大乘积(包含i)
		double minEnd = 0; //以i结尾的子序列的最小乘积
		for (int i = 1; i < arr.length; ++i) {  //[-2.5，4，0，3，0.5，8，-1]
			maxEnd = max * arr[i];
			minEnd = min * arr[i];
			max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
			min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
			result = Math.max(result, max);
		}
		return result;
	}

}
