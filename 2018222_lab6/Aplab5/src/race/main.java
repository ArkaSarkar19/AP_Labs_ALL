package race;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws MyException, ClassNotFoundException, IOException, SaveGameException {
		Scanner stdin = new Scanner(System.in);
		String name = null;
		boolean done = false;
		int l = 0;
		while(!done) {
			try {
				System.out.println(">>Enter the Player Name");
				name  = stdin.next();
				Player player =  deserialize(name);
				player.play();
				done = true;
				}
			catch (InputMismatchException e) {
				System.out.println("@@@@@@@@@ Some Error Occurred @@@@@@@@@@@");
				System.out.println("@@@@@@@@@ Try Again @@@@@@@@@@@@@@@@@@@@@");
			}
			catch (FileNotFoundException e) {
				System.out.println(">>Enter total number of tiles on the race track (length)");
				l = stdin.nextInt();
				game g = new game(l);				
				g.setlayout();
				Player player = new Player(name,g);
				player.play();	
				done = true;

			}
			finally {}
		}

		
	}
	public static Player deserialize(String name) throws IOException,ClassNotFoundException,FileNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(name + ".txt"));
			Player p = (Player)in.readObject();
			return p;
		}
//		catch (NullPointerException e) {
//			throw new FileNotFoundException();
//		}
		finally {
			if(in!=null)in.close();
		}
	}

}
