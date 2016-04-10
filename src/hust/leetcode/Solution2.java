package hust.leetcode;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * 
 * @author liangjian
 *
 */
public class Solution2 {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	/**
	 * 思路：直接复用其中任意一个链表
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        //记录每个链表最后一个元素
        ListNode last1 = null;
        ListNode last2 = null;
        //记录进位情况
        int step =0;
        while(tmp1!=null & tmp2!=null){
            tmp1.val=tmp1.val+tmp2.val+step;
            if(tmp1.val>=10){
                tmp1.val = tmp1.val % 10;
                step = 1;
            }else{
                step =0;
            }
            if(tmp1.next == null) last1 = tmp1;
            if(tmp2.next == null) last2 = tmp2;
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }
        //链表1较长
        while(tmp1!=null){
            tmp1.val = tmp1.val + step;
            if(tmp1.val>=10){
                tmp1.val = tmp1.val % 10;
                step = 1;
            }else{
                step =0;
            }
            if(tmp1.next == null) last1 = tmp1;
            tmp1 = tmp1.next;
        }
        
        //链表2较长，将链表1的最后一个元素指向链表2的多出部分的第1个结点
        if(tmp2 != null){
            last1.next=tmp2;
        }
        while(tmp2!=null){
            tmp2.val = tmp2.val + step;
            if(tmp2.val>=10){
                tmp2.val = tmp2.val % 10;
                step = 1;
            }else{
                step =0;
            }
            if(tmp2.next == null) last2 = tmp2;
            tmp2 = tmp2.next;
        }
        //最后一个元素产生了进位
        if(step == 1) {
            ListNode node = new ListNode(1);
            node.next = null;
            if(last1.next != null) {
                last2.next = node;
            }else{
                last1.next = node;
            }
        }
        return l1;
    }
}
