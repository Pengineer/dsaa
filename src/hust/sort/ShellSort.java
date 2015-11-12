package hust.sort;

/**
 * 希尔排序
 * 
 * 缩小增量排序，属于插入类排序，将一个数组根据增量分成多个不同的子数组，将这些子数组分别采用插入排序使之成为有序子数组.
 * 
 * 时间复杂度：O(n^2)
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] a = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
		shellSortMethod(a, a.length);
		
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void shellSortMethod(int[] a, int N) { //增量序列的一种流行（但是不好）的选择是使用shell建议的序列：increment = N/2, increment/=2;
		int increment=0, i=0, j=0;
		for (increment = N/2; increment > 0; increment/=2) {
			for (i=increment; i < N; i+=increment) { //分组进行插入排序
				int guard = a[i];
				for (j=i-increment; j>=0 && a[j] > guard; j-=increment) {
					a[j+increment] = a[j];
				}
				a[j+increment] = guard;
			}
		}
	}

}
