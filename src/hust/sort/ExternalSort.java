package hust.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 外部排序
 * 
 * 现有100个数要进行排序，内存限制一次只能放20个数。
 * 
 * 思路：
 * 1，将大文件切割成内存允许范围内的文件大小，并对每一个文件进行快速排序；
 * 2，将排序后的各小文件两两进行归并排序，归并的过程中要在内存满之前的数据不断刷到磁盘，归并的结果放在一个新文件中，依次反复，直到最后的文件集合中只有一个文件；
 */
public class ExternalSort {
	
	private final static int SIZE = 10;//留一定空间保证程序正常运行

	public static void main(String[] args) throws Exception {
		String fileName = "E:\\dsaa\\source.txt";
		initSourceFile(fileName);
		mergeFiles(splitAndMultiSort(new File(fileName)));
//		print(result);
	}
	
	//创建一个原始文件
	public static void initSourceFile(String fileName) throws Exception {
		File file = new File(fileName); 
		if(file.exists()) {
			file.delete();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			bw.write(r.nextInt(100) + "\r\n");  //最好用DataInputStream代替基本类型的文件写，因为此处不加"\r\n"会乱码
		}
		bw.close();
		System.out.println("创建原始文件！");
	}
	
	//分割原始文件并对小文件进行多路快速排序
	public static List<File> splitAndMultiSort(File file) throws Exception {
		int[] numbers = new int[SIZE];
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<File> files=new ArrayList<File>();//保存所有分割文件的名称
		int index=0;
		while(true){
			String num=br.readLine();
		    if(num == null){//如果读取完毕后，进行一次排序并保存
		    	files.add(sortAndSave(numbers, index));
		    	break;
		    }
		    numbers[index]=Integer.valueOf(num);
		    index++;
		    if(index == SIZE){
		    	files.add(sortAndSave(numbers, index));//将内存中的SIZE个数进行快速排序并写入磁盘文件
		    	index=0;//重置index
		    }
		}
		System.out.println("切割文件进行多路排序结束！");
		return files;
	}
	
	//对numbers数组实施快排，将结果保存在本地文件
	public static File sortAndSave(int[] numbers, int index) throws Exception {
		quickSort(numbers, 0, index-1);
		String fileName = "E:\\dsaa\\" + System.currentTimeMillis() + ".txt";
		File file = new File(fileName);
		while (file.exists()){
			fileName = "E:\\dsaa\\" + System.currentTimeMillis() + ".txt";
			file = new File(fileName);
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for(int num : numbers) {
			bw.write(num + "\r\n");
		}
		bw.close();
		return file;
	}
	
	//快排
	public static void quickSort(int[] numbers, int start, int end) {
		if(start < end) {
			int i = start, j = end, x = numbers[start];
			while(i < j) {
				while(i < j && numbers[j] >= x){
					j--;
				}
				numbers[i] = numbers[j];
				while(i < j && numbers[i] <=x){
					i++;
				}
				numbers[j] = numbers[i];
			}
			numbers[i] = x;
			quickSort(numbers, start, i-1);
			quickSort(numbers, i+1, end);
		}
	}
	
	//合并多个文件，返回最终合并的文件：将集合中的文件两两合并得到一个新文件，如此反复进行知道集合中只有一个文件
	public static File mergeFiles(List<File> files) throws Exception {
		File file1, file2, newFile = new File("");
		for(int i=0; files.size() >=2; i=0) {
			file1 = files.get(i++);
			file2 = files.get(i);
			newFile = mergeTwoFile(file1, file2);
			files.remove(file1);
			files.remove(file2);
			files.add(newFile);
		}
		newFile.renameTo(new File(newFile.getParent() + "\\result.txt"));
		System.out.println("外部排序完成！");
		return newFile;
	}
	
	//合并file1和file2，返回合并后的文件名
	public static File mergeTwoFile(File file1, File file2) throws Exception {
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		//创建合并后的文件
		String resultFileName = "E:\\dsaa\\" + System.currentTimeMillis() + ".txt";
		File resultFile = new File(resultFileName);
		while (resultFile.exists()) {
			resultFileName = "E:\\dsaa\\" + System.currentTimeMillis() + ".txt";
			resultFile = new File(resultFileName);
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(resultFileName));
		//归并排序，且每读10次，将内存中的数据刷到磁盘
		int num1=0, num2=0, cnt =0; 
		String str1 = br1.readLine();
		String str2 = br2.readLine();
		cnt+=2;
 		while (str1 != null && str2 != null) {
			num1 = Integer.valueOf(str1);
			num2 = Integer.valueOf(str2);
			if (num1 > num2) {
				bw.write(Integer.valueOf(str2) + "\r\n");
				str2 = br2.readLine();
				cnt++;
				if (cnt == 9) {
					cnt = 0;
					bw.flush();
				}
			} else {
				bw.write(Integer.valueOf(str1) + "\r\n");
				str1 = br1.readLine();
				cnt++;
				if (cnt == 9) {
					cnt = 0;
					bw.flush();
				}
			}
		}
		while (str1 != null) {
			bw.write(Integer.valueOf(str1) + "\r\n");
			str1 = br1.readLine();
			cnt++;
			if (cnt == 9) {
				cnt = 0;
				bw.flush();
			}
		}
		while (str2 != null) {
			bw.write(Integer.valueOf(str2) + "\r\n");
			str2 = br2.readLine();
			cnt++;
			if (cnt == 9) {
				cnt = 0;
				bw.flush();
			}
		}
		br1.close();
		br2.close();
		bw.close();
		return resultFile;
	}
	
	
	//打印文件内容
	public static void print(File file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		for(int i=0; i<100; i++) {
			System.out.println(br.readLine());
		}
	}

}
