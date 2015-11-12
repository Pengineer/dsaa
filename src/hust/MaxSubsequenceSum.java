package hust;

/**
 * 求和最大的子串，并输出其和
 */
public class MaxSubsequenceSum {

	public static void main(String[] args) {
		int[] a = {3, -2, -1, 4, 6, -5, -3, 10, -2};
		System.out.println(getMaxSubsequenceSum1(a));
		System.out.println(getMaxSubsequenceSum2(a));
		System.out.println(getMaxSubsequenceSum3(a));
		
		int[] b = getMaxSubsequenceSum4(a);
		System.out.println(b[0] + ".." + b[1] + ".." + b[2]);
	}
	
	//方法一：两层for循环获取所有可能的区间大小，求区间和，O(n^3)
	public static int getMaxSubsequenceSum1(int[] a) {
		int max = a[0];
		for(int i=0; i<a.length; i++){
			for(int j=i; j<a.length; j++){
				int sum =0;
				for(int k=i; k<=j; k++) {
					sum += a[k];
				}
				if(sum > max) {
					max = sum;
				}
			}
		}
		return max;
	}
	
	//方法二：利用上一个区间的和求当前区间的和，O(n^2)
	public static int getMaxSubsequenceSum2(int[] a) {
		int max = a[0];
		for(int i=0; i<a.length; i++){
			int sum=0;
			for(int j=i; j<a.length; j++){
				sum += a[j];
				if(sum > max) {
					max = sum;
				}
			}
		}
		return max;
	}
	
	//方法三：太巧妙了，每一个元素一旦被处理就再也不处理，时间复杂度和空间复杂度均最小，O(n)
	public static int getMaxSubsequenceSum3(int[] a) {
		int tmp = a[0], max=a[0];
		for(int i=1; i<a.length; i++){
			tmp += a[i];
			if(tmp > max) {
				max = tmp;
			} else if(tmp < 0) {
				tmp = 0;
			}
		}
		return max;
	}
	
	//方法四：动态规划，方法三的变形，容易理解一些，O(n)
	public static int[] getMaxSubsequenceSum4(int[] a) {
	    int max=0, tmp=0;  //tmp记录前i-1个元素的最大子串和，max记录实际最大值
	    int bStart=0,bEnd=0;  //最大子串的其实和结束角标
	    int[] result = new int[3];
	    for (int i=0; i<a.length; i++) {
	        if (tmp > 0) {   //如果前i-1个子串的和大于0，则与第i个元素直接求和，下面接着判断如果求和的结果小于前i-1个的最大子串和，则不更新
	        	tmp += a[i];
	        	bEnd=i;
	        } else {    //如果前i-1个子串的和小于或等于0，则尝试最大子串重新从第i开始，并与前面的和比较
	        	tmp = a[i];
	        	bStart=i;  
	            bEnd=i;  
	        }
	        if (tmp > max) { //更新最大子串和
	        	result[0]=bStart;  
	        	result[1]=bEnd;
	        	max = tmp;
	        }
	    }
	    result[2] = max;
	    return result;
	}
	
}
