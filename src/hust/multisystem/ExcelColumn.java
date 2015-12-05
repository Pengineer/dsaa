package hust.multisystem;

/**
 * 在Excel2010中，用A表示第一列，B表示第二列，...，Z表示第26列，AA表示第27列，依次类推。
 * 
 * 要求：输入英文字母，输出列数。
 * 
 * @author 2015-12-05
 *
 */
public class ExcelColumn {

	public static void main(String[] args) {
//		byte[] b = "AB".getBytes();
//		char[] c = "AB".toCharArray();
//		System.out.println(b[0] + " " + (int)b[0] + " " + c[0]);
		
		System.out.println(getColumn("AA"));
	}
	
	public static int getColumn(String input) {
		if(input == null || "".equals(input.trim()) || !input.matches("^[A-Z]+$")) {
			throw new RuntimeException("Invalid parameter");
		}
		
		byte[] b = input.getBytes();
		int result = 0;
		for (int i = 0; i < b.length; i++) { //多进制转十进制
			result = result * 26 + (int)b[i] - 64;
		}
		
		return result;
	}
}
