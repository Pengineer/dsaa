package hust.link;

/**
 * 删除链表中值为target的第一个结点（手写）
 * 
 * @author liangjian
 *
 */
public class RemoveNode1 {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(1);
		Node node5 = new Node(5);
		node1.next = node2;node2.next = node3;node3.next = node4;node4.next = node5;node5.next = null;
		print(node1);
		print(removeNode(node1, 1));
	}
	
	/*
	 * 先找到那个结点的前一个结点（while条件使用node.next），然后再删除，不在找的过程中操作删除（因为删除需要分情况）
	 */
	public static Node removeNode(Node head, int val) {
		if(head == null) {
			return null;
		}
		
		Node node1 = head;
		Node node2 = node1.next;
		if(node1.value == val) { //头结点单独处理
			return node1.next;
		}
		if(node2 == null) { //单结点链表
			return node1;
		}
		
		while(node2 != null && node2.value != val) {
			node1 = node2;
			node2 = node2.next;
		}
		
		if(node2 != null && node2.value == val) {
			node1.next = node2.next;
		}
		return head;
	}
	
	public static void print(Node head) {
		while(head != null) {
			System.out.println(head.value);
			head = head.next;
		}
		System.out.println("------------------------------");
	}
}
