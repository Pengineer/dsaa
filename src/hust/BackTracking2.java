package hust;

import java.util.ArrayList;

/**
 * ���ݷ� 2 ģ��ʽ��̣��԰˻ʺ�����Ϊ��
 * 
 * void Trial(int i, int n){
 * 	  //���뱾����ʱ����n*n����ǰi-1���ѷ����˻���������i-1�����ӡ�
 * 	  //�ִӵ�i�������Ϊ��������ѡ�����λ��
 *    //��i>nʱ�����һ���Ϸ����֣����֮��
 *    if(i>n) ������̵ĵ�ǰ����;      //n=4ʱ����Ϊ4�ʺ�����
 *    else 
 *    	for (j=1; j<=n; j++) {
 *    		�ڵ�i�е�j�з���һ������;
 *    		if(��ǰ���ֺ���) Trail(i+1,n);
 *    		���ߵ�i�е�j�е����ӡ�
 *    	}
 * }
 * 
 * ����ģ�������Ϊ���ݷ�����һ��ģʽ��������������ʿ�������Թ����⡢ѡ���Ž⡣
 */

public class BackTracking2 {
	
	public static void main(String[] args) {
		ArrayList<Integer> dest = new ArrayList<Integer>();
		Trail(1, 8, dest);
	}
	
	public static void Trail(int i, int n, ArrayList<Integer> dest) {
		if(i>n) {
			System.out.println(dest.toString());
		} else {
			for (int j=1; j<=n; j++) {
				dest.add((Integer)(i *10 + j));
				if(Reasonable_2(i*10 +j, i, dest)) Trail(i+1, n, dest);
				dest.remove((Integer)(i *10 + j));
			}
		}
	}
	
	//�����жϷ�ʽ�����⣬������б���ϵ�(num-tmp)%11Ҳ�ᱻ���˵���������������ȫ
	public static boolean Reasonable_1(int num, int i, ArrayList<Integer> dest) { //��i�е�num�Ƿ������������
		for (int j=1; j<i; j++) {
			int tmp = (Integer) dest.get(j-1);
			if((num - tmp) % 10 == 0) return false;                    //��ֱ����
			if((num/10 + num%10 - tmp/10 -tmp%10) == 0) return false;  //��б�߷���
			if((num - tmp)%11 == 0) return false;                      //��б�߷���
		}
		return true;
	}
	
	//б������ľ��䴦��ʽ��ת����б�ʵĴ���
	public static boolean Reasonable_2(int num, int i, ArrayList<Integer> dest) {
		for (int j=1; j<i; j++) {
			int tmp = (Integer) dest.get(j-1);			
			int x1, y1, x2, y2;		
			x1 = num/10;
			y1 = num%10;
			x2 = tmp/10;
			y2 = tmp%10;
			if(y1 == y2) return false;
			if((y1-y2) == (x1-x2)) return false;
			if((y1-y2) == (x2-x1)) return false;
		}
		return true;
	}
}

