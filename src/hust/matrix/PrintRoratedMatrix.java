package hust.matrix;

/**
 * 打印旋转后的矩阵（必须是方阵）
 * 
 * 关键：1，由外到内逐层旋转，与 "RotatePrintMatrix.java" 思路有点相似
 * 	   2，每一层在旋转时，再每四个元素进行分组，这四个元素就是需要相互交换位置的元素
 * 
 * @since 2015-11-28
 *
 */
public class PrintRoratedMatrix {

	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		printMatrix(matrix);
		rotateMatrix(matrix, 0, 0, 3, 3);//左上角和右下角元素确定一个矩阵的一层
		printMatrix(matrix);
	}
	
	public static void rotateMatrix(int[][] matrix, int tR, int tC, int dR, int dC) {
		if(dR <= tR) {
			return;
		}
		//每一层的最左边n个元素分别是n-1个组的元素(注意1和13是同一组的)
		for(int i=0; i<dR-tR; i++) {
			int tmp = matrix[tR + i][tC];
			matrix[tR + i][tC] = matrix[dR][tC+i];
			matrix[dR][tC+i] = matrix[dR-i][dC];
			matrix[dR-i][dC] = matrix[tR][dC-i];
			matrix[tR][dC-i] = tmp;
		}
		
		tR++;tC++;dR--;dC--;
		rotateMatrix(matrix, tR, tC, dR, dC);//旋转内层
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i=0; i< matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "\t" );
			}
			System.out.println();
		}
	}

}
