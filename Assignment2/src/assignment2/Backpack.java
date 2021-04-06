package assignment2;

	

	public class Backpack {
	

	    public int numItems;
	    public int maxItems;
	    public double currWeight;
	    public double maxWeight;
	    private LinkedListNode[] table;
	    private Hashing hash;
	    public double loadFactor;
	

	    public Backpack(int size, double lf) {
	        numItems = 0;
	        maxItems = size;
	        currWeight = 0;
	        maxWeight = 90;
	        loadFactor = lf;
	        table = new LinkedListNode[maxItems];
	        hash = new Hashing(maxItems);
	    }
	

	    public void add(Weapon wp) {
	

	        Double dNumItems = Double.valueOf(numItems);
	        Double dMaxItems = Double.valueOf(maxItems);
	        if ((dNumItems / dMaxItems) < loadFactor) {
	

	            if (search(wp) != -1) {
	                table[search(wp)].addFront(wp);
	                currWeight += wp.weight;
	                numItems++;
	                return;
	            }
	

	            int start = hash.hashFunction(wp);
	            int i = 1;
	            int location = start;
	            while (table[location] != null && table[location].head.data.weaponName.compareTo(wp.weaponName) != 0) {
	                location = (start + i * i) % maxItems;
	                i++;
	            }
	            
	            table[location] = new LinkedListNode();
	            table[location].addFront(wp);
	            currWeight += wp.weight;
	            numItems++;
	        }
	    }
	

	    public int search(Weapon wp) {
	        int start = hash.hashFunction(wp);
	        int i = 1;
	        int location = start;
	        while (table[location] != null && table[location].head.data.weaponName.compareTo(wp.weaponName) != 0) {
	            location = (start + i * i) % maxItems;
	            i++;
	        }
	        if (table[location] == null) {
	            return -1;
	        }
	        return location;
	    }
	

	    public int searchByName(String weap) {
	        Weapon wp = new Weapon(weap);
	        int start = hash.hashFunction(wp);
	        int i = 1;
	        int location = start;
	        while (table[location] != null && table[location].head.data.weaponName.compareTo(wp.weaponName) != 0) {
	            location = (start + i * i) % maxItems;
	            i++;
	        }
	        if (table[location] == null) {
	            return -1;
	        }
	        return location;
	    }
	

	    public boolean weightMaxed(double inWeight) {
	        return currWeight + inWeight >= maxWeight;
	    }
	

	    public boolean itemsMaxed(int inItem) {
	        Double dNumItems = Double.valueOf(numItems + inItem);
	        Double dMaxItems = Double.valueOf(maxItems);
	        if ((dNumItems / dMaxItems) < loadFactor) {
	            return false;
	        }
	        return true;
	    }
	

	    public void printBackpack() {
	        for (int i = 0; i < maxItems; i++) {
	            if (table[i] != null) {
	                System.out.println("\nWeapon:" + table[i].head.data.weaponName + "\nRange:" + table[i].head.data.range
	                        + " Damage:" + table[i].head.data.damage + " Weight:" + table[i].head.data.weight + " Number owned:" + table[i].numItems);
	            }
	        }
	    }
	}
