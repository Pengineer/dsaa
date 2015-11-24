package hust.string;

/**
 * 对于两棵彼此独立的二叉树A和B，请编写一个高效算法，检查A中是否存在一棵子树与B树的拓扑结构完全相同。
 * 给定两棵二叉树的头结点A和B，请返回一个bool值，代表A中是否存在一棵同构于B的子树。
 *
 * @author 2015-11-24
 *
 */

import hust.string.TreeNode;
public class IdenticalTree {
    public static boolean chkIdentical(TreeNode A, TreeNode B) {
        // write code here
        String AStr = getTreeString(A);
        String BStr = getTreeString(B);
        System.out.println(AStr);
        System.out.println(BStr);
        
        if(AStr.contains(BStr)) return true;
        else return false;
    }
    public static String getTreeString(TreeNode head) {
        if(head == null) return "#"; //必须加一个特殊符号
        String tmp = head.val + "#";
        String left = getTreeString(head.left);
        tmp = tmp + left;
        String right = getTreeString(head.right);
        tmp = tmp + right;
    	return tmp;
    }
    
    public static void main(String[] args) {
    	TreeNode headA = new TreeNode(0);
    	TreeNode A1 = new TreeNode(1);
    	TreeNode A2 = new TreeNode(2);
    	TreeNode A3 = new TreeNode(3);
    	TreeNode A4 = new TreeNode(4);
    	TreeNode A5 = new TreeNode(5);
    	headA.left = A1;
    	headA.right = A2;
    	A1.left = A3;
    	A1.right = A4;
    	A2.left = A5;
    	
    	TreeNode headB = new TreeNode(1);
    	TreeNode B1 = new TreeNode(3);
    	TreeNode B2 = new TreeNode(4);
    	headB.left = B1;
    	headB.right = B2;
    	
    	System.out.println(chkIdentical(headA, headB));
	}
}

/* 标答
 import java.util.*;
 

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
public class IdenticalTree {
    public boolean chkIdentical(TreeNode t1, TreeNode t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }
 
    public String serialByPre(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.val + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }
 
    // KMP
    public int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] nextArr = getNextArray(ms);
        int index = 0;
        int mi = 0;
        while (index < ss.length && mi < ms.length) {
            if (ss[index] == ms[mi]) {
                index++;
                mi++;
            } else if (nextArr[mi] == -1) {
                index++;
            } else {
                mi = nextArr[mi];
            }
        }
        return mi == ms.length ? index - mi : -1;
    }
 
    public int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] nextArr = new int[ms.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < nextArr.length) {
            if (ms[pos - 1] == ms[cn]) {
                nextArr[pos++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }
}
 */
