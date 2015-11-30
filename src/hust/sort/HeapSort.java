package hust.sort;

/**
 * 堆排序：堆排序和合并排序一样，是一种时间复杂度为O(nlgn)的算法，同时和插入排序一样，是一种就地排序算法(不需要额外的存储空间)。
 * 
 * 首先使用建立最大堆的算法建立好最大堆，然后将堆顶元素（最大值）与最后一个值交换，同时使得堆的长度减小1 ，调用保持最大堆性质的算法调整，使得堆顶元素成为最大值，此时最后一个元素已被排除在外。
 * 
 * 堆排序中也存在着递归，但是它的额外空间复杂度是O(1)。
 * 
 * @author 2015-11-30
 *
 */
public class HeapSort {

	public static void main(String[] args) {
//		int[] arr = {3,6,2,7,10,5,1,9,12,2};
		int[] arr = {1,2,3,10,4,5,6};
		
		heapSort(arr, arr.length-1);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	//创建最大堆
	public static void createMaxHeap(int[] arr, int len) {
		//从下往上创建最大堆，叶子节点本身就可以看做是一个最大堆，因此不需考虑
		for (int i=arr.length/2 -1; i >= 0; i--) {
			HeapAdjust_nonrecursion(arr, i, len);
		}
	}
	
	//递归：维持堆特性，必须传入三个参数，len在排序时需要
	public static void HeapAdjust_recursion(int[] arr, int node, int len) {
		int left = left(node);
		int right = right(node);
		int max = node;
		if(left <= len && arr[left] > arr[max]) {
			max = left;
		}
		if(right <= len && arr[right] > arr[max]) {
			max = right;
		}
		if(max != node) { //如果最大数不是根，则最大数与根交换，此时，破坏了以最大数为根节点的子树的堆特性，需要对其调整。
			int tmp = arr[max];
			arr[max] = arr[node];
			arr[node] = tmp;
			HeapAdjust_recursion(arr, max, len);
		}
	}
	
	//非递归，和递归没啥区别
	public static void HeapAdjust_nonrecursion(int[] arr, int node, int len) {
		int left = left(node);
		int right = right(node);
		int max = node;
		while(max <= len) {
			if(left <= len && arr[left] > arr[max]) {
				max = left;
			}
			if(right <= len && arr[right] > arr[max]) {
				max = right;
			}
			if(max != node) { 
				int tmp = arr[max];
				arr[max] = arr[node];
				arr[node] = tmp;
				
				node = max;
				left = left(max);
				right = right(max);
			} else { //必加
				break;
			}
		}
	}
	
	//推排序：将最大堆的根节点与最后一个节点交换，重新调整堆，然后再交换，再维持。。。
	public static void heapSort(int[] arr, int len) {
		createMaxHeap(arr, len);
		
		while(len > 0) {
			int tmp = arr[0];
			arr[0] = arr[len];
			arr[len] = tmp;
			HeapAdjust_nonrecursion(arr, 0, --len); //不断取走根，缩小堆的大小
		}
	}
	
	//获取当前节点的左节点
	public static int left(int i) {
		return i*2 + 1;
	}
	
	//获取当前节点的右节点
	public static int right(int i) {
		return i*2 + 2;
	}
	
	//获取当前节点的父节点
	public static int parent(int i) {
		return (i-1)/2;
	}
}
