package hust.plenty.data;

/**
 * 大数相加
 * @author liangjian
 *
 */

public class BigDataCompute {
	public static void main(String[] args) {
		String result = add("71111111111111111111118", "72222222222222222222224");
		System.out.println(result);
	}
	
	public static String add(String s1, String s2) {
		if(s1 == null || s2 == null) {
			return null;
		}
		//获取字符串反转后的字符数组
		char[] c1 = new StringBuilder(s1).reverse().toString().toCharArray();
		char[] c2 = new StringBuilder(s2).reverse().toString().toCharArray();
		int len = c1.length > c2.length ? c1.length : c2.length;
		//获取每一位相加的结果
		int[] result = new int[len+1];
		for (int i = 0; i < result.length; i++) {
			int aint = i < c1.length ? (c1[i] - '0') : 0;
			int bint = i < c2.length ? (c2[i] - '0') : 0;
			result[i] = aint + bint;
		}
		// 处理相加后的结果
		for (int i = 0; i < result.length; i++) {
			if(result[i] >= 10)  {
				result[i+1] = result[i+1] + result[i] / 10;
				result[i] = result[i] % 10;
			}
		}
		// 去除前导0
		StringBuilder sb = new StringBuilder();
		boolean pre = true;
		for (int i = result.length -1; i >=0 ; i--) {
			if (result[i] == 0 && pre) {
				continue;
			} else {
				pre = false;
				sb.append(result[i]);
			}
		}
		return sb.toString();
	}
}
