package hust;

/**
 * 统计1到n整数中1出现的次数
 * 
 * 解法一：
 * 从1到n遍历统计每一个数中1的个数。效率低。
 * 
 * 解法二：
 * 分别求每一位上出现1的次数，然后相加。比如21345，将该数分成1-1345和1346-21345，这样分段就是为了递归。
 * 第二段万位为1的个数是10000个（10000-19999），其它四位为1的个数均是2*10^3，之所以乘2，是因为万位有两种
 * 情况（比如，千位为1，百十个共1000中情况，万位可以为1和2；百位为1，千十个共1000中情况，万位可能为0、1、2，
 * 万位只有两种满足范围条件）这里不需要考虑21133中的1被算了两次，因为这个数本来就有两个1，就应该被计算两次。
 * 同样的方法递归求第一段。
 * 
 * @author 2015-12-10
 *
 */
public class NumberOf1 {

	public static void main(String[] args) {
		System.out.println(getNumberOf1_1(10000));
		System.out.println(getNumberOf1_2(10000));
	}
	
	//解法一
	public static int getNumberOf1_1(int n) {
		if(n < 0) {
			System.out.println("Invalid paramter");
			return -1;
		}
		int count=0;
		for (int i = 0; i <= n; i++) {
			count += numberInOneNum(i);
		}
		return count;
	}
	public static int numberInOneNum(int n) {
		int count =0;
		while(n>0) {
			if(n % 10 == 1) count++;
			n = n / 10;
		}
		return count;
	}
	
	//解法二
	public static int getNumberOf1_2(int n) {
		int len =getLength(n);
		int first = getFirst(n);
		if (len == 1 && first == 0) return 0; //n为0
		
		int numFirstDigit =0; //最高位为1的次数（分两种情况）
		if(first > 1) {
			numFirstDigit = getPowerBase10(len-1);
		} else if(first == 1) {
			numFirstDigit = n - getPowerBase10(len-1) + 1;
		}
		int numOtherDigit = first * (len -1) * getPowerBase10(len -2); //最高位之外的其它几位中1的次数
		int numRecursive = getNumberOf1_2(n % getPowerBase10(len -1)); //去掉最高位，递归
		
		return numFirstDigit + numOtherDigit + numRecursive;
	}
	
	public static int getLength(int n) {
		int len=0;
		while(n != 0) {
			n = n / 10;
			len++;
		}
		return len ==0 ? 1 : len;
	}
	//返回n的第一位数
	public static int getFirst(int n) {
		while(n > 9) {
			n = n/10;
		}
		return n;
	}
	//返回10^n
	public static int getPowerBase10(int n) {
		int result = 1;
		for(int i=0; i<n; i++) {
			result *= 10;
		}
		return result;
	}

}
