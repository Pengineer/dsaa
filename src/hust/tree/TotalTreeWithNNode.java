package hust.tree;

/**
 * 求n个结点的二叉树的形态个数(卡特兰数的应用，出栈入栈问题)
 * 
 * 果然是二叉树离不开递归.
 * 可以分析，当n=1时，只有1个根节点，则只能组成1种形态的二叉树，令n个节点可组成的二叉树数量表示为h(n)，则h(1)=1; h(0)=1;
 * 当n=2时，1个根节点固定，还有2-1个节点。这一个节点可以分成（1,0），（0,1）两组。即左边放1个，右边放0个；或者左边放0个，右边放1个。
 *    即：h(2)=h(0)*h(1)+h(1)*h(0)=2，则能组成2种形态的二叉树。
 * 当n=3时，1个根节点固定，还有2个节点。这2个节点可以分成（2,0），（1,1），（0,2）3组。
 *    即：h(3)=h(0)*h(2)+h(1)*h(1)+h(2)*h(0)=5，则能组成5种形态的二叉树。
 * 以此类推，当n>=2时，可组成的二叉树数量为h(n)=h(0)*h(n-1)+h(1)*h(n-2)+...+h(n-1)*h(0)种，即符合Catalan数的定义，可直接利用通项公式
 * 得出结果。
 * 令h(1)＝1,h(0)=1，catalan数（卡特兰数）满足递归式：
 *     h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)h(0) (其中n>=2)
 * 
 * @author liangjian
 * @since 2016-03-09
 * 
 */
public class TotalTreeWithNNode {
	
	public static void main(String[] args) {
		System.out.println(f(3));
	}

	public static int f(int n) {
		if(n == 0) {
			return 1;
		}else if(n==1) {
			return 1;
		}else{
			int tmp = 0;
			for(int i=0; i<=n-1; i++) {
				tmp += f(i) * f(n-i-1);
			}
			return tmp;
		}
	}
}
