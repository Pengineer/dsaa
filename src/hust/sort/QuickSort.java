package hust.sort;

/**
 * ���ţ���ð�������һ�ָĽ�
 * 
 * ����˼�롣
 * 
 * ��������ʱ�临�Ӷ��½�ΪO(k*n*lnn)������ΪO(n^2)����ʵ��Ӧ���У����������ƽ��ʱ�临�Ӷ�ΪO(nlogn)���ռ临�Ӷ���O(logn)��
 * ������ͬ�������������㷨��������õġ�
 * 
 * ��˵����
 * �ռ临�Ӷ���O(logn)��ԭ��ÿ�εݹ��ʱ����Ҫ��¼���ֵ��λ�ã���ô�ݹ���ٴξ���Ҫ��¼����λ�ã��ô���Ҳ���ǿռ临�Ӷȡ�
 * 							  �����������ĵ�ʽ����һ��ȷ��1��Ԫ�ص�λ�ã��ڶ���������2���������������ٷ�2^2����������1+2+2^2+...2^x=n����Ϊ�ܻ��ֵ����n���ô���X=x+1=log(n+1)
 * ʱ�临�Ӷ���O(nlogn)��ԭ��ÿһ���һ��ֵ��ʱ�临�Ӷ���O(n)��һ��Ҫ��log(n+1)�Σ���ʱ�临�Ӷ�ΪO(nlogn)��
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] a = {35, 56, 12, 8, 7, 23, 7, 48};
		QuickSortMethod(a, 0, a.length-1);
		
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void QuickSortMethod(int[] a, int low, int high) {//������С��Χ
		if(a == null || low > high || low < 0 || high < 0) {
			return;
		}
		int pos;
		if(low < high) {
			pos = Partition(a, low, high);  //��ģ��ʵ��˼��
			QuickSortMethod(a, low, pos-1);
			QuickSortMethod(a, pos+1, high);
		}
	}
	
	public static int Partition(int[] a, int low, int high) {
		int guard = a[low];
		while(low < high) {
			while(low < high && a[high] >= guard) { //�����=������a[low] = a[high]��ֵ��������ֺ�guard��ͬ��Ԫ�أ�����ѭ��
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
	
	//���ϴ���
	public static void quick_sort(int s[], int l, int h)
	{
		if(s == null || l > h || l < 0 || h < 0) {
			return;
		}
		
	    if (l < h)
	    {
	        int i = l, j = h, x = s[l];
	        while (i < j)
	        {
	            while(i < j && s[j] >= x) // ���������ҵ�һ��С��x����
					j--;  
	            if(i < j) 
					s[i++] = s[j];
				
	            while(i < j && s[i] < x) // ���������ҵ�һ�����ڵ���x����
					i++;  
	            if(i < j) 
					s[j--] = s[i];
	        }
	        s[i] = x;
	        quick_sort(s, l, i - 1); // �ݹ���� 
	        quick_sort(s, i + 1, h);
	    }
	}

}
