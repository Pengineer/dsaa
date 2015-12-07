package hust.link;

/**
 * 用一次遍历查看链表中倒数第k个结点的值
 * 
 * 解析：由于只能用一次遍历，有没有链表结点的总数，因此无法按照常规的方式得到第n-k+1个结点。
 *      此问题是一类题型，即获取链表/数组/队列等最后的第K个元素。解决思路就是设置两个指针，让它们的间隔是K即可。
 *      
 * 变形：
 * 1，求链表的中间结点。仍然是定义两个指针，第一个指针一次走一步，另一个指针一次走两步，走得快的到达终点时，
 *       走的慢的刚好在中间处。（求链表的1/3处等等。。。）
 * 2，判断一个单向链表是否具有环形结构。（@see JudgeCircleLink.java）
 * 3，求一个环的长度，记录环中当前结点，旋转一圈回到此结点，走过的结点数就是环长度；获则用两个指针，如1，当
 *    两个指针再次相遇时，环长度=慢的走过的结点数 * 2。
 * 
 * @author 2015-12-07
 *
 */
public class ObtainBehindKthNode {

	public static void main(String[] args) {
		Node header = new Node();
		Node node1 = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		Node node4 = new Node();
		Node node5 = new Node();

		header.value = Integer.MAX_VALUE;
		node1.value = 1;
		node2.value = 2;
		node3.value = 3;
		node4.value = 4;
		node5.value = 5;
		header.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = null;
		
		System.out.println(getKthValue(header, 6));
	}
	
	public static int getKthValue(Node header, int k) {
		if(header == null || k <= 0) {
			throw new RuntimeException("Invalid parameter");
		}
		
		Node ahead = header;
		int count =0;
		while(ahead != null && count < k) {
			ahead = ahead.next;
			count++;
		}
		if(ahead == null && count <= k) { //必须为<=，否则边界6输出异常
			throw new RuntimeException("OutOfRange:" + k);
		}
		
		Node behind = header;
		while(ahead != null) {
			ahead = ahead.next;
			behind = behind.next;
		}
		
		return behind.value;
	}

}
