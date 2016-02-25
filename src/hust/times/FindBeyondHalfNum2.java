package hust.times;

/**
 * 在数组中打印出现次数大于一半的数。如果不存在，什么也不打印。 
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 * 
 * @since 2015-11-30
 *
 */
public class FindBeyondHalfNum2 {

	public static void main(String[] args) {
		int [] arr = {1,2,3,2,1,2,1,3,1,5};
		
		System.out.println(findHalfNum(arr));
	}
	
	//如果有超过一半的元素，一定有且仅有一个，而且他的次数大于所有其它元素出现的次数
	public static int findHalfNum(int[] arr) {
		int candidate = -1;
		int times = 0;
		for (int i = 0; i < arr.length; i++) {
			if(times == 0) { //times为0，当前为候选
				candidate = arr[i];
				times=1;
			} else if(arr[i] == candidate) {
				times++;
			} else { //第一个是1，第二个是2，candidate不需要变
				times--;
			}
			//如下是错误的
			/*if(arr[i] == candidate) {
				times++;
			} else {
				times--;
				if(times == 0) {
					candidate = arr[i];
					times=1;
				}
			}*/
		}
		//
		times =0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == candidate) times++;
		}
		if(times >= arr.length/2)
			return candidate;
		
		return -1;
	}
}
