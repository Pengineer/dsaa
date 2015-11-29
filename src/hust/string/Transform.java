package hust.string;
/**
 * 对于两个字符串A和B，如果A和B中出现的字符种类相同且每种字符出现的次数相同，则A和B互为变形词，请设计一个高效算法，检查两给定串是否互为变形词。
 * 给定两个字符串A和B及他们的长度，请返回一个bool值，代表他们是否互为变形词。
 * 
 * @author 2015-11-26
 *
 */
public class Transform {

	public static void main(String[] args) {
		System.out.println(verify2("abc", "bba"));
		
//		int[] map = new int[256];
//		map['a'] = 2;   //这也可以，一般map[(int)'a'] = 2; 
	}
	
	//最愚蠢的方式
	public static boolean verify1(String A, String B) {
		if(A.length() != B.length()) return false;
		char[] p = A.toCharArray();
		for (int i = 0; i < p.length; i++) {
			if (B.indexOf(p[i]) < 0) return false;
		}
		return true;
	}
	
	//最佳
	public static boolean verify2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] hashMap = new int[256]; //字符总个数256个，初始值为0，使用数组实现简单哈希表
        for (int i = 0; i < chas1.length; i++) {
        	hashMap[chas1[i]]++;
        }
        for (int i = 0; i < chas2.length; i++) {
            if (hashMap[chas2[i]]-- == 0) { //先比较，再自减，一个数组解决问题
                return false;
            }
        }
        return true;
    }
	
	//大多人使用两个数组
	public static boolean verify2_1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map1 = new int[256]; //字符总个数256个
        int[] map2 = new int[256]; //字符总个数256个
        for (int i = 0; i < chas1.length; i++) {
            map1[chas1[i]]++;
            map2[chas2[i]]++;
        }
        for (int i = 0; i < chas2.length; i++) {
            if (map1[chas2[i]] != map2[chas2[i]]) {
                return false;
            }
        }
        return true;
    }
}
