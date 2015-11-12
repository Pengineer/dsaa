package hust;

/**
 * �۰���ң�ǰ����˳���
 * 
 * ���ģ�����keyֵ���ϸı�����Ĵ�С��
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {1,3,5,7,9,11,13,15,17,19};
		boolean isExist = Bin_Search_2(a, 15);
		System.out.println(isExist);
	}
	
	//�ǵݹ�: O(logn)
	public static boolean Bin_Search(int[] a, int key) {
		int low=0, high=a.length;
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
	
	//�ݹ�
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
			System.arraycopy(a, mid, b, 0, mid);   //��һ������Ĳ���copy����һ������
			return Bin_Search_2(b, key);
		} else {
			System.arraycopy(a, 0, b, 0, mid);
			return Bin_Search_2(b, key);
		}
	}
}
