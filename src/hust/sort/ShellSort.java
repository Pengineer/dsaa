package hust.sort;

/**
 * ϣ������
 * 
 * ��С�����������ڲ��������򣬽�һ��������������ֳɶ����ͬ�������飬����Щ������ֱ���ò�������ʹ֮��Ϊ����������.
 * 
 * ʱ�临�Ӷȣ�O(n^2)
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] a = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
		shellSortMethod(a, a.length);
		
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void shellSortMethod(int[] a, int N) { //�������е�һ�����У����ǲ��ã���ѡ����ʹ��shell��������У�increment = N/2, increment/=2;
		int increment=0, i=0, j=0;
		for (increment = N/2; increment > 0; increment/=2) {
			for (i=increment; i < N; i+=increment) { //������в�������
				int guard = a[i];
				for (j=i-increment; j>=0 && a[j] > guard; j-=increment) {
					a[j+increment] = a[j];
				}
				a[j+increment] = guard;
			}
		}
	}

}
