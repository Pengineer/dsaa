package hust.matrix;

/**
 * 题目：
 *     地上有一个m行n列的方格，一个机器人从坐标(0,0)的各自开始移动，它每一次可以向上、下、左、右移动一格，但不能进
 * 入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格(35,37)，因为3+5+3+7=18。但它不能进入
 * 方格(35,38)，因为3+5+3+8=19>18。请问该机器人能够到达多少个格子？
 * 
 * @see StringPathInMatrix   关于回溯法的一个题型
 * @author 2015-12-15
 *
 */
public class MovableInMatrix {

	public static void main(String[] args) {
		System.out.println(movableCount(10, 12, 1));
	}
	
	//初始化（由于方格是以(0,0)开始的自然数矩阵，不需要额外输入矩阵）
	public static int movableCount(int rows, int cols, int threshold) {
		if(rows < 0 || cols < 0 || threshold < 0) {
			return 0;
		}
		
		boolean[] visited = new boolean[rows * cols];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		
		return movableCountCore(rows, cols, 0, 0, threshold, visited);
	}

	//递归计算能达到的格子数
	public static int movableCountCore(int rows, int cols, int row, int col, 
			int threshold, boolean[] visited) {
		int count =0;
		if(row >= 0 && row < rows && col >= 0 && col < cols 
		            && getDigitSum(row, col) <= threshold 
		            && !visited[row * cols + col]) {
			visited[row * cols + col] = true;   //必须在递归之前
			count = 1 + movableCountCore(rows, cols, row - 1, col, threshold, visited)   //四面相加
					  + movableCountCore(rows, cols, row + 1, col, threshold, visited)
					  + movableCountCore(rows, cols, row, col - 1, threshold, visited)
					  + movableCountCore(rows, cols, row, col + 1, threshold, visited);
		}
		
		return count;
	}

	//获取行列坐标的数位和
	public static int getDigitSum(int row, int col) {
		int sum =0;
		while(row > 0){
			sum += row % 10;
			row /= 10;
		}
		while(col > 0){
			sum += col % 10;
			col /= 10;
		}
		return sum;
	}
}
