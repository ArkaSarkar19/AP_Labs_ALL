package archlegends;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class sidekick  {
	protected boolean hascloned;

	protected double HP;
	protected double XP;
	protected int attack;
	protected int maxHP;
	public sidekick(int attack) {
		this.attack = attack;
		this.HP = this.maxHP = 100;
		this.XP = 0;
	}
	public void levelup(int heroxp) {
		HP = maxHP;
		double myxp = heroxp/10;
		XP+=myxp;
		if(myxp>=5) {
			attack+=1;
			
		}
	}
	public int attack(monster monster) {
		monster.attacked(attack);
		System.out.println("Sidekick attack and inflicted " + attack + " damage to the monster");
		System.out.println("Sidekick Hp: " +(Math.floor(this.HP)) + "/" + this.maxHP );

		return attack;
	}
	public double attacked(double a) {
		HP-=1.5*a;
		return a;		
	}
	public boolean isdead() {
		return this.HP<=0;
	}
	public int getattack() {
		return this.attack;
	}
	public double getXP() {
		return this.XP;
	}
	public double getHP() {
		return this.HP;
	}
	public int getmaxHP() {
		return this.maxHP;
	}
	public boolean hascloned() {
		return this.hascloned;
	}
	
	public void setcloned(boolean s) {
		this.hascloned = s;
	}
	public abstract String gettype();
	public abstract int specialmove();	
}

class minion extends sidekick implements Cloneable{
	public minion() {
		super(1);
		hascloned = false;
	}

	@Override
	public int specialmove() {
		if(hascloned) {
			System.out.println("Cannot perform move already cloned in another level");

		return 0;
		}
		System.out.println("Cloning done");
		return 1;
	}
	public minion clone() {
		try {
			minion copy = (minion)super.clone();
			return copy;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}
	
	

	@Override
	public String gettype() {
		// TODO Auto-generated method stub
		return "minion";
	}
	
}

class knight extends sidekick{
	
	public knight() {
		super(2);
	}

	@Override
	public int specialmove() {
		return 5;
	}
	@Override
	public String gettype() {
		// TODO Auto-generated method stub
		return "knight";
	}
}

class xpcomparator implements Comparator<sidekick>{

	@Override
	public int compare(sidekick arg0, sidekick arg1) {
		return (int) (arg0.getXP()-arg1.getXP());
	}
	
}
