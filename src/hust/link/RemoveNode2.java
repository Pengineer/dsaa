package hust.link;

/**
 * 一种怪异的节点删除方式
 * 【题目】
 * 链表节点值类型为int型，给定一个链表中的节点node，但不给定整个链表的头节点，如何在链表中删除node？
 * 请实现这个函数，并分析这么会出现哪些问题。
 * 
 * 要求时间复杂度为O(1)。
 * 
 * 【问题】
 * 1，无法删除最后一个结点。比如说最后一个结点是node3，我们的目的就是让node2.next=null（注意了，node2.next指向的是node3指向的对象，与node3这个引用
 *    没有半毛钱关系，因此node3=null没意义），那能不能将node3所指向的对象所在的区域变成null呢？不能，null在系统中是一个特性的区域，如果想让node2的
 *    next指针指向null，必须找到node2。
 * 2，这种删除方式在本质上根本就不是删除了node结点，而是把node结点的值改变，然后删除node的下一个结点，在实际的工程中可能会带来很大的问题。比如，工程上
 *    的一个结点可能代表很复杂的结构，节点值的复制会相当复杂，或者可能改变节点值这个操作都是被禁止的；再如，工程上的一个结点代表提供服务的一个服务器，
 *    外界对每个结点都有很多依赖，比如，示例中删除节点2时，其实影响了节点3对外提供的服务。
 * 
 * @author liangjian
 * @since 2016-01-29
 *
 */
public class RemoveNode2 {

	public static void remove(Node node) {
		if(node == null) {
			return;
		}
		Node next = node.next;
		if(next == null) {
			throw new RuntimeException("Cannot delete the last one");
//			node = null; 原引用置空没用，前面结点的next引用的指向与node无关
//			return;
		}
		
		node.value = next.value;
		node.next = next.next;
		next = null;
	}
	
	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = null;
		printLinkedList(node1);
		remove(node3);
		printLinkedList(node1);
	}
}
