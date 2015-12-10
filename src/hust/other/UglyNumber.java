package hust.other;


/**
 * 题目：
 * 我们把只包含因子2、3、5的数称为丑数，通常我们把1当作第一个丑数。求按小到大的顺序的第1500个丑数。
 * 
 * 解法一：
 * 从1开始逐个判读。
 * 
 * 解法二：
 * 以空间换时间，后面的丑数都可以由前面的丑数乘以2/3/5得到。
 * 
 * @author 2015-12-10
 *
 */

public class UglyNumber {

	public static void main(String[] args) {
		System.out.println(getUglyNumber1(1500)); //效率相当明显
		System.out.println(getUglyNumber2(1500));
	}
	
	//解法一
	public static int getUglyNumber1(int index) {
		if(index <= 0) {
			System.out.println("Invalid paramter");
			return -1;
		}
		
		int count =0;
		int i =0;
		while(count < index) {
			if(isUgly(++i)) {
				count ++;
			}
		}
		return i;
	}
	
	public static boolean isUgly(int n) {
		while(n%2 == 0) {
			n = n/2;
		}
		while(n%3 == 0) {
			n = n/3;
		}
		while(n%5 == 0) {
			n = n/5;
		}
		return n==1 ? true : false;
	}
	
	/*
	 * 解法二:因为后面的丑数都可以由前面的丑数乘以2/3/5得到（思路的关键），因此只要找到一个最小的即可，但是我们
	 *       也没必须从前面的最小丑数开始，因为我们需要乘以2/3/5后，得到的数大于现有最大丑数。（如果小于的话，
	 *       肯定已经在数组中了）
	 *       
	 *       关键的机智点：每次增加一个元素都要重新记录pMultiply2的位置，这个位置的元素是数组中乘以2后大于数组
	 *                    中最后一个丑数的最小丑数。比如[1,2,3,4,5,6,8,10,12,15]，那么pMultiply2为元素8的位置.
	 *                    pMultiply3和pMultiply5同理。
	 */
	public static int getUglyNumber2(int index) {
		if(index <= 0) {
			System.out.println("Invalid paramter");
			return -1;
		}
		
//		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(); 没必要用队列排序，元素的加入时本来就是有序的
		int[] container = new int[index];
		container[0] = 1;
		int valid = 1;
		
		int pMultiply2 =0; //重要，五星
		int pMultiply3 =0;
		int pMultiply5 =0;
		
		while(valid < index) {
			int next = min(container[pMultiply2] * 2, container[pMultiply3] * 3, container[pMultiply5] * 5);
			container[valid++] = next;
			//调整三个指针的位置
			while(container[pMultiply2] * 2 <= next)
				pMultiply2++;
			while(container[pMultiply3] * 3 <= next)
				pMultiply3++;
			while(container[pMultiply5] * 5 <= next)
				pMultiply5++;
		}
		int result = container[valid-1];
		container = null;  //重要，释放数组空间
		return result;
	}

	public static int min(int i, int j, int k) {
		int min = i<j ? i : j;
		return min<k ? min : k;
	} 
}
