package hust.sort;

/**
 * 冒泡排序
 * 每次都是相邻的两个元素进行比较
 *
 */
public class BuddleSort {
	public static void main(String[] args) {
		int[] a = {35, 56, 12, 8, 7, 23, 7, 48};
		BuddleSort(a);
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void BuddleSort(int[] a) {
		for (int i=0; i<a.length-1; i++) {
			for(int j=0; j<a.length - i -1; j++) {
				if(a[j] > a[j+1]){
					swap(a, j, j+1);
				}
			}
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int tmp = a[j];
		a[j] = a[i];
		a[i] = tmp;
	}
}
