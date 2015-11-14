package hust;

/**
 * 荷兰国旗问题
 * 
 * 有一个只由0，1，2三种元素构成的整数数组，请使用交换、原地排序而不是使用计数进行排序。请返回排序后的数组.
 *
 * @author 2015-11-14
 */
public class NetherlandsFlag {
	public static void main(String[] args) {
//		int[] a = {1,0,1,0,2,1,2,0};
		int[] a = {1,2,0,2};
		
		sort(a);
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void sort(int[] a) {
		int left=0, right=a.length-1;
		for(int i=0; i<=right; i++){ //遍历到right结束
			if(a[i] == 0){
				swap(a, left, i);
				left++;
			}
			if(a[i] == 2){
				swap(a, right, i);
				i--; //从后面交换过来的数之前没有遍历过，因此下一次遍历还是在该位置
				right--;
			}
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int tmp = a[j];
		a[j] = a[i];
		a[i] = tmp;
	}
}
