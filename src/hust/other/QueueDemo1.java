package hust.other;

import hust.tree.Node;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 从上往下按层打印二叉树，同一层按照先左后右的顺序打印
 *            1
 *      2           3
 *   4     5     6     7
 * 输出：1234567
 * 
 * 核心：这个题目并不能用三种常见的二叉树遍历方式去遍历，可以用队列比较好实现。首先打印根节点，同时将根节点的
 *       左右节点放入队列中，然后获取队列的第一个元素2，同时将2的左右节点放入队列中。。。
 * 
 * @author 2015-12-08
 *
 */
public class QueueDemo1 {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node4.left = null;
		node4.right = null;
		node5.left = null;
		node5.right = null;
		node6.left = null;
		node6.right = null;
		node7.left = null;
		node7.right = null;
		
		print(node1);
	}
	
	public static void print(Node root) {
		if (root == null) {
			throw new RuntimeException("Null input");
		}
//		Queue<Node> q = new LinkedList<Node>(); //*****LinkedList底层是链表结构的队列*****
		Queue<Node> q = new ArrayDeque<Node>(); //根据JavaAPI，ArrayDeque速度可能快于LinkedList
		q.add(root);
		Node node = null;
		while(q.size() > 0) {
			node = q.poll();
			System.out.print(node.value + " ");
			if(node.left != null) {
				q.add(node.left);
			}
			if(node.right != null) {
				q.add(node.right);
			}
		}
	}
	
}
