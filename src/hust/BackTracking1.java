package hust;

import java.util.ArrayList;

/**
 * ���ݷ���һ��ѡ�����������ֳ�Ϊ��̽������ѡ��������ǰ�������ԴﵽĿ�ꡣ����̽����ĳһ��ʱ��
 * 		     ����ԭ��ѡ�񲢲��Ż�ﲻ��Ŀ�꣬���˻�һ������ѡ�������߲�ͨ���˻����ߵļ���Ϊ���ݷ���
 * 
 * ���⣺����n��Ԫ�صļ��ϵ��Ӽ�����Ԫ�ص�ȡ�����⣩
 */
public class BackTracking1 {

	public static void main(String[] args) {
		ArrayList<String> source = new ArrayList<String>(); //ԭʼ����
		ArrayList<String> dest = new ArrayList<String>();   //ԭ�����Ӽ�
		source.add("1");
		source.add("2");
		source.add("3");
		SubSet(0, source, dest);
		
		char[] a = {'a','b','c'}; 
		subSet(a);
		System.out.println(1<<3);
	}
	
	//����һ��Ԫ��i�Ƿ����Ӽ�dest��
	public static void SubSet(int i, ArrayList<String> src, ArrayList<String> dest) {
		if(i >= src.size()) {//�˳������������һ��Ԫ�صĴ�����
			System.out.println(dest.toString());
		} else {
			dest.add(src.get(i));
			SubSet(i+1, src, dest); //����Ԫ��i���Ӽ�
			dest.remove(src.get(i));
			SubSet(i+1, src, dest); //������i���Ӽ�
		}
	}
	
	//������
	private static void subSet(char[] a){
		int n = a.length;
		for(int i=0; i<(1<<n); i++){//ѭ��2^n��
			String setStr = Integer.toBinaryString(i);//��intֵת���ɶ�����ֵ���ַ���
			int unChoose = n-setStr.length();
			System.out.print("{");
			for(int j=0; j<setStr.length(); j++){
				if(setStr.charAt(j)=='1')//1��ʾ��ѡ�У�0��ʾû�б�ѡ��
					System.out.print(a[unChoose+j]);
			}
			System.out.println("}");
		}
	}
	
	/**
	 * ����һ���ݹ�ʵ��    --by �����ݽṹ���㷨��
	 * ��ԭʼ�����е�Ԫ�ؽǶȳ�������Ҫô����ĳһ�Ӽ���Ҫô�����ڣ�O(2^n)
	 * 
	 * 
	 * ���������ǵݹ�ʵ��   --by http://blog.csdn.net/silent_strings/article/details/48732583
	 * �ö����Ƶ�0��ʾ�У�1��ʾû�У�101,��ʾ��a��c��û��b�����{a,c}
	 */

}
