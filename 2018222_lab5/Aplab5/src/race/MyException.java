package race;

import java.util.Random;


public  class  MyException extends Exception{
	protected final int d;
	public MyException(String message, int d) {
		super(message);
		this.d = d;
	}
	public int move(int currpos) {
		if(this instanceof TrampolineException) currpos+=d;
		else currpos-=d;
		return currpos;
	}
	
	
}
class SnakeBiteException extends MyException{
	public SnakeBiteException(String message,int d) {
		super(message,d);
	}
}

class VultureBiteException extends MyException{
	public VultureBiteException(String message,int d) {
		super(message,d);
	}
}
class CricketBiteException extends MyException{
	public CricketBiteException(String message,int d) {
		super(message,d);
	}
}
class TrampolineException extends MyException{
	public TrampolineException(String message,int d) {
		super(message,d);
	}
}

class GameWinnerException extends MyException{
	public GameWinnerException(String message) {
		super(message,-1);
	}
}