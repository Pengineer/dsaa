package hust;

/**
 * 最大的leftMax与rightMax之差的绝对值
 * 给定一个长度为N(N>1)的整型数组arr，可以划分成左右两个部分，左部分arr[0..K]，右部分arr[K+1..N-1]，K可以取值的范围是[0,N-2]。
 * 求这么多划分方案中，左部分中的最大值减去右部分最大值的绝对值，最大是多少？
 * 例如[2,7,3,1,1]，当左部分为[2,7]，右部分为[3,1,1]时，左部分中的最大值减去右部分最大值的绝对值为4。
 * 当左部分为[2,7,3]，右部分为[1,1]时，左部分中的最大值减去右部分最大值的绝对值为6。还有很多划分方案，但最终返回6。
 * 
 * @author 2015-12-01
 *
 */

public class MaxDvalue {

	public static void main(String[] args) {
		
	}
	
	/*方法一，时间复杂度O(N^2)，额外空间复杂度O(1)。
	这是最笨的方法，在数组的每个位置(i)都做一次这种划分，找到arr[0..i]的最大值maxLeft，找到arr[i+1..N-1]的最大值maxRight，
	然后计算两个值相减的绝对值。每次划分都这样求一次，自然可以得到最大的相减的绝对值。*/
	public int maxABS1(int[] arr) {
		int res = Integer.MIN_VALUE;
		int maxLeft = 0;
		int maxRight = 0;
		for (int i = 0; i != arr.length - 1; i++) {
			maxLeft = Integer.MIN_VALUE;
			for (int j = 0; j != i + 1; j++) {
				maxLeft = Math.max(arr[j], maxLeft);
			}
			maxRight = Integer.MIN_VALUE;
			for (int j = i + 1; j != arr.length; j++) {
				maxRight = Math.max(arr[j], maxRight);
			}
			res = Math.max(Math.abs(maxLeft - maxRight), res);
		}
		return res;
	}
	
	/*方法二，时间复杂度O(N)，额外空间复杂度O(N)。
	使用预处理数组的方法，先从左到右遍历一次生成lArr，lArr[i]表示arr[0..i]中的最大值。
	再从右到左遍历一次生成rArr，rArr[i]表示arr[i..N-1]中的最大值。
	最后一次遍历看看哪种划分的情况下可以得到两部分最大的相减的绝对值，因为预处理数组已经保存了所有划分的max值，所以过程得到了加速。*/
	public int maxABS2(int[] arr) {
		int[] lArr = new int[arr.length]; //预处理也是一种经常使用的方式
		int[] rArr = new int[arr.length];
		lArr[0] = arr[0];
		rArr[arr.length - 1] = arr[arr.length - 1];
		for (int i = 1; i < arr.length; i++) {
			lArr[i] = Math.max(lArr[i - 1], arr[i]);
		}
		for (int i = arr.length - 2; i > -1; i--) {
			rArr[i] = Math.max(rArr[i + 1], arr[i]);
		}
		int max = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			max = Math.max(max, Math.abs(lArr[i] - rArr[i + 1]));
		}
		return max;
	}
	
	/*方法三，最优解，时间复杂度O(N)，额外空间复杂度O(1)。
	先求整个arr的最大值max，因为max是全局最大值，所以不管怎么划分，max要么会成为左部分的最大值，要么会成为右部分的最大值。
	如果max作为左部分的最大值，接下来我们只要让右部分的最大值尽量小就可以了。
	右部分的最大值怎么尽量小呢？右部分只含有arr[N-1]的时候就是尽量小的时候。
	同理，如果max作为右部分的最大值，只要让左部分的最大值尽量小就可以了，左部分只含有arr[0]的时候就是尽量小的时候。
	所以整个求解过程会变得异常简单。*/
	public int maxABS3(int[] arr) {
		int max = Integer.MIN_VALUE; //初始化
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
		}
		return max - Math.min(arr[0], arr[arr.length - 1]);
	}
}
