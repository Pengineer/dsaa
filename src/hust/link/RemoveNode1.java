package hust.link;

/**
 * 删除链表中值为target的第一个结点（手写）
 * 
 * @author liangjian
 *
 */
public class RemoveNode1 {

	public static void main(String[] args) {
		
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
		
		while(node2.next != null) {
			if(node2.next.value != val) {
				node1 = node2;
				node2 = node2.next;
			}
		}
		
		if(node2.next != null && node2.next.value == val) { //不是最后一个结点
			node2.next = node2.next.next;
		} else if(node2.next == null && node2.next.value == val){ //最后一个结点
			node1.next = null;
		}
		return head;
	}
	
}
