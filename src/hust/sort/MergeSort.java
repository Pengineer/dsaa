package hust.sort;

/**
 * �鲢����
 * 
 * ���룺�ϲ�+����
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
	
	//������a��l~m-1��m~r�������򲿷ֺϲ���һ��l~r���������򲿷�
	public static void merge(int[] a, int l, int m, int r) {//l��ʾ����������ʼλ�ã�m��ʾ�ұ��������ʼλ�ã�r��ʾ�ұ�����Ľ���λ��
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
	
	//���÷���˼��ʵ�ֹ鲢����
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
