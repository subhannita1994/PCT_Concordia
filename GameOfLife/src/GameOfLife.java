import java.util.Scanner;
public class GameOfLife {

	public static void main(String[] args) {
		
		
		Scanner reader = new Scanner(System.in);
		int r = reader.nextInt();
		int c = reader.nextInt();
		Field field = new Field(r,c);
		String str = reader.nextLine();
		for(int i=0;i<r;i++) {
			str+=reader.nextLine();
		}
		field.buildField(str);	//n2
		int n = reader.nextInt();
		
		FieldStatus[][] newField = field.genNewField(n); //n3
		
		field.printField();
		//n2
		int count=0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(newField[i][j] == FieldStatus.ORGANISM)
					count++;
			}
		}
		
		System.out.println(count);
		reader.close();
		
	}
}
