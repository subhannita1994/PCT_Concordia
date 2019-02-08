import java.util.Random;
public class FieldSamples {

	private static String getSaltString(int len) {
        String SALTCHARS = "#@";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < len) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] size = {{3,3},{3,3},{4,7},{5,10},{20,20},{20,25},{30,30}};
		int[] n = {3,4,8,5,3,9,6};
		String str;
		long time;
		GameOfLife obj = new GameOfLife();
		
		for(int i=0;i<7;i++) {
			str = getSaltString(size[i][0]*size[i][1]);
			System.out.print("Case "+(i+1)+" size,n:"+size[i][0]+"by"+size[i][1]+";"+n[i]+"---");
			time = System.currentTimeMillis();
			obj.main(size[i][0], size[i][1], str, n[i]);
			System.out.println(Long.toString(System.currentTimeMillis()-time));
			
		}
		

	}

}
