package game1;
import java.util.ArrayList;

public class DotCom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfGuesses=0;
		GameHelper helper=new GameHelper();
		SimpleDotCom theDotCom=new SimpleDotCom();
		int randomNum=(int) (Math.random()*5);
		ArrayList<String> locations = new ArrayList<String>();
		locations.add(String.valueOf(randomNum));
		locations.add(String.valueOf(randomNum+1));
		locations.add(String.valueOf(randomNum+2));
		theDotCom.setLocationCells(locations);
		boolean isAlive=true;
		
		while(isAlive==true) {
			String guess=helper.getUserInput("enter a number");
			String result=theDotCom.checkYourself(guess);
			numOfGuesses++;
			if(result.equals("kill")) {
				isAlive=false;
				System.out.println("You took "+numOfGuesses+" guesses");
			}
		}
	}
}
