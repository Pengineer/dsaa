package hust;

/**
 * 现在有一个行和列都排好序的矩阵，请设计一个高效算法，快速查找矩阵中是否含有值x。
 * 
 * 给定一个int矩阵mat，同时给定矩阵大小nxm及待查找的数x，请返回一个bool值，代表矩阵中是否存在x。所有矩阵中数字及x均为int范围内整数。保证n和m均小于等于1000。
 *
 * 1 2 4
 * 3 5 8
 * 7 9 11
 * @author 2015-11-14
 */
public class FindNumInTwoDimensionArray {
	/*
	 * 思路：从矩阵的右上角开始查找，如果该数>x，则去掉该数所在的行，如果该数<x，则去掉该数所在的列
	 */
	public static void main(String[] args) {
		int[][] mat = {{1,2,4},{3,5,8},{7,9,11}};
//		boolean isExist = find1(mat, 0, 2, 3);
//		boolean isExist = find2(mat, 3, 3, 9);
		boolean isExist = find3(mat, 3, 3, 1);
		System.out.println(isExist);
	}
	
	//方式一：递归（低效）
	public static boolean find1(int[][] mat, int row, int column, int num) {
		if(row>=3 || column<0){
			return false;
		}
		if(mat[row][column] > num) {
			return find1(mat, row, column-1, num);
		} else if(mat[row][column] < num) {
			return find1(mat, row+1, column, num);
		} else 
			return true;
	}
	
	//方式二：遍历二维矩阵
	public static boolean find2(int[][] mat, int n, int m, int num) {
		for(int i=0; i<n; i++){
			for(int j=m-1; j>=0; j--){
				if(mat[i][j] == num) return true;
			}
		}
		return false;
	}
	
	//方式三：
	public static boolean find3(int[][] mat, int n, int m, int num) {
		int i=0, j=m-1;
		while(i<n && j>=0){
			if(mat[i][j] > num){
				j--;
			} else if(mat[i][j] < num){
				i++;
			} else {
				return true;
			}
		}
		return false;
	}
}
