import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SortBooks {
	private static File file;
	private static BufferedReader in;
	private static FileWriter fout;
	private static BufferedWriter bw;
	private static int caseNum = 0;
	
	public static void start(String path){
		file = new File(path);
		try {
			fout = new FileWriter("result_" + path + ".txt");
			bw = new BufferedWriter(fout);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("result.txt can not created!");
		}
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("path is not found:" + path);
		}
		String temp = "";
		try {
			temp = in.readLine();
			caseNum = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Number Format Exception:" + temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int index = 1;
		while(index <= caseNum){
			try {
				String outLine = "";
				outLine = sortBooks(in.readLine(),in.readLine());
				bw.write("Case #" + index + ": " + outLine + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			index++;
		}
		try {
			bw.close();
			fout.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String sortBooks(String len, String bookarray) {
		String result = "";
		int[] alex = new int[1024];
		int[] bob = new int[1024];
		int[] array = new int[1024];
		int length = Integer.parseInt(len);
		String[] arraystr = bookarray.split(" ");
		for(int i = 0; i < length; i++){
			array[i] = Integer.parseInt(arraystr[i]);
		}
		
		array = sortBob(array, length);	
		array = sortAlex(array, length);
		
		for(int i = 0; i < length; i++){
			result += array[i] + " ";
		}
	
		return result;
	}
	
	private static int[] sortAlex(int[] array, int length) {
		int start = 0;
		int end = 0;
		for(int i = 0; i < length; i++){
			if(array[i] % 2 != 0){
				start = i;
				break;
			}
		}
		for(int i = length -1 ; i >= 0; i--){
			if(array[i] % 2 != 0){
				end = i;
				break;
			}
		}
		return quickSort(array,start,end);
		
	}
	private static int[] quickSort(int[] array, int start, int end) {
		if(start >= end){
			return array;
		}
		int p = array[start];
		int i = start;
		int j = end;
		
		while(i < j){
			if(array[j] % 2 != 0){
				if(array[j] > p){
					j--;
				}else{
					if(array[i] % 2 != 0){
						if(array[i] > p){
							int temp = array[i];
							array[i] = array[j];
							array[j] = temp;
							j--;
						}else{
							i++;
						}
					}else{
						i++;
					}
				}
			}else{
				j--;
			}
		}
		array[start] = array[i];
		array[i] = p;
		for(int k = i - 1; k >= start; k--){
			if(array[k] % 2 != 0){
				array = quickSort(array,start,k);
				break;
			}
		}
		for(int k = i+1; k <= end; k++){
			if(array[k] % 2 != 0){
				array = quickSort(array,k,end);
				break;
			}
		}
		
		return array;
	}
	private static int[] sortBob(int[] array, int length) {
		int start = 0;
		int end = 0;
		for(int i = 0; i < length; i++){
			if(array[i] % 2 == 0){
				start = i;
				break;
			}
		}
		for(int i = length -1 ; i >= 0; i--){
			if(array[i] % 2 == 0){
				end = i;
				break;
			}
		}
		return quickSort2(array,start,end);
		
	}
	private static int[] quickSort2(int[] array, int start, int end) {
		if(start >= end){
			return array;
		}
		int p = array[start];
		int i = start;
		int j = end;
		
		while(i < j){
			if(array[j] % 2 == 0){
				if(array[j] < p){
					j--;
				}else{
					if(array[i] % 2 == 0){
						if(array[i] < p){
							int temp = array[i];
							array[i] = array[j];
							array[j] = temp;
							j--;
						}else{
							i++;
						}
					}else{
						i++;
					}
				}
			}else{
				j--;
			}
		}
		array[start] = array[i];
		array[i] = p;
		
		for(int k = i - 1; k >= start; k--){
			if(array[k] % 2 == 0){
				array = quickSort2(array,start,k);
				break;
			}
		}
		for(int k = i+1; k <= end; k++){
			if(array[k] % 2 == 0){
				array = quickSort2(array,k,end);
				break;
			}
		}
		return array;
	}
	public static void main(String[] args) {
		start("C-small-practice.in");
		start("C-large-practice.in");

	}
	
	/*standard quick sort
	 * public static int[] qs(int[] array, int start, int end){
		int p = array[start];
		
		int i = start;
		int j = end;
		if(i >= j){
			return array;
		}
		while(i < j){
			if(array[j] < p){
				if(array[i] > p){
					int t = array[i];
					array[i] = array[j];
					array[j] = t;
					j--;
				}else{
					i++;
				}
			}else{
				j--;
			}
		}

		array[start] = array[i];
		array[i] = p;

		array = qs(array,start,i-1);
		array = qs(array,i+1,end);
		return array;
		
		
	}*/
}
