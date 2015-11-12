package hust;

/**
 * 模式匹配：子串的定位操作
 */

public class MatchString {
	public static void main(String[] args) {
		String father = "abcdefg";
		String child = "de";
		
		int pos = match(father, child);
		System.out.println(pos);
	}
	
	//时间复杂度：o(m + n)，m是主串长度，n是子串/模式长度
	//如果是全部是01，那么最坏的情况是0(m * n)
	public static int match(String father, String child) {
		int i=0, j=0;
		while(i<father.length() && j<child.length()) {
			if(father.charAt(i) == child.charAt(j)) {
				i++;j++;
			} else { //关键是确定匹配一半不成功后的回退点
				j=0;
				i=i-j+1;
			}
		}
		if(j == child.length()) {
			return i-j;
		}
		return -1;
	}
	
	//KMP算法，比较复杂。（略）
}
