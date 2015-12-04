package hust.tree;

/**
 * 现在有一种新的二叉树节点类型
 * 该结构比普通二叉树节点结构多了一条指向父节点的parent指针。假设有一棵Node类型的节点组成的二叉树，树中每个节点的parent指针都正确的指向自己的父节点，头节点的parent指向null。只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。在二叉树的中序遍历的序列中，node的下一个节点叫做node的后继节点。
 * 例如，下图的二叉树：
__________6__________
         / \
   __3__      __9__
    / \        / \
   1   4      8   10
    \   \    /
     2   5  7
 * 中序遍历的结果为：1，2，3，4，5，6，7，8，9，10
 * 所以节点1的后继为节点2，节点2的后继为节点3，…，节点10的后继为null
 * 
 * @author 2015-12-02
 *
 */
public class DescendantNode {
	public Node getNextNode(Node node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {
			return getLeftMost(node.right);
		} else {
			Node parent = node.parent;
			while (parent != null && parent.left != node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	public Node getLeftMost(Node node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
}
