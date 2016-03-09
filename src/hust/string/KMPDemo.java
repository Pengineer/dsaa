package hust.string;

/**
 * 题目：找出一篇文章中某个单词出现的次数（比如music，那么musical也需要匹配）
 * 
 * 原型：模式匹配问题，时间复杂度O(n)
 * 
 * @author liangjian
 * @see IdenticalTree
 *
 */
public class KMPDemo {
	
	public static void main(String[] args) {
		System.out.println(getSum("Hello 常数  abcd我frbcd我fjh bcd我f，bcd 我。", "bcd我f"));
	}
	
	public static int getSum(String source, String pattern) {
        if (source == null || pattern == null || pattern.length() < 1 || source.length() < pattern.length()) {
            return -1;
        }
        char[] ss = source.toCharArray();
        char[] ms = pattern.toCharArray();
        int[] nextArr = getNextArray(ms);
        int index = 0;
        int mi = 0;
        int count=0;
        while(index < ss.length) {
        	if(mi == ms.length) {
        		count++;
        		mi = 0;
        	} else if(ss[index] == ms[mi]) {
        		index++;
        		mi++;
        	} else if(nextArr[mi] == -1) {
        		index++;
        	} else {
        		mi = nextArr[mi];
        	}
        }
        return count;
    }
 
    //获取next函数
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] nextArr = new int[ms.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < nextArr.length) {
            if (ms[pos - 1] == ms[cn]) {
                nextArr[pos++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }
}
