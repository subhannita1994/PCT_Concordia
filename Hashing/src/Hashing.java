import java.util.Arrays;
import java.util.Scanner;

public class Hashing {
	private static boolean lastDigit(int number) {
		if(number/10 == 0)
			return true;
		return false;
	}
	
	private static int shift(int capacity,int origin,int shift,int direction) {
		//returns new index when shifted by shift from origin index according to direction(forward if odd otherwise backward)
		int des = (direction%2==0)? origin-shift : origin+shift;
		des = (des<0)? capacity-(Math.abs(des)%capacity) : des%capacity;
		return des;
		
	}

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		int capacity = reader.nextInt();
		String output="";
		int j,lastDig,i;
		//initiating array of size n-- all of which are -1
		int[] arr = new int[capacity];
		Arrays.fill(arr, -1);
		
		//continue to take input until -1 (stopping criteria)
		int num = reader.nextInt();
		while(num!=-1) {
			i = num;
			j = i%capacity;
			output += Integer.toString(j)+" ";
			while(arr[j]!=-1) {	//continue going till collision is resolved 

				if(lastDigit(i)) {	//if only one digit left
					j = shift(capacity,j,1,i);
				}
				else {	//more than one digit left
					lastDig = i%10;
					i = (i-lastDig)/10;
					j = shift(capacity,j,i%capacity,lastDig);
				}
				output += Integer.toString(j)+" ";
			}
			
			arr[j] = num;
			output += "\n";
			num = reader.nextInt();
			
		}
		
		System.out.println(output);
		
		reader.close();
	}
	
}
