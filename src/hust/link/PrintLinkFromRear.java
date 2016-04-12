package hust.link;

import java.util.Stack;

/**
 * 从尾到头打印链表
 * 
 * @author liangjian
 *
 */
public class PrintLinkFromRear {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(1);
		Node node5 = new Node(5);
		node1.next = node2;node2.next = node3;node3.next = node4;node4.next = node5;node5.next = null;
		print(node1);
	}
	
	//不能改变链表结构
	public static void print(Node head) {
		Stack<Node> stack = new Stack<Node>();
		while(head != null) {
			stack.push(head);
			head = head.next;
		}
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.println(node.value);
		}
	}
	
}
