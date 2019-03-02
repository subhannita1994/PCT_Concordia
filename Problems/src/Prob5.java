import java.util.Arrays;
import java.util.Scanner;
public class Prob5 {
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		String str = reader.nextLine();
		String letters = reader.nextLine();
		int strLen = str.length();
		int lettersLen = letters.length();
		int minLength = strLen;
		String minSequence="blabla";
		char l[] = new char[lettersLen];
		int o[] = new int[lettersLen];
		l = letters.toCharArray();
		Arrays.fill(o, -1);
		char ch;
		for(int i=0;i<strLen;i++) {
			ch = str.charAt(i);
			for(int j =0;j<lettersLen;j++) {
				if(ch==l[j]) {
					System.out.println("found");
					o[j]++;
					if(Arrays.stream(o).min().getAsInt()!=-1) {
						System.out.println("updating");
						int length = Arrays.stream(o).max().getAsInt() - Arrays.stream(o).min().getAsInt() + 1;
						if(length < minLength) { 
							minLength = length;
							minSequence = str.substring(Arrays.stream(o).min().getAsInt(), Arrays.stream(o).max().getAsInt());
							System.out.println(minSequence);
						}
					}
				}
			}
			
		}
		System.out.println("here");
		System.out.println(minSequence);
		reader.close();
	}

}
