package hust.matrix;

/**
 * 顺时针旋转打印矩阵
 * 
 * 关键：左上角和右下角来确定一个矩阵
 * 
 * @since 2015-11-28
 *
 */
public class RotatePrintMatrix {
	
	public static void main(String[] args) {
		//最内圈是一个4元素方针
		int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		print(matrix, 0, 0, 3, 3);
		System.out.println();
		
		//最内圈是一个单元素方阵
		int[][] m2 = {{1,2,3},{4,5,6},{7,8,9}};
		print(m2, 0, 0, 2, 2);
		System.out.println();
		
		//总行数 < 总列数
		int[][] m3 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		print(m3, 0, 0, 2, 3);
		System.out.println();
		
		//总行数 > 总列数
		int[][] m4 = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		print(m4, 0, 0, 3, 2);
		System.out.println();
	}
	
	//记录矩阵的左上和右下点的位置
	public static void print(int[][] matrix, int leftUpX, int leftUpY, int rightDownX, int rightDownY) {
		int c1 = leftUpX, r1 = leftUpY;
		int c2 = rightDownX, r2 = rightDownY;
		
		for (int i=r1; i <= r2; i++) {
			System.out.print(matrix[c1][i] + " ");
		}
		for (int i=c1+1; i <= c2; i++) {
			System.out.print(matrix[i][r2] + " ");
		}
		for (int i=r2-1; i >= r1; i--) {
			System.out.print(matrix[c2][i] + " ");
		}
		for (int i = c2 -1; i > c1; i--) {
			System.out.print(matrix[i][r1] + " ");
		}
		
		c1++;r1++;c2--;r2--;//缩小矩阵
		
		//不规则矩阵：总行数 < 总列数
		if(c1 == c2 && r1 < r2) {
			for (int i = r1; i <= r2; i++) {
				System.out.print(matrix[c1][i] + " ");
			}
			return;
		}
		
		//不规则矩阵：总行数 > 总列数
		if(r1 == r2 && c1 < c2) {
			for (int i = c1; i <= c2; i++) {
				System.out.print(matrix[i][r1] + " ");
			}
			return;
		}
		
		//规则矩阵（方阵）：总行数 = 总列数
		if(c1 <= c2) {
			print(matrix, c1, r1, c2, r2);
		}
	}
}
