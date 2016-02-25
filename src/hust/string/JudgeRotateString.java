package hust.string;

/**
 * 判断字符串的旋转词
 * 对字符串的旋转操作描述如下： 
 * 例如：str = “123456”，str的所有旋转词为："123456"，"234561", “345612", "456123", "561234", "612345"。 
 * 
 * 给定两个字符串str1和str2，实现判断str1是否是str2的旋转词。
 * 
 * 解答：
 * 1，生成str2+str2的字符串str2Double 
 * 2，判断str1是否是str2Double的子串
 * 
 * @since 2015-11-30
 *
 */
public class JudgeRotateString {
	
	public static void main(String[] args) {
		String str1 = "123456";
		String str2 = "561234";
		
		System.out.println(judge(str1, str2));
	}
	
	public static boolean judge(String str1, String str2) {
		if(str1.length() != str2.length() || str1 == null || str2 == null)
			return false;
		
		String tmp = str2 + str2;
		if(tmp.contains(str1)) { //contains可使用KMP算法
			return true;
		}
		return false;
	}

}
