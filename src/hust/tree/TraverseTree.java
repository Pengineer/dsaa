package hust.tree;

import java.util.Stack;

/**
 * 遍历是对树的一种最基本的运算，所谓遍历二叉树，就是按一定的规则和顺序走遍二叉树的所有结点，使每一个结点都被访问一次，而且只被访问一次。由于二叉树是非线性结构，因此，树的遍历实质上是将二叉树的各个结点转换成为一个线性序列来表示。 
 * 	设L、D、R分别表示遍历左子树、访问根结点和遍历右子树， 则对一棵二叉树的遍历有三种情况：DLR（称为先根次序遍历），LDR（称为中根次序遍历），LRD （称为后根次序遍历）。 
 * (1)先序遍历 
 * 	访问根；按先序遍历左子树；按先序遍历右子树 
 * (2)中序遍历 
 * 	按中序遍历左子树；访问根；按中序遍历右子树 
 * (3)后序遍历 
 * 	按后序遍历左子树；按后序遍历右子树；访问根 
 * (4)层次遍历 
 * 	即按照层次访问，通常用队列来做。访问根，访问子女，再访问子女的子女（越往后的层次越低）（两个子女的级别相同）
 * 
 * @category	
 * 递归的实质就是栈操作，因此将递归改为非递归，就是使用栈来实现。（栈：先进后出，深度递归）
 * 树的层次遍历不能使用递归来实现，通常用队列来做。（队列：先进先出，层次遍历）
 * 
 * @see TreeDept   求树的深度
 * @see QueueDemo1 树的层次遍历
 * @author 2015-12-13
*/  
public class TraverseTree {

	public static void main(String[] args) {
		
	}
	
	/** 访问节点 */  
	public static void visit(Node p) {  
	    System.out.print(p.getValue() + " ");  
	}
	
	/** 递归实现前序遍历 */  
	protected static void preorder(Node p) {  
		if (p != null) {  
			visit(p);  
			preorder(p.getLeft());  
			preorder(p.getRight());  
		}  
	}
	
	/** 递归实现中序遍历 */  
	protected static void inorder(Node p) {  
		if (p != null) {  
			inorder(p.getLeft());  
			visit(p);  
			inorder(p.getRight());  
		}  
	}
	
	/** 递归实现后序遍历 */  
	protected static void postorder(Node p) {  
		if (p != null) {  
			postorder(p.getLeft());  
			postorder(p.getRight());  
			visit(p);  
		}  
	}
	
	/** 非递归实现前序遍历 */
	protected static void iterativePreorder(Node p) {  
		Stack<Node> stack = new Stack<Node>();  
		if (p != null) {  
			stack.push(p);  
			while (!stack.empty()) {  
				p = stack.pop();  
				visit(p);  
				if (p.getRight() != null)  
					stack.push(p.getRight());  
				if (p.getLeft() != null)  
					stack.push(p.getLeft());  
			}  
		}  
	} 
	
	/** 非递归实现后序遍历 */  
	protected static void iterativePostorder(Node p) {  
		Node q = p;  
		Stack<Node> stack = new Stack<Node>();  
		while (p != null) {  
			// 左子树入栈  
			for (; p.getLeft() != null; p = p.getLeft())  
				stack.push(p);  
			// 当前节点无右子或右子已经输出  
			while (p != null && (p.getRight() == null || p.getRight() == q)) {  
				visit(p);  
				q = p;// 记录上一个已输出节点  
				if (stack.empty())  
					return;  
				p = stack.pop();  
			}  
			// 处理右子  
			stack.push(p);  
			p = p.getRight();  
		}  
	} 
	
	/** 非递归实现中序遍历 */  
	protected static void iterativeInorder(Node p) {  
		Stack<Node> stack = new Stack<Node>();  
		while (p != null) {  
			while (p != null) {  
				if (p.getRight() != null)  
					stack.push(p.getRight());// 当前节点右子入栈  
				stack.push(p);// 当前节点入栈  
				p = p.getLeft();  
			}  
			p = stack.pop();  
			while (!stack.empty() && p.getRight() == null) {  
				visit(p);  
				p = stack.pop();  
			}  
			visit(p);  
			if (!stack.empty())  
				p = stack.pop();  
			else  
				p = null;  
		}  
	}

}
