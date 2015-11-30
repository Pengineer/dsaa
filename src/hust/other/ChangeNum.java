package hust.other;

/**
 * 不申请任何额外变量，交换两个数的值
 * 
 * @author 2015-11-30
 *
 */

public class ChangeNum {

	public static void main(String[] args) {
		change_1();
		change_2();
	}
	
	public static void change_1() {
		int a = 5, b = 6;
		a=a^b;
		b=a^b;
		a=a^b;
		System.out.println("a=" + a + " b=" + b);
	}
	
	public static void change_2() {
		int a=5, b=6;
		a = a+b;
		b = a-b;
		a = a-b;
		System.out.println("a=" + a + " b=" + b);
	}
}
