/*
Name : Arka Sarkar
Roll number : 2018222;
AP Labassignment 2*/


package myproj;
import java.util.*;
public class mercury {
	public static ArrayList<merchant> allmerchants = new ArrayList<merchant>();
	public static ArrayList<customer> allcustomers = new ArrayList<customer>();
	public static ArrayList<member> allmembers = new ArrayList<member>();
	public static int id = 1;
	public static ArrayList<super_category> allitems = new ArrayList<super_category>();
	public static int accountbalance = 0;
	
	public static void main(String[] args) {
	Scanner stdin = new Scanner(System.in);

	member jack = new merchant("jack", allmembers, allitems,1);
	member john = new merchant("john", allmembers, allitems,2);
	member james =new merchant("james", allmembers, allitems,3);
	member jeff = new merchant("jeff", allmembers, allitems,4);
	member joseph = new merchant("joseph", allmembers, allitems,5);
	allmembers.add(jack);
	allmembers.add(john);
	allmembers.add(james);
	allmembers.add(jeff);
	allmembers.add(joseph);

	member ali = new customer("ali",allmembers, allitems,6);
	member nobby = new customer("nobby",allmembers, allitems,7);
	member bruno = new customer("bruno",allmembers, allitems,8);
	member borat = new customer("borat",allmembers, allitems,9);
	member aladeen = new customer("aladeen",allmembers, allitems,10);
	allmembers.add(ali);
	allmembers.add(nobby);
	allmembers.add(bruno);
	allmembers.add(borat);
	allmembers.add(aladeen);
	
	while(true) {
	
	
	
	
	
	while(true) {
		System.out.println("Welcome to Mercury");
		System.out.println("1) Enter as Merchant");
		System.out.println("2) Enter as Customer");
		System.out.println("3) See user details");
		System.out.println("4) Company account balance");
		System.out.println("5) Exit");
		
		int query = stdin.nextInt();
		if(query == 5) break;
		switch(query) {
		case 1:
			System.out.println("Choose Merchant");
			for(int i=0;i<allmembers.size();i++) {
				if(allmembers.get(i) instanceof merchant) {
					merchant m = (merchant)allmembers.get(i);
					System.out.println(i + 1 + " " + m.getname());
				}
				
			}
			int m = stdin.nextInt();
			
			while(true) {
				merchant mer = (merchant)allmembers.get(m-1);
				System.out.println("Welcome " + mer.getname());
				System.out.println("Merchant Menu");
				System.out.println("1) Add item");
				System.out.println("2) Edit item");
				System.out.println("3) Search by category");
				System.out.println("4) Add offer");
				System.out.println("5) Rewards won");
				System.out.println("6) Exit");
				int q = stdin.nextInt();
				if(q==6) break;

				switch(q) {
				case 1:
					System.out.println("Enter Item Details");
					System.out.println("Item Name:");
					String name = stdin.next();
					System.out.println("Item Price: ");
					float price = stdin.nextFloat();
					System.out.println("Item Quantity: ");
					int quantity = stdin.nextInt();
					System.out.println("Item Category: ");
					String category = stdin.next();
					item e = new item(id, name,price,quantity);
					mer.additem(category, e);
					
					id++;
					e.display();
					break;
				case 2:
					System.out.println("Choose item by code ");
					mer.displayitems();
					int code_edit = stdin.nextInt();
					System.out.println("Enter edit details");
					System.out.println("Item price: ");
					float price_edit = stdin.nextInt();
					System.out.println("Item quantity: ");
					int quantity_edit = stdin.nextInt();
					mer.edititem(code_edit, price_edit, quantity_edit);
					break;
				case 3:
					System.out.println("Choose a category");
					for(int i=0;i<allitems.size();i++) {
						System.out.println(i+1 + " " + allitems.get(i).getcategory());
					}
					int index_c = stdin.nextInt();
					allitems.get(index_c-1).display();
					
					break;				
					
				case 4:
					System.out.println("Choose item by code");
					mer.displayitems();
					int offer_id = stdin.nextInt();
					item toadd = mer.getitem(offer_id);
					if(toadd.getquantity()<2) {
						System.out.println("Choose offer");
						System.out.println("1) 25% off");
						int offer_in = stdin.nextInt();
						offer newoffer;
						if(offer_in==1) {
							newoffer = new offer(2);
							toadd.setoffer(newoffer);
							toadd.display();
						}
						else System.err.println("Enter a valid offer");
					}
					else {
					System.out.println("Choose offer");
					System.out.println("1) buy one get one");
					System.out.println("2) 25% off");
					int offer_in = stdin.nextInt();
					offer newoffer;
					if(offer_in<=2) {
						newoffer = new offer(offer_in);
						toadd.setoffer(newoffer);
						toadd.display();
						
					}
					else System.err.println("Enter a valid offer");
					}
					break;
				case 5:
					System.out.print("Total rewards " + mer.getreward());
					break;
				
				}
				
			}
			break;
			
		case 2:
			System.out.println("Choose Customer");
			for(int i=0;i<allmembers.size();i++) {
				if(allmembers.get(i) instanceof customer) {
					customer c = (customer)allmembers.get(i);
					System.out.println(i - 4 + " " + c.getname());
				}
				
			}
			int c = stdin.nextInt();
			
			while(true) {
				customer cus = (customer)allmembers.get(c+4);
				System.out.println("Welcome " + cus.getname());
				System.out.println("Customer Menu");
				System.out.println("1) Search item");
				System.out.println("2) Checkout cart");
				System.out.println("3) Reward won");
				System.out.println("4) print latest orders");
				System.out.println("5) Exit");
				int q_2 = stdin.nextInt();
				if(q_2==5) break;
				switch(q_2) {
				case 1:
					System.out.println("Choose a category");
					for(int i=0;i<allitems.size();i++) {
						System.out.println(i+1 + " " + allitems.get(i).getcategory());
					}
					int s_c = stdin.nextInt();
					System.out.println("Choose item my code");
					allitems.get(s_c-1).display();
					System.out.println("Enter item code");
					int code_2  = stdin.nextInt();
					System.out.println("Enter item quantity");
					int quan_2 = stdin.nextInt();
					merchant mer_2 = allitems.get(s_c-1).getmerchant(code_2);
					cus.setquantity(quan_2);
					item ite_2 = mer_2.getitem(code_2);
					cus.setlatestitem(ite_2);
					System.out.println("Choose method of transaction");
					System.out.println("1) Buy item");
					System.out.println("2) Add item to cart");
					System.out.println("3) Exit");
					int in_2 = stdin.nextInt();
					cus.additem(String.valueOf(in_2), ite_2);
					accountbalance+=cus.getfees(ite_2, quan_2) + ite_2.getmerchant().getfees(ite_2, quan_2);
					break;
				
				case 2:
					accountbalance+=cus.checkoutcart();
					break;
				case 3:
					System.out.println(cus.getreward());
					break;
				case 4:
					cus.displayrecents();
					break;
					
				}
				break;
			}
			break;
			
			
		case 3:
			System.out.println("All members id , name , type");
			for(int i=0;i<allmembers.size();i++) {
				System.out.println(allmembers.get(i).getid()+ " " +  allmembers.get(i).getname() + " " + allmembers.get(i).gettype());
			}
			System.out.println("Enter M for merchant or Enter C for customer");
			String q_3 = stdin.next();
			System.out.println("Enter ID");
			int id = stdin.nextInt();
			for(int i=0;i<allmembers.size();i++) {
				if(allmembers.get(i).getid() == id) allmembers.get(i).getdetails();
			}
			System.out.println();
			break;
		case 4:
			System.out.println("Company account balance : " + accountbalance);
			break;
		case 5:
			break;
			
		}

	}
	break;
	}
}
}

class super_category{
	private String category;
	private ArrayList<item> items;
	public super_category() {
		items = new ArrayList<item>();
	}
	
	public String getcategory() {
		return this.category;
	}
	public void additem(item e) {
		 items.add(e);
	}
	public void setcategory(String c) {
		this.category = c;
	}
	public void display() {
		System.out.println();
		for(int i=0;i<items.size();i++) {
			System.out.print(items.get(i).getid() + " " + items.get(i).getname() + " " + items.get(i).getprice() + " " + items.get(i).getquantity() + " ");
			if(items.get(i).hasoffer()) System.out.print(items.get(i).getoffer() + " ");
			else System.out.print("None ");
			System.out.print(category + " from merchant " + items.get(i).getmerchant().getname());
			System.out.println();
			}
		System.out.println();
	}
	public merchant getmerchant(int code) {
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getid() == code) return items.get(i).getmerchant();
				
		}
		return null;
	}
}

class item {
	private int id;
	private String name;
	private float price;
	private int quantity;
	private category category;
	private offer offer = null ;
	private boolean hasoffer = false;
	private merchant mymerchant = null;

	public item(int id, String name, float price, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		
	}
			
	public void display() {
		System.out.print(id + " " + name + " " + price + " " + quantity + " ");
		if(offer !=null) System.out.print(offer.getoffer() + " ");
		else System.out.print("None ");
		System.out.print(category.getcategory().getname());
		System.out.println();
	}
	public int getid() {
		return id;
	}
	public float getprice() {
		return price;
	}
	public int getquantity() {
		return quantity;
	}
	public String getcategory() {
		return category.getname();
	}
	public int getoffer() {
		if(offer == null) return 0;
		else return offer.getofferid();
	}
	public void setprice(float price) {
		this.price = price;
	}
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	public void setcategory(category c) {
		this.category = c;
	}
	public void setoffer(offer e) {
		offer = e;
		hasoffer = true;
	}
	public boolean hasoffer() {
		return this.hasoffer;
	}
	public merchant getmerchant() {
		return mymerchant;
	}
	public void setmerchant(merchant c) {
		this.mymerchant = c;
	}
	
	
	public String getname() {
		return this.name;
	}
}

class offer{
	private boolean hasoffer;
	private String offer = null;
	private int offerid;
	public offer(int i) {
		//if i = 1 then buy one get one 
		//if i = 2 then 25% off
		if(i == 1) {
			offer = "Buy one get one free";
			hasoffer = true;
			offerid = 1;
		}
		if(i == 2) {
			offer = "25% off";
			hasoffer = true;
			offerid = 2;
		}
		
	}
	
	public boolean hasoffer() {
		return this.hasoffer;
	}
	
	public offer getoffer() {
		return this;
	}
	public int getofferid() {
		return this.offerid;
	}
	
}

class merchant implements member{
	private int rewardslots = 0;
	private String name;
	private ArrayList<category> categories;
	private double money;
	private ArrayList<member> allmembers;
	private ArrayList<super_category> allitems;
	private double totalcontribution;
	private int id;
	public merchant(String name, ArrayList<member> allmembers, ArrayList<super_category> allitems,int id ) {
		this.name = name;
		categories = new ArrayList<category>();
		this.money = 0;
		this.allmembers = allmembers;
		this.allitems = allitems;
		this.id = id;
	}
	
	@Override
	public String getname() {
		return this.name;
	}
	
	@Override
	public String gettype() {
		return "merchant";
	}
	private void add(String usr_category, item e) {
		boolean newcategory = true;
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).getcategory().getname().equals(usr_category)) {
				categories.get(i).additem(e);
				
				for(int j=0;j<allitems.size();j++) {
					if(allitems.get(j).getcategory().equals(categories.get(i).getcategory().getname())) {
						allitems.get(j).additem(e);
					}
				}
				newcategory = false;
				break;
			}
		}
		if(newcategory) {
			category addcategory = new category(usr_category);
			addcategory.additem(e);
			categories.add(addcategory);
			super_category s_c = new super_category();
			s_c.additem(e);
			s_c.setcategory(usr_category);
			
			boolean ans = false;
			for(int j=0;j<allitems.size();j++) {
				if(allitems.get(j).getcategory().equals(usr_category)) {
					allitems.get(j).additem(e);
					ans = true;
				}
			}
			
			if(!ans) allitems.add(s_c);

		}
		
	}
	private void seachindex(int index) {
		categories.get(index).display();
	}
	
	public void edititem(int code, float price, int quantity) {
		boolean ans = false;
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).edititem(code, price, quantity)) {
				ans = true;
				break;
			}
		}
		if(!ans) System.err.println("Enter a valid code");;
	}

	
	
	@Override
	public void additem(String category, item e) {
		e.setmerchant(this);
		this.add(category,e);
		
	}

	@Override
	public void search(int category) {
		this.seachindex(category - 1);
	}

	@Override
	public float getreward() {
		System.out.println();
		return (this.rewardslots);
		
	}
	
	public void displayitems() {
		System.out.println();
		for(int i=0;i<categories.size();i++) {
			categories.get(i).display();
		}
		System.out.println();
	}
	public void displayitems(int category) {
		categories.get(category).display();
	}
	public void displaycategory() {
		for(int i=0;i<categories.size();i++) {
			System.out.println(i+1 + ")" + " " + categories.get(i).getcategory());
		}
	}
	public void addoffer(int id, offer e) {
		boolean ans = false;
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).addoffer(id, e)) {
				ans = true;
				break;
			}
		}
		if(!ans) System.err.println("Enter a valid code");;
	}
	public item getitem(int id) {
		for(int i=0;i<categories.size();i++) {
			item k = categories.get(i).getitem(id);
			if(k!=null) return k;
		}
		return null;
	}
	public void addmoney(double newmoney) {
		this.money += newmoney;
	}
	public double getmoney() {
		return money;
	}

	@Override
	public void getdetails() {
		System.out.println("Name : " + this.name);
		System.out.println("ID : " + this.id);
		System.out.println("Total contribution : " + this.totalcontribution);
	}
	
	@Override
	public int getid() {
		return this.id;
	}
	
	public double getfees(item e, int quan) {
		this.totalcontribution+= e.getprice()*quan*0.005;
		this.money -= e.getprice()*quan*0.005;
		if(this.totalcontribution%100 == 0) this.rewardslots++;
		return e.getprice()*quan*0.005;
	}
	
}

class customer implements member{
	private String name;
	private LinkedList<myitem> mycart;
	private double mymoney;
	private int reward;
	private myitem[] recentorders;
	private ArrayList<member> allmembers;
	private ArrayList<super_category> allitems;
	private item latestitemselected;
	private int latestquantity;
	private int purchasenumber;
	private int id;
	
	public customer(String name, ArrayList<member> allmembers, ArrayList<super_category> allitems,int id) {
		this.name = name;
		mycart = new LinkedList<myitem>();
		mymoney = 100;
		reward = 0;
		recentorders = new myitem[10];
		this.allmembers = allmembers;
		this.allitems = allitems;
		this.latestitemselected = null;
		this.purchasenumber = 0;
		this.latestquantity = 0;
		this.id = id;
	}
	public double getfees(item e, int quan) {
		return e.getprice()*quan*0.005;
	}
	@Override
	public int getid() {
		return this.id;
	}
	@Override
	public void additem(String category, item e) {
		/*
		The customer has to choose a method of transaction: 1. a choice of buying an item, 2.
		adding it to cart, 3. exiting the current option.
		*/
		myitem add = new myitem(e,latestquantity);
		if(category.equals("1")) {
			int redquan = e.getquantity() - this.latestquantity;
			if(mymoney - e.getprice()*1.005< 0) { System.err.println("Out of money");
			}			
			else {
				e.setquantity(redquan);;
				this.addtorecents(add);
				reward = 0;
				int offertype = add.getparent().getoffer();
				if(offertype == 1) {
					mymoney =  (mymoney + reward- e.getprice()*add.getmyquantity()*1.005);
					e.setquantity(e.getquantity() - add.getmyquantity());
					add.setmyquantity(add.getmyquantity()*2);
					add.getparent().getmerchant().addmoney(add.getparent().getprice());
					add.setmoney(e.getprice()*add.getmyquantity());

				}
				if(offertype == 2) {
					mymoney =  (mymoney + reward- e.getprice()*0.75*add.getmyquantity()*1.005);
					add.getparent().getmerchant().addmoney(add.getparent().getprice()*0.75);
					add.setmoney(e.getprice()*add.getmyquantity()*0.75);


				}
				else {
					mymoney =  (mymoney + reward- e.getprice()*add.getmyquantity()*1.005);
					add.getparent().getmerchant().addmoney(add.getparent().getprice());
					add.setmoney(e.getprice()*add.getmyquantity());

				}
				this.purchasenumber++;
				if(this.purchasenumber%5==0) reward+=10;

				
				System.out.println("Item successfully bought");
			}
		}
		
		if(category.equals("2")) {	
			mycart.addLast(add);
			System.out.println("Item successfully added to cart");
		}
		if(category.equals("3")) {
			add = null;
			System.out.println("Successfully exited");
		}
		
		
	}

	@Override
	public void search(int category) {
		//category here is unique item id
		boolean ans = false;
		for(int i=0;i<allmembers.size();i++) {
			
			if(allmembers.get(i) instanceof merchant) {
				merchant mer = (merchant)allmembers.get(i);
				item k = mer.getitem(category);
				if(k!=null) {
					this.latestitemselected = k;
					ans = true;
					break;
				}
		
				if(!ans) System.err.println("Enter a vaid ID");
			}
		}
		
	}

	@Override
	public float getreward() {
		System.out.println();
		return (this.reward);
	}
	public void displayallitems() {
		for(int i=0;i<allmembers.size();i++) {
	
		if(allmembers.get(i) instanceof merchant) {
			merchant mer = (merchant)allmembers.get(i);
			mer.displayitems();
			}
		}
	}
	public void setquantity(int k) {
		this.latestquantity = k;
	}
	public int getlatestqun() {
		return this.latestquantity;
	}
	public void setlatestitem(item e) {
		this.latestitemselected = e;
	}
	private void addtorecents(myitem e) {
		int i;
		for( i=0;i<10;i++) {
			if(recentorders[i] == null) {
				recentorders[i] = e;
				break;
			}
		}
		if(i==9) {
			for(int j=1;j<10;j++) {
				recentorders[j-1] = recentorders[j]; 
			}
			recentorders[9] = e;
		}
	}
	public double	checkoutcart() {
		double totalfees = 0;
		if(mycart.size()==0) System.err.println("Cart is empty");
		else {
		
		while(mycart.size()!=0) {
			myitem curr = mycart.getFirst();
			if(curr==null) break;
			else {
				if(curr.getparent().getquantity() < curr.getmyquantity()) {
					System.err.println("Out of Stock");
					break;
				}
				if(mymoney < curr.getparent().getprice()*curr.getmyquantity()*1.005) {
					System.err.println("Out of Money");
					break;
				}
				else {
					int offertype = curr.getparent().getoffer();
					if(offertype == 1) {
						curr.getparent().setquantity(curr.getparent().getquantity() - curr.getmyquantity()*2);
						mymoney = mymoney + reward - curr.getparent().getprice()*curr.getmyquantity()*1.005;
						curr.setmyquantity(curr.getmyquantity()*2);
						totalfees+=curr.getparent().getmerchant().getfees(curr.getparent(), curr.getmyquantity()/2) + curr.getparent().getprice()*curr.getmyquantity()/2*0.005;
						curr.getparent().getmerchant().addmoney(curr.getparent().getprice()*curr.getmyquantity());
						curr.setmoney(curr.getparent().getprice()*curr.getmyquantity()/2);


					}
					if(offertype == 2) {
						mymoney =  (mymoney + reward- curr.getparent().getprice()*0.75*curr.getmyquantity()*1.005);
						curr.getparent().setquantity(curr.getparent().getquantity() - curr.getmyquantity());
						totalfees+=curr.getparent().getmerchant().getfees(curr.getparent(), curr.getmyquantity()) + curr.getparent().getprice()*0.75*curr.getmyquantity()*0.005;
						curr.getparent().getmerchant().addmoney(curr.getparent().getprice()*0.75*curr.getmyquantity());
						curr.setmoney(curr.getparent().getprice()*curr.getmyquantity()*0.75);

					}
					else {
						mymoney =  (mymoney + reward- curr.getparent().getprice()*curr.getmyquantity()*1.005);
						curr.getparent().setquantity(curr.getparent().getquantity() - curr.getmyquantity());
						totalfees+=curr.getparent().getmerchant().getfees(curr.getparent(), curr.getmyquantity()) + curr.getparent().getprice()*curr.getmyquantity()*0.005;
						curr.getparent().getmerchant().addmoney(curr.getparent().getprice()*curr.getmyquantity());
						curr.setmoney(curr.getparent().getprice()*curr.getmyquantity());


					}

					reward = 0;
					this.addtorecents(curr);
					mycart.removeFirst();
					this.purchasenumber++;
					if(this.purchasenumber%5==0) reward+=10;
					System.out.println(curr.getmyquantity() + "  " + curr.getparent().getname() + " successfully bought");
				}
			}
		}
	}
		return totalfees;
	}
	public void displayrecents() {
		for(int i=0;i<10;i++) {
			myitem k = recentorders[i];
			if(k==null) break;
			System.out.print("Bought Item ");
			System.out.print(k.getparent().getname() + " quantity: " + k.getmyquantity() + " for Rs" + k.getmoney() + " from Merchant " + k.getparent().getmerchant().getname());
			System.out.println();
		}
	}
	@Override
	public String getname() {
		return this.name;
	}
	@Override
	public String gettype() {
		return "customer";
	}


	@Override
	public void getdetails() {
		System.out.println("Name : " + this.name);
		System.out.println("ID : " + this.id);
		System.out.println("Number of orders : "  + this.purchasenumber);
	}
	
	
}


interface member{
	public void additem(String category, item e);
	public void search(int category);
	public float getreward();
	public String getname();
	public String gettype();
	public int getid();
	public void getdetails();
}

class category{
	private String name;
	private ArrayList<item> items;
	private offer offer = null;
	private int n_items;
	
	
	public category(String name) {
		this.name = name;
		this.n_items = 0;
		items = new ArrayList<item>();
	}
	public void additem(item e) {
		items.add(e);
		e.setcategory(this);
		n_items++ ;
	}
	public category getcategory() {		
		return this;
	}
	public String getname() {
		return this.name;
	}
	
	public boolean edititem(int code, float price, int quantity) {
		boolean ans = false;
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getid() == code) {
				items.get(i).setprice(price);
				items.get(i).setquantity(quantity);
				ans = true;
				items.get(i).display();
			}
		}
		return ans;
	}
	public void display() {
		for(int i=0;i<items.size();i++) {
			items.get(i).display();
		}
	}
	public boolean addoffer(int id, offer e) {
		boolean ans = false;
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getid() == id) {
				items.get(i).setoffer(e);
				ans = true;
			}
		}
		return ans;
	}
	public item getitem(int id) {
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getid() == id) return items.get(i);
			
		}
		
		return null;
	}
	public int getnitems() {
		return items.size();
	}
	
}

class myitem {
	private int myquantity;
	private item parentitem;
	private double mymoney;
	public myitem(item e, int quantity) {
		this.parentitem = e;
		this.myquantity = quantity;
	}
	public void setmyquantity(int myq) {
		this.myquantity = myq;
	}
	public int getmyquantity() {
		return this.myquantity;
	}
	public item getparent() {
		return this.parentitem;
	}
	public void setmoney(double money) {
		mymoney = money;
	}
	public double getmoney() {
		return mymoney;
	}
	
}

