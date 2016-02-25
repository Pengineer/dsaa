package hust.string;

/**
 * 翻转一个英文句子中单词的顺序，单词本身不变。（无标点符号）
 * 例如输入 "I am a student"，输出"student a am I".
 * 
 * @see RotateString
 * @since 2015-12-14
 *
 */
public class ReverseWordInSentence {

	public static void main(String[] args) {
		String str = "I am a student";
		System.out.println(reverseSentence(str));
	}
	
	//将翻转字符串变成翻转指定区间的字符数组
	public static char[] reverseCharArray(char[] ss, int start, int end) {
		while(start < end) {
			char temp = ss[start];
			ss[start] = ss[end];
			ss[end] = temp;
			start++;
			end--;
		}
		return ss;
	}
	
	public static String reverseSentence(String str) {
		char[] ss = str.toCharArray();
		ss = reverseCharArray(ss, 0, ss.length - 1);
		for (int start = 0, end = 0; end < ss.length; end++) {
			if(ss[end] == ' ') {
				reverseCharArray(ss, start, end - 1);
				start = end + 1;
			} else if (end == ss.length - 1) {
				reverseCharArray(ss, start, end);
				start = end + 1;
			}
		}
		return String.valueOf(ss);
	}
}
