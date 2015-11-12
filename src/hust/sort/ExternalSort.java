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
 * �ⲿ����
 * 
 * ����100����Ҫ���������ڴ�����һ��ֻ�ܷ�20������
 * 
 * ˼·��
 * 1�������ļ��и���ڴ�����Χ�ڵ��ļ���С������ÿһ���ļ����п�������
 * 2���������ĸ�С�ļ��������й鲢���򣬹鲢�Ĺ�����Ҫ���ڴ���֮ǰ�����ݲ���ˢ�����̣��鲢�Ľ������һ�����ļ��У����η�����ֱ�������ļ�������ֻ��һ���ļ���
 */
public class ExternalSort {
	
	private final static int SIZE = 10;//��һ���ռ䱣֤������������

	public static void main(String[] args) throws Exception {
		String fileName = "E:\\dsaa\\source.txt";
		initSourceFile(fileName);
		mergeFiles(splitAndMultiSort(new File(fileName)));
//		print(result);
	}
	
	//����һ��ԭʼ�ļ�
	public static void initSourceFile(String fileName) throws Exception {
		File file = new File(fileName); 
		if(file.exists()) {
			file.delete();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			bw.write(r.nextInt(100) + "\r\n");  //�����DataInputStream����������͵��ļ�д����Ϊ�˴�����"\r\n"������
		}
		bw.close();
		System.out.println("����ԭʼ�ļ���");
	}
	
	//�ָ�ԭʼ�ļ�����С�ļ����ж�·��������
	public static List<File> splitAndMultiSort(File file) throws Exception {
		int[] numbers = new int[SIZE];
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<File> files=new ArrayList<File>();//�������зָ��ļ�������
		int index=0;
		while(true){
			String num=br.readLine();
		    if(num == null){//�����ȡ��Ϻ󣬽���һ�����򲢱���
		    	files.add(sortAndSave(numbers, index));
		    	break;
		    }
		    numbers[index]=Integer.valueOf(num);
		    index++;
		    if(index == SIZE){
		    	files.add(sortAndSave(numbers, index));//���ڴ��е�SIZE�������п�������д������ļ�
		    	index=0;//����index
		    }
		}
		System.out.println("�и��ļ����ж�·���������");
		return files;
	}
	
	//��numbers����ʵʩ���ţ�����������ڱ����ļ�
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
	
	//����
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
	
	//�ϲ�����ļ����������պϲ����ļ����������е��ļ������ϲ��õ�һ�����ļ�����˷�������֪��������ֻ��һ���ļ�
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
		System.out.println("�ⲿ������ɣ�");
		return newFile;
	}
	
	//�ϲ�file1��file2�����غϲ�����ļ���
	public static File mergeTwoFile(File file1, File file2) throws Exception {
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		//�����ϲ�����ļ�
		String resultFileName = "E:\\dsaa\\" + System.currentTimeMillis() + ".txt";
		File resultFile = new File(resultFileName);
		while (resultFile.exists()) {
			resultFileName = "E:\\dsaa\\" + System.currentTimeMillis() + ".txt";
			resultFile = new File(resultFileName);
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(resultFileName));
		//�鲢������ÿ��10�Σ����ڴ��е�����ˢ������
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
	
	
	//��ӡ�ļ�����
	public static void print(File file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		for(int i=0; i<100; i++) {
			System.out.println(br.readLine());
		}
	}

}
