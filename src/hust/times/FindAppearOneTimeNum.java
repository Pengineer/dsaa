package hust.times;

/**
 * 找出数组中只出现一次的数字
 * 
 * 题目：
 * 假定数组里面有且仅有两个数字出现的次数为1，其他的都为两次，找出这两个数字。
 * 要求：
 * 时间复杂度是O(n)，空间复杂度O(1)。
 * 
 * 补充：数组是数值的，用hash表实现，额外空间复杂度为O(n)；也不能用数组模拟hash表，因为与字符串不同，数字的范围基本是不可控的。
 * 
 * @author 2015-12-14
 *
 */
public class FindAppearOneTimeNum {

	public static void main(String[] args) {
		int[] arr = {5, 2, 7, 0, 9, 2, 8, 8, 7, 9};
		findOneTimeNum(arr, 10);
	}
	
	/* 
	 * 异或特性：
	 * 		a ^ 0 = a;
	 * 		a ^ b = b ^ a;
	 * 		a ^ b ^ a = b;
	 * 		a ^ b ^ c = a ^ (b ^ c).
	 * 如果数组中只有一个出现1次的元素，那么将所有元素异或后得到的结果就是所需要的数。
	 * 本题数组中有两个元素出现1次，那么我们可以将数组分成两部分，每一部分包含一个出现1次的元素即可。关键就是数组GIA如何去分，
	 * 由于这两个元素不相同，那么所有元素异或后的结果肯定不为0，那么只需要找到该结果的二进制中从低位到高位第一个为1的位置，
	 * 记为第n位。然后以第n位是否为1进行划分即可。
	 * 
	 */
	public static void findOneTimeNum(int[] arr, int length) {
		if(arr == null || length <= 0) {
			return;
		}
		int exclusiveOR = 0;
		for (int i = 0; i < length; i++) {
			exclusiveOR ^= arr[i];
		}
		int index = findFirstBitIs1(exclusiveOR);
		int num1=0, num2=0; //初始化所查找的两个数为0（与0异或为其本身，故不影响）
		for (int i = 0; i < length; i++) {
			if(isBit1(arr[i], index)) {
				num1 ^= arr[i];
			} else {
				num2 ^=arr[i];
			}
		}
		System.out.println(num1); 
		System.out.println(num2);
	}

	//找到num的二进制中最右边第一个为1的位置
	public static int findFirstBitIs1(int num) {
		int index = 0;
		while(((num & 1) == 0) && (index < 8 * 4)) {
			num = num >> 1;
			++index;
		}
		return index;
	}
	
	//判断num的二进制的index位上是否为1
	public static boolean isBit1(int num , int index) {
		num = num >> index;
		return (num & 1) == 1;
	}
}
