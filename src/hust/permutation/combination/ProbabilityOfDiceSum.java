package hust.permutation.combination;

/**
 * 求n个骰子朝上一面的点数和的概率。
 * 
 * 思路：
 * 1、先求出所有可能的和的种类，然后求概率；
 * 		显然这是一个大小为n*6的二维数组的排列组合问题。
 * 
 * @since 2015-12-14
 *
 */
public class ProbabilityOfDiceSum {
	
	public static final int NUMBER = 6;      //骰子个数
	public static final int G_MAXVALUE = 6;  //每个骰子各个面的最大值

	public static void main(String[] args) {
		int[] sums = new int[NUMBER * G_MAXVALUE - G_MAXVALUE + 1];
		
		getAllProbabilitySum(NUMBER, G_MAXVALUE, 0, 0, sums);
		int total = getTotal(NUMBER, G_MAXVALUE);
		
		for (int i = 0; i < sums.length; i++) {
			double ratio = (double)sums[i] / total; //必须将两个int中的一个先转成double，否则输出都为0
			System.out.println(i + NUMBER + "出现的概率: " + ratio + " " + '\t');
		}
	}

	//方法一：得到所有可能的和的种类放入sums数组中，数组的第一个元素表示最小和6出现的种类
	public static void getAllProbabilitySum(int number, int maxValue, int i, int currentSum, int[] sums) {
		if(i == number) {
			sums[currentSum - maxValue]++;
		} else {
			for (int j = 1; j <= maxValue; j++) {
				//currentSum += j;  不能这么加，只能把currentSum + j做参数进行传递,递归到最后一行i=5时，执行完这一行currentSum=6，在执行下一行后j=2，然后currentSum=8，直接跳过了7.
				getAllProbabilitySum(number, maxValue, i+1, currentSum + j, sums);//currentSum + j 表示前i-1行的currentSum固定，根据本行的各列来求可能的和，这也说明了currentSum是不能再本行前面发生变化的。
			}
		}
	}

	public static int getTotal(int number, int gMaxvalue) {
		int result =1;
		for (int i = 0; i < number; i++) {
			result *= gMaxvalue;
		}
		return result;
	}
}
