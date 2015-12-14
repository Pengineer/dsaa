package hust.other;

/**
 * 求1+2+3+...+n，不允许使用乘除法，不允许使用API函数，循环，递归，各类条件判断以及三目运算。
 * 
 * 一道没有任何实际意义的题。
 * 
 * @author 2015-12-14
 *
 */
public class GetSumFrom1Ton {

	public static int sum = 0;
	public static int N = 0;
	
	GetSumFrom1Ton() {
		N++;
		sum += N;
	}
	
	public static void main(String[] args) {
		GetSumFrom1Ton[] s0 = new GetSumFrom1Ton[3]; //Java不行额
		s0[1].toString();
//		GetSumFrom1Ton s1 = new GetSumFrom1Ton();
//		GetSumFrom1Ton s2 = new GetSumFrom1Ton();
		
		System.out.println(sum);
	}
	
}
