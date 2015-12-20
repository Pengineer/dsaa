package hust.tree;

/**
 * 统计完全二叉树的节点数
 * 【题目】
 * 给定一棵完全二叉树的头节点head，返回这棵树的节点个数。
 * 【要求】
 * 如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。
 * 
 * 思路：
 * 本题有点二分法的思路，因为是一颗满二叉树，那么将一个结点的左右子树分开，那么其中一颗树必然也为满二叉树，然后
 * 在递归求不是满二叉树的那颗子树。判断满二叉树的左右子树是否为满二叉树，只需要判断右子树的最左结点是否在最底层。
 * 
 * 注意，如何求(2^n) + 1：
 * 2^n == 1 << n，因此为 (1<<n) + 1. 此处必须加括号，否则结果不对。
 * 
 * {@link http://www.nowcoder.com/discuss/595}
 * @author 2015-12-20
 *
 */
public class TotalNodes {
	
	public static void main(String[] args) {
		Node header = new Node(1);
		Node node1 = new Node(2);
		Node node2 = new Node(3);
		Node node3 = new Node(4);
		Node node4 = new Node(5);
		Node node5 = new Node(6);
		Node node6 = new Node(7);
		Node node7 = new Node(8);
		Node node8 = new Node(8);
		Node node9 = new Node(8);
		Node node10 = new Node(8);
		Node node11= new Node(8);
		header.left = node1;
		header.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left = node5;
		node2.right = node6;
		node3.left = node7;
		node3.right = node8;
		node4.left = node9;
		node4.right = node10;
		node5.left = node11;
		
		System.out.println(nodeNum(header));
	}
	
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return getNum(head, 1, mostLeftLevel(head, 1));  //关键就是递归不断判断当前结点的左右子树的最左结点是否到达整个树的最底层，从而拆分左右子树（其中一个肯定是是满二叉树），这样就可以将树不断缩小
	}
	
	/**
	 * 求以node为根结点的子树中结点总和
	 * @param node             当前子树的根结点
	 * @param currentDepth     当前子树的根结点在整个树中的深度
	 * @param depth     整个树的深度，递归中始终不变的量
	 * @return
	 */
	public static int getNum(Node node, int currentDepth, int depth) {
		if (currentDepth == depth) {
			return 1;
		}
		if (mostLeftLevel(node.right, currentDepth + 1) == depth) { //如果node的右结点的最左节点为的深度在整个树中的深度为h，那么node的左子树一定是一个满二叉树，且树的最后一层在整个树的h层
			return (1 << (depth - currentDepth)) + getNum(node.right, currentDepth + 1, depth); //1 << (h - l)即为node的左子树（满二叉树）的结点和 + node结点；getNum(node.right, currentDepth + 1, depth)为递归求node的右子树结点和  
		} else {
			return (1 << (depth - currentDepth - 1)) + getNum(node.left, currentDepth + 1, depth); //否则，node的右子树是比左子树少一层的满二叉树（表达式前面部分），node的左子树的最后一层没有满，需要再次递归求
		}
	}
	
	/**
	 * 获取以node为根节点的左子树的在整个树中的深度
	 * @param node   当前结点
	 * @param level  当前结点在整个树中的深度
	 * @return
	 */
	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}
}
