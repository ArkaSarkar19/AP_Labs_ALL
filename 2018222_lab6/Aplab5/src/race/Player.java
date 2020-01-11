package race;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable{
	private game game;
	private final String name;
	public Player(String name, game game) {
		this.name = name;
		this.game = game;
	}
	public void play() throws MyException, SaveGameException, IOException {
		try {
			game.setname(name);
			System.out.println(">>Starting the game with " + name);
			System.out.println(">>Control transferred to Computer for rolling the Dice for "+name);
			Scanner s = new Scanner(System.in);
			System.out.println(">>Hit enter to start the game");
			String ll = s.nextLine();
			game.playgame();
		}
		catch (GameWinnerException e) {
			System.out.println(">>        " + name +" wins the race in " + game.getrolls() + " rolls!");
			System.out.println(">>        Total Snake Bites = " + game.getS0());
			System.out.println(">>        Total Vulture Bites = " + game.getV0());
			System.out.println(">>        Total Cricket Bites = " + game.getC0());
			System.out.println(">>        Total Trampoline = " + game.getT0());
		}
		catch (SaveGameException e) {
			e.save(this);
		}
	}
	public String getname() {
		return this.name;
	}
}
 class dice {
	private final int faces = 6;
	public int rolldice() {
		Random r = new Random();
		return r.nextInt(faces) + 1;
		}
}