package hust;

/**
 * ģʽƥ�䣺�Ӵ��Ķ�λ����
 */

public class MatchString {
	public static void main(String[] args) {
		String father = "abcdefg";
		String child = "de";
		
		int pos = match(father, child);
		System.out.println(pos);
	}
	
	//ʱ�临�Ӷȣ�o(m + n)��m���������ȣ�n���Ӵ�/ģʽ����
	//�����ȫ����01����ô��������0(m * n)
	public static int match(String father, String child) {
		int i=0, j=0;
		while(i<father.length() && j<child.length()) {
			if(father.charAt(i) == child.charAt(j)) {
				i++;j++;
			} else { //�ؼ���ȷ��ƥ��һ�벻�ɹ���Ļ��˵�
				j=0;
				i=i-j+1;
			}
		}
		if(j == child.length()) {
			return i-j;
		}
		return -1;
	}
	
	//KMP�㷨���Ƚϸ��ӡ����ԣ�
}
