package race;

import java.io.Serializable;

public abstract class tile implements Serializable{
	protected final String type;
	public tile(String t) {
		this.type = t;
	}
	public abstract void shake() throws MyException;
}

class White extends tile{

	public White() {
		super("white");
	}

	@Override
	public void shake() {
		return;
	}	
}

class Snake extends tile{
	private final int damage;
	public Snake(int d) {
		super("Snake");
		this.damage = d;
	}

	@Override
	public void shake() throws SnakeBiteException{
		throw new SnakeBiteException(">>    Hiss...! I am a Snake, you go back " + damage + " tiles!",damage);
	}
	public int getdamage() {
		return this.damage;
	}
}

class Vulture extends tile{
	private final int damage;

	public Vulture(int d) {
		super("Vulture");
		this.damage = d;
	}

	@Override
	public void shake() throws VultureBiteException {
		throw new VultureBiteException(">>    Yapping...! I am a Vulture, you go back "+ damage + " tiles!",damage);
	}
	public int getdamage() {
		return this.damage;
	}
	
}

class Cricket extends tile{
	private final int damage;

	public Cricket(int d) {
		super("Cricket");
		this.damage = d;
	}

	@Override
	public void shake() throws CricketBiteException {
		throw new CricketBiteException(">>    Chirp...! I am a Cricket, you go back " + damage +" tiles!",damage);
	}
	public int getdamage() {
		return this.damage;
	}
	
}

class Trampoline extends tile{
	private final int advance;

	public Trampoline(int a) {
		super("Trampoline");
		this.advance = a;
	}

	@Override
	public void shake() throws TrampolineException {
		throw new TrampolineException(">>    PingPong! I am a Trampoline, you advance "+advance + " tiles",advance);
	}
	public int getadvance() {
		return this.advance;
	}
	
}
class GameWinner extends tile{
	public GameWinner() {
		super("Game Winner");
	}
	
	@Override
	public void shake() throws GameWinnerException {
		throw new GameWinnerException("Game Won");
	}
}
