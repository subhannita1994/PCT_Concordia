import java.util.Arrays;

public class Field {
	
	private int row; private int col;
	private FieldStatus[][] field;
	public Field(int row, int col) { 
		this.row = row; this.col = col; 
		this.field = new FieldStatus[row][col];
		}
	
	public void buildField(String str) {
		
		int index=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(str.charAt(index)=='#')
					field[i][j] = FieldStatus.BLANK;
				else
					field[i][j] = FieldStatus.ORGANISM;
				index++;
			}
		}
		
	}
	
	public void printField() {
		System.out.println();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.print(field[i][j].text());
			}
			System.out.println();
		}
	}
	
	private int getLiveNbors(FieldStatus[][] field,int row, int col) {
		
		int[][] nborIndices = {{row-1,col-1},{row-1,col},{row-1,col+1},{row,col+1},{row+1,col+1},{row+1,col},{row+1,col-1},{row,col-1}};
		int count = 0;
		for(int i=0;i<8;i++) {
			if(nborIndices[i][0]>=0 && nborIndices[i][1]>=0 && nborIndices[i][0]<this.row && nborIndices[i][1]<this.col) {
//				System.out.println("Valid position ("+nborIndices[i][0]+","+nborIndices[i][1]+")"+"; Status: "+ field[nborIndices[i][0]][nborIndices[i][1]]);
				if(field[nborIndices[i][0]][nborIndices[i][1]] == FieldStatus.ORGANISM) {
//					System.out.println("Organism nbor found at ("+nborIndices[i][0]+","+nborIndices[i][1]+")");
					count++;
				}
			}
		}
		return count;
	}
	
	
	public FieldStatus[][] genNewField(int gen) {
		
		FieldStatus[][] nextField = new FieldStatus[row][col];
		FieldStatus cellStatus;
		int liveNbors;
		for(int g=0;g<gen;g++) {
//			System.out.println("Generation: "+g);
			for(int i=0;i<row;i++) {
				for(int j=0;j<col;j++) {
					cellStatus = field[i][j];
					liveNbors = getLiveNbors(field,i,j);
//					System.out.println("Coords:("+i+","+j+") Status:"+cellStatus+" Live Nbors:"+liveNbors);
					if((cellStatus == FieldStatus.BLANK && liveNbors==3) || (cellStatus==FieldStatus.ORGANISM && (liveNbors==2 || liveNbors==3)))
						nextField[i][j] = FieldStatus.ORGANISM;
					else
						nextField[i][j] = FieldStatus.BLANK;
					
				}
			}
			for(int i=0;i<nextField.length;i++) {
				field[i] = Arrays.copyOf(nextField[i], nextField[i].length);
			}
//			System.out.println("STATUS:"+field[1][0].text());
//			this.printField();
			
		}
		
		return field;
	}

}
