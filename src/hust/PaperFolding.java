package hust;

/**
 * 折纸问题
 * 请把纸条竖着放在桌子上，然后从纸条的下边向上方对折，压出折痕后再展 开。此时有1条折痕，突起的方向指向纸条的背面，这条折痕叫做“下”折痕  ；
 * 突起的方向指向纸条正面的折痕叫做“上”折痕。如果每次都从下边向上面 对折，对折N次。请从上到下打印所有折痕的方向。
 * 
 * 解答：   
 * 1，产生第i+1层折痕的过程，就是在每一条i层折痕的左右两边，依次插入  “上”折痕和“下”折痕的过程；   
 * 2，所有折痕的组织结构是一棵满二叉树，在这棵满二叉树中，头节点为  “下”折痕，每一棵左子树的头节点都为“上”折痕，每一棵右子树的头节点都  为“下”折痕；   
 * 3，从上到下打印所有折痕的过程，就是按照右，中，左的方式遍历整棵二叉树的过程；
 * 
 * @author 2015-11-28
 *
 */
public class PaperFolding {
	
	public static void main(String[] args) {
		int N = 3;
		printAllFolds(N);
	}
	
	public static void printAllFolds(int N) {
		printProcess(1, N, true);
	}
	
	//N是整棵树的深度，i是当前遍历的深度
	public static void printProcess(int i, int N, boolean down) { 
		if (i > N) { //并没有叶子节点的信息，用当前遍历的深度作为退出条件
			return;
		}
		printProcess(i + 1, N, true); //第三个参数表示左右节点的值
		System.out.println(down ? "down " : "up ");
		printProcess(i + 1, N, false);
	}
	
}
