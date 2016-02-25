package hust.tree;

/**
 * 求二叉树的深度
 * 
 * 这是一个关于树的层次问题，但不是层次遍历，因为我们要得到的是树的深度，是一个直接深度线性问题，而层次遍历是一个先层次后
 * 深度线性的问题（队列解决）。
 * 
 * @since 2015-12-13
 *
 */
public class TreeDept {

	public static void main(String[] args) {
		Node root = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		root.left = node2;
		root.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.right = node6;
		node5.left = node7;
		
		System.out.println(getDept(root));
		
	}
	
	/**
	 * 理解递归在二叉树的遍历中的使用。
	 * 递归的结果返回有两种形式：
	 * 1，要得到这一层的结果，先得到下一层的结果，通过下一层的返回结果计算得到这一层的结果。（思想：要得到...，先得到...）
	 *    例如：@see TreeDept，要得到根节点的最大深度，只需要左右节点最大的深度+1。树的递归遍历中较多。
	 * 2，上层的结果直接作用于下一层，先处理好上层，将上层的结果传递下层，整个递归结束后，直接返回最后一层的计算结果。
	 *    例如：@see FindNumberOfK 或则 QuickSort，数组的分段遍历中较多。
	 * 
	 */
	public static int getDept(Node node) {
		if(node == null) {
			return 0;
		}
		int leftDept = getDept(node.left);
		int rightDept = getDept(node.right);
		return leftDept > rightDept ? leftDept + 1 : rightDept + 1;
	}
	
}
