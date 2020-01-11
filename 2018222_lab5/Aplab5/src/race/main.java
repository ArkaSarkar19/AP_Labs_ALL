package race;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws MyException {
		System.out.println(">>Enter total number of tiles on the race track (length)");
		boolean done = false;
		int l = 0;
		while(!done) {
			try {
				Scanner stdin = new Scanner(System.in);
				l = stdin.nextInt();
				game g = new game(l);				
				g.setlayout();
				System.out.println(">>Enter the Player Name");
				String name  = stdin.next();
				Player player = new Player(name,g);
				player.play();
				done = true;
				}
			catch (InputMismatchException e) {
				System.out.println("@@@@@@@@@ Some Error Occurred @@@@@@@@@@@");
				System.out.println("@@@@@@@@@ Try Again @@@@@@@@@@@@@@@@@@@@@");
			}
			finally {}
		}

		
	}

}
