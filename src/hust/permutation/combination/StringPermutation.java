package hust.permutation.combination;

/**
 * 字符串的排列组合问题
 * 
 * 题目：输入一个字符串，打印出该字符串中字符的所有排列。例如输入abc，则打印abc,acb,bac,bca,cab,cba。
 * 
 * @author 2015-12-09
 *
 */
public class StringPermutation {

	public static void main(String[] args) {
		print2("abc");
	}
	
	//第一感觉，采用八皇后问题的解决思路，将输入的字符串转换成一个n*n的二维数组，每一行每一列上的元素只能出现一次
	public static void print1(String input,  int n, char[] out) {}
	
	//2，回溯法原理虽然是递归，但是首先还是需要找到递归的具体思路（第一感觉的问题就是解题思路没考虑清楚）
	public static void print2(String input) {
		if(input == null || "".equals(input)) {
			throw new RuntimeException("Null input");
		}
		char[] arr = input.toCharArray();
		permutation(arr, 0, arr.length);
	}
	
	/* 思路：
	 * 以输入"abc"为例，固定第一个字符a，求后面两个字符bc的排列。当两个字符bc的排列求好之后，我们把第一个字符a和后面
	 * 的b交换，得到bac，接着我们固定第一个字符b，求后面两个字符ac的排列。现在是把c放到第一位置的时候了。记住前面我
	 * 们已经把原先的第一个字符a和后面的b做了交换，为了保证这次c仍然是和原先处在第一位置的a交换，我们在拿c和第一个字
	 * 符交换之前，先要把b和a交换回来。在交换b和a之后，再拿c和处在第一位置的a进行交换，得到cba。我们再次固定第一个字
	 * 符c，求后面两个字符b、a的排列。
	 * 
	 * 递归总是给人一种很空洞的sense，总感觉什么都没做，却得出了正确结果，有条不紊的思路才是递归的灵魂之所在。
	 */
	public static void permutation(char[] arr, int i, int len) {
		if(i == len) { //退出条件：对最后一个元素的处理完
			System.out.print(String.valueOf(arr) + " ");
		} else {
			for (int j = i; j < arr.length; j++) { //当前元素arr[i]依次与后面的每一位进行交换
				swap(arr, i, j);
				permutation(arr, i+1, len);
				swap(arr, i, j);
			}
		}
	}
	
	public static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
