package hust.other;

/**
 * 字符转转十进制整数
 * 
 * 值得细看，关于输入异常的处理、正负数、超范围（如何界定正负数范围）、边界检测、核心转换算法等等。
 * 
 * @since 2015-12-15
 *
 */
public class StringConvertToInteger {

	public static final int VALID =1;
	public static final int INVALID =0;

	public static void main(String[] args) {
		System.out.println(convert(""));
		System.out.println(convert("-"));
		System.out.println(convert("+"));
		System.out.println(convert("-12"));
		System.out.println(convert("+12"));
		System.out.println(convert("12"));
		System.out.println(convert("a12"));
		System.out.println(convert("1a2"));
		System.out.println(convert("12a"));
		System.out.println(convert("1111111111111"));
		System.out.println(convert("-111111111111"));
	}
	
	public static long convert(String str) {
		int status = INVALID; //输入字符串的合法性
		long num =0;
		if(str == null || "".equals(str)) {
			status = INVALID;
		} else {
			char[] c = str.toCharArray();
			int flag = 1; //正负数
			if(c[0] == '-') {
				flag = -1;
			}
			
			int i = flag==-1 ? 1 : (c[0] == '+') ? 1 : 0;
			while(i < c.length) {
				if(c[i] >= '0' && c[i] <= '9') {
					num = num * 10 + flag * (c[i] - '0'); //转换的核心算法
					if((flag == 1 && num > 0x7fffffff) || (flag == -1 && num < 0x80000000)) { //超出范围（正整数的最大值是0x7fff ffff，最小的负整数是0x8000 0000）
						num =0;
						break;
					}
					i++;
				} else {
					num =0;
					break;
				}
			}
			if(i != c.length) { //判断输入的字符串是否全都是数字
				status = VALID;
			}
		}
		
		if (status == INVALID) {
//			throw new RuntimeException("Invalid parameter!");
		}
		
		return num;
	}
}
