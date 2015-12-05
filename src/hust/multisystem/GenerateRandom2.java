package hust.multisystem;

/**
 * 给定一个以p概率产生0，以1-p概率产生1的随机函数rand01p如下：
 * public int rand01p() {
 *    // you can change p as you like
 *    double p = 0.83;
 *    return Math.random() < p ? 0 : 1;
 * }
 * 除此之外不能使用任何额外的随机机制，请用rand01p实现等概率随机产生1~6的随机函数rand1To6。
 * 
 * @author 2015-12-01
 *
 */

public class GenerateRandom2 {

	public static void main(String[] args) {
		System.out.println(rand1To6());
	}
	
	//将不等概率转化成等概率：所有产生01或则10的概率都相等
	public static int rand01() {
		int num;
		do {
			num = rand01p();
		} while (num == rand01p());
		return num == 1 ? 1 : 0;
	}
	
	public static int rand0To3() {
		return rand01() * 2 + rand01();
	}
	
	public static int rand1To6() {
		int num = 0;
		do {
			num = rand0To3() * 4 + rand0To3();
		} while (num > 11);
		return num % 6 + 1;
	}
	
	public static int rand01p() {
		// you can change p as you like
		double p = 0.83;
		return Math.random() < p ? 0 : 1;
	}
}
