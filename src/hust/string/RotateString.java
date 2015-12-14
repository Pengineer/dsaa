package hust.string;

/**
 * 字符串旋转问题   
 * 
 * 给定一个字符串str，和一个非负的整数i代表str中的位置，包括i位置在内的左侧部分想移到右边来，i位置右边的位置想移到左边来，请写函数实现。
 * 例如： str = "ABCDEFGH";
 * 		 i = 4;   
 * 		 调整结果为："FGHABCDE"
 * 要求：时间复杂度为O(N)，额外空间复杂度为O(1)。
 * 
 * 解法一：先左部分逆序，再右部分逆序，然后整体逆序
 * 解法二：1，根据i位置将str分为左右两个部分。如果左部分小，左部分就和右部分的右侧交换；如果右部分小，右部分就和左部分的左侧交换；
 * 		  2，交换之后会产生新的左右部分，继续按照这种方式交换，直到左部分和右部分等长，交换之后就停止。
 * 
 * @see ReverseWordInSentence
 * @author 2015-11-29
 *
 */
public class RotateString {
	
	public static void main(String[] args) {
		char[] str = "ABCDEFGH".toCharArray();
		rotate1(str, 4);
		System.out.println(String.valueOf(str));
	}
	
	//解法一
	public static void reverse(char[] chas, int start, int end) {
		char tmp = 0;
		while (start < end) {
			tmp = chas[start];
			chas[start] = chas[end];
			chas[end] = tmp;
			start++;
			end--;
		}
	}
	public static void rotate1(char[] chas, int index) {
		if (chas == null || index <= 0 || index >= chas.length) {
			return;
		}
		reverse(chas, 0, index);
		reverse(chas, index + 1, chas.length - 1);
		reverse(chas, 0, chas.length - 1);
	}
	
	//解法二
	public static void rotate2(char[] chas, int size) {
		if (chas == null || size <= 0 || size >= chas.length) {
			return;
		}
		int start = 0;
		int end = chas.length - 1;
		int lpart = size;
		int rpart = chas.length - size;
		int s = Math.min(lpart, rpart);
		int d = lpart - rpart;
		while (true) {
			exchange(chas, start, end, s);// * 
			if (d == 0) {
				break;
			} else if (d > 0) {
				start += s;
				lpart = d;
			} else {
				end -= s;
				rpart = -d;
			}
			s = Math.min(lpart, rpart);
			d = lpart - rpart;
		}
	}
	public static void exchange(char[] chas, int start, int end, int size) {
		int i = end - size + 1;
		char tmp = 0;
		while (size-- != 0) {
			tmp = chas[start];
			chas[start] = chas[i];
			chas[i] = tmp;
			start++;
			i++;
		}
	}
	
	//自己的解法，和解法二思想一致，没写出final code
//	public static String rotate(String src, int index) {
//		char[] srcb = src.toCharArray();
//		int pos=0;
//		while (pos < index) {
//			if(index+1+pos == srcb.length) break;
//			char privot = srcb[pos];
//			srcb[pos] = srcb[index+1+pos];
//			srcb[index+1+pos] = privot;
//			pos++;
//		}
//		while(pos < index) {
//			char privot = srcb[pos];
//			srcb[pos] = srcb[srcb.length - pos];
//			srcb[srcb.length - pos] = privot;
//			pos++;
//		}
//		
//		return "";
//	}
}
