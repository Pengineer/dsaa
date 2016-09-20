package hust.link;

/**
 * 单向链表的快速排序
 * 
 * 核心：利用快排的分治思想，单向链表patition的实现与数组的patition实现不一样。
 * 
 * @author liangjian
 *
 */
public class LinkSort {

	public static void main(String[] args) {
		Node n1 = new Node(3);
		Node n2 = new Node(2);
		Node n3 = new Node(7);
		Node n4 = new Node(4);
		Node n5 = new Node(1);
		n1.next = n2;n2.next=n3;n3.next=n4;n4.next=n5;n5.next=null;
		print(n1);
		sort(n1,n5);
		print(n1);
	}
	
	public static void sort(Node begin, Node end) {
		if(begin == null) return;
		
		if(begin != end) {
			Node location = patition(begin, end);
			sort(begin, location);
			sort(location.next, end);
		}
	}
	
	/*
	 * p指针前面的结点值一定小于key，唯一使p前进的条件是q碰到小于key的结点值
	 */
	public static Node patition(Node begin, Node end) {
		int key = begin.value;
		Node p = begin, q=p.next;
		while(q != end.next) {
			if(q.value < key) {
				p = p.next;
				swapValue(p, q);
			}
			q = q.next;
		}
		swapValue(p, begin);
		return p;
	}
	
	
	////////辅助函数
	public static void swapValue(Node p, Node q) {
		int tmp = p.value;
		p.value = q.value;
		q.value = tmp;
	}
	
	public static void print(Node head){
		while(head!=null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println("-----------------------------------");
	}
}
