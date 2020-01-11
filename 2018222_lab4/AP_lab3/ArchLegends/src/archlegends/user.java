package archlegends;

final public class user {
	final private String username;
	private Hero myhero;
	private game mygame;
	private boolean haswon;
	public user(String username, Hero myhero) {
		this.username = username;
		this.myhero = myhero;
		this.haswon = false;
	}
	public String getusername() {
		return this.username;
	}
	public Hero getmyhero() {
		return this.myhero;
	}
	public void newgame(game game) {
		this.mygame = game;
		game.newlayout();
	}
	public boolean haswon() {
		return mygame.haswon();
	}
	
}
