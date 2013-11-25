import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.RandomAccessFile; 
import java.io.Reader; 
public class ReadPhoneNumber {
	public static final String[] prefix = {
		"","","double", "triple", "quadruple", "quintuple", "sextuple", "septuple", "octuple", "nonuple", "decuple"
	};
	public static final String[] digitStr = {
		"zero","one","two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
	};
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
				outLine = numberRead(in.readLine());
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
	
	private static String numberRead(String line){
		String[] temp = line.split(" ");
		String result = "";
		if(temp.length != 2){
			System.out.println("error when split " + line + " !");
			System.exit(1);
		}
		String[] separateNumStr = temp[1].split("-");
		int start = 0;
		int end = 0;
		for(int i = 0; i < separateNumStr.length; i++){
			end = start + Integer.parseInt( separateNumStr[i] );
			result += read(temp[0].substring(start, end));
			start = end;
		}
		return result;
	} 
	private static String read(String numstr) {
		String result = "";
		int lastDigit = -1;
		int occurance = 0;
		for(int i = 0; i < numstr.length(); i++){
			if(lastDigit != (numstr.charAt(i) - 48) && lastDigit != -1){
				if(occurance > 10){
					for(int j = 0; j< occurance; j++){
						result += digitStr[lastDigit] + " ";
					}
				}else{
					result += prefix[occurance] + " " + digitStr[lastDigit] + " ";
				}
				occurance = 0;
			}
				
			lastDigit = (numstr.charAt(i) - 48);
			occurance++;
		}
		if(occurance > 10){
			for(int j = 0; j< occurance; j++){
				result += digitStr[lastDigit] + " ";
			}
		}else{
			result += prefix[occurance] + " " + digitStr[lastDigit] + " ";
		}
		return result;
	}
	
	public static void main(String[] args) {
		start("A-small-practice.in");
		start("A-large-practice.in");	
	}
}
