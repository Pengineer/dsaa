package hust.link;

import java.util.Stack;

/**
 * 对于一个链表，请设计一个时间复杂度为O(n),额外空间复杂度为O(1)的算法，判断其是否为回文结构。
 * 给定一个链表的头指针A，请返回一个bool值，代表其是否为回文结构。保证链表长度小于等于900。
 * 
 * 本题关于倒序的两点思路：
 * 1，利用栈来获取倒序的元素；
 * 2，利用链表本身的指针特性来倒序元素。
 * 
 * @author liangjian
 * @since 2016-01-27
 *
 */
public class JudgeLinkPlalindrome {

	public static void main(String[] args) {
		Node header = new Node();
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(2);
		Node node5 = new Node(1);
		header.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = null;
		
//		Node header = new Node();
//		Node node1 = new Node(1);
//		Node node2 = new Node(2);
//		Node node3 = new Node(3);
//		Node node4 = new Node(3);
//		Node node5 = new Node(2);
//		Node node6 = new Node(1);
//		header.next = node1;
//		node1.next = node2;
//		node2.next = node3;
//		node3.next = node4;
//		node4.next = node5;
//		node5.next = node6;
//		node6.next = null;
		
		System.out.println(judge1(header));
		System.out.println(judge2(header));
		System.out.println(judge3(header));
	}
	
	//方法一：额外空间复杂度是O(n)，利用栈先进后出的特性来获取一个逆序的链表
	public static boolean judge1(Node header) {
		if(header == null) {
			throw new RuntimeException("Invalid parameter");
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		Node next = header.next;
		while(next != null) {
			stack.push(next.value);
			next = next.next;
		}
		
		next = header.next;
		while(next != null) {
			if(next.value != stack.pop()) {
				return false;
			}
			next = next.next;
		}
		
		return true;
	}
	
	//方法二：额外空间复杂度O(n/2)，还是利用栈先进先出的特性来获取一个逆序的链表，只不过只需要比较n/2次
	public static boolean judge2(Node header) {
		if(header == null) {
			throw new RuntimeException("Invalid parameter");
		}
		
		//定义两个指针：快指针和慢指针，寻找到链表的中间点
		Node fast = header, slow = header;
		boolean isOdd = false; //链表奇偶数
		while((fast = fast.next) != null) {
			if((fast = fast.next) != null) {
				slow =slow.next;
			} else {
				isOdd = true;
				break;
			}
		}
		
		// 来自牛客网，找中间元素
//		Node n1 = header;
//		Node n2 = header;
//		while (n2.next != null && n2.next.next != null) { // find mid node
//			n1 = n1.next; // n1 -> mid
//			n2 = n2.next.next; // n2 -> end
//		}
		
		Stack<Integer> stack = new Stack<Integer>();
		if(isOdd) {
			stack.push(slow.value); //补一个
		}
		while((slow = slow.next) != null) {
			stack.push(slow.value);
		}
		
		Node next = header.next;
		while(!stack.empty()) {
			if(stack.pop() != next.value) {
				return false;
			}
			next = next.next;
		}
		
		return true;
	}
	
	//方法三：额外空间复杂度O(1)，缺点是破坏了链表的结构
	public static boolean judge3(Node header) {
		if(header == null) {
			throw new RuntimeException();
		}
		
		//定义两个指针：快指针和慢指针，寻找到链表的中间点
		Node fast = header, slow = header;
		while((fast = fast.next) != null) {
			if((fast = fast.next) != null) {
				slow =slow.next;
			} else {
				break;
			}
		}
		//将链表后半部分的元素的指针反向
		Node node1 = slow;//slow的位置需要记录
		Node node2 = node1.next;
		Node node3 = node2.next;
		while(node3 != null) {
			node2.next=node1;
			node1 = node2;
			node2 = node3;
			node3 = node3.next;
		}
		node2.next=node1;
		
		//从链表的首尾开始比较
		node1 = header.next;
		while(node1 != slow.next) {
			if(node1.value != node2.value) {
				return false;
			}
			node1 = node1.next;
			node2 = node2.next;
		}
		
		//如果不能改变链表的结构，需要还原链表的结构（同上反向方法）
		//....
		
		return true;
	}
}
