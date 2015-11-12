package hust.sort;

/**
 * 快排：对冒泡排序的一种改进
 * 
 * 分治思想。
 * 
 * 快速排序时间复杂度下界为O(k*n*lnn)，最坏情况为O(n^2)。在实际应用中，快速排序的平均时间复杂度为O(nlogn)，
 * 是所有同数量级的排序算法中性能最好的。
 * 
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] a = {35, 56, 12, 8, 7, 23, 7, 48};
		QuickSortMethod(a, 0, a.length-1);
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void QuickSortMethod(int[] a, int low, int high) {//不断缩小范围
		int pos;
		if(low < high) {
			pos = findFirstElementLocation(a, low, high);  //分模块实现思想
			QuickSortMethod(a, low, pos-1);
			QuickSortMethod(a, pos+1, high);
		}
	}
	
	public static int findFirstElementLocation(int[] a, int low, int high) {
		int guard = a[low];
		while(low < high) {
			while(low < high && a[high] >= guard) { //必须加=，否则a[low] = a[high]赋值后如果出现和guard相同的元素，将死循环
				high--;
			}
			a[low] = a[high];
			while(low < high && a[low] <= guard) {
				low++;
			}
			a[high] = a[low];
		}
		a[low] = guard;
		return low;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	//整合代码
	public static void quick_sort(int s[], int l, int h)
	{
	    if (l < h)
	    {
	        int i = l, j = h, x = s[l];
	        while (i < j)
	        {
	            while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
					j--;  
	            if(i < j) 
					s[i++] = s[j];
				
	            while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
					i++;  
	            if(i < j) 
					s[j--] = s[i];
	        }
	        s[i] = x;
	        quick_sort(s, l, i - 1); // 递归调用 
	        quick_sort(s, i + 1, h);
	    }
	}

}
