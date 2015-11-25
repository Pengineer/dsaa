package hust.sort;

/**
 * 对于一个数组，请设计一个高效算法计算需要排序的最短子数组的长度。
 * 给定一个int数组A和数组的大小n，请返回一个二元组，代表所求序列的长度。(原序列位置从0开始标号,若原序列有序，返回0)。保证A中元素均为正整数
 * @author 2015-11-14
 */
public class ShortestSubsequence {

	/*
	 * 思路：从左边开始遍历，不断找最大值；然后再从最右边遍历，不断找最小值
	 */
	public static void main(String[] args) {
		int[] a ={1,4,6,5,9,10,2,4,7,3,11,12}; //需要排序的最短子数组是4,6,5,9,10,2,4,7,3
		int len = getShortestSubsequence(a);
		System.out.println(len);
	}
	
	public static int getShortestSubsequence(int[] a){
		int begin=-1,end=-1;
		int max=a[0],min=a[a.length-1];
		for(int i=1; i<a.length; i++){
			if(a[i] > max)
				max = a[i];
			if(a[i] < max)
				end=i;
		}
		
		if(end<0)
			return 0;
		
		for(int i=a.length-2; i>=0; i--){
			if(a[i] < min)
				min=a[i];
			if(a[i] > min)
				begin=i;
		}
		return end - begin +1;
	}
	
	public static void swap(int[] a, int i, int j) {
		int tmp = a[j];
		a[j] = a[i];
		a[i] = tmp;
	}
	

}
