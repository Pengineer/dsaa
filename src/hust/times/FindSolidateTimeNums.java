package hust.times;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * 输出数组中个数大于len/K的所有元素，其中，len为数组长度，K为输入参数
 * 
 * 要求：额外空间复杂度为O(1)
 * 
 * @author 2015-11-30
 * @category 牛客网 http://www.nowcoder.com/discuss/338
 *
 */

public class FindSolidateTimeNums {
	
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
		
		findKMajor(arr, 4);
	}
	
	public static void findKMajor(int[] arr, int K) {
		if (K < 2) {
			System.out.println("the value of K is invalid.");
			return;
		}
		HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>(); //放置arr中元素个数最多的K-1个元素
		for (int i = 0; i != arr.length; i++) {
			if (cands.containsKey(arr[i])) {
				cands.put(arr[i], cands.get(arr[i]) + 1);
			} else {
				if (cands.size() == K - 1) {  //保证额外空间复杂度为O(1)，因为元素个数大于N/K的最多只有K-1个
					allCandsMinusOne(cands);
				} else {
					cands.put(arr[i], 1);
				}
			}
		}
		HashMap<Integer, Integer> reals = getReals(arr, cands);
		boolean hasPrint = false;
		for (Entry<Integer, Integer> set : cands.entrySet()) {
			Integer key = set.getKey();
			if (reals.get(key) > arr.length / K) {
				hasPrint = true;
				System.out.print(key + " ");
			}
		}
		System.out.println(hasPrint ? "" : "no such number.");
	}
	
	//将map中的元素的value减1，如果减为0，则剔除
	public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
		List<Integer> removeList = new LinkedList<Integer>();
		for (Entry<Integer, Integer> set : map.entrySet()) {
			Integer key = set.getKey();
			Integer value = set.getValue();
			if (value == 1) {
				removeList.add(key);
			}
			map.put(key, value - 1);
		}
		for (Integer removeKey : removeList) {
			map.remove(removeKey);
		}
	}
	
	//因为cands中元素的value并不一定是该元素实际在数组中的个数（allCandsMinusOne函数有减1），因此，本函数就是获取这些元素的真实个数
	public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
		HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
		for (int i = 0; i != arr.length; i++) {
			int curNum = arr[i];
			if (cands.containsKey(curNum)) {
				if (reals.containsKey(curNum)) {
					reals.put(curNum, reals.get(curNum) + 1);
				} else {
					reals.put(curNum, 1);
				}
			}
		}
		return reals;
	}
}
