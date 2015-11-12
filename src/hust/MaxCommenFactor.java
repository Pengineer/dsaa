package hust;

import java.util.ArrayList;

/**
 * ��ȡ���������������
 */
public class MaxCommenFactor {

	public static void main(String[] args) {
		//����m>n
		System.out.println(getMaxCommenFactor1(648, 282));
		System.out.println(getMaxCommenFactor2(648, 282));
	}
	
	//����һ��ŷ������㷨��O(logn)
	public static int getMaxCommenFactor1(int m, int n) {
		while (n > 0) {
			int rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}
	
	//����������ý�С�������й�����������Щ�ж��ǲ��ǽϴ����Ĺ�������O(n)
	public static int getMaxCommenFactor2(int m, int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int cnt=0;
		for(int i =1; i< n; i++) {
			if(n%i ==0) {
				list.add(i);
				cnt++;
			}
		}
		while(cnt>0) {
			int tmp = list.get(--cnt);
			if(m%tmp ==0){
				return tmp;
			}
		}
		return 1;
	}
}
