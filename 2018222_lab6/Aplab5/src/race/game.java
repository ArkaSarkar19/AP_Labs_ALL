package race;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class game implements Serializable{
	private final int tiles ;
	private String name = null;
	private int danger_snake = 0;
	private int danger_cricket = 0;
	private int danger_vulture = 0;
	private int trampoline_advance = 0;
	private int n_s = 0;
	private int n_v = 0;
	private int n_c = 0;
	private int n_t = 0;
	private int s0 = 0;
	private int v0 = 0;
	private int c0 = 0;
	private int t0 = 0;
	private int rolls = 1;
	private tile track[];
	private int currpos = 1;
	private Player myplayer = null;
	private boolean checkpoint1,checkpoint2,checkpoint3 = false;
	public game(int n) {
		this.tiles = n;
		this.track = new tile[n];
	}
	public void setname(String name) {
		this.name = name;
	}
	public void setplayer(Player player) {
		myplayer=player;
	}
	public void setlayout() {		
		try {
		System.out.println(">>Setting up the race track...");
		Random r = new Random();
		trampoline_advance = r.nextInt(10) + 1;
		danger_snake = r.nextInt(10) + 1;
		danger_cricket = r.nextInt(10) + 1;
		danger_vulture = r.nextInt(10) + 1;
		for(int i=1;i<tiles-1;i++) {
			if(track[i-1] instanceof White) {
			int a = r.nextInt(10);
			if(a<3) {
				int k = r.nextInt(3) + 1;
				if(k==1) { track[i] = new Snake(danger_snake);
				n_s++;
				}
				else if(k==2) { track[i] = new Vulture(danger_vulture);
				n_v++;
				}
				else if(k==3) { track[i] = new Cricket(danger_cricket);
				n_c++;
				}
				else  ;
			}
			else if (a==6 || a == 4){
				track[i] = new Trampoline(trampoline_advance);
				n_t++;
			}
			
			else track[i] = new White();
			}
			
			else {
				int a = r.nextInt(10);

				if(a<2) {
					int k = r.nextInt(5) + 1;
					if(k==1) { track[i] = new Snake(danger_snake);
					n_s++;
					}
					else if(k==4) { track[i] = new Vulture(danger_vulture);
					n_v++;
					}
					else if(k==3) { track[i] = new Cricket(danger_cricket);
					n_c++;
					}
					else  ;
				}
				else if (a==6 || a == 4){
					track[i] = new Trampoline(trampoline_advance);
					n_t++;
				}
				
				else track[i] = new White();
				
			}
		}
		track[0] = new White();
		track[tiles-1] = new GameWinner();
		System.out.println(">>Danger: There are " +n_s + ","+ n_c + "," + n_v + " numbers of Snakes, Cricket, and Vultures respectively on your track!");
		System.out.println(">>Danger: Each Snake, Cricket, and Vultures can throw you back by " + danger_snake + "," + danger_cricket + "," +  danger_vulture + " number of Tiles respectively!");
		System.out.println(">>Good News: There are " + n_t + " number of Trampolines on your track!");
		System.out.println(">>Good News: Each Trampoline can help you advance by  " + trampoline_advance  + " number of Tiles");
		}
		catch (Exception e) {
			throw new InputMismatchException() ;
		}
		
	}
	
	public void playgame() throws MyException,SaveGameException {
		System.out.println(">>Game Started ================================>");
		rolls = 0;
		dice d = new dice();
		s0=v0=c0=t0=0;
		while(true) {
		try {
				int k = d.rolldice();
				System.out.print(">>[Roll-" + rolls++ + "]: " + name +" rolled "+ k +" at Tile-" + currpos + ",");
				if(currpos==1 && k!=6) {
					System.out.println("OOPs you need 6 to start");
				}
				else if(currpos == 1 && k==6) {			
					System.out.println("You are out of the cage! You get a free roll");
					currpos++;
				}
				else if(currpos < tiles && currpos + k>tiles) {
					System.out.println("landed on Tile-" +currpos);
				}

				else {
					currpos+=k;

					if(currpos >= tiles) currpos = tiles;
					System.out.println("landed on Tile-" + currpos + ".");
					System.out.println(">>    Trying to shake the Tile-"+currpos);
					track[currpos-1].shake();
					System.out.println(">>    I am a White tile!");
				}
				if(!checkpoint3 && currpos >= this.tiles*3/4 || !checkpoint2 && currpos>=this.tiles/2 || !checkpoint1 && currpos>=this.tiles/4   ) {
					System.out.println(">> " + currpos*100/tiles + "% of game completed. " );
					System.out.println(">> Press 1 to Save Progress and Exit");
					System.out.println(">> Press 2 to Continue Pushing Your Luck.");
					Scanner stdin = new Scanner(System.in);
					if(!checkpoint1) checkpoint1=true;
					else if(!checkpoint2) checkpoint2=true;
					else checkpoint3 = true;
					int k2 = stdin.nextInt();
					if(k2==1) {
						throw new SaveGameException();
					}
					else if(k2==2) {
						System.out.println(">>Contibuing");
					}
					else {};
				}
			
		}
		catch (NullPointerException e) {
			System.out.println(">>    I am a White tile!");
		}
		catch (GameWinnerException e) {
			throw e;
		}
		catch (MyException e) {
			currpos = e.move(currpos);
			System.out.println(e.getMessage());
			if(e instanceof SnakeBiteException ) s0 ++;
			if(e instanceof VultureBiteException ) v0 ++;
			if(e instanceof CricketBiteException ) c0 ++;
			if(e instanceof TrampolineException ) t0 ++;
		}
		finally {
			if(currpos<1) currpos = 1;
			System.out.println(">>    " + name + " moved to Tile-"+currpos);
		}
	}
	}
	public int getS0() {
		return s0;
	}
	public int getV0() {
		return v0;
	}
	public int getC0() {
		return c0;
	}
	public int getT0() {
		return t0;
	}
	public int getrolls() {
		return rolls;
	}
}

