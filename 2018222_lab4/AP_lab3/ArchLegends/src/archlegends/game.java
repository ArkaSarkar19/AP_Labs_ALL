package archlegends;
import java.util.Iterator;
import java.util.LinkedList;
final public class game {
	final private user thisuser;
	private Hero currhero ;
	private monster currmonster;
	private int currposition;
	private gamelayout layout;
	public game(user newuser) {
		this.thisuser = newuser;
		this.currposition = 0;
	}
	public void sethero(Hero hero) {
		this.currhero = hero;
	}
	public void newlayout() {
		this.layout = new gamelayout();
	}
	public boolean haswon() {
		return (currmonster instanceof Lionfang) && currmonster.isdead();
	}
	public LinkedList<node> getnext(){
		return layout.getnextpaths(currposition);
	}
	public int getcurrposition() {
		return this.currposition;
	}
	public void setcurrposition(int position) {
		this.currposition = position;
	}
	public monster startfight() {
		int monsterlevel = layout.getwhatmonsterlevel(currposition);
		System.out.println("You are fighting a level " + monsterlevel + "  Monster.");
		if(monsterlevel==1) {
			return new Goblin();
		}
		if(monsterlevel==2) {
			return new Zombie();
		}
		if(monsterlevel==3) {
			return new Fiend();
		}
		if(monsterlevel==4) {
			return new Lionfang();
		}
		return null;
	}
	
}

final class gamelayout{
	final private int start = 0;
	final private LinkedList<node> graph[] = new LinkedList[11];
	public gamelayout() {
		for(int i=0;i<11;i++) {
			graph[i] = new LinkedList<node>();
		}
		
		graph[start].add(new node(1,0,1));
		graph[start].add(new node(2,3,4));
		graph[start].add(new node(3,6,7));
		
		for(int i=1;i<8;i=i+3) {
			graph[i].add(new node(1,1,2));
			graph[i].add(new node(2,4,5));
			graph[i].add(new node(3,7,8));
			if(i==1)
			graph[i].add(new node(1,-1,i));
			else if(i==4)
				graph[i].add(new node(2,-1,i));
			else if(i==7)
				graph[i].add(new node(3,-1,i));
			else;
			
		}
		for(int i=2;i<9;i=i+3) {
			graph[i].add(new node(1,2,3));
			graph[i].add(new node(2,5,6));
			graph[i].add(new node(3,8,9));
			if(i==2)
				graph[i].add(new node(1,-1,i));
			else if(i==5)
				graph[i].add(new node(2,-1,i));
			else if(i==8)
				graph[i].add(new node(3,-1,i));
			else;
		
		}
		for(int i=3;i<10;i=i+3) {
			graph[i].add(new node(4,9,10));
			
			if(i==3)
				graph[i].add(new node(1,-1,i));
			else if(i==6)
				graph[i].add(new node(2,-1,i));
			else if(i==9)
				graph[i].add(new node(3,-1,i));
			else;
		}
	}
	public void printall(){
        for(int i=0;i<11;i++){
            LinkedList<node> curr = graph[i];
            Iterator it = curr.iterator();
            System.out.println("vertex " + i);
            while(it.hasNext()){
                node c = (node) it.next();
                System.out.print(c.getvertex()+ " ");

            }
           System.out.println(); 
        }
    }
	public LinkedList<node> getnextpaths(int vertex){
		return graph[vertex];
	}
	
	public int getwhatmonsterlevel(int vertex) {
		if(vertex==1 || vertex == 2 || vertex == 3) {
			return 1;
		}
		if(vertex==4 || vertex == 5 || vertex == 6) {
			return 2;
		}
		if(vertex==7 || vertex == 8 || vertex == 9) {
			return 3;
		}
		if(vertex==10) {
			return 4;
		}
		return -1;
	}
	
	
}

final class node{
	private int monsterlevel;
	private int location;
	private int vertex;
	public node(int monsterlevel, int location, int vertex) {
		this.monsterlevel = monsterlevel;
		this.location = location;
		this.vertex = vertex;
	}
	public int getmonsterlevel() {
		return this.monsterlevel;
	}
	public int getlocation() {
		return this.location;
	}
	public int getvertex(){
        return this.vertex;
    }
}