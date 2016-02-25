package hust.stack.queue;

import java.util.Stack;

/**
 * Java API中的的栈：底层数组实现
 * 
 * @since 2015-12-04
 *
 */

public class StackDemo1 {
	
	public static void main(String[] args) {
//		process1();
		
		int[] arr = {1,2,3,4,5};
		process2(arr, arr.length);
	}
	
	//先进后出打印元素（Java API）
	public static void process1() {
		Stack<String> s = new Stack<String>();
		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		s.push("e");
		s.push("f");
		s.push("g");
		System.out.println(s.capacity()); //栈容量（初始大小为10）
		System.out.println(s.size());     //栈中有效元素个数
		System.out.println(s.peek());     //栈顶元素（仅取值，不弹出）
		System.out.println(s.empty() + " || " + s.isEmpty());
		while(s.size() > 0) {
			System.out.println(s.pop());
		}
		System.out.println(s.empty() + " || " + s.isEmpty());
	}
	
	//先进后出打印元素（递归的本质就是一个栈结构）
	public static void process2(int[] arr, int len) {
		if(len > 0) {
			System.out.print(arr[len-1] + " ");
			process2(arr, len-1);
		}
	}
	
//	基于递归的代码看起来很简洁，但有个问题：当链表非常长的时候，就会导致函数调用的层级很深，
//	从而有可能导致函数调用栈溢出。显式用栈基于循环实现的代码的鲁棒性要好一些。
	
}
