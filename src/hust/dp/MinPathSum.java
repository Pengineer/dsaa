package hust.dp;

/**
 * 动态规划经典题型：三角数塔问题
 * 
 * 设有一个三角形的数塔，顶点为根结点，每个结点有一个整数值。从顶点出发，可以向左走或向右走，如图所示:
 *         13
 *       11   8
 *     12   7   26
 *   6   14   15   8
 * 12  7   13   24   11   （详图参见： @see 三角路径图.png）
 * 要求从根结点开始，请找出一条路径，使路径之和最大，只要输出路径的和。
 * 
 * 区别动态规划和回溯法，回溯法是每一步是否满足条件，动态规划所求的一般是最值问题。
 * 
 * @see hust.matrix.MinPath.java （还可以使用压缩空间的算法）
 * @author liangjian
 * @date 2016-01-19
 *
 */
public class MinPathSum {

	public static void main(String[] args) {
		int[][] arr = {{13},{11,8},{12,7,26},{6,14,15,8},{12,7,13,24,11}}; //行大小不一致，但是普通情况下动态规划所需的空间必须是maxROW * maxCOL
		System.out.println(getMinPath1(arr, 5));
		System.out.println(getMinPath2(arr, 5));
	}
	
	// 从最底层开始往上遍历
	public static int getMinPath1(int[][] arr, int depth) {
		if(arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
			return 0;
		}
		int[][] dp = new int[depth][depth];
		for(int i=depth-1; i>=0; i--) {
			dp[depth-1][i] = arr[depth-1][i];
		}
		for(int i=depth-2; i>=0; i--) {  
			for(int j=0; j<=i; j++) {
				dp[i][j] = (dp[i+1][j] > dp[i+1][j+1]) ? (dp[i+1][j+1] + arr[i][j]) : (dp[i+1][j] + arr[i][j]); // 最短路径动态规划的核心算法
			}
		}

		return dp[0][0]; // * 第一个元素就是所求元素
	}
	
	// 从最上层开始往下遍历
	public static int getMinPath2(int[][] arr, int depth) {
		if(arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
			return 0;
		}
		int[][] dp = new int[depth][depth];
		dp[0][0] = arr[0][0];
		for(int i=1; i<depth; i++) { //从上面到达两边最边上的路劲就有一条，单独处理，中间其它的都是两条
			dp[i][0] = dp[i-1][0] + arr[i][0];
			dp[i][i] = dp[i-1][i-1] + arr[i][i];
		}
		for(int i=2; i<depth; i++) {
			for(int j=1; j<i; j++) {
				dp[i][j] = (dp[i-1][j-1] > dp[i-1][j]) ? (dp[i-1][j] + arr[i][j]) : (dp[i-1][j-1] + arr[i][j]);
			}
		}
		int min = dp[depth-1][0];  // 最小元素是最后一排中的最小值
		for(int i=1; i<depth; i++) {
			min = Math.min(min, dp[depth-1][i]);
		}
		return min;
	}
}
