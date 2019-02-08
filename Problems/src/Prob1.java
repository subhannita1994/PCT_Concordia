//check credit card number
import java.util.Scanner;
public class Prob1 {
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		long num = reader.nextLong();
		int flag = 0, sum=0;
		for(long i = num;i>0;i=i/10) {
			sum+= (flag%2==0) ? i%10 : ((i%10)*2)%10 + ((i%10)*2)/10 ;
//			System.out.println(i%10+" "+sum);
			flag++;
		}
		if(sum%10 == 0)
			System.out.println("VALID");
		else {
			flag = 10 - (int)(sum- (num%10))%10;
			System.out.println("INVALID "+Integer.toString(flag));
		}
		reader.close();
	}

}
