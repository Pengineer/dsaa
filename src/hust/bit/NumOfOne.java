package hust.bit;

/**
 * 获取一个数的二进制中1的个数
 * 
 * 这是一类题目的原型，核心思路：把一个整数减去1之后再和原来的整数做位于运算，得到的结果相当于是把整数的二进制
 * 表示中的最右边一个1变成0。
 * 
 * 举一反三：
 * （1）用一条语句判断一个整数是不是2的整数次方。如果是，那么该整数的二进制有且仅有1个1。
 * （2）输入两个整数m和n，计算需要改变m的二进制表示中的多少位才能的到n。解决思路：第一步求这两个数的异或；第二步统计以后结果中1的位数。
 * 
 * @author 2015-12-05
 *
 */

public class NumOfOne {

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(-3));
		System.out.println(getNumOfOne1(-3));
	}
	
	//方法一：num的每一位和1相与，其它位和0相与，看结果是否不为0。循环次数为整形所占的位数。
	public static int getNumOfOne1(int num) {
		int count =0;
		int flag =1;
		while(flag != 0) {
			if((num & flag) !=0) {
				count++;
			}
			flag = flag << 1;
		}
		
		return count;
	}
	
	//方法二：利用概述中的核心思路，循环次数仅仅为1的个数。
	public static int getNumOfOne2(int num) {
		int count =0;
		while(num != 0) {
			num = (num-1) & num;
			count++;
		}
		return count;
	}
}
