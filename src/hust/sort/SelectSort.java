package hust.sort;

/**
 * 选择排序
 * 
 * 思路：从第一个元素开始，选择最小的放在最前面。
 * 
 * 时间复杂度：O(n^2)
 */
public class SelectSort {

	public static void main(String[] args) {
		int[] a = {35, 56, 12, 8, 7, 23, 7, 48};
		SelectSortMethod(a);
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void SelectSortMethod(int[] a) {
		for(int i=0; i<a.length; i++) {
			for(int j=i+1; j<a.length; j++) {
				if(a[j] < a[i]) {
					swap(a, i, j);
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
