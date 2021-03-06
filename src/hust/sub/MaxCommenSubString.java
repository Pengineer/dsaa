package hust.sub;


/**
 * 求两个字符串的最长公共子串
 * 
 * 1.公共子串的元素必须相邻:
	LCS问题就是求两个字符串最长公共子串的问题。解法就是用一个矩阵来记录两个字符串中所有位置的两个字符之间的匹配情况，若是匹配则为1，否则为0。
然后求出对角线最长的1序列，其对应的位置就是最长匹配子串的位置.

	下面是字符串21232523311324和字符串312123223445的匹配矩阵，前者为X方向的，后者为Y方向的。不难找到，红色部分是最长的匹配子串。通过查找位置
我们得到最长的匹配子串为：21232
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   0 1 0 0 0 0 0 0 0 1 1 0 0 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 1 0 0 0 0 0 0 0 1 1 0 0 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
但是在0和1的矩阵中找最长的1对角线序列又要花去一定的时间。通过改进矩阵的生成方式和设置标记变量，可以省去这部分时间。下面是新的矩阵生成方式：
   0 0 0 1 0 0 0 1 1 0 0 1 0 0 0
   0 1 0 0 0 0 0 0 0 2 1 0 0 0 0
   1 0 2 0 1 0 1 0 0 0 0 0 1 0 0
   0 2 0 0 0 0 0 0 0 1 1 0 0 0 0
   1 0 3 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 4 0 0 0 2 1 0 0 1 0 0 0
   1 0 1 0 5 0 1 0 0 0 0 0 2 0 0
   1 0 1 0 1 0 1 0 0 0 0 0 1 0 0
   0 0 0 2 0 0 0 2 1 0 0 1 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
   0 0 0 0 0 1 0 0 0 0 0 0 0 0 0
   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
当字符匹配的时候，我们并不是简单的给相应元素赋上1，而是赋上其左上角元素的值加一。我们用两个标记变量来标记矩阵中值最大的元素的位置，在矩阵生成的
过程中来判断当前生成的元素的值是不是最大的，据此来改变标记变量的值，那么到矩阵完成的时候，最长匹配子串的位置和长度就已经出来了。

算法的基本思想：
当字符匹配的时候，不是简单的给相应元素赋上1，而是赋上其左上角元素的值加一。我们用两个标记变量来标记矩阵中值最大的元素的位置，在矩阵生成的过程
中来判断当前生成的元素的值是不是最大的，据此来改变标记变量的值，那么到矩阵完成的时候，最长匹配子串的位置和长度就已经出来了。

类比Subquence问题的动态规划解法，Substring也可以用动态规划解决，令c[i][j]表示以i/j结尾的Xi和Yi的最大Substring的长度，动态转移方程为：
   如果xi == yj， 则 c[i][j] = c[i-1][j-1]+1
   如果xi ! = yj,  那么c[i][j] = 0
   
   
 * @author liangjian
 * @since 2016-03-07
 * @see http://blog.csdn.net/hackbuteer1/article/details/6686931
 * @see http://www.cnblogs.com/dartagnan/archive/2011/10/06/2199764.html
 */
public class MaxCommenSubString {

	public static int longest_common_substring(char[] str1, char[] str2) {
		//输入校验（省）
		int len1 = str1.length;
		int len2 = str2.length;
		int[][] c = new int[len2+1][len1+1];
		for(int i=0; i<len1+1; i++) {
			c[0][i] = 0;
		}
		for(int i=0; i<len2+1; i++) {
			c[i][0] = 0;
		}
		int max = Integer.MIN_VALUE;
		for(int i=1; i<len2+1 ; i++) {
			for(int j=1; j<len1+1; j++) {
				c[i][j] = (str1[j-1] == str2[i-1]) ? (c[i-1][j-1] + 1) : 0;
				max = Math.max(max, c[i][j]);
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		char[] c1 = "abcdefg".toCharArray();
		char[] c2 = "acdeg".toCharArray();
		System.out.println(longest_common_substring(c1, c2));
	}
}
