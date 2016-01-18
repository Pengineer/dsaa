package hust.plalindrome;

/**
 * Manacher算法（戏称马拉车算法）
 * 【题目】
 * 给定一个字符串str，返回str中的最长回文子串的长度。
 * 【举例】
 * str=“123”。其中的最长回文子串“1”或者“2”或者“3”，所以返回1。
 * str=“abc1234321ab”。其中的最长回文子串“1234321”，所以返回7。
 * 
 * @author 2015-12-28
 *
 */
public class Manacher {
	
	public static void main(String[] args) {
		Manacher m = new Manacher();
		System.out.println(m.maxLcpsLength("abc1234321ab"));
	}
	
	public char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        /*
         * 定义四个关键变量：
         * charArr[]：在原字符串的字符之间间隔插入单个特殊字符'#'得到2N级别的新串，这样可以避免原字串长度的奇偶之分（其实可以证明插入任意字符都一样）
         * pArr[]：用于存放每一个字符处的最长回文子串长度。
         * pR：回文处理过程中到达的最右边的下一个位置，只增不减。（很关键）
         * index：pR更新的时候的回文中心的位置。
         * 
         * 假设当前处理元素为i，i`为i关于index对称的点，总共有四种情况：
         * （1）i`的回文被以index为中心的回文完全包括（左边界不相等），可以直接得到pArr[i]=PArr[i`]值；
         * （2）i`的回文最左边超出了index的最左边，也可以直接得到pArr[i]=pR-i；
         * （3）i`的回文最左边与以index为中心的回文左边界重合，则需要在i现有的部分回文基础上扩充；
         * （4）i在以pR的右边，重新对i进行完全扩充，并更新index。
         */
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }
}
