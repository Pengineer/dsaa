package hust.stack.queue;

import java.util.Stack;

/**
 * 利用栈实现十进制转十六进制
 * 
 * 大于十进制的进制（十六进制除外）可以转成十进制，反之不能，除非有字母定义。
 * 
 * @since 2015-12-05
 *
 */
public class StackDemo2 {

	public static void main(String[] args) {
		System.out.println(convertDecimalToHex(26));
	}
	
	public static String convertDecimalToHex (int value) {
		Stack<Integer> s = new Stack<Integer>();
		int n = value;
		String e="";
		while(n != 0) {
			if (n < 16) {
				s.push(n);
				break;
			} else {
				s.push(n % 16);
				n=n/16;
			}
		}
		while(s.size() != 0) {
			int c = s.pop();
			if(c >= 10)
				e = e + (char)((int)('A')+(c-10));
			else
				e+=c;
		}
		return e;
	}
}
