package hust.tree;

/**
 * 给定一个二叉树和一个整数，判断二叉树从根节点到叶子节点是否存在一条路径，满足这条路径的节点和等于给定的数。
 * 
 * 这是关于二叉树的一个常用套路啊：将二叉树的问题分摊到左右子树来递归求解。（效率可能不是很高）
 * 
 * @author liangjian
 *
 */
public class JudgePathSum {

	public static void main(String[] args) {
		Node y1 = new Node(1);
		Node y2 = new Node(2);
		Node y3 = new Node(3);
		Node y4 = new Node(4);
		Node c1 = new Node(2, y1, y2);
		Node c2 = new Node(2, y3, y4);
		Node r1 = new Node(1, c1, c2);
		
		System.out.println(isExist(r1, 1));
		System.out.println(getTotalNodes(r1));
	}
	
	public static boolean isExist(Node node, int target) {
		if(node == null && target == 0) return true;
		if(node == null && target != 0) return false;
		target = target - node.value;
		return isExist(node.left, target) || isExist(node.right, target);
	}
	
	//统计二叉树的节点数
	public static int getTotalNodes(Node node) {
		if(node == null) return 0;
		return getTotalNodes(node.left) + getTotalNodes(node.right) + 1;
	}
	
	//求树的深度
	public static int getDept(Node node) {
		if(node == null) return 0;
		int leftDept = getDept(node.left);
		int rightDept = getDept(node.right);
		return leftDept > rightDept ? leftDept + 1 : rightDept + 1;
	}
}
