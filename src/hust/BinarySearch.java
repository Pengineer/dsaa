package hust;

/**
 * 折半查找：前提是顺序表
 * 
 * 核心：根据key值不断改变区间的大小。
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {1,3,5};
		boolean isExist = Bin_Search(a, 5);
		System.out.println(isExist);
	}
	
	//非递归: O(logn)
	public static boolean Bin_Search(int[] a, int key) {
		int low=0, high=a.length-1;
		while(low <= high) {
			int mid = (low+high)/2;
			if (key == a[mid]) {
				return true;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return false;
	}
	
	//递归
	public static boolean Bin_Search_2(int[] a, int key) {
		int low=0, high=a.length;
		int mid = (low+high)/2;
		int[] b = new int[mid];
		
		if(mid == 0) {
			return false;
		}
		if(key == a[mid]) {
			return true;
		} else if (key > a[mid]) {
			System.arraycopy(a, mid, b, 0, mid);   //将一个数组的部分copy到另一个数组
			return Bin_Search_2(b, key);
		} else {
			System.arraycopy(a, 0, b, 0, mid);
			return Bin_Search_2(b, key);
		}
	}
}
