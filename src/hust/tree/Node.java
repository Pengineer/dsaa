package hust.tree;

public class Node {
	public int value;
	public Node left;
	public Node right;
	public Node parent;
	
	public Node(int data) {
		this(data, null, null, null);
	}
	
	public Node(int data, Node left, Node right) {
		this(data, null, left, right);
	}
	
	public Node(int data, Node parent, Node left, Node right) {
		this.value = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}
