package hust;

import java.util.LinkedList;

/**
 * 生成窗口最大值数组
 * 
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时：
 * [4 3 5] 4 3 3 6 7 窗口中最大值为5
 * 4 [3 5 4] 3 3 6 7 窗口中最大值为5
 * 4 3 [5 4 3] 3 6 7 窗口中最大值为5
 * 4 3 5 [4 3 3] 6 7 窗口中最大值为4
 * 4 3 5 4 [3 3 6] 7 窗口中最大值为6
 * 4 3 5 4 3 [3 6 7] 窗口中最大值为7
 * 
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。请实现一个函数，给定一个数组arr，窗口大小w。
 * 返回一个长度为n-w+1的数组res,res[i]表示每一种窗口状态下的最大值。以本题为例，结果应该返回[5,5,5,4,6,7]。
 * 
 * @author liangjian
 *
 */
public class MaxWindowArray {

	/*
	 * 在剑指offer上面试题21有时间复杂度O(1)下获取栈中的最小值，使用的是一个辅助栈，那是栈的特性后进先出决定的，实际栈和辅助栈在弹出时总
	 * 是弹出的最新入栈的值，因此，对辅助栈里面的原始值不会有影响，但是队列不一样，队列出队时是从最老元素开始的，这会影响辅助队列后面的所有
	 * 值，因此队列是很难用这种方式实现的。
	 * 
	 * 本题使用的是双端队列，队列存储的是元素的脚标，并保证队列脚标所对应的元素是从大到小的顺序，如果后面的元素大于前面的元素，则不断弹出
	 * 前面的元素的脚标，	直到符合从大到小的顺序。
	 * 
	 * 为什么存放脚标，因为：1，通过脚标我可以直接获取数组元素；2，通过脚标我可以判定队首元素对应的最大值是否过期。
	 * 
	 * 为什么使用双端队列，因为我要获取队首元素，还要往队尾添加元素。
	 * 
	 */
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();  // LinkList是Java提供的双端队列，既可以在队首插入删除，又可以在队尾插入删除
		int[] res = new int[arr.length - w + 1];
		qmax.add(0);
		for (int i=1; i<arr.length; i++) {
			if (arr[qmax.peekLast()] > arr[i]) {
				qmax.addLast(i);
			} else {
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) // 判空条件必须写在前面，汗！汗！汗！
					qmax.pollLast();
				if (!qmax.isEmpty() && i - qmax.peekFirst() >= w) // 用if就可以了，每一个元素都会判断队首，因此最多一个过期
					qmax.pollFirst();
				qmax.add(i); // 如果add写在if的上面，上面的if就不需要判空了
			}
			
			if (i >= w - 1) {
				res[i-w+1] = arr[qmax.peekFirst()];
			}
		}
		
		return res;
	}
	
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		printArray(getMaxWindow(arr, w));
	}
}
