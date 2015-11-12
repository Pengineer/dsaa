package hust.sort;

/**
 * 插入排序
 * 
 * 两层for循环，时间复杂度O(n^2)，简单但仅适合于排序量很小的记录集。
 * 
 * 思路：将元素a[i]与a[0...i-1]一次比较，选择插入位置，为了实现数组内的移动，此处将待插入元素设置为哨兵。从前往后一次实现有序化。
 */

public class InsertSort {

	public static void main(String[] args) {
		int[] a = {35, 2, 56, 7, 8, 23, 7, 45, 60, 27};
		DirectInsertSortMethod(a);
		System.out.println();
//		BinaryInsertSortMethod(a);
	}
	
	//直接插入排序，时间复杂度O(n^2)
	public static void DirectInsertSortMethod(int[] a) {
		int i =0, j =0;
		for(i=1; i<a.length; i++) {
			int guard = a[i];  //设置哨兵
			for(j=i-1; j>=0 && a[j] > guard; j--) { //将大于哨兵的元素均后移一位
					a[j+1] = a[j];
			}
			a[j+1] = guard;
		}
		
		for(i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	//折半插入排序，时间复杂度O(n^2)
	public static void BinaryInsertSortMethod(int[] a) {
		for(int i=1; i<a.length; i++) {
			int guard = a[i];  //设置哨兵
			int high=i, low=0;
			while(low < high) { //找到待插入点
				int mid = (low + high)/2;
				if(guard < a[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			for(int j=i-1; j>high; j--) //high后面的记录后移
				a[j+1] = a[j];
			a[high] = guard; //或则a[low] = guard;
		}
		
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}

}
