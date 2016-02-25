package hust.sort;

/**
 * 归并排序
 * 
 * 输入：合并+分治
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] a = {2,7,8,9,3,4,5,6};
//		merge(a,0,4,7);
		mergeSort(a,0,7);
		
		for (int i =0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	//将数组a的l~m-1和m~r两个有序部分合并成一个l~r的整体有序部分
	public static void merge(int[] a, int l, int m, int r) {//l表示左边数组的起始位置，m表示右边数组的起始位置，r表示右边数组的结束位置
		int LETF_SIZE = m-l;
		int RIGHT_SIZE = r - m + 1;
		int[] left = new int[LETF_SIZE];
		int[] right = new int[RIGHT_SIZE];
		for(int i=l; i<m; i++) {
			left[i-l] = a[i];
		}
		for(int i=m; i<=r; i++) {
			right[i-m] = a[i];
		}
		
		int i=0, j=0, k=l;
		while (i < LETF_SIZE && j < RIGHT_SIZE) {
			if (left[i] < right[j]) {
				a[k] = left[i];
				i++;
				k++;
			} else {
				a[k] = right[j];
				j++;
				k++;
			}
		}
		while (i < LETF_SIZE) {
			a[k] = left[i];
			i++;
			k++;
		}
		while (j < RIGHT_SIZE) {
			a[k] = right[j];
			j++;
			k++;
		}
	}
	
	//利用分治思想实现归并排序
	public static void mergeSort(int[] a, int start, int end) {
		if(start == end) {
			return;
		} else {
			int m = (start + end) / 2;
			mergeSort(a, start, m);
			mergeSort(a, m+1, end);
			merge(a, start, m+1, end);
		}
	}

}
