package hust.other;

/**
 * 青蛙跳台阶
 * 
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 
 * 核心：斐波拉契数列。
 * 跳上n（n>=3）级台阶的前一步有两种情况，从n-1级台阶跳一次或则从n-2级台阶跳1次。
 * 
 * @author 2015-12-05
 *
 */
public class FrogJumpStep {

	public static void main(String[] args) {
		System.out.println(jumpMethod(4));
	}
	
	public static int jumpMethod(int n) {
		if(n <= 0) {
			throw new RuntimeException("Invalid parameter");
		}
		
		int Fn_2 = 1;
		int Fn_1 = 2;
		int Fn = 0;
		if(n == 1)
			return 1;
		if(n == 2)
			return 2;
		for (int i = 3; i <= n; i++) {
			Fn = Fn_1 + Fn_2;
			Fn_2 = Fn_1;
			Fn_1 = Fn;
		}
		return Fn;
	}
	
}
