package assignment2;

public class Weapon {


    public String weaponName;
    public int range;
    public int damage;
    public double weight;
    public double cost;
    public boolean deleted; // uses boolean to mark if its deleted, this way it will delete from store but remain in backpack


    public Weapon(String n, int rang, int dam, double w, double c) {
        weaponName = n;
        damage = dam;
        range = rang;
        weight = w;
        cost = c;
        deleted = false;
    }


    public Weapon(String n) { // other constructor easier for searching
        weaponName = n;
    }

}
