package hust;

/**
 * ��������Ӵ�����������
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
	
	//����һ������forѭ����ȡ���п��ܵ������С��������ͣ�O(n^3)
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
	
	//��������������һ������ĺ���ǰ����ĺͣ�O(n^2)
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
	
	//��������̫�����ˣ�ÿһ��Ԫ��һ�����������Ҳ������ʱ�临�ӶȺͿռ临�ӶȾ���С��O(n)
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
	
	//�����ģ���̬�滮���������ı��Σ��������һЩ��O(n)
	public static int[] getMaxSubsequenceSum4(int[] a) {
	    int max=0, tmp=0;  //tmp��¼ǰi-1��Ԫ�ص�����Ӵ��ͣ�max��¼ʵ�����ֵ
	    int bStart=0,bEnd=0;  //����Ӵ�����ʵ�ͽ����Ǳ�
	    int[] result = new int[3];
	    for (int i=0; i<a.length; i++) {
	        if (tmp > 0) {   //���ǰi-1���Ӵ��ĺʹ���0�������i��Ԫ��ֱ����ͣ���������ж������͵Ľ��С��ǰi-1��������Ӵ��ͣ��򲻸���
	        	tmp += a[i];
	        	bEnd=i;
	        } else {    //���ǰi-1���Ӵ��ĺ�С�ڻ����0����������Ӵ����´ӵ�i��ʼ������ǰ��ĺͱȽ�
	        	tmp = a[i];
	        	bStart=i;  
	            bEnd=i;  
	        }
	        if (tmp > max) { //��������Ӵ���
	        	result[0]=bStart;  
	        	result[1]=bEnd;
	        	max = tmp;
	        }
	    }
	    result[2] = max;
	    return result;
	}
	
}
