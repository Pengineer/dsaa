package hust.other;

import hust.times.FindBeyondHalfNum1;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 获取数组中最小的K个数
 * 
 * 解法一：
 * 基于patition函数的时间复杂度为O(n)的算法，前提是可以修改输入的数组。
 * 
 * 解法二：
 * 适合海量数据，时间复杂度是O(nlogk)的算法，不会修改输入数组，而且输出有序。
 * 
 * @see FindBeyondHalfNum1
 * @author 2015-12-10
 *
 */
public class FindLeastKNum {

	public static void main(String[] args) {
		int[] arr = {5,7,2,8,3,6,3,1};
		getLeastKNum2(arr, 4, arr.length);
	}
	
	//解法一
	public static void getLeastKNum1(int[] arr, int k, int len) {
		if(arr == null || len < k) {
			System.out.println("非法输入");
			return;
		}
		int start =0;
		int end =len-1;
		int location = patition(arr, start, end);
		while(start < end && location != k-1) {
			if(location > k-1) {
				end = location-1;
				location = patition(arr, start, end);
			} else {
				start = location + 1;
				location = patition(arr, start, end);
			}
		}
		
		for(int i=0; i < k; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public static int patition(int[] arr, int start, int end) {
		int pivot = arr[start];
		while(start < end) {
			while(start < end && arr[end] >= pivot) {
				end--;
			}
			arr[start] = arr[end];
			while(start < end && arr[start] <=pivot) {
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = pivot;
		return pivot;
	}
	
	/*
	 * 解法二：
	 * 定义一个容器来存放最小的k个数。当容器满后，需要做三件事：找出k个数中最大的；可能删除最大数；可能插入新数。
	 * 找集合中最大的数很容易就想到堆排序，但是现场写一个可能时间不够，这里直接用Java提供的优先队列PriorityQueue，
	 * 它的底层就是基于堆结构实现的，自动排序所有元素，默认队首最小，队尾最大，这显然需要重写compare方法进行倒排。
	 * 
	 * 注意：
	 * 不能使用基于红黑树的TreeSet结合，因为Set集合不允许存在重复元素。（红黑树的时间复杂度也是为O(logn)）
	 */
	public static void getLeastKNum2(int[] arr, int k, int len) {
		if(arr == null || len < k) {
			System.out.println("非法输入");
			return;
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 > o2 ? -1 : o1 == o2 ? 0 : 1;
			}
		});
		queue.clear();
		for (int i = 0; i < len; i++) {
			if(queue.size() < k) {
				queue.add(arr[i]);
			} else {
				int max = queue.peek(); //peek函数只获取不删除，poll函数获取的同时删除
				if(arr[i] < max) {
					queue.remove(max);
					queue.add(arr[i]);
				}
			}
		}
		
		while(queue.size() > 0) {
			System.out.print(queue.poll() + " ");
		}
	}

}
