
public class Start {
	public static void main(String[] args) {
//		ReadPhoneNumber.start(ReadPhoneNumber.pathSmall);
//		ReadPhoneNumber.start(ReadPhoneNumber.pathLarge);
		loop1 : 	
		for(int i=0; i<5; i++){
			   loop : 
				   for(int j=0; j<5; j++){
					   for(int t = 0; t < 10; t++){
						   if(i * j == 10)
							   continue loop1;
					   }
			   }
			}
		
		
	}
	

}
