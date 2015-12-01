package hust.random;

/**
 * 了解：GenerateRandom1和GenerateRandom2的推广
 * 
 * 给定一个等概率随机产生1~M的随机函数rand1ToM如下：
 * 	public int rand1ToM(int m) {
 * 		return (int) (Math.random() * m) + 1;
 * 	}
 * 除此之外不能使用任何额外的随机机制。有两个输入参数分别为m和n，请用rand1ToM(m)实现等概率随机产生1~n的随机函数rand1ToN。
 * 
 * @author 2015-12-01
 *
 */

public class GenerateRandom3 {
	
	public static int rand1ToM(int m) {
		return (int) (Math.random() * m) + 1;
	}
	
	public static int rand1ToN(int n, int m) {
		int[] nMSys = getMSysNum(n - 1, m);
		int[] randNum = getRanMSysNumLessN(nMSys, m);
		return getNumFromMSysNum(randNum, m) + 1;
	}
	
	// 把value转成m进制的数
	public static int[] getMSysNum(int value, int m) {
		int[] res = new int[32];
		int index = res.length - 1;
		while (value != 0) {
			res[index--] = value % m;
			value = value / m;
		}
		return res;
	}
	// 等概率随机产生一个0~nMsys范围上的数，只不过是m进制表达的。
	public static int[] getRanMSysNumLessN(int[] nMSys, int m) {
		int[] res = new int[nMSys.length];
		int start = 0;
		while (nMSys[start] == 0) {
			start++;
		}
		int index = start;
		boolean lastEqual = true;
		while (index != nMSys.length) {
			res[index] = rand1ToM(m) - 1;
			if (lastEqual) {
				if (res[index] > nMSys[index]) {
					index = start;
					lastEqual = true;
					continue;
				} else {
					lastEqual = res[index] == nMSys[index];
				}
			}
			index++;
		}
		return res;
	}
	// 把m进制的数转成10进制
	public static int getNumFromMSysNum(int[] mSysNum, int m) {
		int res = 0;
		for (int i = 0; i != mSysNum.length; i++) {
			res = res * m + mSysNum[i];
		}
		return res;
	}
}
