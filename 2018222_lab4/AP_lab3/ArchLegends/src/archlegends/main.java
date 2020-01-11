package archlegends;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
public class main {
	private static ArrayList<user> allusers = new ArrayList<user>();
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		while(true) {
			System.out.println(">>>>>>>>>    Welcome to ArchLegends   <<<<<<<<<<");
			System.out.println("Choose your option");
			System.out.println("1) New User");
			System.out.println("2) Existing User");
			System.out.println("3) Exit");
			int query = stdin.nextInt();
			switch(query) {
			case 1:
				System.out.println("Enter Username");
				String name1 = stdin.next();
				System.out.println("Choose Hero");
				System.out.println("1) Warrior");
				System.out.println("2) Thief");
				System.out.println("3) Mage");
				System.out.println("4) Healer");
				Hero newhero = null;
				String heroname = null;
				int q1 = stdin.nextInt();
				if(q1==1) {
					newhero = new Warrior();
					heroname = "Warrior";
				}
				if(q1==2) {
					newhero = new Thief();
					heroname = "Thief";

				}
				if(q1==3) {
					newhero = new Mage();
					heroname = "Mage";

				}
				if(q1==4) {
					newhero = new Healer();
					heroname = "Healer";

				}
				adduser(name1,newhero);
				
				break;
			case 2:
				System.out.println("Enter Username");
				String name2 = stdin.next();
				user curruser = searchuser(name2);
				
				System.out.println(">>>>>>>>>>>>>>>>>>> Welcome " + curruser.getusername() + " <<<<<<<<<<<<<<<<");
				game currgame = new game(curruser);
				curruser.newgame(currgame);
				while(!curruser.haswon()) {
					ArrayList<minion> clones = new ArrayList<minion>();
					sidekick currsidekick = null;
					boolean usesidekick = false;
					int currpos = currgame.getcurrposition();
					if(currpos==0) {
						System.out.println("You are at the starting location. Choose path:");
						LinkedList<node> nextpaths = currgame.getnext();
						for(int i=0;i<nextpaths.size();i++) {
							System.out.println(i+1 + ")" + "Go to Location "  + nextpaths.get(i).getlocation());
						}
						System.out.println("Enter -1 to exit");
						int pos2 = stdin.nextInt();
						if(pos2==-1) System.exit(0);
						currpos = nextpaths.get(pos2-1).getvertex();
						System.out.println("Moving to Location " + (currpos-1));
						currgame.setcurrposition(currpos);
					}
					else {
						System.out.println("You are at the starting " + currpos + ". Choose path:");
						LinkedList<node> nextpaths = currgame.getnext();
						for(int i=0;i<nextpaths.size();i++) {
							if(nextpaths.get(i).getlocation()!=-1)
							System.out.println(i+1 + ")" + "Go to Location "  + nextpaths.get(i).getlocation());
						}
						System.out.println("4) Go back");
						int pos2 = stdin.nextInt();
						if(pos2==4) System.out.println("Going back");
						else {
							currpos = nextpaths.get(pos2-1).getvertex();
							System.out.println("Moving to Location " + (currpos-1));
							currgame.setcurrposition(currpos);


						}
					}
					System.out.println("!!!!!!  Fight Started  !!!!!!");
					if(curruser.getmyhero().checksidekick()) {
					System.out.println("Type yes if you want to use your sidekick , else type no");
					String s = stdin.next();
					if(s.equals("yes")) {
						usesidekick = true;
						currsidekick = curruser.getmyhero().getsidekick();
						System.out.println("you have a sidekick " + currsidekick.gettype() + " with you ");
						if(currsidekick instanceof minion) {
							System.out.println("Press c to enable cloning ability. Else press f to move to fight");
							String s1 = stdin.next();
							if(s1.equals("c")) {
								currsidekick.specialmove();
								for(int i=0;i<3;i++) {
									clones.add(((minion) currsidekick).clone());
								}
							}
							else if(s1.equals("f")) System.out.println("Starting fight");
							else System.out.println("Invalid input ");
						}
					}
					if(s.equals("no")) System.out.println("Best of luck without a sidekick ");
					}

					monster currmonster = currgame.startfight();
					int flag = 0;
					while(!currmonster.isdead()) {
						if(currsidekick!=null && currsidekick instanceof knight && currmonster instanceof Zombie && currmonster.getHP()==currmonster.getmaxHP()) {
							curruser.getmyhero().setdefense(5);
							System.out.println("Defense increased by 5");
						}
						
						System.out.println("Choose move:");
						if(curruser.getmyhero().hasspecialmove()) {
							System.out.println("1) Attack");
							System.out.println("2) Defense");
							System.out.println("3) Special Move");
							
						}
						else {
							System.out.println("1) Attack");
							System.out.println("2) Defense");
						}
						int damage = 0;
						int in1 = stdin.nextInt();
						if(in1==1) {
							System.out.println("You choose to attack.");
							curruser.getmyhero().attack(currmonster);
							System.out.println("You attacked and inflicted "+  curruser.getmyhero().getattackvalues() +" damage to the monster.");
							if(currsidekick!=null && !currsidekick.isdead()  ) {
								currsidekick.attack(currmonster);
								if(currsidekick instanceof minion && clones.size()!=0 && !currsidekick.hascloned()) {
									for(int i=0;i<clones.size();i++) {
										if(!clones.get(i).equals(currsidekick))
										clones.get(i).attack(currmonster);
									}
								}
							}
							System.out.println("Your Hp: " +(Math.floor(curruser.getmyhero().gethp())) + "/" + curruser.getmyhero().getmaxhp() + "Monsters Hp: "  + Math.floor(currmonster.getHP())+"/"+currmonster.getmaxHP());
							System.out.println("Monster attack!");
							damage = curruser.getmyhero().attacked(currmonster);
							
						}
						if(in1==2) {
							System.out.println("You choose to defend.");
							System.out.println("Monster attack reduced by" +  curruser.getmyhero().getdefensevalue() + "!");
							System.out.println("Your Hp: " +(Math.floor(curruser.getmyhero().gethp())) + "/" + curruser.getmyhero().getmaxhp() + "Monsters Hp: "  + Math.floor(currmonster.getHP())+"/"+currmonster.getmaxHP());
							System.out.println("Monster attack!");
							damage = curruser.getmyhero().defense(currmonster);

						}
						if(in1==3) {
							System.out.println("Special power activated");
							System.out.println("Performing special attack");
							curruser.getmyhero().specialmove(currmonster);
						}
						
						if(curruser.getmyhero().isdead()) {
							System.out.println("You Lost");
							System.exit(0);
						}
						System.out.println("Monster attacked and inflicted " + damage + " damage to you");
						System.out.println("Your Hp: " +(Math.floor(curruser.getmyhero().gethp())) + "/" + curruser.getmyhero().getmaxhp() + "Monsters Hp: "  + Math.floor(currmonster.getHP())+"/"+currmonster.getmaxHP());
						if(currsidekick!=null  && !currsidekick.isdead() ) {
							currsidekick.attacked(damage);
							System.out.println("Sidekick Hp: " +(Math.floor(currsidekick.getHP())) + "/" + currsidekick.getmaxHP());

						}
						
						if(currsidekick!=null && !currsidekick.isdead() && currsidekick instanceof minion && clones.size()!=0 && !currsidekick.hascloned()) {
							for(int i=0;i<clones.size();i++) {
								clones.get(i).attacked(damage);
								System.out.println("Sidekick Hp: " +(Math.floor(clones.get(i).getHP())) + "/" + clones.get(i).getmaxHP());

							}
						}

						if(flag == 0 && currsidekick!=null && currsidekick.isdead()) {
							System.out.println("Sidekick DIED you are on your own now");
							if(currsidekick instanceof knight && currmonster instanceof Zombie) {
								curruser.getmyhero().setdefense(-5);
								System.out.println("Defense decreased by 5");

							}
							curruser.getmyhero().removesidekick(currsidekick);
							flag = 1;

						}
						
						if(currmonster.isdead()) {
							curruser.getmyhero().checkxp(currmonster);
							if(currsidekick!=null)currsidekick.levelup(currmonster.getlevel()*20);
							System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level");
							String in_2 = stdin.next();
							if(in_2.equals("yes")) {
								System.out.println("Your current XP is " + curruser.getmyhero().getXP());
								System.out.println("If you want to buy a minion press 1");
								System.out.println("If you want to buy a knight press 2");
								int s_1 = stdin.nextInt();
								sidekick add = null;
								Random r = new Random();
								int a = 0;
								if(s_1 == 1) {
									a = r.nextInt(7) + 5;
									System.out.println("XP to spend : " + a);
									add = new minion();
									
								}
								if(s_1 == 2) {
									a = r.nextInt(10) + 8;
									System.out.println("XP to spend : " + a);
									add = new knight();
								}
								curruser.getmyhero().addsidekick(add,a);
								
							}
							else if(in_2.equals("no")) {
								curruser.getmyhero().levelup();
								System.out.println("Level up to " + curruser.getmyhero().getlevel());
								
							}
							else {
								System.out.println("Invalid input ");
							}
							if(currsidekick instanceof minion && clones.size()!=0) currsidekick.setcloned(true);
							currsidekick = null;

							clones = new ArrayList<minion>();
							curruser.getmyhero().checkspecialmove();
							break;

						}
					}
						
					if(currmonster.isdead() && currmonster.getlevel()==4) {
						System.out.println("Congratulations !!!!!!!!!!!!!!!!!!!!!!!    You won the game ");
						System.out.println("Exiting");
						break;
					}
				}
				
				break;
			case 3:
				System.out.println("Exiting Game");
				System.out.println(">>>>>>>>>>>>  BYE  <<<<<<<<<<<<<");
				System.exit(0);
				
				break;
			}
			
		}
	}
	public static void adduser(String name1, Hero hero) {
		user newuser = new user(name1,hero);
		allusers.add(newuser);
		System.out.println("User Creation done. Username: " + name1 + ".Hero type: " + hero.gettype() +  ". Log in to play the game . Exiting");
	}
	public static user searchuser(String name2) {
		user curruser = null;
		for(int i=0;i<allusers.size();i++) {
			if(allusers.get(i).getusername().equals(name2)) {
				curruser = allusers.get(i);
				System.out.println("User Found... logging in");
				break;
			}
		}
		return curruser;
	}
}
