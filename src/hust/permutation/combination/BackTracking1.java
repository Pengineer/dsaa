package hust.permutation.combination;

import java.util.ArrayList;

/**
 * 回溯法：一种选优搜索法，又称为试探法，按选优条件向前搜索，以达到目标。但当探索到某一步时，
 * 		     发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法。
 * 
 * 问题：求含有n个元素的集合的子集。（元素的取舍问题）
 */
public class BackTracking1 {

	public static void main(String[] args) {
		ArrayList<String> source = new ArrayList<String>(); //原始集合
		ArrayList<String> dest = new ArrayList<String>();   //原集合子集
		source.add("1");
		source.add("2");
		source.add("3");
		SubSet(0, source, dest);
		
		char[] a = {'a','b','c'}; 
		subSet(a);
		System.out.println(1<<3);
	}
	
	//方法一：元素i是否在子集dest中
	public static void SubSet(int i, ArrayList<String> src, ArrayList<String> dest) {
		if(i >= src.size()) {//退出条件：对最后一个元素的处理完
			System.out.println(dest.toString());
		} else {
			dest.add(src.get(i)); //子集包含元素i
			SubSet(i+1, src, dest); 
			dest.remove(src.get(i));//子集不包含元素i
			SubSet(i+1, src, dest); 
		}
	}
	
	//方法二
	private static void subSet(char[] a){
		int n = a.length;
		for(int i=0; i<(1<<n); i++){//循环2^n次
			String setStr = Integer.toBinaryString(i);//将int值转换成二进制值的字符串
			int unChoose = n-setStr.length();
			System.out.print("{");
			for(int j=0; j<setStr.length(); j++){
				if(setStr.charAt(j)=='1')//1表示被选中，0表示没有被选中
					System.out.print(a[unChoose+j]);
			}
			System.out.println("}");
		}
	}
	
	/**
	 * 方法一：递归实现    --by 《数据结构与算法》
	 * 从原始集合中的元素角度出发，它要么属于某一子集，要么不属于：O(2^n)
	 * 
	 * 
	 * 方法二：非递归实现   --by http://blog.csdn.net/silent_strings/article/details/48732583
	 * 用二进制的0表示有，1表示没有：101,表示有a和c，没有b，输出{a,c}
	 */

}
