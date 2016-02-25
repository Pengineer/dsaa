package hust.multisystem;

/**
 * 给定一个等概率随机产生1~5的随机函数rand1To5如下：
 * public int rand1To5() {
 *		return (int) (Math.random() * 5) + 1;
 * }
 * 除此之外不能使用任何额外的随机机制，请用rand1To5实现等概率随机产生1~7的随机函数rand1To7。
 * 
 * 其实这就是一个多进制的问题，题目给的就是五进制(0-4)，一位最大是4，因此需要两位才能不小于7；两位最大是4*5+4，要想等概率产生1-7，
 * 那么21-24就不能要。整个就是组合（产生0-24连续随机数）和筛选（去掉21-24）的过程。
 * 
 * @since 2015-12-01
 *
 */

public class GenerateRandom1 {
	
	public static void main(String[] args) {
		System.out.println(rand1To7());
	}
	
	//关键是独立产生随机数
	public static int rand1To7() {
//		int tmp1 = rand1To5() - 1;        //独立产生0-4的随机数
//		int tmp2 = (rand1To5() - 1) * 5;  //独立产生0,5,10,15,20这5个随机数
//		return (tmp1 + tmp2) % 7 + 1;     //tmp1和tmp2的和就是等概率的0-24（组合的过程）
		
		int num = 0;
		do {
			num = (rand1To5() - 1) * 5 + (rand1To5() - 1);
		} while (num > 20);
		return num % 7 + 1;
	}
	
	public static int rand1To5() {
		return (int)(Math.random() * 5) + 1;
	}
}
