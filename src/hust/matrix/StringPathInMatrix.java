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
 * @since 2015-12-16
 *
 */
public class StringPathInMatrix {

	public static void main(String[] args) {
		char[][] matrix = {{'a', 'b', 'c', 'e'},{'s', 'f', 'c', 's'},{'a', 'd', 'e', 'e'}};
		System.out.println(hasPath(matrix, 3, 4, "bcced".toCharArray()));
		System.out.println(hasPath(matrix, 3, 4, "abcb".toCharArray()));
	}
	
	/**
	 * 指定路径的起始点
	 * @param matrix  输入矩阵
	 * @param rows    矩阵行数
	 * @param cols    矩阵列数
	 * @param str     输入字符串
	 * @return
	 */
	public static boolean hasPath(char[][] matrix, int rows, int cols, char[] str) {
		// 参数校验
		if(matrix == null || rows < 1 || cols < 1 || str == null) {
			return false;
		}
		
		// 声明矩阵元素访问数组并初始化
		boolean[] visited = new boolean[rows * cols];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		
		// 遍历矩阵，指定矩阵中每一个元素为起始点
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
	 * 使用回溯法以（row, col）为起点开始匹配
	 * @param matrix      输入矩阵
	 * @param rows        矩阵行数
	 * @param cols        矩阵列数
	 * @param row         当前元素的行坐标
	 * @param col         当前元素的列坐标
	 * @param str         输入字符串
	 * @param pathLength  已经处理的str中字符个数
	 * @param visited     访问标记数组
	 * @return            是否找到 true是，false否
	 */
	public static boolean hasPathCore(char[][] matrix, int rows, int cols,
			int row, int col, char[] str, int[] pathLength, boolean[] visited) {
		// 全部匹配，返回true
		if(str.length == pathLength[0]) {
			return true;
		}
		boolean hasPath = false;
		if(row >= 0 && row < rows && col >= 0 && col < cols    // 当前传递的元素是否合法（越界）
				    && matrix[row][col] == str[pathLength[0]]  // 单字符匹配成功
				    && !visited[row * cols + col]) {           // 而且本次匹配过程中没有被访问过
			++pathLength[0];
			visited[row * cols + col] = true;
			//匹配下一个元素
			hasPath = hasPathCore(matrix, rows, cols, row, col - 1, str, pathLength, visited)
				   || hasPathCore(matrix, rows, cols, row, col + 1, str, pathLength, visited)
				   || hasPathCore(matrix, rows, cols, row - 1, col, str, pathLength, visited)
				   || hasPathCore(matrix, rows, cols, row + 1, col, str, pathLength, visited);
			//当前元素在四个方向上匹配下一个元素均失败，回退到当前元素的上一个元素
			if(!hasPath) {
				pathLength[0]--;
				visited[row * cols + col] = false;
			}
		}
		
		return hasPath;
	}
}
