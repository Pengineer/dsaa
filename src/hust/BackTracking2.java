package hust;

import java.util.ArrayList;

/**
 * 回溯法 2 模板式编程：以八皇后问题为例
 * 
 * void Trial(int i, int n){
 * 	  //进入本函数时，在n*n棋盘前i-1行已放置了互不攻击的i-1个棋子。
 * 	  //现从第i行起继续为后续棋子选择合适位置
 *    //当i>n时，求得一个合法布局，输出之。
 *    if(i>n) 输出棋盘的当前布局;      //n=4时，即为4皇后问题
 *    else 
 *    	for (j=1; j<=n; j++) {
 *    		在第i行第j列放置一个棋子;
 *    		if(当前布局合理) Trail(i+1,n);
 *    		移走第i行第j列的棋子。
 *    	}
 * }
 * 
 * 以上模板可以作为回溯法求解的一般模式，类似问题有骑士游历、迷宫问题、选最优解。
 */

public class BackTracking2 {
	
	public static void main(String[] args) {
		ArrayList<Integer> dest = new ArrayList<Integer>();
		Trail(1, 8, dest);
	}
	
	public static void Trail(int i, int n, ArrayList<Integer> dest) {
		if(i>n) {
			System.out.println(dest.toString());
		} else {
			for (int j=1; j<=n; j++) {
				dest.add((Integer)(i *10 + j));
				if(Reasonable_2(i*10 +j, i, dest)) Trail(i+1, n, dest);
				dest.remove((Integer)(i *10 + j));
			}
		}
	}
	
	//这种判断方式有问题，比如正斜线上的(num-tmp)%11也会被过滤掉，导致输出结果不全
	public static boolean Reasonable_1(int num, int i, ArrayList<Integer> dest) { //第i行的num是否满足放置条件
		for (int j=1; j<i; j++) {
			int tmp = (Integer) dest.get(j-1);
			if((num - tmp) % 10 == 0) return false;                    //垂直方向
			if((num/10 + num%10 - tmp/10 -tmp%10) == 0) return false;  //正斜线方向
			if((num - tmp)%11 == 0) return false;                      //反斜线方向
		}
		return true;
	}
	
	//斜线问题的经典处理方式是转换成斜率的处理
	public static boolean Reasonable_2(int num, int i, ArrayList<Integer> dest) {
		for (int j=1; j<i; j++) {
			int tmp = (Integer) dest.get(j-1);			
			int x1, y1, x2, y2;		
			x1 = num/10;
			y1 = num%10;
			x2 = tmp/10;
			y2 = tmp%10;
			if(y1 == y2) return false;
			if((y1-y2) == (x1-x2)) return false;
			if((y1-y2) == (x2-x1)) return false;
		}
		return true;
	}
}

