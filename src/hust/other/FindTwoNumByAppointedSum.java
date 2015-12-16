package hust.other;

/**
 * 题目：
 * 找出排序数组中和为指定数字的两个数，如果存在多个，返回任意一对即可。例如输入的数组{1,2,4,7,11,15}和数字15，返回4和11。
 * 
 * 低效算法:
 * 1，两层遍历，时间复杂度O(n^2)，没有用到排序条件。
 * 2，一层遍历，确定一个数，然后二分法查找另一个数，时间复杂度O(nlogn)。
 * 
 * @author 2015-12-14
 *
 */
public class FindTwoNumByAppointedSum {

	public static void main(String[] args) {
		int[] arr = {3,6,10,7,8};
		find(arr, 5, 13);
	}
	
	/*
	 * 因为这里的数组是排序的，因此定义两个指针，一前一后，如果相加的和大于sum，则后面的指针前移，如果相加的和小于sum，
	 * 则前面的指针后移。时间复杂度O(n)。
	 */
	public static void find(int[] arr, int length, int sum) {
		if(arr == null || length <= 0) {
			return;
		}
		//定义两个指针
		int forward = 0;
		int afterward = length - 1;
		while(forward < afterward) {
			int tempSum = arr[forward] + arr[afterward];
			if(tempSum == sum) {
				System.out.println("arr[" + forward +"] + " + "arr[" + afterward +"] = " + arr[forward] + " + " + arr[afterward]);
				break;
			} else if(tempSum > sum) {
				afterward--;
			} else {
				forward++;
			}
		}
		
		if(arr[forward] + arr[afterward] != sum) {
			System.out.println("不存在满足要求的两个数！");
		}
	}

}
