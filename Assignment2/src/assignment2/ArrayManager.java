package assignment2;

public class ArrayManager {


    public int maxItems;    // max size of the table
    public int numItems;     // check number of items in the list
    private ShopItem[] table; //hash table implemented
    private Hashing hash;
    public double loadFactor;


    public ArrayManager(int size, double loadFactor) {
        maxItems = size;
        numItems = 0;
        table = new ShopItem[maxItems];
        hash = new Hashing(maxItems);
        this.loadFactor = loadFactor;
    }


    public int getNumItems() {
        return numItems;
    }


    public void addQuantity(String wp, int quantity) {
        Weapon tempWeapon = new Weapon(wp);
        table[search(tempWeapon)].numberInStock += quantity;
    }


    public void put(Weapon wp, int quantity) {
        
        Double dNumItems = Double.valueOf(numItems);
        Double dMaxItems = Double.valueOf(maxItems);
        if ((dNumItems / dMaxItems) < loadFactor) {
            int start = hash.hashFunction(wp);
            int i = 1;
            int location = start;
            while (table[location] != null && table[location].item.deleted == false) {
                location = (start + i * i) % maxItems;
                i++;
            }
            table[location] = new ShopItem(wp, quantity);
            numItems++;
        }
    }


    public int search(Weapon wp) {
        int start = hash.hashFunction(wp);
        int i = 1;
        int location = start;
        while (table[location] != null && table[location].item.weaponName.compareTo(wp.weaponName) != 0) {
            location = (start + i * i) % maxItems;
            i++;
        }
        if (table[location] == null) {
            return -1;
        }
        return location;
    }


    public ShopItem get(String weaponName) {
        Weapon tempWeapon = new Weapon(weaponName);
        if (search(tempWeapon) != -1) {
            return table[search(tempWeapon)];
        }


        return null;
    }


    public boolean delete(String weaponName) {
        Weapon tempWeapon = new Weapon(weaponName);
        if (search(tempWeapon) != -1) {
            this.table[search(tempWeapon)].item.deleted = true;
            numItems--;
            return true;
        }
        return false;
    }


    public boolean exists(String weaponName) {
        Weapon tempWeapon = new Weapon(weaponName);
        return search(tempWeapon) != -1;
    }


    public void printTable() {
        int i = 0;
        for (int x = 0; x < maxItems; x++) {
            if (table[x] != null && table[x].item.deleted == false) {
                System.out.println("Name: " + table[x].item.weaponName + "   Damage:" + table[x].item.damage + "    Cost:" + table[x].item.cost + "     Quantity in stock:" + table[x].numberInStock);
            }
        }
    }


}
