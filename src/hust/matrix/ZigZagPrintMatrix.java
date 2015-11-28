package hust.matrix;

/**
 * “之”字形打印矩阵
 * @author 2015-11-28
 *
 */
public class ZigZagPrintMatrix {
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		printMatrix_1(matrix);
		System.out.println();
		printMatrix_2(matrix);
	}
	
	//确定斜线右上角起点(缺点：总是从斜线上面开始打印)
	public static void printMatrix_1(int[][] matrix) {
		int r=0,c=0;
		while (c < matrix[0].length || r < matrix.length) {
			printDiagonalLine_1(matrix, r, c);
			if(c != matrix[0].length-1) {
				c++;
			} else {
				r++;
			}
			
			if(c == (matrix[0].length -1) && r ==  matrix.length)
				break;
		}
	}
	
	//打印斜线
	public static void printDiagonalLine_1(int[][] matrix, int r, int c) {
		while(c >= 0 && r <= matrix.length-1) {
			System.out.print(matrix[r][c] + " ");
			c--;
			r++;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	//真正的“之”字形斜线的打印方式可上可下，因此需要确定斜线的两端
	public static void printMatrix_2(int[][] matrix) {
		boolean fromUp = false;
		int tr =0, tc =0, br =0, bc =0;
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		while (tr <= endR) {
			printDiagonalLine_2(matrix, tr, tc, br, bc, fromUp);
			tr = (tc == endC) ? (++tr) : tr; //1，上顶点右移/下移，必导致下顶点下移/右移；2，注意求坐标顺序
			tc = (tc == endC) ? endC : (++tc); 
			bc = (br == endR) ? (++bc) : bc;
			br = (br == endR) ? endR : (++br);
			fromUp = !fromUp;
		}
	}
	public static void printDiagonalLine_2(int[][] matrix, int tr, int tc, int br, int bc, boolean fromUp) {
		if(fromUp) {
			while(tr <= br) {
				System.out.print(matrix[tr++][tc--] + " ");
			}
		} else {
			while(tr <= br) {
				System.out.print(matrix[br--][bc++] + " ");
			}
		}
	}
}
