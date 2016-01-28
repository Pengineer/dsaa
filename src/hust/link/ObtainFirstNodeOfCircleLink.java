package hust.link;

import java.util.Hashtable;

/**
 * 如何得到链表中第一个在环里面的结点
 * 
 * 假设链表长度l，环长度n，链表中第一个在环里面的结点位置是k，指定p和q两个指针，p一次走一步，q一次走两步，
 * 同时从头结点出发，则可推算出两者第一次相遇的位置为n-(k-1)，此时头结点和n-(k-1)位置的结点距离k结点的位置都是k-1，因此
 * 我们只需要将p和q分区重定向到头结点和k结点，修改它们的步长为1，那么它们的相遇点就是k。
 * 
 * 补充推算第一次相遇位置是n-k的过程：
 * i-（k-1） mod n = 2i - (k-1) mod n  -->  i mod n = 0  --> i = mn. 取最小i = n，则相遇位置为n-(k-1)
 * 
 * @see JudgeCircleLink
 * {@link http://www.cnblogs.com/zhyg6516/archive/2011/03/29/1998831.html}
 * @author 2015-12-07
 *
 */
public class ObtainFirstNodeOfCircleLink {

	public static void main(String[] args) {
		Node header = new Node();
		Node node1 = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		Node node4 = new Node();
		Node node5 = new Node();
		Node node6 = new Node();
		Node node7 = new Node();
		Node node8 = new Node();
		Node node9 = new Node();
		Node node10 = new Node();
		header.value = Integer.MAX_VALUE;
		node1.value = 1;
		node2.value = 2;
		node3.value = 3;
		node4.value = 4;
		node5.value = 5;
		node6.value = 6;
		node7.value = 7;
		node8.value = 8;
		node9.value = 9;
		node10.value = 10;
		header.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;
		node9.next = node10;
		node10.next = node5;
		
		System.out.println(obtain(header).value);
		System.out.println(method2(header).value);
	}
	
	//必须给我头结点
	public static Node obtain(Node header) {
		if (header == null || header.next == null) {
			throw new RuntimeException("Invald parameter");
		}
		
		//1，获取p和q第一次相交的位置
		Node intersection = getIntersectNode(header);
		if (intersection == null) {
			throw new RuntimeException("No circle in link"); //需要判空
		}
		
		//2，在定义两个同步同速度指针，一个从头结点开始，一个从n-k节点开始
		while(true) {
			header = header.next;
			intersection = intersection.next;
			if(header == intersection)
				return intersection;
		}
	}

	public static Node getIntersectNode(Node header) {
		Node p = header;
		Node q = header;
		while (true) {
			p = p.next;
			q = q.next == null ? null : q.next.next;
			if(q == null)
				return null;
			if(p == q)
				break;
		}
		return p;
	}
	
	// 方法二：利用hash表，额外空间复杂度O(n)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Node method2(Node head) {
		if(head == null) {
			return null;
		}
		
		Hashtable table = new Hashtable();
		Node next = head;
		while(next != null) {
			if (table.get(next) == null) {
				table.put(next, 1);
				next = next.next;
			} else {
				return next;
			}
		}
		return null;
	}
	
}
