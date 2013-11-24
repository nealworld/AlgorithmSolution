import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

public class RationalTreeNumber {
	public static File file;
	public static BufferedReader br;
	public static PrintWriter pw;
	  
	public static void start(String path){
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path+"_result.txt")),true);
			
			int caseNum = Integer.parseInt(br.readLine());
			int index = 1;
			while(index <= caseNum){
				String line = br.readLine();
				String[] contents = line.split(" ");
				if(contents.length == 2){//case 1
					pw.write("Case " + "#" + index + ": " + getPQ(contents[1]) + "\n");
					pw.flush();
				}else if(contents.length == 3){//case 2
					pw.write("Case " + "#" + index + ": " + getNumber(contents[1],contents[2]) + "\n");
					pw.flush();
				}
				
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String getNumber(String pStr, String qStr) {
		BigInteger p = new BigInteger(pStr);
		BigInteger q = new BigInteger(qStr);
		BigInteger num = new BigInteger("1");
		BigInteger one = new BigInteger("1");
		
		char[] rightLeftStack = new char[128];
		int len = 0;
		for(int i = 0; i < 128; i++){
			if(p.compareTo(q) > 0){
				rightLeftStack[i] = 1;
				p = p.subtract(q);
			}else if(p.compareTo(q) < 0){
				rightLeftStack[i] = 0;
				q = q.subtract(p);
			}else{
				break;
			}
			len++;
		}
		for(int i = len -1; i >= 0; i--){
			if(rightLeftStack[i] == 1){
				num = num.shiftLeft(1).add(one);
			}else{
				num = num.shiftLeft(1);
			}
		}
		return num.toString();
	}
	private static String getPQ(String num) {
		BigInteger number = new BigInteger(num);
		BigInteger p = new BigInteger("1");
		BigInteger q = new BigInteger("1");
		BigInteger one = new BigInteger("1");
		char[] rightLeftStack = new char[128];
		int length = 0;
		for(int i = 0;i < 128; i++){
			if(number.compareTo(one) == 0){
				break;
			}
			if(number.testBit(0)){// number is odd
				rightLeftStack[i] = 1; 
			}else{
				rightLeftStack[i] = 0;
			}
			length++;
			number = number.shiftRight(1);
		}
		for(int i = length - 1; i >= 0; i--){
			if(rightLeftStack[i] == 0){
				q = p.add(q);
			}else{
				p = p.add(q);
			}
		}
		
		return p.toString() + " " + q.toString();
	}
	public static void main(String[] args) {
		start("B-small-practice.in");
		start("B-large-practice.in");
	}

}
