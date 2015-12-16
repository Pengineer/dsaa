package hust.matrix;

/**
 * 题目：
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中任意一格开始，每一步
 * 可以在矩阵中间向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 
 * 举例分析：
 * 例如在下面的3*4的矩阵中包含一条字符串"bcced"的路径。但矩阵中不包含字符串"abcb"的路径，因为字符串的第一个字符b
 * 占据了矩阵中的第一行第二格子之后，路径不能再次进入这个格子。 
 * a b c e 
 * s f c s 
 * a d e e
 * 
 * 解题思路：
 *     这是一个可以用回朔法解决的典型题。首先，在矩阵中任选一个格子作为路径的起点。假设矩阵中某个格子的字符为ch并且
 * 这个格子不可能处在路径上的第i个位置。如果路径上的第i个字符不是ch，那么这个格子不可能处在路径上的第i个位置。如果
 * 路径上的第i个字符不是ch，那么这个格子不可能处在路径上的第i个位置。如果路径上的第i个字符正好是ch，那么往相邻的格
 * 子寻找路径上的第i+1个字符。除在矩阵边界上的格子之外，其他格子都有4个相邻的格子。重复这个过程直到路径上的所有字符
 * 都在矩阵中找到相应的位置。 
 *     由于回朔法的递归特性，路径可以被看成一个栈。当在矩阵中定位了路径中前n个字符的位置之后，在与第n个字符对应的格
 * 子的周围都没有找到第n+1个字符，这个时候只好在路径上回到第n-1个字符，重新定位第n个字符。 
 *     由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。 
 *     当矩阵中坐标为（row,col）的格子和路径字符串中下标为pathLength的字符一样时，从4个相邻的格子(row,col-1),
 * (row-1,col),(row,col+1)以及(row+1,col)中去定位路径字符串中下标为pathLength+1的字符。 
 *     如果4个相邻的格子都没有匹配字符串中下标为pathLength+1的字符，表明当前路径字符串中下标为pathLength的字符在矩
 * 阵中的定位不正确，我们需要回到前一个字符(pathLength-1)，然后重新定位。一直重复这个过程，直到路径字符串上所有字符
 * 都在矩阵中找到合适的位置。
 * 
 * @author 2015-12-16
 *
 */
public class StringPathInMatrix {

	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * @param matrix
	 * @param rows
	 * @param cols
	 * @param str
	 * @return
	 */
	public static boolean hasPath(int[][] matrix, int rows, int cols, String str) {
		if(matrix == null || rows < 1 || cols < 1 || str == null) {
			return false;
		}
		
		boolean[] visited = new boolean[rows * cols];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		
		int[] pathLength = {0};
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if(hasPathCore(matrix, rows, cols, row, col, str, pathLength, visited)) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * 
	 * @param matrix
	 * @param rows
	 * @param cols
	 * @param row
	 * @param col
	 * @param str
	 * @param pathLength
	 * @param visited
	 * @return
	 */
	public static boolean hasPathCore(int[][] matrix, int rows, int cols,
			int row, int col, String str, int[] pathLength, boolean[] visited) {
		// TODO Auto-generated method stub
		return false;
	}
}
