package hust.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 在3*3方格中寻找最短路径，规定只能上下左右移动（决定了节点的邻接关系）
 * 
 * @author xingyu.pl
 *
 */
public class BFSDemo {
	public static void main(String[] args) {
		BFSDemo demo = new BFSDemo();
		// 构造各节点的邻接关系
		LinkedList<Integer> list_1 = new LinkedList<Integer>();
		list_1.add(2);
		LinkedList<Integer> list_2 = new LinkedList<Integer>();
		list_2.add(1);
		list_2.add(3);
		list_2.add(5);
		LinkedList<Integer> list_3 = new LinkedList<Integer>();
		list_3.add(2);
		list_3.add(6);
		LinkedList<Integer> list_5 = new LinkedList<Integer>();
		list_5.add(2);
		list_5.add(6);
		list_5.add(8);
		LinkedList<Integer> list_6 = new LinkedList<Integer>();
		list_6.add(3);
		list_6.add(5);
		LinkedList<Integer> list_7 = new LinkedList<Integer>();
		list_7.add(8);
		LinkedList<Integer> list_8 = new LinkedList<Integer>();
		list_8.add(5);
		list_8.add(7);
		HashMap<Integer, LinkedList<Integer>> graph = new HashMap<Integer, LinkedList<Integer>>();
		graph.put(1, list_1);
		graph.put(2, list_2);
		graph.put(3, list_3);
		graph.put(5, list_5);
		graph.put(6, list_6);
		graph.put(7, list_7);
		graph.put(8, list_8);
		HashMap<Integer, Integer> visited = new HashMap<>();
		demo.grid(graph, visited, 1, 8);
	}
	
	public void grid(HashMap<Integer, LinkedList<Integer>> graph, HashMap<Integer, Integer> visited, Integer start, Integer target) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited.put(start, 0);
		while(!q.isEmpty()) {
			Integer top = q.poll();
			if(top == target) {
				System.out.println("reslut: " + visited.get(top));
				return;
			}
			for(Integer n : graph.get(top)) {
				if (!visited.containsKey(n)) {
					visited.put(n, visited.get(top)+1); // 邻接点的距离总是在当前节点的基础上变化，因此visited必须要记录各节点的路径
					q.add(n);
				}
			}
		}
	}
}
