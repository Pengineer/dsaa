package hust.link;

/**
 * 反转链表（腾讯现场笔试题）
 * 
 * 主要考擦对边界条件的考虑
 * 
 * @author liangjian
 *
 */
public class ReverseLink {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n1.next = n2;n2.next=n3;n3.next=n4;n4.next=n5;n5.next=null;
		print(n1);
		print(reverse(n1));
	}
	
	public static Node reverse(Node head) {
		if(head == null) {
			return null;
		}
		Node n1 = head;
		Node n2 = head.next;
		if(n2 == null) {
			return n1;
		}
		Node n3 = n2.next;
		if(n3 == null) {
			n2.next = n1;
			n1.next = null;
			return n2;
		}
		while(n3 != null) {
			n2.next = n1;
		  //n3.next = n2; 这一步不能有，一次只能反转一个指针
			n1 = n2;
			n2 = n3;
			n3 = n3.next;
		}
		n2.next = n1;
		head.next = null;//这一步不能少，最后要将头指针置空
		
		return n2;
	}
	
	public static void print(Node head){
		while(head!=null) {
			System.out.println(head.value);
			head = head.next;
		}
		System.out.println("-----------------------------------");
	}
	
	
}
