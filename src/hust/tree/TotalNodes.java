package hust.tree;

/**
 * 统计完全二叉树的节点数
 * 【题目】
 * 给定一棵完全二叉树的头节点head，返回这棵树的节点个数。
 * 【要求】
 * 如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。
 * 
 * @author 2015-12-20
 *
 */
public class TotalNodes {
	
	public static void main(String[] args) {
		
	}
	
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}
	
	public static int bs(Node node, int l, int h) {
		if (l == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, l + 1) == h) {
			return (1 << (h - l)) + bs(node.right, l + 1, h);
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
	}
	
	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}
}
