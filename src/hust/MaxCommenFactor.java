package hust;

import java.util.ArrayList;

/**
 * 获取两个数的最大公因数
 * 
 * 更多方法有待深入。。。（求最小公倍数）
 */
public class MaxCommenFactor {

	public static void main(String[] args) {
		//假设m>n
		System.out.println(getMaxCommenFactor1(648, 282));
		System.out.println(getMaxCommenFactor2(648, 282));
	}
	
	//方法一：欧几里得算法，O(logn)
	public static int getMaxCommenFactor1(int m, int n) {
		while (n > 0) {
			int rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}
	
	//方法二：求得较小数的所有公因数，在这些判断是不是较大数的公因数，O(n)
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
