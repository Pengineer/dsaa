package hust.link;

/**
 * 关于链表中有环的问题（仅讨论单循环）：
 * 
 * 1，判断一个单向链表是否具有环形结构（@see JudgeCircleLink）
 * 
 * 假定单链表的长度为n，并且该单链表是环状的，现有两个指针p、q，p指针一次走一步，q指针一次走两步，如何判断p和q
 * 一定会相遇？
 * （1）假设p和q同时从环中的某一个结点开始出发，如果p走了i步后两者相遇，那么一定有i mod n = 2i mod n，即
 *     （2i-i） mod n =0，即i mod n =0，得i=kn。故走了n步后，两个会第一次相遇。
 * （2）假设p和q起点不同，q在p的前m结点处，则i mod n = (2i+m) mod n，得i=kn-m。
 * （3）如果两个的速度不一样，原理同上，根据等式来判断两者是否能相遇。
 * 
 * 2，如果链表不是一个单纯的环结构，开始的几个结点并不在环里面，如何得到链表中第一个在环里面的结点。（@see ObtainFirstNodeOfCircleLink）
 * 
 * @see ObtainBehindKthNode
 * @author 2015-12-07
 *
 */
public class JudgeCircleLink {

	public static void main(String[] args) {
		Node node1 = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		Node node4 = new Node();
		Node node5 = new Node();
		node1.value = 1;
		node2.value = 2;
		node3.value = 3;
		node4.value = 4;
		node5.value = 5;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node1;
		
//		System.out.println(node1.next.next.next.next.next == node1);
		
		System.out.println(judge(node1));
	}
	
	public static boolean judge(Node node) {
		if(node == null) {
			throw new RuntimeException("Invalid parameter");
		}
		Node p = node;
		Node q = node;
		while(p != null) {
			p = p.next;
			q = q.next == null ? null : q.next.next;
			if(q == null)
				return false;
			if (p == q) { //如果有环，此种走法肯定会相遇，不会陷入死循环
				return true;
			}
		}
		return false;
	}
}
