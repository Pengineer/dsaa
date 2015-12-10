package hust.link;

/**
 * 寻找两个链表的第一个公共结点
 * 1->2->3->6->7
 *    4->5->6->7
 * 
 * 分析：
 * 1，首先我们可以通过两层遍历来达到目的，但是效率显然不高。
 * 通过画图，我们可以看到，只要两个链表的某两个结点开始相同，那么它们后面的结点都相同，因此有下面的思路：
 * 2，我们还可以从两个链表的最后面开始比较，要从链表的后面往前面遍历，可以使用两个栈将两个链表分别存入两个栈中，那么栈顶
 *    元素就是链表尾部，一次弹出栈顶元素，直到最后一个相同的结点。
 * 3，第二种方法中，使用了额外的空间，增加了空间复杂度。再仔细观察两个链表，我们可以发现，其实可以从链表表头开始遍历，只不过
 *    不是同时开始遍历，只需要保证两个链表的尾部对齐，然后开始同步遍历，直到出现相同的结点。
 * 
 * @author 2015-12-10
 *
 */
public class FindFirstCommonNode {

	public static void main(String[] args) {
		Node head1 = new Node();
		Node head2 = new Node();
//		Node head3 = new Node();
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		head1.next = node1;
		head2.next = node4;
		node1.next = node2;
		node2.next = node3;
		node3.next = node6;
		node6.next = node7;
		node4.next = node5;
		node5.next = node6;
		
		Node result = getFirstCommonNode(head2, head1);
		System.out.println(result == null ? null : result.value);
	}
	
	public static Node getFirstCommonNode(Node head1, Node head2) {
		int len1 = getLinkLength(head1);
		int len2 = getLinkLength(head2);
		int step = len2 - len1;
		Node longLink = head2;
		Node shortLink = head1;
		if(len1 > len2) {
			longLink = head1;
			shortLink = head2;
			step = len1 - len2;
		}
		//长的先走step步
		for(int i=0; i < step; i++) {
			longLink = longLink.next;
		}
		
		while(longLink != null && longLink != shortLink) {
			longLink = longLink.next;
			shortLink = shortLink.next;
		}
		return longLink;
	}
	
	public static int getLinkLength(Node head) {
		int len=0;
		Node tmp = head;
		while(tmp != null){
			tmp = tmp.next;
			len++;
		}
		return len;
	}
}
