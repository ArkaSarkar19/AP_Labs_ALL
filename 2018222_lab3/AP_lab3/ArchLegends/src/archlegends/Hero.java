package archlegends;

public abstract class Hero {
	protected int maxHP;
	protected double HP;
	protected int XP;	
	protected int attack;
	protected int defense;
	protected int moves;
	protected int specialmove;
	protected boolean hasspecialpower;
	protected int level;
	
	public Hero(int attack,int defense) {
		this.HP = this.maxHP = 100;
		this.XP = 0;
		this.attack = attack;
		this.defense = defense;
		moves=0;
		specialmove = 0;
		hasspecialpower = false;
		level = 1;
	}

	protected void levelup() {
		if(XP>=120) {
			this.attack+=1;
			this.defense+=1;
			this.maxHP = 250;
			level = 4;
			System.out.println("!!!!!!!!  Level Up to Level 4 !!!!!!!!");
			System.out.println("60 XP added, Health Restored");
		}
		else if(XP>=60) {
			this.attack+=1;
			this.defense+=1;
			this.maxHP = 200;
			level = 3;
			System.out.println("!!!!!!!!  Level Up to Level 3 !!!!!!!!");
			System.out.println("40 XP added, Health Restored");
		}
		else if(XP>=20) {
			this.attack+=1;
			this.defense+=1;
			level = 2;
			System.out.println("!!!!!!!!  Level Up to Level 2 !!!!!!!!");
			System.out.println("20 XP added, Health Restored");
		}
		else {System.out.println("0 XP added , Health Restored");}
		this.HP = this.maxHP;

	}
	public int attacked(monster monster) {
		moves++;
		int a = monster.attack();
		this.checkspecialmove();
		if(monster.attack()==-1) {
			this.lionfangattack();
			return (int) this.HP;
		}
		else {
			this.HP-= a;
			return a;

		}
		
		
	}
	
	protected boolean checkspecialmove() {
		return (this.moves - this.specialmove) >2;
	}
	protected void lionfangattack() {
		
		this.HP = 0.5*this.HP;
		
	}
	protected void killedmonster(monster monster) {
		this.XP  += monster.getlevel()*20;
		this.HP = this.maxHP;
		this.levelup();
	}
	protected int getlevel() {
		return this.level;
	}
	public boolean hasspecialmove() {
		return this.checkspecialmove();
	}
	public int getattackvalues() {
		return this.attack;
	}
	public double gethp() {
		return this.HP;
	}
	public int getmaxhp() {
		return this.maxHP;
	}
	public boolean isdead() {
		return this.HP<=0;
	}
	public int getdefensevalue() {
		return this.defense;
	}
	public void checklevel(monster monster) {
		this.killedmonster(monster);
	}
	public abstract void specialmove(monster monster);
	public abstract int attack(monster monster);
	public abstract int defense(monster monster);
	public abstract String gettype();
}

final class Warrior extends Hero{
	final private int specialpower = 5;
	public Warrior() {
		super(10,3);
	}
	
	@Override
	public int attack(monster monster) {
		int ans = (this.hasspecialpower)? this.attack+specialpower : this.attack;
		this.moves++;
		this.checkspecialmove();
		monster.attacked(ans);

		return ans;
	}
	
	@Override
	public int defense(monster monster) {
		int a = (int)(monster.attack() + ((this.hasspecialpower)? defense+specialpower : defense));
		HP-=a ;
		this.moves++;
		this.checkspecialmove();
		return a;
		
	}
	
	@Override
	public void specialmove(monster monster) {
		this.hasspecialpower = true;
		this.specialmove = this.moves;
		monster.specialmove(this);
	}
	public String gettype() {
		return "Warrior";
	}
}


final class Mage extends Hero{

	public Mage() {
		super(5, 5);
	}
	@Override
	public void specialmove(monster monster) {
		this.hasspecialpower = false;
		this.specialmove = this.moves;
		monster.specialmove(this);

	}
	public String gettype() {
		return "Mage";
	}
	@Override
	public int defense(monster monster) {
		moves++;
		int a = monster.attack() - this.defense;
		if(a<0) a = 0;
		this.checkspecialmove();
		if(monster.attack()==-1) {
			this.lionfangattack();
			return (int) this.HP;
		}
		else {
			this.HP-= a;
			return a;

		}	
	}
	@Override
	public int attack(monster monster) {
		moves++;
		this.checkspecialmove();
		monster.attacked(attack);
		return this.attack;
	}
	
}

final class Thief extends Hero{

	public Thief() {
		super(6, 4);
	}
	@Override
	public void specialmove(monster monster) {
		this.hasspecialpower = false;
		this.specialmove = this.moves;
		this.HP+=monster.getHP()*0.3;
		if(this.HP>this.maxHP) this.HP = this.maxHP;
		monster.specialmove(this);

	}
	public String gettype() {
		return "Thief";
	}
	@Override
	public int defense(monster monster) {
		moves++;
		int a = monster.attack() - this.defense;
		if(a<0) a = 0;
		this.checkspecialmove();
		if(monster.attack()==-1) {
			this.lionfangattack();
			return (int) this.HP;
		}
		else {
			this.HP-= a;
			return a;

		}	
	}
	@Override
	public int attack(monster monster) {
		moves++;
		this.checkspecialmove();
		monster.attacked(attack);
		return this.attack;
	}
}

final class Healer extends Hero{

	public Healer() {
		super(4, 8);
	}
	
	@Override
	public void specialmove(monster monster) {
		this.hasspecialpower = false;
		this.specialmove = this.moves;
		System.out.println("Heal 5% for every 3 moves");
		monster.specialmove(this);
	}
	
	private void heal() {
		if(this.moves <= this.specialmove+3) {
			this.HP = 1.05*this.HP;
		}
		
	}
	@Override
	public int attack(monster monster) {
		this.heal();
		return this.attack(monster);

	}
	@Override
	public int defense(monster monster) {
		int a = this.defense1(monster);
		this.heal();
		return a;
	}
	public String gettype() {
		return "Healer";
	}
	private int defense1(monster monster) {
		moves++;
		int a = monster.attack() - this.defense;
		if(a<0) a = 0;
		this.checkspecialmove();
		if(monster.attack()==-1) {
			this.lionfangattack();
			return (int) this.HP;
		}
		else {
			this.HP-= a;
			return a;

		}
		
		
	}
}