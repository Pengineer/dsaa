package hust;

/**
 * ������
 * 
 * x^n
 */
public class PowerOperation {

	public static void main(String[] args) {
		System.out.println(doPower1(2, 3));
		System.out.println(doPower2(2, 3));
	}
	
	//����һ��ʱ�临�Ӷ�O(n)
	public static int doPower1(int num, int index) {
		if (index==1) {
			return num;
		} else {
			return doPower1(num, index-1) * num;
		}
	}
	
	//���������ְ���㣬x^31 = (x^2)^15 * x ; x^30 = (x^2)^15��ʱ�临�Ӷ�O(logn)
	public static int doPower2(int num, int index) {
		if (index == 0) {
			return 1;
		}
		if (index == 1) {
			return num;
		} 
		if(index % 2 == 0) {
			return doPower2(num*num, index/2);
		} else {
			return doPower2(num*num, index/2) * num;
		}
	}

}
