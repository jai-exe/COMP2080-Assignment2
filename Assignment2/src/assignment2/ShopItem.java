package assignment2; 

public class ShopItem {


    Weapon item;
    int numberInStock;


    public ShopItem(Weapon w, int nInStock) {
        item = w;
        numberInStock = nInStock;
    }


    @Override
    public String toString() {
        return ("Name: " + item.weaponName + "   Damage:" + item.damage + "    Cost:" + item.cost + "     Quantity in stock:" + numberInStock);
    }
}


