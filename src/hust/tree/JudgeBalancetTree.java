package hust.tree;

/**
 * 判断二叉树是平衡二叉树
 * 
 * 定义：
 * 平衡二叉树：如果某二叉树中任意结点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 
 * @author 2015-12-13
 *
 */
public class JudgeBalancetTree {

	public static void main(String[] args) {
		
	}
	
	//方法一：根据TreeDept.java的启发
	public static int getDept(Node node) {
		if(node == null) {
			return 0;
		}
		int leftDept = getDept(node.left);
		int rightDept = getDept(node.right);
		return leftDept > rightDept ? leftDept + 1 : rightDept + 1;
	}

	public static boolean isBalance1(Node node){
		if(node==null){
			return true;
		}
		int left = getDept(node.left);
		int right = getDept(node.right);
		int diff = left - right;
		if(diff > 1 || diff < -1)
			return false;
		return isBalance1(node.left) && isBalance1(node.right);
	}
	
	/*
	 * 方法二：由于方法一存在重复遍历结点的问题，因此效率较低。
	 *     此处我们用后序遍历的方式遍历二叉树的每一个结点，在遍历到一个结点之前我们已经遍历了它的左右子树。
	 *     只要在遍历每个结点的时候记录它的深度（某一结点的深度等于它到叶节点的路径的长度），我们就可以一边
	 *     遍历一边判断每个结点是不是平衡的。
	 */
	public static boolean isBalance2(Node node) {
		Depth d = new Depth(0);
		return isBalance(node, d);
	}
	
	public static boolean isBalance(Node node, Depth d) {
		if(node == null){
			d.depth = 0;
			return true;
		}
		Depth right = new Depth();
		Depth left = new Depth();
		if(isBalance(node.left, left) && isBalance(node.right, right)) {
			int diff = left.depth - right.depth;
			if(diff <= 1 || diff >= -1) {//绝对值小于等于1
				//如果是平衡树，才有必要算深度，然后看上级是不是平衡树
				d.depth = left.depth > right.depth ? left.depth : right.depth;
				return true;
			}
		}
		return false;
	}
	
	static class Depth {
		int depth;
		
		Depth(){}
		
		Depth(int depth) {
			this.depth = depth;
		}
	}
	
}
