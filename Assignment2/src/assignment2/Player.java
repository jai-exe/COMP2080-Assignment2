package assignment2;



	public class Player {
	

	    public String name;
	    public double money;
	    public Backpack backpack;
	

	    public Player(String n, double m, int bpSize, double lf) {
	        name = n;
	        money = m;
	        //numItems = 0;
	        backpack = new Backpack(bpSize, lf);
	    }
	

	    public void buy(Weapon w) {
	        if (backpack.itemsMaxed(1)) {
	            System.out.println("You have no more room in your backpack!");
	            return;
	        }
	

	        System.out.println(w.weaponName + " bought...");
	        backpack.add(w);
	        System.out.println("Number of items you own: " + backpack.numItems);
	    }
	

	    public void withdraw(double amt) {
	        money = money - amt;
	    }
	

	    public void printCharacter() {
	        System.out.println(" Name:" + name + "\n Money:" + money);
	        backpack.printBackpack();
	    }
	

	}
