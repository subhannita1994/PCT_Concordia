
public enum FieldStatus {
	BLANK('#'),ORGANISM('@');
	private char text;
	FieldStatus(char s){ this.text = s;}
	public char text() {return this.text;}

}
