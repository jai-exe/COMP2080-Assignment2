package assignment2;

public class Hashing {


    private int tableSize;


    public Hashing(int ts) {
        tableSize = ts;
    }


    public int hashFunction(Weapon wp) {
        int value = 0, weight = 1;
        for (int i = 0; i < wp.weaponName.length(); i++) {
            value += (wp.weaponName.charAt(i) - 'a' + 1) * weight;
            weight++;
        }
        //value += wp.cost + wp.damage + wp.range + wp.weight; // wasnt searching properly with this
        return value % tableSize;
    }


}

