package hust.sort;

/**
 * ��������
 * 
 * ����forѭ����ʱ�临�Ӷ�O(n^2)���򵥵����ʺ�����������С�ļ�¼����
 * 
 * ˼·����Ԫ��a[i]��a[0...i-1]һ�αȽϣ�ѡ�����λ�ã�Ϊ��ʵ�������ڵ��ƶ����˴���������Ԫ������Ϊ�ڱ�����ǰ����һ��ʵ�����򻯡�
 */

public class InsertSort {

	public static void main(String[] args) {
		int[] a = {35, 2, 56, 7, 8, 23, 7, 45, 60, 27};
		DirectInsertSortMethod(a);
		System.out.println();
//		BinaryInsertSortMethod(a);
	}
	
	//ֱ�Ӳ�������ʱ�临�Ӷ�O(n^2)
	public static void DirectInsertSortMethod(int[] a) {
		int i =0, j =0;
		for(i=1; i<a.length; i++) {
			int guard = a[i];  //�����ڱ�
			for(j=i-1; j>=0 && a[j] > guard; j--) { //�������ڱ���Ԫ�ؾ�����һλ
					a[j+1] = a[j];
			}
			a[j+1] = guard;
		}
		
		for(i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	//�۰��������ʱ�临�Ӷ�O(n^2)
	public static void BinaryInsertSortMethod(int[] a) {
		for(int i=1; i<a.length; i++) {
			int guard = a[i];  //�����ڱ�
			int high=i, low=0;
			while(low < high) { //�ҵ��������
				int mid = (low + high)/2;
				if(guard < a[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			for(int j=i-1; j>high; j--) //high����ļ�¼����
				a[j+1] = a[j];
			a[high] = guard; //����a[low] = guard;
		}
		
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}

}
