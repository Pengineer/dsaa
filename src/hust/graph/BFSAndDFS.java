package hust.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * BFS多用于寻找最短路径的问题，DFS多用于快速发现底部节点，图样如下：
 *   r--1--s   i--1--u
 *   |     |  /|   / |
 *   1     1 5 1  1  1
 * 	 |     |/  | /   |
 *   v     w-1-x--4--y
 * 
 * @author xingyu.pl
 *
 */
public class BFSAndDFS {
	
	public static void main(String args[]) {
		BFSAndDFS bb = new BFSAndDFS();
		// s顶点的邻接表
		LinkedList<Character> list_s = new LinkedList<Character>();
		list_s.add('w');
		list_s.add('r');
		LinkedList<Character> list_w = new LinkedList<Character>();
		list_w.add('s');
		list_w.add('i');
		list_w.add('x');
		LinkedList<Character> list_r = new LinkedList<Character>();
		list_r.add('s');
		list_r.add('v');
		LinkedList<Character> list_x = new LinkedList<Character>();
		list_x.add('w');
		list_x.add('i');
		list_x.add('u');
		list_x.add('y');
		LinkedList<Character> list_v = new LinkedList<Character>();
		list_v.add('r');
		LinkedList<Character> list_i = new LinkedList<Character>();
		list_i.add('u');
		list_i.add('x');
		list_i.add('w');
		LinkedList<Character> list_u = new LinkedList<Character>();
		list_u.add('i');
		list_u.add('x');
		list_u.add('y');
		LinkedList<Character> list_y = new LinkedList<Character>();
		list_y.add('u');
		list_y.add('x');
		HashMap<Character, LinkedList<Character>> graph = new HashMap<Character, LinkedList<Character>>();
		graph.put('s', list_s);
		graph.put('w', list_w);
		graph.put('r', list_r);
		graph.put('x', list_x);
		graph.put('v', list_v);
		graph.put('i', list_i);
		graph.put('y', list_y);
		graph.put('u', list_u);
		
		Map<String, Integer> weights = new HashMap<String, Integer>();
		weights.put("vr", 1);weights.put("rv", 1);weights.put("rs", 1);weights.put("sr", 1);weights.put("wi", 5);
		weights.put("iw", 5);weights.put("wx", 1);weights.put("xw", 1);weights.put("ix", 1);weights.put("xi", 1);
		weights.put("iu", 1);weights.put("ui", 1);weights.put("uy", 1);weights.put("yu", 1);weights.put("xy", 4);
		weights.put("yx", 4);weights.put("xu", 1);weights.put("ux", 1);weights.put("sw", 1);weights.put("ws", 1);
		
//		HashMap<Character, Integer> dist = new HashMap<Character, Integer>();
//		char start = 's';
//		bb.bfs(graph, dist, start);
//		bb.bfs_weight(graph, dist, weights, start);
		
		HashMap<Character, Boolean> visited = new HashMap<Character, Boolean>();
		bb.dfs(graph, visited);
	}
	
	/**
	 * 无权重；无路径输出
	 * HashMap<Character,LinkedList<Character>> graph: 这个HashMap是用于存放图中每个node的邻接表
	 * HashMap<Character,Integer> dist： 用于存放每个node与距离顶点s的距离的映射关系，同时充当visited角色
	 */
	private void bfs(HashMap<Character, LinkedList<Character>> graph,HashMap<Character, Integer> dist,char start) {
		// 双端队列，每遍历一个节点，则将它的邻接点放入队尾；队列中的几点均为已访问节点，均已计算出dist距离值
		Queue<Character> q=new LinkedList<Character>();
		q.add(start);
		dist.put(start, 0);

		int i=0;
		while(!q.isEmpty()) {
			char top=q.poll();//访问对头节点并移出队列
			i++;
			System.out.println("The "+i+"th element:"+top+" Distance from s is:"+dist.get(top));

			// 遍历当前节点的邻接点
			for (Character c : graph.get(top)) {
				if(!dist.containsKey(c)) { //如果dist中还没有该元素说明还没有被访问（每一个元素可能在队列q中出现多次，因为一个节点可能是多个节点的邻接点，但是每个节点只会在dist中出现一个）
					dist.put(c, dist.get(top)+1); //得出其周边还未被访问的节点的距离(如果边上含有权重，应使用map存放各边权重值，此处加上对应的权重值，同时还有个比较的过程)
					q.add(c); //将该节点加入队尾
				}
			}
		}
	}
	
	// 带权重的BFS
	private void bfs_weight(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Integer> dist, Map<String, Integer> weights, Character start) {
		Queue<Character> q=new LinkedList<Character>();
		q.add(start);
		dist.put(start, 0);
		
		while(!q.isEmpty()) {
			Character top=q.poll();
			for (Character c : graph.get(top)) {
				Integer w = weights.get(top+""+c);
				if(!dist.containsKey(c)) {
					dist.put(c, dist.get(top)+w);
					q.add(c);
				} else {
					dist.put(c, dist.get(top)+w > dist.get(c) ? dist.get(c) : dist.get(top)+w);
				}
			}
		}
		
		for (Map.Entry<Character, Integer> entry : dist.entrySet()) {
			System.out.println("The element:" + entry.getKey() + " Distance from s is:" + entry.getValue());
		}
	}

	//DFS，DFS中visited只记录是否被访问，而不需要像BFS那样需要依赖当前节点路径计算邻接点路径。因此此处visited可以使用list。
	private void dfs(HashMap<Character , LinkedList<Character>> graph, HashMap<Character, Boolean> visited) {
	    visit(graph, visited, 'u'); //为了和图中的顺序一样，我认为控制了DFS先访问u节点
	    visit(graph, visited, 'w');
	}
	public int count =0;
	private void visit(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited, char start) {
	    if (!visited.containsKey(start)) {
	        count++;
	        System.out.println("The time into element "+start+":"+count);//记录进入该节点的时间
	        visited.put(start, true);
	        for (char c : graph.get(start)) {
		        if (!visited.containsKey(c)) {
		            visit(graph, visited, c); //递归访问其邻近节点
		        }
	        }
	        count++;
	        System.out.println("The time out element "+start+":"+count); //记录离开该节点的时间
	    }
	}
}

































