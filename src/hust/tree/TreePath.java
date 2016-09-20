package hust.tree;

/**
 * 获取二叉树的最小深度（各个节点的值均为正数）
 * 
 * 获取二叉树的最小路径和，待续.....
 * 
 * @author liangjian
 *
 */
public class TreePath {
	public static void main(String[] args) {
		
	}
	
	public static int minDepth(Node root) {
	    if(root == null){
	        return 0;
	    }
	    int k = getPath(root, 0);
	    return k;
	}
	
	public static int getPath(Node node, int cur) {
		int left = 0;
		int right = 0;
		if(node.left != null) {
			left = getPath(node.left, cur + 1);
		}
		if(node.right != null) {
			right = getPath(node.right, cur + 1);
		}
		// 叶子节点
		if(node.left == null && node.right == null) {
			return cur + 1;
		}
		// 无左节点
		if (node.left == null) {
			return right;
		}
		// 无右节点
		if (node.right == null) {
			return left;
		}
		// 两边均有节点，返回层数小的
		if (left > right) {
			return right;
		}
		return left;
	}
}
