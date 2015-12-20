package hust.other;

/**
 * 题目:
 * 输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。例如输入15，鱼油1+2+3+4+5=4+5+6=7+8=15，所以结果打印出3个连续
 * 序列1-5、4-6和7-8.
 * 
 * 受@see FindTwoNumByAppointedSum 的指引有此方法。
 * 
 * @author 2015-12-14
 *
 */
public class FindContinuousSequenceByAppointedSum {

	public static void main(String[] args) {
		find(15);
	}
	
	/*
	 * 定义两个指针，分别指向序列的首尾处。
	 */
	public static void find(int sum) {
		if(sum < 3) {
			return;
		}
		
		int small = 1;
		int big = 2;
		int tempSum = small + big;
		while(small < big) {
			if(tempSum == sum) {
				System.out.println(small + " ~ " + big);
				big++;
				tempSum += big;
			} else if(tempSum < sum) {
				big++;
				tempSum += big;
			} else {
				tempSum -= small;
				small++;
			}
		}
	}

}
